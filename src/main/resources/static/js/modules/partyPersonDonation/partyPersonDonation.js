'use strict';
(function(){
		
	angular.module('brazilElectionsApp.partyPersonDonation').controller('PartyPersonDonationCtrl', PartyPersonDonationCtrl);

	PartyPersonDonationCtrl.$inject = ['PartyPersonDonationService', '$location', '$scope', '$rootScope', '$state'];
	
	function PartyPersonDonationCtrl(PartyPersonDonationService, $location, $scope, $rootScope, $state) {
		var ctrl = this;		
		
		ctrl.loadLargestDonors = loadLargestDonors;
		ctrl.carregarGrafico = carregarGrafico;
		ctrl.listLargestDonors = [];
		
		function loadLargestDonors(destination) {
        	console.log('CONTROLLER - loadLargestDonors');
        	PartyPersonDonationService.loadLargestDonors(destination)
			.then(function (response) {
				ctrl.listLargestDonors = response.data;
				console.log(response);
				
			});
		}
		
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
		
		//TABS END		
		function carregarGrafico() {
			console.log('CARREGAR GRAFICO');
			PartyPersonDonationService.carregarGrafico()
			.then(function (response) {
				console.log(response);					
				ctrl.data = response.data;				
			});			
		}
		
		//GRÁFICO
		var color = d3.scale.category20()
		ctrl.options = {
	        chart: {
	            type: 'forceDirectedGraph',
	            height: 450,
	            width: 754, //(function(){ return nv.utils.windowSize().width })(),
	            margin:{top: 20, right: 20, bottom: 20, left: 20},
	            color: function(d){
	                return color(d.group)
	            },
//	            tooltip : { 
//	                contentGenerator : function (obj) { return "<div> **custom formating** </div>"}              
//	            },	            
	            nodeExtras: function(node) {
	                node && node
	                  .append("text")
	                  .attr("dx", 8)
	                  .attr("dy", ".35em")
	                  .text(function(d) { return d.name })
	                  .style('font-size', '10px');
	            }
	        }
	    };
		
	}	
	    
})();
