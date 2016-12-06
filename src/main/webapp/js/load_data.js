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
    var length = select.options.length;
    for (i = 0; i < length; i++) {
        select.remove(i)
    }
    var opt = document.createElement("option");
    opt.value = 0;
    opt.innerHTML = defaultOptionText;
    select.appendChild(opt);
}

function addOptionsToSelect(select, leagues, defaultOptionText){
    if(leagues.length > 0) {
        setDefaultSelect(select, defaultOptionText);
        select.removeAttribute("disabled");
        for (var i = 0; i < leagues.length; i++){
            var opt = document.createElement("option");
            var league = leagues[i];
            opt.value = league["id"];
            opt.innerHTML = league["name"];
            select.appendChild(opt);
        }
    }
}