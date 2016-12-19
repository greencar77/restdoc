"use strict";

var mod1 = angular.module('mainModule', ['ui.router']);

postInit();

function postInit() {
    console.log("postInit()");
    mergeData();
    extractJavaClasses();
}

function extractJavaClasses() {
    javaClasses = new Object();

    for (var i = 0; i < ops.length; i++) {
        if (ops[i].returnTypeList != null) {
            for (var p = 0; p < ops[i].returnTypeList.length; p++) {
                extractJavaClassesReturning(ops[i].returnTypeList[p], ops[i]);
            }
        }

        if (ops[i].className != null) {
            var t = acquireClass(ops[i].className);
            t.restResource = true;
        }

        if (ops[i].params != null) {
            for (var p = 0; p < ops[i].params.length; p++) {
                extractJavaClassesParams(ops[i].params[p], ops[i]);
            }
        }
    }
}

function extractJavaClassesReturning(javaType, operation) {
    var t = acquireClass(javaType.className);
    t.returnType = true;
    if (t.returning.indexOf(operation) == -1) {
        t.returning.push(operation);
    }

    if (javaType.genericTypes != null) {
        for (var i = 0; i < javaType.genericTypes.length; i++) {
            extractJavaClassesReturning(javaType.genericTypes[i], operation);
        }
    }
}

function extractJavaClassesParams(javaType, operation) {
    var t = acquireClass(javaType.className);
    t.param = true;
    if (t.inputting.indexOf(operation) == -1) {
        t.inputting.push(operation);
    }

    if (javaType.genericTypes != null) {
        for (var i = 0; i < javaType.genericTypes.length; i++) {
            extractJavaClassesParams(javaType.genericTypes[i], operation);
        }
    }
}

function acquireClass(className) {
    var t;
    if (!(className in javaClasses)) {
        t = new Object();
        t.name = className;
        t.returning = [];
        t.inputting = [];
        javaClasses[className] = t;
    } else {
        t = javaClasses[className];
    }

    return t;
}

function mergeData() {
    for (var i = 0; i < ops.length; i++) {
        var patchOp = findOperationPatch(ops[i].path, ops[i].verb);
        ops[i].returnTypeList = new Array();
        ops[i].returnTypeList.push(ops[i].returnType);
        if (patchOp != null) {
            ops[i].returnTypeList.push(patchOp.returnType);
        }
    }
}


function findOperationPatch(path, verb) {
    for (var i = 0; i < opsPatch.length; i++) {
        if (opsPatch[i].path == path && opsPatch[i].verb == verb) {
            return opsPatch[i];
        }
    }
}
