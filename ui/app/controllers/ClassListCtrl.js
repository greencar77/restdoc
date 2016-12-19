"use strict";

mod1.controller('ClassListCtrl', function ($scope, $rootScope) {
    $rootScope.header = 'Class list';

    $scope.types = Object.values(javaClasses);

    $scope.filterm = new Object();
    $scope.filterm.typeParameter = true;
    $scope.filterm.typeReturn = true;
    $scope.filterm.typeOther = true;

    $scope.filterm.originJdk = true;
    $scope.filterm.originProjectSpecific = true;
    $scope.filterm.originThirdParty = true;

    //$filter('searchx')(Object.values(javaClasses), true);


/*
    $scope.$watch('filter.parameter', function() {
    });
*/
});
