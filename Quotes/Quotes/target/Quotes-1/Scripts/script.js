document.onload = nextQuote();

var newJoke = document.getElementById("newJoke");
newJoke.onclick = function () {
    var joke = document.getElementById("joke");
    var url = "http://localhost:8084/Quotes/api/quote/random";
    var conf = {method: 'get'};
    var error = false;
    var promise = fetch(url, conf);
    promise.then(function (response) {
        if (response.status >= 400) {
            error = true;
        }
        return response.text();
    }).then(function (text) {
        console.log(text);
        var newJoke = JSON.parse(text);
        if(error){
            alert(newJoke.message);
        }
        joke.innerHTML = newJoke.quote;
    });
};

function nextQuote() {
    var joke = document.getElementById("joke");
    var url = "http://localhost:8084/Quotes/api/quote/random";
    var conf = {method: 'get'};
    var promise = fetch(url, conf);
    var error = false;
    promise.then(function (response) {
        if (response.status >= 400) {
            error = true;
        }
        return response.text();
    }).then(function (text) {
        console.log(text);
        var newJoke = JSON.parse(text);
        if(error){
            alert(newJoke.message);
        }
        joke.innerHTML = newJoke.quote;
    });
}
;

function DELETE() {
    event.preventDefault();
    var url = "http://localhost:8084/Quotes/api/quote/" + document.getElementById("id").value;
    var conf = {method: 'delete'};
    var promise = fetch(url, conf);
    var error = false;
    promise.then(function (response) {
        if (response.status >= 400) {
            error = true;
        }
        return response.text();
    }).then(function (text) {
        console.log(text);
        if(error){
            alert(JSON.parse(text).message);
        }
    });
}
;

function edit() {
    event.preventDefault();
    var quote = {
        id: document.getElementById("id").value,
        quote: document.getElementById("quote").value
    };
    var error = false;
    var data = (JSON.stringify(quote));
    var url = "http://localhost:8084/Quotes/api/quote/" + document.getElementById("id").value;
    var conf = {method: 'put',
        body: data};
    var promise = fetch(url, conf);
    promise.then(function (response) {
        if (response.status >= 400) {
            error = true;
        }
        return response.text();
    }).then(function (text) {
        console.log(text);
        if(error){
            alert(JSON.parse(text).message);
        }
    });
}
;

function create() {
    event.preventDefault();
    var quote = {
        quote: document.getElementById("quote").value
    };
    var error = false;
    var data = (JSON.stringify(quote));
    var url = "http://localhost:8084/Quotes/api/quote/";
    var conf = {method: 'post',
        body: data};
    var promise = fetch(url, conf);
    promise.then(function (response) {
        if (response.status >= 400) {
            error = true;
        }
        return response.text();
    }).then(function (text) {
        console.log(text);
        if(error){
            alert(JSON.parse(text).message);
        }
    });
}
;
