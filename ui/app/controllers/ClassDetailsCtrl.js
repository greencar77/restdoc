"use strict";

mod1.controller('ClassDetailsCtrl', function ($scope, $rootScope, $stateParams) {
    $rootScope.header = 'Class ' + $stateParams.className;

    $scope.name = $stateParams.className;
    $scope.class = javaClasses[$stateParams.className];
    $scope.returning = javaClasses[$stateParams.className].returning;
    $scope.inputting = javaClasses[$stateParams.className].inputting;
    $scope.gitUrl = resolveGitSource($stateParams.className);
});
