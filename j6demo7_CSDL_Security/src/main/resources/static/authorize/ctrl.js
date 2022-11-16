var app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http) {
    $http.get("/rest/authorities").then(resp => {
        $scope.db = resp.data;
        console.log(resp.data);
    })

    $scope.index_of = function (username, role) {
        return $scope.db.authorities
            .findIndex(a => a.account.username == username && role.id == role);
    }

    // $scope.update =
})