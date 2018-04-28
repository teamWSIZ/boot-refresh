
//List modules which it uses
var app = angular.module('myApp', [
    'ngRoute',
    'myApp.controllers'
]);

app.config(['$routeProvider', function ($routeProvider) {
    var urlBase='partials/';

    $routeProvider.when('/', {
        templateUrl: urlBase + 'basicView.html',
        controller: 'masterCtrl'
    }).when('/view1', {
        templateUrl: urlBase + 'basicView.html',
        controller: 'masterCtrl'
    }).when('/view2', {
        templateUrl: urlBase + 'dataView.html',
        controller: 'masterCtrl'
    });
}]);

app.run(function ($rootScope) {
    console.log('Uruchamiam app.run');

    $rootScope.M = {};
    //Global properties
    $rootScope.M.URL = 'https://localhost:8444';

    // Globalnie-dostępna tablica
    $rootScope.users = [];
});

//Dyrektywa do zaczepienia
//https://stackoverflow.com/questions/17922557/angularjs-how-to-check-for-changes-in-file-input-fields
app.directive('customOnChange', function() {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var onChangeHandler = scope.$eval(attrs.customOnChange);
            element.on('change', onChangeHandler);
            element.on('$destroy', function() {
                element.off();
            });

        }
    };
});
