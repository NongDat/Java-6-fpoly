app.controller('product-ctrl', function($scope, $http){
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};
    
    $scope.initialize = function() {
        // load products
        $http.get('/rest/products').then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                // khi tải về là string nên cần chuyển sang date của JS
                item.createDate = new Date(item.createDate);
            });
        })
        //load categories
        $http.get('/rest/categories').then(resp => {
            $scope.cates = resp.data;
        })
    }

    $scope.initialize();

    // Xoá form
    $scope.reset = function() {
        $scope.form = {
            createDate: new Date(),
            image: 'cloud-upload.jpg',
            available: true
        }
    };

    // Hiển thị lên form
    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
        $('.nav-tabs a:eq(0)').tab('show');
    };

    // Thêm sản phẩm mời
    $scope.create = function() {
        var item = angular.copy($scope.form);
        if(!validator()) {
            console.log("Err")
        } else{
            alert("OK")
        }
        // $http.post(`/rest/products`, item).then(resp => {
        //     resp.data.createDate = new Date(resp.data.createDate)
        //     $scope.items.push(resp.data);
        //     $scope.reset();
        //     alert('Thêm mới sản phẩm thành công');
        // }).catch(err => {
        //     alert("error", err);
        //     console.log(err);
        // })
    };

    // Cập nhập sản phẩm
    $scope.update = function() {
        var item = angular.copy($scope.form);
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id === item.id);
            $scope.items[index] = item;
            $('.nav-tabs a:eq(1)').tab('show');
            alert('Cập nhập sản phẩm thành công!');
        }).catch(err => {
            alert('Lỗi cập nhập sản phẩm!');
            console.log('err', err);
        })
    };

    // Xóa sản phẩm
    $scope.delete = function(item) {
        var item = angular.copy($scope.form);
        $http.delete(`/rest/products/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id === item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            $('.nav-tabs a:eq(1)').tab('show');
            alert('Xóa sản phẩm thành công!');
        }).catch(err => {
            alert('Lỗi xóa sản phẩm!');
            console.log('err', err);
        })  
    };

    // Upload hình
    $scope.imageChanged = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type':undefined}
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(err => {
            alert('Lỗi upload hình ảnh');
            console.log('err', err);
        })
    };

    $scope.pager = {
        page: 0,
        size: 10,
        get items() {
            var start = this.page*this.size;
            return $scope.items.slice(start,start + this.size);
        },
        get count() {
            return Math.ceil(1.0 *$scope.items.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if(this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if(this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }
    }
     function validator() {
        var name = document.getElementById('name');
        var nameErr = document.getElementById('nameErr');
        var price = document.getElementById('price');
        var priceErr = document.getElementById('priceErr');
        var createDate = document.getElementById('createDate');
        var createDateErr = document.getElementById('createDateErr');
        var image = document.getElementById('image');
        var imageErr = document.getElementById('imageErr');
        if(name.value.length == 0 || price.value.length == 0|| image.value.length == 0||createDate.value.length == 0) {
            
        if(name.value.length == 0) {
            name.style.border = '1px solid red';
            nameErr.innerHTML = "Không để trống tên!";
           
        }
        if(price.value.length == 0) {
            price.style.border = '1px solid red';
            priceErr.innerHTML = "Không để trống giá!";
            
        }
        if(image.value.length == 0) {
            image.style.border = '1px solid red';
            imageErr.innerHTML = "Không để trống ảnh!";
            
        }
        if(createDate.value.length == 0) {
            createDate.style.border = '1px solid red';
            createDateErr.innerHTML = "Không để trống ngày tạo!";
            
        }
            return false;
        }
        return true;
    }
    
})