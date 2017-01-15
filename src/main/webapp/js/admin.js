var idList = [];

function ban(){
    post("/main?command=ban", idList);
    setTimeout(function(){
        changeBanStatusOnFront(true, idList);
        idList = [];
    }, 0);
    resetCheckboxes();
    return false;
}

function unban(){
    post("/main?command=unban", idList);
    setTimeout(function(){
        changeBanStatusOnFront(false, idList);
        idList = [];
    }, 0);
    resetCheckboxes();
    return false;
}

function changeRole(){
    var role = document.getElementById("role").value;
    post("/main?command=changeRole", idList, role);
    setTimeout(function(){
        changeRoleOnFront(role, idList);
        idList = [];
    }, 0);
    resetCheckboxes();
    return false;
}

function changeRoleOnFront(role, idList){
    idList.forEach(function(id){
        document.getElementById(id).parentElement.nextElementSibling.nextElementSibling.firstElementChild.innerHTML = role;
    });
}

function changeBanStatusOnFront(status, idList){
    idList.forEach(function(id){
        document.getElementById(id).parentElement.parentElement.lastElementChild.lastElementChild.innerHTML = status;
    });
}

function resetCheckboxes(){
    var cbs = document.getElementsByName("checkbox");
    cbs.forEach(function(cb) {
        cb.checked = false;
    });
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