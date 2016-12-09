var memberSelectCount = 1;

function setLeaguesSelect(){
    var category = document.getElementById("category-select").value;
    var select = document.getElementById("league-select");
    if(category != 0) {
        var leagues = getLeagues(category);
        addOptionsToSelect(select, leagues, "Выберите лигу");
    } else{
        setDefaultSelect(select, "Выберите лигу");
    }
}

function setMembersSelect(){
    var league = document.getElementById("league-select").value;
    var selects = document.querySelectorAll("#member-select");
    if(league != 0) {
        var members = getMembers(league);
        for (var i = 0; i < selects.length; i++) {
            var select = selects[i];
            addOptionsToSelect(select, members, "Выберите участника");
        }
    }
    else{
        for (var i = 0; i < selects.length; i++) {
            var select = selects[i];
            setDefaultSelect(select, "Выберите участника");
        }
    }
}

function addMemberSelect(){
    memberSelectCount++;
    var selectDiv = document.getElementsByClassName("member-div")[0];
    var select = document.createElement("select");
    select.required = true;
    select.setAttribute("id", "member-select");
    select.setAttribute("name", "member-select-" + memberSelectCount);

    var league = document.getElementById("league-select").value;
    var members = getMembers(league);
    addOptionsToSelect(select, members, "Выберите участника");

    selectDiv.appendChild(select);
}

function getMembers(league) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/main?command=getMembersByLeagueJson&leagueId=' + league, false);
    xhr.send();
    if(xhr.status != 200){
        return [];
    } else {
        return JSON.parse(xhr.responseText);
    }
}

function getLeagues(category){
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/main?command=getLeaguesByCategoryJson&categoryId=' + category, false);
    xhr.send();
    if (xhr.status != 200) {
        return [];
    } else {
        return JSON.parse(xhr.responseText);
    }
}

function setDefaultSelect(select, defaultOptionText) {
    var opts = select.options;
    if(opts) {
        var length = opts.length;
        for (i = length - 1; i >= 0; i--) {
            select.remove(i)
        }
    }
    var opt = document.createElement("option");
    opt.value = 0;
    opt.innerHTML = defaultOptionText;
    select.appendChild(opt);
}

function addOptionsToSelect(select, items, defaultOptionText){
    if(items.length > 0) {
        setDefaultSelect(select, defaultOptionText);
        //select.removeAttribute("disabled");
        for (var i = 0; i < items.length; i++){
            var opt = document.createElement("option");
            var item = items[i];
            opt.value = item["id"];
            opt.innerHTML = item["name"];
            select.appendChild(opt);
        }
    }
}