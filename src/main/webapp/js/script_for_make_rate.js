function onRateTypeSelectChange(){
    var selectedRateType = document.getElementById('rate-type-select').value;
    var changablePart =  document.getElementById('changable-part');
    if(selectedRateType != ''){
        switch(selectedRateType){
            case 'WIN':
            case 'FIRST_GOAL':
                var members = getMembersForEvent(document.getElementById('event-id-input').value);
                changablePart.innerHTML = "";

                var html;
                html = createSelectHtmlWithMembers('member-1', 'member-1', members);
                html = wrapIntoDiv(html);
                changablePart.innerHTML += html;
                break;
            case 'DRAW':
                changablePart.innerHTML = "";
                break;

            case 'EXACT_SCORE':
                var members = getMembersForEvent(document.getElementById('event-id-input').value);
                changablePart.innerHTML = "";

                var html;
                html = createSelectHtmlWithMembers('member-1', 'member-1', members);
                html += createInputForMemberScore("member-1-score");
                html = wrapIntoDiv(html);
                changablePart.innerHTML += html;

                html = "";
                html = createSelectHtmlWithMembers('member-2', 'member-2', members);
                html += createInputForMemberScore("member-2-score");
                html = wrapIntoDiv(html);
                changablePart.innerHTML += html;
                break;
        }
    }
    else{
        changablePart.innerHTML = "";
    }
}

function wrapIntoDiv(html){
    return "<div class='input-div'>" + html + "</div>";
}

function createSelectHtmlWithMembers(id, name, members){

    var options = '<option selected>Choose member #1</option>';
    for(var i = 0; i < members.length; i++){
        options += "<option value=\"" + members[i].id + "\">" + members[i].name +"</option>";
    }
    return "<select id=\"" + id + "\" name=\"" + name + "\" required>" + options + "</select>";
}

function createInputForMemberScore(name){
    return "<input name=\"" + name + "\" type=\"text\" required pattern=\"[0-9]+\"/>";
}

function getMembersForEvent(eventId){
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/main?command=getMembersByEventJson&eventId=' + eventId, false);
        xhr.send();
        if(xhr.status != 200){
            return [];
        } else {
            return JSON.parse(xhr.responseText);
        }
}

