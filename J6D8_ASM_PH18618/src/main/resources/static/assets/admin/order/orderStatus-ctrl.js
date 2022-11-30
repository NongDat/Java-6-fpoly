
 app.controller('orderStatus-ctrl', function($scope, $http) {
    $scope.items = [];
    $scope.statuss = [];
    $scope.orderDetails = [];
      var orderIndex = 12;
    $scope.initialize = function() {
        // load Order
        $http.get('/rest/orders/list').then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate);
            });
        });
    }

    $scope.changeStatus = function(id) {
        var item = $scope.items.find(o => o.id === id);
        if(item.status == 'PENDING') {
            item.status = 'CONFIRMED';
        } else if(item.status == 'CONFIRMED') {
            item.status = 'DELIVERING';
        } else if(item.status == 'DELIVERING') {
            item.status = 'SUCCESSFUL';
        }
        $http.put(`/rest/orders/status/update/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(o => o.id === id);
            $scope.items[index] = item;
            alert('Xác nhận thành công!');
        })
    };

    $scope.delStatus = function(id) {
        var item = $scope.items.find(o => o.id === id);
            item.status = 'DELETED';
        $http.put(`/rest/orders/status/update/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(o => o.id === id);
            $scope.items[index] = item;
            alert('Huỷ thành công!');
        })
    };

    $scope.order_detail = function(id) {
        orderIndex = id;
        $http.get(`/rest/orderDetail/list/${id}`).then(resp => {
        $scope.orderDetails =  resp.data;
        });

    }

    $scope.deleteProduct = function(id) {
        var index = $scope.orderDetails.findIndex(oD => oD.id === id);
        $scope.orderDetails.splice(index, 1);
    }


    $scope.initialize();
});