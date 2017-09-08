
var url = "http://localhost:8084/restJson/api/data";
var conf = {method: 'get'};
var promise = fetch(url, conf);
var error = false;
promise.then(function (response) {
    if (response.status >= 400) {
        error = true;
    }
    return response.json();
}).then(function (json) {
    if (error) {
        alert("startUp" + json.message);
    }
    originalJson = json;

    var html = "<table><tr><th>ID</th><th>Firstname</th><th>Lastname</th> <th>Age</th><th> </th><th> </th></tr>";
    for (var i = 0; i < json.length; i++) {
        html += "<tr><td>" + json[i].id + "</td><td>" + json[i].fname + "</td><td>" + json[i].lname + "</td><td>" + json[i].age + "</td></tr>";
    }
    html += "</table>";
    document.getElementById("tblbody").innerHTML = html;
});

var generateTable = document.getElementById("generateTable");
generateTable.onclick = function () {

    var amount = document.getElementById("amount").value;
    var startingID = document.getElementById("startingID").value;
    var error = false;

    var url = "http://localhost:8084/restJson/api/data/"+amount+"/"+startingID;
   var conf = {method: 'get'};
var promise = fetch(url, conf);
var error = false;
promise.then(function (response) {
    if (response.status >= 400) {
        error = true;
    }
    return response.json();
}).then(function (json) {
    if (error) {
        alert("startUp" + json.message);
    }
    originalJson = json;

    var html = "<table><tr><th>ID</th><th>Firstname</th><th>Lastname</th> <th>Age</th><th> </th><th> </th></tr>";
    for (var i = 0; i < json.length; i++) {
        html += "<tr><td>" + json[i].id + "</td><td>" + json[i].fname + "</td><td>" + json[i].lname + "</td><td>" + json[i].age + "</td></tr>";
    }
    html += "</table>";
    document.getElementById("tblbody").innerHTML = html;
});
};

