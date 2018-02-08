$('#ru').click(function switchLang(query, parameters) {
    var xhr = new XMLHttpRequest();
    parameters = parameters.replace(/(&)(command=\w+)(&)/g,"$1$3");
    parameters = parameters.replace(/(&)(command=\w+)/g,"");
    parameters = parameters.replace(/(command=\w+)(&)/g,"");
    parameters = parameters.replace(/(command=\w+)/g,"");
    xhr.open('GET', query + parameters, true);
    xhr.send(null);
});

$('#en').click(function switchLang(query, parameters) {
    var xhr = new XMLHttpRequest();
    parameters = parameters.replace(/(&)(command=\w+)(&)/g,"$1$3");
    parameters = parameters.replace(/(&)(command=\w+)/g,"");
    parameters = parameters.replace(/(command=\w+)(&)/g,"");
    parameters = parameters.replace(/(command=\w+)/g,"");
    xhr.open('GET', query + parameters, true);
    xhr.send(null);
});