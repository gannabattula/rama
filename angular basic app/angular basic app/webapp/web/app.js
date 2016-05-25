(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config( $routeProvider, $locationProvider ) {
        // $routeProvider
        //     .when('/', {
        //         controller: 'DataPrimeController',
        //         templateUrl: 'index.html',
        //         controllerAs: 'vm'
        //     })  

            

        //     .otherwise({ redirectTo: '/login' });

            // $locationProvider.html5Mode(true);
            // $locationProvider.hashPrefix('!');
            
    }

    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
    function run($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        
    }

})();