var app = angular.module('myApp', []);

app.controller('knowledgeController', function ($scope, $http) {

    $scope.hideForm = true;

    $scope.show = function () {
        $scope.hideForm = false;
    };

    $scope.cancel = function () {
        $scope.hideForm = true;
    };

    $scope.findAll = function () {
        $http.get("/user/knowledge/rest/all").then(function (value) {
            $scope.knowledgeList = value.data;
        });
    };

    $scope.save = function (title, description) {
        if (title == null || description == null) {
            return;
        }
        $http.post('/user/knowledge/rest/' + title + '/' + description)
            .then(function () {
                $scope.findAll();
                $scope.hideForm = true;
                $scope.title = '';
                $scope.description = '';
            })
    };

    $scope.delete = function (index) {
        $http.delete('/user/knowledge/rest/' + index).then(function () {
            $scope.findAll();
        })
    };

});
