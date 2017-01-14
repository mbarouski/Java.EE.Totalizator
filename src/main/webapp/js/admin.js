var idList = [];

function ban(){
    post("/main?command=ban", idList);
    return false;
}

function unban(){
    post("/main?command=unban", idList);
    return false;
}

function onCheckboxClick(checkbox){
    var index;
    if((index = idList.indexOf(checkbox.id)) == -1){
        idList.push(checkbox.id);
    } else {
        idList.splice(index, 1);
    }
}

function post(url, idList, role){
    var xhr = new XMLHttpRequest();

    var body = 'id-list=' + encodeURIComponent(idList);
    if(role){
        body += "&role=" + encodeURIComponent(role);
    }

    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function() {
        console.log("ready");
    };

    xhr.send(body);
}