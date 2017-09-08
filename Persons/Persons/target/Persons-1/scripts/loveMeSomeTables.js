
var url = "http://localhost:8084/Persons/api/person";
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
        alert("startUp"+json.message);
    }
    originalJson = json;

    var html = "<table><tr><th>ID</th><th>Firstname</th><th>Lastname</th> <th>Phone</th><th> </th><th> </th></tr>";
    for (var i = 0; i < json.length; i++) {
        html += "<tr><td>" + json[i].id + "</td><td>" + json[i].fName + "</td><td>" + json[i].lName + "</td><td>" + json[i].phone + "</td><td> <a href=\"#\" class=\"btndelete\" id=" + json[i].id + " onclick=\"deletes(this.id)\">delete/</a></td> <td> <a href=\"#\" class=\"btnedit\" id=" + json[i].id + " onclick=\"edit(this.id)\">edit</a></td></tr>";
    }
    html += "</table>";
    document.getElementById("tblbody").innerHTML = html;
});

function refresh() {
    location.reload();
}

var userAdd = document.getElementById("userAdd");
userAdd.onclick = function () {
    var user = {
        fName: document.getElementById("fName").value,
        lName: document.getElementById("lName").value,
        phone: document.getElementById("phone").value
    };
    var error = false;
    var data = (JSON.stringify(user));

    var url = "http://localhost:8084/Persons/api/person";
    var conf = {method: 'post',
        body: data,
        headers: new Headers({'content-type': 'application/json'})
    };
    var promise = fetch(url, conf);
    promise.then(function (response) {
        if (response.status >= 400) {
            error = true;
        }
        return response.json();
    }).then(function (json) {
        if (error) {
            alert(json.message);
        }
    });
};

function deletes(id) {
    var url = "http://localhost:8084/Persons/api/person/" + id;
    var conf = {method: 'DELETE'
    };
    var promise = fetch(url, conf);
    promise.then(function (response) {
        if (response.status >= 400) {
            error = true;
        }
        return response.json();
    }).then(function (json) {
        if (error) {
            alert(json.message);
        }
    });
    refresh();
}
function edit(id) {
    var user = {
        fName: document.getElementById("fName").value,
        lName: document.getElementById("lName").value,
        phone: document.getElementById("phone").value
    };
    var data = (JSON.stringify(user));

    var url = "http://localhost:8084/Persons/api/person/" + id;
    var conf = {method: 'PUT',
        body: data,
        headers: new Headers({'content-type': 'application/json'})
    };
    var promise = fetch(url, conf);
    promise.then(function (response) {
        if (response.status >= 400) {
            error = true;
        }
        return response.json();
    }).then(function (json) {
        if (error) {
            alert(json.message);
        }
    });
    refresh();
}