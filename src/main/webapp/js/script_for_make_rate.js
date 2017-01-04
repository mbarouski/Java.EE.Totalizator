function onRateTypeSelectChange(){
    var selectedRateType = document.getElementById('rate-type-select').value;
    var changablePart =  document.getElementById('changable-part');
    if(selectedRateType != ''){
        changablePart.innerHTML = createSelectHtmlWithMembers('member-1', 'member-1', getMembersForEvent(0));
    }
    else{
        changablePart.innerHTML = "<p>Select rate type</p>";
    }
}

function createSelectHtmlWithMembers(id, name, members){
    var options = '<option selected>Choose member #1</option>';
    for(var member in members){
        options += "<option value=\"" + member.id + "\">" + member.name +"</option>";
    }
    return "<select id=\"" + id + "\" name=\"" + name + "\">" + options + "</select>";
}

function getMembersForEvent(eventId){
    return [];
}