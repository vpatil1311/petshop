pet.controller('cartController', function($scope, growl, Data) {
	$scope.Data = Data;
	$scope.cart = $scope.Data.cart;
	alert('in cart JS ctrl');
	alert(JSON.stringify($scope.cart));

});