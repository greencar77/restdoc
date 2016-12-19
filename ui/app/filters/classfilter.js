"use strict";

mod1.filter('classfilter', function () {
    return function (items, filter) {
        var filtered = [];
        for (var i = 0; i < items.length; i++) {
            var item = items[i];
            if (item.param == filter.typeParameter || item.returnType == filter.typeReturn
                || (filter.typeOther && item.param != filter.typeParameter && item.returnType != filter.typeReturn)) {
                filtered.push(item);
            }
        }
        return filtered;
    };
});
