(function () {
    'use strict';

    angular
        .module('app')
        .factory('DataPrimeService', DataPrimeService);

    DataPrimeService.$inject = ['$timeout', '$filter', '$q', '$http'];
    function DataPrimeService($timeout, $filter, $q, $http) {

        var service = {};

        return service;
        

    }
})();