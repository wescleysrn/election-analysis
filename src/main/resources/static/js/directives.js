// =========================================================================
// DIRECTIVE APP
// =========================================================================
'use strict';
(function(){
angular.module('brazilElectionsDirective', [])

	//---------------------------------------------------------------
	// DIRECTIVE SIDEBAR LEFT
	//---------------------------------------------------------------
	// Add class active on current MENU selected
	.directive('activeMenu', function ($location) {
	    return {
	        link: function postLink(scope, element, attrs) {
	            scope.$on("$stateChangeSuccess", function (event, current, previous) {
	                if(attrs.href!=undefined){// this directive is called twice for some reason
	                    // this var grabs the tab-level off the attribute, or defaults to 1
	                    var pathLevel = attrs.activeTab || 1,
	                    // this var finds what the path is at the level specified
	                        pathToCheck = $location.path().split('/')[pathLevel],
	                    // this var finds grabs the same level of the href attribute
	                        tabLink = attrs.href.split('/')[pathLevel];
	                    // now compare the two:
	                    if (pathToCheck === tabLink) {
	                        if(element.closest('.submenu').length){
	                            element.closest('.submenu').addClass('active');
	                            element.closest('.submenu').parents('.submenu').addClass('active');
	                            element.append('<span class="selected"></span>'); // add selected mark
	                        }
	                        element.parent().addClass("active"); // parent to get the <li>
	                        element.append('<span class="selected"></span>'); // add selected mark
	                    }
	                    else {
	                        element.parent().removeClass("active");
	                        element.find('.selected').remove(); // remove element contain selected mark
	                    }
	                }
	            });
	        }
	    };
	})
	
	// Trigger dropdown sidebar menu
	.directive('collapseMenu', ['settings', function(settings){
	    return {
	        restrict: 'A',
	        link: function (scope, element, attrs) {
	            /* Create trigger click for open menu sidebar */
	            element.find('a').on('click', function() {
	
	                var parentElementMenu = $(this).parent('li'),
	                    parentElementSubmenu = $(this).parent('.submenu'),
	                    nextElement = $(this).nextAll(),
	                    arrowIcon = $(this).find('.arrow'),
	                    plusIcon = $(this).find('.plus');
		
	                parentElementMenu.siblings().removeClass('active');
	
	                if(parentElementSubmenu.parent('ul').find('ul:visible')){
	                    parentElementSubmenu.parent('ul').find('ul:visible').slideUp('fast');
	                    parentElementSubmenu.parent('ul').find('.open').removeClass('open');
	                    parentElementSubmenu.siblings().children('a').find('.selected').remove();
	                    parentElementMenu.siblings().children('a').find('.selected').remove();
	                }
	
	                if(nextElement.is('ul:visible')) {
	                    arrowIcon.removeClass('open');
	                    plusIcon.removeClass('open');
	                    nextElement.slideUp('fast');
	                }
	
	                if(!nextElement.is('ul:visible')) {
	                    nextElement.slideDown('fast');
	                    parentElementMenu.children('a').append('<span class="selected"></span>'); // add selected mark
	                    parentElementSubmenu.addClass('active');
	                    parentElementSubmenu.children('a').append('<span class="selected"></span>');
	                    arrowIcon.addClass('open');
	                    plusIcon.addClass('open');
	                }
	
	            });
	
	        }
	    }
	}])
		

})();
