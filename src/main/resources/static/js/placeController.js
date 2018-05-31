var app = angular.module('myApp', []);

app.controller('placeController', function ($scope, $http) {

    $scope.hideForm = true;

    $scope.show = function () {
        $scope.hideForm = false;
    };

    $scope.cancel = function () {
        $scope.hideForm = true;
        $scope.title = '';
    };

    $scope.findAll = function () {
        $http.get("/user/place/rest/all").then(function (value) {
            $scope.userList = value.data;
        });
    };

    $scope.save = function (title) {
        if (title == null) {
            return;
        }
        $http.post('/user/place/rest/' + title)
            .then(function () {
                $scope.findAll();
                $scope.hideForm = true;
                $scope.title = '';
            })
    };

    $scope.delete = function (index) {
        $http.delete('/user/place/rest/' + index).then(function () {
            $scope.findAll();
        })
    };
});
