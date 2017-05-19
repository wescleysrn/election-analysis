'use strict';
(function(){
	
	angular.module('brazilElectionsApp.dashboard').controller('DashboardCtrl', DashboardCtrl);

	DashboardCtrl.$inject = ['DashboardService', '$location', '$scope', '$rootScope', '$state'];
	
	function DashboardCtrl(DashboardService, $location, $scope, $rootScope, $state) {

		var ctrl = this;		
		
		ctrl.loadDashboard = loadDashboard;
		ctrl.loadCandidateDonationGraph = loadCandidateDonationGraph;
		ctrl.loadPartyDonationGraph = loadPartyDonationGraph;

		ctrl.listLargestCompanyDonors = [];
		ctrl.listLargestPersonDonors = [];

		
		//TABS INIT
		ctrl.tab = 1;
		
		ctrl.setTab = setTab;
		ctrl.isSet = isSet;
		
		function setTab(newTab) {
			ctrl.tab = newTab;
		}
		
		function isSet(tabNum) {
			return ctrl.tab === tabNum;
		}
		
		function loadDashboard() {
        	console.log('CONTROLLER - loadDashboard');
        	DashboardService.loadLargestCompanyDonors()
			.then(function (response) {
				
				console.log('loadLargestCompanyDonors ' + JSON.stringify(response));
				ctrl.listLargestCompanyDonors = response.data;
				
			});
        	
        	DashboardService.loadLargestPersonDonors()
			.then(function (response) {
				
				console.log('loadLargestPersonDonors ' + JSON.stringify(response));
				ctrl.listLargestPersonDonors = response.data;
				
			});
        	
		}
		
		function loadCandidateDonationGraph(){
        	console.log('CONTROLLER - loadCandidateDonationGraph');
        	
        	DashboardService.loadCandidateDonationGraph()
			.then(function (response) {
				
				console.log('loadCandidateDonationGraph ' + JSON.stringify(response));
				
				ctrl.dataDonationsCandidate = response.data;				
				
			});
        	
        	ctrl.optionsDonationsCandidate = {
                    chart: {
                        type: 'multiBarHorizontalChart',
                        height: 450,
                        x: function(d){return d.label;},
                        y: function(d){return d.value;},
                        showControls: false,
                        showValues: true,
                        duration: 500,
                        xAxis: {
                            showMaxMin: false
                        },
                        yAxis: {
                            axisLabel: 'Donation Values',
                            tickFormat: function(d){
                                return d3.format(',.2f')(d);
                            }
                        }
                    }
                };
        	
		}
		
		function loadPartyDonationGraph(){
        	console.log('CONTROLLER - loadPartyDonationGraph');
        	
        	DashboardService.loadPartyDonationGraph()
			.then(function (response) {
				
				console.log('loadPartyDonationGraph ' + JSON.stringify(response));
				ctrl.dataDonationsParty = response.data;
				
			});

        	ctrl.optionsDonationsParty = {
    	            chart: {
    	                type: 'pieChart',
    	                height: 500,
    	                x: function(d){return d.key;},
    	                y: function(d){return d.y;},
    	                showLabels: true,
    	                duration: 500,
    	                labelThreshold: 0.01,
    	                labelSunbeamLayout: true,
    	                legend: {
    	                    margin: {
    	                        top: 5,
    	                        right: 35,
    	                        bottom: 5,
    	                        left: 0
    	                    }
    	                }
    	            }
    		    };
		}				
		
		
	}	
	    
})();
