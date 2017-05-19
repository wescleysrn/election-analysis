// =========================================================================
// CONFIGURATION ROUTE
// =========================================================================

'use strict';
angular.module('brazilElectionsConfig', [])

    // Setup global settings
    .factory('settings', ['$rootScope', function($rootScope) {
        var baseURL = 'http://localhost:8080/election-analysis/', // Setting base url app
            settings = {
                baseURL                 : baseURL,
                pluginPath              : baseURL+'/plugins/bower_components',
                imagePath         		: baseURL+'/images',
                cssPath                 : baseURL+'/css',
                jsPath					: baseURL+'/js'
        };
        $rootScope.settings = settings;
        return settings;
    }])

    // Configuration ocLazyLoad with ui router
    .config(function($stateProvider, $locationProvider, $urlRouterProvider) {
        // Redirect any unmatched url
        $urlRouterProvider.otherwise('dashboard');

        $stateProvider

	        // =========================================================================
	        // DASHBOARD
	        // =========================================================================
	        .state('dashboard', {
	            url: '/dashboard',
	            templateUrl: 'views/dashboard.html',
	            data: {
	                pageTitle: 'DASHBOARD',
                    pageHeader: {
                        icon: 'fa fa-home',
                        title: 'Dashboard',
                        subtitle: 'dashboard & statistics'
                    }
	            },
	            controller: 'DashboardCtrl',
	            resolve: {
	                deps: ['$ocLazyLoad', 'settings', function($ocLazyLoad, settings) {
	                    var cssPath = settings.cssPath, // Create variable css path
	                        pluginPath = settings.pluginPath; // Create variable plugin path	
	                    // you can lazy load files for an existing module
	                    return $ocLazyLoad.load(
	                        [
	                            {
	                                insertBefore: '#load_css_before',
	                                files: [
	                                    pluginPath+'/horizontal-chart/build/css/horizBarChart.css',
	                                    cssPath+'/component.css',
	                                    cssPath+'/views/dashboard.css'
	                                ]
	                            },
	                            {
	                                files: [
//	                                    pluginPath+''
	                                ]
	                            },
	                            {
	                                name: 'brazilElectionsApp.dashboard',
	                                files: [
	                                    pluginPath+'/horizontal-chart/build/js/jquery.horizBarChart.min.js',
	                                    'js/services/dashboardService.js', 
	                                    'js/modules/dashboard/dashboard.js'
	                                ]
	                            }
	                        ]
	                    );
	                }]
	            }
	        })
        
	        // =========================================================================
	        // INITIAL OPTIONS PARTY
	        // =========================================================================
	        // =========================================================================
	        // PERSON DONATION
	        // =========================================================================
	        .state('partyPersonDonation', {
	            url: '/partyPersonDonation',
	            templateUrl: 'views/partyPersonDonation.html',
	            data: {
	                pageTitle: 'PHYSICAL PERSON DONATION',
//                    pageHeader: {
//                        icon: 'fa fa-home',
//                        title: 'Physical Person Donation',
//                        subtitle: 'donations made for physical person'
//                    },
                    breadcrumbs: [
                        {title: 'Party'},{title: 'Donation Received'},{title: 'Physical Person'}
                    ]
	            },
	            controller: 'PartyPersonDonationCtrl',
	            resolve: {
	                deps: ['$ocLazyLoad', 'settings', function($ocLazyLoad, settings) {
	                    var cssPath = settings.cssPath, // Create variable css path
	                        pluginPath = settings.pluginPath; // Create variable plugin path	
	                    // you can lazy load files for an existing module
	                    return $ocLazyLoad.load(
	                        [
	                            {
	                                insertBefore: '#load_css_before',
	                                files: [
	                                    cssPath+'/views/personDonation.css'
	                                ]
	                            },
	                            {
	                                files: [
//	                                    pluginPath+''
	                                ]
	                            },
	                            {
	                                name: 'brazilElectionsApp.partyPersonDonation',
	                                files: [
	                                    pluginPath+'/angular-input-masks/angular-input-masks-standalone.js',
	                                    'js/services/partyPersonDonationService.js', 
	                                    'js/modules/partyPersonDonation/partyPersonDonation.js'
	                                ]
	                            }
	                        ]
	                    );
	                }]
	            }
	        })
	        // =========================================================================
	        // COMPANY DONATION
	        // =========================================================================
	        .state('partyCompanyDonation', {
	            url: '/partyCompanyDonation',
	            templateUrl: 'views/partyCompanyDonation.html',
	            data: {
	                pageTitle: 'COMPANY DONATION',
//                    pageHeader: {
//                        icon: 'fa fa-home',
//                        title: 'Physical Person Donation',
//                        subtitle: 'donations made for physical person'
//                    },
                    breadcrumbs: [
                        {title: 'Party'},{title: 'Donation Received'},{title: 'Company'}
                    ]
	            },
	            controller: 'PartyCompanyDonationCtrl',
	            resolve: {
	                deps: ['$ocLazyLoad', 'settings', function($ocLazyLoad, settings) {
	                    var cssPath = settings.cssPath, // Create variable css path
	                        pluginPath = settings.pluginPath; // Create variable plugin path	
	                    // you can lazy load files for an existing module
	                    return $ocLazyLoad.load(
	                        [
	                            {
	                                insertBefore: '#load_css_before',
	                                files: [
	                                    cssPath+'/views/companyDonation.css'
	                                ]
	                            },
	                            {
	                                files: [
//	                                    pluginPath+''
	                                ]
	                            },
	                            {
	                                name: 'brazilElectionsApp.partyCompanyDonation',
	                                files: [
	                                    pluginPath+'/angular-input-masks/angular-input-masks-standalone.js',
	                                    'js/services/partyCompanyDonationService.js', 
	                                    'js/modules/partyCompanyDonation/partyCompanyDonation.js'
	                                ]
	                            }
	                        ]
	                    );
	                }]
	            }
	        })
	        
	        
	        // =========================================================================
	        // END OPTIONS PARTY
	        // =========================================================================
	        




	        
	        
	        
	        
	        
	        // =========================================================================
	        // INITIAL OPTIONS CANDIDATE
	        // =========================================================================
	        // =========================================================================
	        // PERSON DONATION
	        // =========================================================================
	        .state('candidatePersonDonation', {
	            url: '/candidatePersonDonation',
	            templateUrl: 'views/candidatePersonDonation.html',
	            data: {
	                pageTitle: 'PHYSICAL PERSON DONATION',
                    breadcrumbs: [
                        {title: 'Candidate'},{title: 'Donation Received'},{title: 'Physical Person'}
                    ]
	            },
	            controller: 'CandidatePersonDonationCtrl',
	            resolve: {
	                deps: ['$ocLazyLoad', 'settings', function($ocLazyLoad, settings) {
	                    var cssPath = settings.cssPath, // Create variable css path
	                        pluginPath = settings.pluginPath; // Create variable plugin path	
	                    // you can lazy load files for an existing module
	                    return $ocLazyLoad.load(
	                        [
	                            {
	                                insertBefore: '#load_css_before',
	                                files: [
	                                    cssPath+'/views/personDonation.css'
	                                ]
	                            },
	                            {
	                                files: [
//	                                    pluginPath+''
	                                ]
	                            },
	                            {
	                                name: 'brazilElectionsApp.candidatePersonDonation',
	                                files: [
	                                    pluginPath+'/angular-input-masks/angular-input-masks-standalone.js',
	                                    'js/services/candidatePersonDonationService.js', 
	                                    'js/modules/candidatePersonDonation/candidatePersonDonation.js'
	                                ]
	                            }
	                        ]
	                    );
	                }]
	            }
	        })	        

	        // =========================================================================
	        // COMPANY DONATION
	        // =========================================================================
	        .state('candidateCompanyDonation', {
	            url: '/candidateCompanyDonation',
	            templateUrl: 'views/candidateCompanyDonation.html',
	            data: {
	                pageTitle: 'COMPANY DONATION',
//                    pageHeader: {
//                        icon: 'fa fa-home',
//                        title: 'Physical Person Donation',
//                        subtitle: 'donations made for physical person'
//                    },
                    breadcrumbs: [
                        {title: 'Candidate'},{title: 'Donation Received'},{title: 'Company'}
                    ]
	            },
	            controller: 'CandidateCompanyDonationCtrl',
	            resolve: {
	                deps: ['$ocLazyLoad', 'settings', function($ocLazyLoad, settings) {
	                    var cssPath = settings.cssPath, // Create variable css path
	                        pluginPath = settings.pluginPath; // Create variable plugin path	
	                    // you can lazy load files for an existing module
	                    return $ocLazyLoad.load(
	                        [
	                            {
	                                insertBefore: '#load_css_before',
	                                files: [
	                                    cssPath+'/views/companyDonation.css'
	                                ]
	                            },
	                            {
	                                files: [
//	                                    pluginPath+''
	                                ]
	                            },
	                            {
	                                name: 'brazilElectionsApp.candidateCompanyDonation',
	                                files: [
	                                    pluginPath+'/angular-input-masks/angular-input-masks-standalone.js',
	                                    'js/services/candidateCompanyDonationService.js', 
	                                    'js/modules/candidateCompanyDonation/candidateCompanyDonation.js'
	                                ]
	                            }
	                        ]
	                    );
	                }]
	            }
	        })

	        
    })

    
    // Init app run
    .run(["$rootScope", "settings", "$state", function($rootScope, settings, $state, $location) {
        $rootScope.$state = $state; // state to be accessed from view
        $rootScope.$location = $location; // location to be accessed from view
        $rootScope.settings = settings; // global settings to be accessed from view
    }]);
