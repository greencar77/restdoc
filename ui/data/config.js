//end with slash
var sourcePrefix = 'src/main/java/';
var gitSource = 'https://github.com/greencar77/restdoc/tree/master/';

var javaClasses; //Java classes


function resolveGitSource(className) {
    if (className.startsWith('com.rabarbers.restdoc.mockproj')) {
        return gitSource + 'restdocmock/' + sourcePrefix + className.replace(/\./g, '/') + '.java';
    } else if (className.startsWith('uuu')) {
        return gitSource + 'x/' + sourcePrefix + className.replace(/\./g, '/') + '.java';
    } else {
        return gitSource + 'x/' + sourcePrefix + className.replace(/\./g, '/') + '.java';
    }
}
