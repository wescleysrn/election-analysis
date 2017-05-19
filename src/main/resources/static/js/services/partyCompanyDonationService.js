(function () {
    'use strict';

	angular.module("brazilElectionsApp.partyCompanyDonation", ['nvd3']);

    angular.module('brazilElectionsApp.partyCompanyDonation').factory('PartyCompanyDonationService', PartyCompanyDonationService);

    PartyCompanyDonationService.$inject = ['$http', '$rootScope'];
    
    function PartyCompanyDonationService($http, $rootScope) {
    	
        var service = {};        
        service.loadLargestDonors = loadLargestDonors;        
        service.carregarGrafico = carregarGrafico;        
        return service;

        function loadLargestDonors(destination) {
        	console.log('SERVICE - loadLargestDonors');
        	return $http.get($rootScope.settings.baseURL + 'company/largestDonors/' + destination + '/company') 
        	.success(function (result) {
                return result;
            }).error(function (erro) {
            	handleError('Erro ao buscar usuario por login.')
            });
        }        
        
        function carregarGrafico() {
        	console.log('SERVICE - carregarGrafico');
        	return $http.get($rootScope.settings.baseURL + 'party/company/donation/graph') 
        	.success(function (result) {
                return result;
            }).error(function (erro) {
            	handleError('Erro ao buscar usuario por login.')
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
