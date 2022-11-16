let host = "https://poly-java-6-dd2ca-default-rtdb.firebaseio.com/";
// Khác biệt lớn nhất trong bài này
// 1- URL: EntryPoint là của mk
// 2- Khi gọi để lấy toàn bộ sinh viên về thì nó trả về cho cta 1 cái list, mà ở phía client
//  chúng ta lại nhận được là Array chứ k phải là 1 Object Vì Vậy =>
//  items để chứa dữ liệu từ server trả về thì cta để là Array
// Khi cta post thì cta nhận được đối tượng chứ k phải 1 key như fire base quy định
// let host = "http://localhost:8080/rest/";
const app = angular.module("app",[]);
app.controller("ctrl", function ($scope,$http) {
    $scope.form = {}
    $scope.items = {}
    $scope.reset = function() {
        $scope.form = {gender: true, country: 'VN'};
        $scope.key = null;
    }
    $scope.load_all = function() {
        var url = `${host}/students.json`;
        $http.get(url).then(resp =>{
            $scope.items = resp.data;
            console.log("Success", resp);
        }).catch(err =>{
            console.log("Error", err);
        });
    }
    $scope.edit = function(key) {
        var url = `${host}/students/${key}.json`;
        $http.get(url).then(resp =>{
            $scope.form = resp.data;
            $scope.key = key;
            console.log("Success", resp);
        }).catch(err =>{
            console.log("Error", err);
        })
    }
    $scope.create = function() {
        var item = angular.copy($scope.form);
        var url = `${host}/students.json`;
        $http.post(url, item).then(resp => {
            $scope.key = resp.data.name;
            $scope.items[$scope.key] = item;
            $scope.reset();
            console.log("Success", resp);
        }).catch(err => {
            console.log("Error", err);
        })
    }
    $scope.update = function() {
        var item = angular.copy($scope.form);
        var url =  `${host}/students/${$scope.key}.json`;
        $http.put(url, item).then(resp =>{
            $scope.items[$scope.key] = resp.data;
            console.log("Success", resp);
        }).catch(err =>{
            console.log("Error", err);
        })
    }  
    $scope.delete = function(key) {
        var url =  `${host}/students/${key}.json`;
        $http.delete(url).then(resp =>{
            delete $scope.items[key]; //delete trên server rồi thì trên client cũng cần xóa
            $scope.reset();
            console.log("Success", resp);
        }).catch(err =>{
            console.log("Error", err);
        })
    }



    $scope.load_all();
    $scope.reset();


});