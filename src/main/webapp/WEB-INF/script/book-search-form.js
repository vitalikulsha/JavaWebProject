function checkSearchParam(id, submit){
    var idValue = document.getElementById(id).value;
    if(idValue != 0){
        document.getElementById(submit).disabled = false;
    } else {
        document.getElementById(submit).disabled = true;
    }
};