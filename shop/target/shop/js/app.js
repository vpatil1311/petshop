var pet = angular.module('pet',  ['ngRoute', 'datatables']);

pet.config(function($routeProvider) {
	
    $routeProvider

        .when('/', {
            templateUrl : 'pages/home.html',
            controller  : 'productController'
        })

        .when('/about', {
            templateUrl : 'pages/products.html',
            controller  : 'productController'
        })

        .when('/contact', {
            templateUrl : 'pages/contact.html',
            controller  : 'contactController'
        });
});