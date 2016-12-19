"use strict";

mod1.directive('typeTree', function () {
    return {
        templateUrl: 'views/dir/typeTree.html',
        scope: {
            type: '='
        }
    };
});