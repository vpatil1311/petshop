pet.controller('productController', function($scope, $http) {
	var basePath = '/shop/';
	
	
	$http.get(basePath + '/shop/getProducts').success(function(data) {
		alert(data);
		$scope.products = data;
	});

});