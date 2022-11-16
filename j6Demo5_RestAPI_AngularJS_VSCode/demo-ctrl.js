// let host = "https://poly-java-6-dd2ca-default-rtdb.firebaseio.com/";
// Khác biệt lớn nhất trong bài này
// 1- URL: EntryPoint là của mk
// 2- Khi gọi để lấy toàn bộ sinh viên về thì nó trả về cho cta 1 cái list, mà ở phía client
//  chúng ta lại nhận được là Array chứ k phải là 1 Object Vì Vậy =>
//  items để chứa dữ liệu từ server trả về thì cta để là Array
// Khi cta post thì cta nhận được đối tượng chứ k phải 1 key như fire base quy định
let host = "http://localhost:8080/rest";
const app = angular.module("app",[]);
app.controller("ctrl", function ($scope,$http) {
    $scope.form = {}
    $scope.items = []
    $scope.reset = function() {
        $scope.form = {gender: true, country: 'VN'};
        $scope.key = null;
    }
    $scope.load_all = function() {
        var url = `${host}/students`;
        $http.get(url).then(resp =>{
            $scope.items = resp.data;
            console.log("Success", resp);
        }).catch(err =>{
            console.log("Error", err);
        });
    }
    $scope.edit = function(email) {
        var url = `${host}/students/${email}`;
        $http.get(url).then(resp =>{
            $scope.form = resp.data;
            console.log("Success", resp);
        }).catch(err =>{
            console.log("Error", err);
        })
    }
    $scope.create = function() {
        var item = angular.copy($scope.form);
        var url = `${host}/students`;
        $http.post(url, item).then(resp => {
            $scope.items.push(item);
            $scope.reset();
            console.log("Success", resp);
        }).catch(err => {
            console.log("Error", err);
        })
    }
    $scope.update = function() {
        var item = angular.copy($scope.form);
        var url =  `${host}/students/${$scope.form.email}`;
        $http.put(url, item).then(resp =>{
//          Tìm vị trí phần tử lúc ấn edit 
            var index = $scope.items.findIndex(item => item.email === $scope.form.email);
//          Cập nhập lại sv theo đúng vị trí update    
            $scope.items[index] = resp.data;
            console.log("Success", resp);
        }).catch(err =>{
            console.log("Error", err);
        })
    }  
    $scope.delete = function(email) {
        var url =  `${host}/students/${email}`;
        $http.delete(url).then(resp =>{
//          Tìm vị trí phần tử cần xóa 
            var index = $scope.items.findIndex(item => item.email === $scope.form.email);
//          delete trên server rồi thì trên client cũng cần xóa
//          tại vị trí index và xóa 1 phần tử
            $scope.items.splice(index, 1);
            $scope.reset();
            console.log("Success", resp);
        }).catch(err =>{
            console.log("Error", err);
        })
    }



    $scope.load_all();
    $scope.reset();


});