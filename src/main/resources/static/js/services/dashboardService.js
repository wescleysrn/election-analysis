(function () {
    'use strict';

	angular.module("brazilElectionsApp.dashboard", ['nvd3']);

    angular.module('brazilElectionsApp.dashboard').factory('DashboardService', DashboardService);

    DashboardService.$inject = ['$http', '$rootScope'];
    
    function DashboardService($http, $rootScope) {
    	
        var service = {};        
//        service.loadDashboard = loadDashboard;        
        service.loadCandidateDonationGraph = loadCandidateDonationGraph;        
        service.loadPartyDonationGraph = loadPartyDonationGraph;
        service.loadLargestCompanyDonors = loadLargestCompanyDonors;        
        service.loadLargestPersonDonors = loadLargestPersonDonors;        
        return service;

        /*
        function loadDashboard() {
        	console.log('SERVICE - loadDashboard');
        	return $http.get($rootScope.settings.baseURL + 'dashboard/load') 
        	.success(function (result) {
                return result;
            }).error(function (erro) {
            	handleError('Erro ao buscar usuario por login.')
            });
        } 
        */       
        
        function loadCandidateDonationGraph() {
        	console.log('SERVICE - loadCandidateDonationGraph');
        	return $http.get($rootScope.settings.baseURL + 'dashboard/candidate/donation/graph') 
        	.success(function (result) {
                return result;
            }).error(function (erro) {
            	handleError('Error - Load candidate donation graph.')
            });        	        	
        }
        
        function loadPartyDonationGraph() {
        	console.log('SERVICE - loadPartyDonationGraph');
        	return $http.get($rootScope.settings.baseURL + 'dashboard/party/donation/graph') 
        	.success(function (result) {
                return result;
            }).error(function (erro) {
            	handleError('Error - Load party donation graph.')
            });        	        	        	
        }
        
        function loadLargestCompanyDonors() {
        	console.log('SERVICE - loadLargestCompanyDonors');
        	return $http.get($rootScope.settings.baseURL + 'company/largestDonors/total/company') 
        	.success(function (result) {
                return result;
            }).error(function (erro) {
            	handleError('Error - Load largest company donors.')
            });        	
        }
        
        function loadLargestPersonDonors() {
        	console.log('SERVICE - loadLargestPersonDonors');
        	return $http.get($rootScope.settings.baseURL + 'person/largestDonors/total/person') 
        	.success(function (result) {
                return result;
            }).error(function (erro) {
            	handleError('Error - Load largest company donors.')
            });        	
        }
        
        // private functions
        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }

    }
    
})();
