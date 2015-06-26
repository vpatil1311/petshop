var pet = angular.module('pet',  ['ngRoute', 'datatables', 'angular-growl', 'xeditable', 'ngStorage']);

pet.config(function($routeProvider) {
	
    $routeProvider
        .when('/', {
            templateUrl : 'pages/home.html',
            controller  : 'productController'
        })
        .when('/cart', {
            templateUrl : 'pages/mycart.html',
            controller  : 'productController'
        })
        .when('/contact', {
            templateUrl : 'pages/contact.html',
            controller  : 'contactController'
        });
});
