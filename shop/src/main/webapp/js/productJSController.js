pet.controller('productController', function($scope, $http, DTOptionsBuilder, DTColumnDefBuilder, $filter, growl, $localStorage) {
	var basePath = '/shop';
	$scope.selectedCat = {};
	$localStorage.cart = [];
	$scope.cart = []; 
	
	var aTable = this;
	aTable.dtOptions = DTOptionsBuilder.newOptions().withPaginationType('full_numbers').withDisplayLength(5);
	
	$http.get(basePath + '/shop/getCategories').success(function(data) {
		$scope.categories = data;
	});
	
	$http.get(basePath + '/shop/getProducts/0').success(function(data) {
		$scope.products = data;
	});
	
	$scope.getCatProducts = function() {
		alert($scope.selectedCat.categoryId);
		$http.get(basePath + '/shop/getProducts/'+$scope.selectedCat.categoryId).success(function(data) {
			$scope.products = data;
		});
	}
	
	$scope.addToCart = function(id) {
		var currentProduct = $filter('filter')($scope.products, {productId: id}, true);
		$scope.cart.push(currentProduct);
		alert('cart items'+ $scope.cart.length);
		
		/*$http.post(basePath + '/shop/addToCart', {product: currentProduct}).
		  success(function(data, status, headers, config) {
			  alert('success');
		    // this callback will be called asynchronously
		    // when the response is available
		  }).
		  error(function(data, status, headers, config) {
			  alert('error');
		    // called asynchronously if an error occurs
		    // or server returns response with an error status.
		  });*/
		growl.addSuccessMessage("Product added to cart.", {ttl: 2000});
	};

});