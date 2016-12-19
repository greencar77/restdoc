"use strict";

mod1.controller('OperationDetailsCtrl', function ($scope, $stateParams) {
    $scope.operation = findOperation($stateParams.path, $stateParams.verb);

    $scope.path = $stateParams.path;
    $scope.verb = $stateParams.verb;
    $scope.gitUrl = resolveGitSource($scope.operation.className);
});

function findOperation(path, verb) {
    for (var i = 0; i<ops.length; i++) {
        if (ops[i].path == path && ops[i].verb == verb) {
            return ops[i];
        }
    }
}
