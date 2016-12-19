"use strict";

mod1.config(function($stateProvider) {
    $stateProvider
        .state('a', {
            url: '/',
            templateUrl: 'views/operationList.html'
        })
        .state('x', {
            url: '/operation/-{path:any}/{verb}',
            templateUrl: 'views/operationDetails.html'
        })
        .state('classList', {
            url: '/classes',
            templateUrl: 'views/classList.html'
        })
        .state('classdetails', {
            url: '/class/:className',
            templateUrl: 'views/classDetails.html'
        })
    ;
});
