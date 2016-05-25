
(function () {
    'use strict';

    angular
        .module('app')
        .controller('DataPrimeController', DataPrimeController);

    DataPrimeController.$inject = ['DataPrimeService', '$rootScope','$location', '$filter','$window', 'FlashService','$route'];
    function DataPrimeController ( DataPrimeService, $rootScope, $location, $filter, $window, FlashService, $route ) {
        var vm = this;
        vm.user = null;
        vm.allUsers = [];
        vm.LoadDataSet = LoadDataSet;
        vm.DataSetsList = DataSetsList;
        vm.GetDetailById = GetDetailById;

        vm.GetDataListPage = GetDataListPage;
        vm.SwitchDataSet = SwitchDataSet;
        vm.CreateAnnotation = CreateAnnotation;
        vm.SaveAndNext = SaveAndNext;

        vm.dslist = sessionStorage.getItem('dslist');
        vm.id = sessionStorage.getItem('id');
       
        vm.swId = sessionStorage.getItem('switchId');
        vm.startindex = sessionStorage.getItem('startindex');     
        vm.currentuser = sessionStorage.getItem('currentuser');
       
        vm.toatlrecords = sessionStorage.getItem('toatlrecords');

        if(vm.swId === null){
            sessionStorage.setItem('switchId', 1);
            vm.swId = sessionStorage.getItem('switchId');
        }

        initController( vm.swId );

        vm.setlable = 'Data Sets';
        if(vm.swId == 1){
            vm.setlable = 'Data Storage';

        }else if(vm.swId == 2){
            vm.setlable = 'Data Sets';
        }

        function GetDataListPage ( dsname ) {

            $location.path('/datasetlist');
            sessionStorage.removeItem('dslist');
            sessionStorage.setItem('dslist', dsname);
        }

        function initController () {
            if($location.$$url === '/'){

                LoadDataSet(vm.swId);
                // sessionStorage.removeItem('switchId');
                // sessionStorage.setItem('switchId', 2);
                vm.imageName = '';
            }

            $(".button-collapse").sideNav();

            if($location.$$url === '/datasetlist'){
                vm.DataSetsList( sessionStorage.getItem('dslist'), 20, vm.startindex);
                
            }
            
            if( $location.$$url === '/datasetsingle' ){
                vm.GetDetailById( sessionStorage.getItem('dslist'), sessionStorage.getItem('id') );
            }

            if(vm.swId == 1){
                vm.setlable = 'Data Storage';

            }else{
                vm.setlable = 'Data Sets';
            }
        }

        
        function LoadDataSet (switchId) {
            vm.dataLoading = true;
            $('html').addClass('blur');
            DataPrimeService.GetDataSets(switchId)
            .then(function (dataset) {
                vm.dataLoading = false;
                $('html').removeClass('blur');
                // console.log(dataset);
                vm.dataset = dataset.data.data;
                sessionStorage.removeItem('dslist');
                sessionStorage.removeItem('startindex');
                // sessionStorage.setItem('startindex', 1);
                vm.dslist = '';
                vm.toatlrecords = '';
            });
        }


        function DataSetsList ( dsname, limit, start ) {
            vm.dataLoading = true;
            $('html').addClass('blur');
            if( dsname ) {
                sessionStorage.removeItem('dslist');
                sessionStorage.setItem('dslist', dsname);

            }
            // return;
            if( vm.expectedIndex > vm.numberofpages ){
                alert( 'search between 1 to '+ vm.numberofpages );
                start = sessionStorage.getItem('startindex');
            }
            vm.start = ( start ) ? start : 1;
            vm.limit = ( limit ) ? limit : 20;
            
            DataPrimeService.GetDataSetsList( sessionStorage.getItem('dslist'), vm.swId, vm.limit, vm.start )
            .then(function (list) {
                vm.dataLoading = false;
                $('html').removeClass('blur');
                vm.datasetlist = list.data.data;                                
                vm.dsidlist = list.data;
                vm.total = list.data.totalCount;

                sessionStorage.setItem('toatlrecords', vm.total);

                sessionStorage.removeItem('startindex');
                sessionStorage.setItem('startindex', vm.start);

                vm.numberofpages = Math.ceil( vm.total/vm.limit );
                vm.half_of_tatal = Math.round( vm.numberofpages/2 );
                // console.log(list.data);
                vm.currentPage = Math.round( vm.start/vm.limit ) +1;
                vm.active = ( vm.currentPage  );
                vm.next = parseInt( vm.start + vm.limit );
                vm.pre = parseInt( vm.start - vm.limit );
                vm.offPrePagination = false;
                vm.offNextPagination = false;
                if(  ( vm.next + vm.limit )  > vm.total ) {
                    vm.offNextPagination = true;
                }
                if( vm.pre < 1 ) {
                    vm.offPrePagination = true;
                }
                if ( vm.start != 1 && !parseInt(vm.start/vm.limit) ) {
                
                    vm.start = parseInt(vm.start)+1;
                    console.log(vm.numberofpages);
                }
                vm.expectedIndex = '';
                vm.toatlrecords = '';     
                vm.imageName = '';           
            });
        }
        
        function GetDetailById ( dsname, id ) {
            vm.dataLoading = true;
            vm.datasetdetail = '';
            $('html').addClass('blur');
            if( dsname && id ) {
                sessionStorage.removeItem('dslist');
                sessionStorage.setItem('dslist', dsname);
                sessionStorage.removeItem('id');
                sessionStorage.setItem('id', id);
              }
            
            DataPrimeService.GetDetailById( vm.swId, dsname, id )
            .then( function(response) {
                vm.dataLoading = false;
                $('html').removeClass('blur');   
        
                vm.datasetdetail = response.data;
                if( vm.datasetdetail.status === 'Success' ){

                    sessionStorage.removeItem( 'imageName');
                    sessionStorage.setItem( 'imageName', vm.datasetdetail.name);
                    vm.imageName = sessionStorage.getItem('imageName');
                    
                    sessionStorage.removeItem( 'nextId');
                    sessionStorage.setItem( 'nextId', vm.datasetdetail.nextId); 

                    sessionStorage.removeItem( 'previousId' );
                    sessionStorage.setItem( 'previousId', vm.datasetdetail.previousId);

                    vm.nextId = sessionStorage.getItem('nextId');
                    vm.previous = sessionStorage.getItem('previousId');

                    $location.path( '/datasetsingle' );
                    // $location.path('/datasetsingle').search('');
                     // $route.reload();
                    
                }else{ 

                    $('#displaymessage').html('<h4>No Records Found</h4>');
                    // FlashService.Error(vm.datasetdetail.msg); 
                    $('.annotation').hide();  
                }
            });
        }

        function SwitchDataSet( switchId ) {
            vm.dataLoading = true;
            $('html').addClass('blur');
            if( switchId ) {
                sessionStorage.removeItem('switchId');
                sessionStorage.setItem('switchId', switchId);
                vm.swId = sessionStorage.getItem('switchId');
               if(vm.swId == 1){
                    vm.setlable = 'Data Storage';

                }else{
                    vm.setlable = 'Data Sets';
                }
                $location.path('/');
            }

            DataPrimeService.GetDataSets( switchId )
            .then( function ( dataset ) {
                vm.dataLoading = false;
                $('html').removeClass('blur');
                vm.dataset = dataset.data.data;
                sessionStorage.removeItem( 'dslist' );
                vm.dslist = '';
            });
        }


        function CreateAnnotation(){
           vm.dataLoading = true;
            $('html').addClass('blur');
            vm.payload = {'id':vm.datasetdetail.id,'name':vm.datasetdetail.name,'location':vm.datasetdetail.location,'dname':sessionStorage.getItem('dslist'),
            'size':vm.datasetdetail.size,'targetDir': vm.retinopathy.conclusion, 'nextId': vm.datasetdetail.nextId, 'previousId': vm.datasetdetail.previousId, 'uname': sessionStorage.getItem('currentuser') };
            // console.log(vm.payload);
             // return;
            DataPrimeService.CreateDataSet(vm.payload, sessionStorage.getItem('switchId'))
                .then(function (response) {
                    vm.dataLoading = false;  
                    $('html').removeClass('blur');     
                            
                     if (response.data.status === 'Success') {
                            FlashService.Success(response.message, false);
                            $location.path('/datasetlist');
                            } else {
                                // FlashService.Error(response.data.msg);
                                alert(response.data.msg);
                                $route.reload();
                            }
                        $route.reload();
                })
           
        }

        function SaveAndNext( id ){
            vm.dataLoading = true;
            $('html').addClass('blur');
            vm.payload = {'id':vm.datasetdetail.id,'name':vm.datasetdetail.name,'location':vm.datasetdetail.location,'dname':sessionStorage.getItem('dslist'),
            'size':vm.datasetdetail.size,'targetDir': vm.retinopathy.conclusion, 'nextId': vm.datasetdetail.nextId, 'previousId': vm.datasetdetail.previousId, 'uname': sessionStorage.getItem('currentuser') };
            console.log(vm.payload);
             // return;
            DataPrimeService.CreateDataSet(vm.payload, sessionStorage.getItem('switchId'))
                .then(function (response) {
                    vm.dataLoading = false;  
                    $('html').removeClass('blur');     
                            
                     if (response.data.status === 'Success') {
                            FlashService.Success(response.message, false);
                            } else {
                                // FlashService.Error(response.data.msg);
                                alert(response.data.msg);
                                $location.path('/datasetlist');
                            }
                        $route.reload();
                })
                console.log(id);
                vm.GetDetailById(sessionStorage.getItem('dslist'), id);
        }

        $('.menu').click(function(){
            $('.menu').removeClass('highlite');
            $(this).addClass('highlite');
        });

        setTimeout(function(){
            if (vm.previous == 0){
                console.log(vm.previous);
                $('#save_previous').attr('disabled', true);
            }else if (vm.nextId == 0) {
                $('#save_next').attr('disabled', true);
            }
        }, 1000);

        $('.currentuser').html('Welcome, ' + vm.currentuser);
    }

})();