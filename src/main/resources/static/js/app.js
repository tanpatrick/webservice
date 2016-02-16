var app = angular.module('webServiceApp', ['ngRoute']);

app.config(function ($routeProvider, $httpProvider, $provide) {
    $routeProvider.when('/', {
        templateUrl: '/ui/home',
        controller: 'companyCtrl'
    }).when('/company', {
        templateUrl: '/ui/company',
        controller: 'companyCtrl'
    }).when('/company/:id', {
        templateUrl: '/ui/company',
        controller: 'companyCtrl'
    }).when('/owner', {
        templateUrl: '/ui/owner',
        controller: 'ownerCtrl'
    }).when('/owner?companyUri=:uri', {
        templateUrl: '/ui/owner',
        controller: 'ownerCtrl'
    });

    $provide.factory('httpInterceptor', function ($q, $rootScope) {
        return {
            'responseError': function (response) {
                /**
                 * Parse the validation errors so that errors can be easily 
                 * mapped it to the web page.
                 */
                var temp = {};

                for (var index in response.data) {
                    var error = response.data[index];
                    temp[error.field] = error.message;
                }

                response.data = temp;

                $rootScope.$broadcast('httpRequestError', response);

                return $q.reject(response);
            }
        };
    });

    $httpProvider.interceptors.push('httpInterceptor');
});

app.controller('companyCtrl', function ($scope, $http, $routeParams) {
    if ($routeParams.id) {
        $http.get('/company/' + $routeParams.id)
                .then(function (response) {
                    $scope.company = response.data;
                });
    }

    $scope.create = function () {
        var method = 'POST';
        var url = '/company';

        if ($scope.company && $scope.company.id) {
            method = 'PATCH';
            url += '/' + $scope.company.id;
        } else if(!$scope.company) {
            $scope.company = {};
        }

        $http({
            method: method,
            url: url,
            data: JSON.stringify($scope.company),
            headers: {'Content-Type': 'application/json'}
        }).success(function (data) {
            console.log(data);
            alert('Record Saved!');
        }).error(function (data) {
            $scope.errors = data;
        });
    };
    $scope.getCompanies = function () {
        $http.get('/company')
                .then(function (response) {
                    $scope.companies = response.data._embedded.company;
                });
    };
    $scope.getOwners = function (companyId) {
        var data = this;
        data.owners = [];

        $http.get('/company/' + companyId + '/owners')
                .then(function (response) {
                    data.owners = response.data._embedded.owner;
                });

        return data;
    };
});

app.controller('ownerCtrl', function ($scope, $http, $routeParams) {
    $scope.create = function () {
        var data = {
            name: $scope.owner.name,
            company: $routeParams.companyUri
        };

        $http({
            method: 'POST',
            url: '/owner',
            data: JSON.stringify(data),
            headers: {'Content-Type': 'application/json'}
        }).success(function (data) {
            console.log(data);
            alert('Record Saved!');
        }).error(function (data) {
            $scope.errors = data;
        });
    };
});