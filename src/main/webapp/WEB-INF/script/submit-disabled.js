function checkForm(){
     var f = document.forms["formReg"].elements;
     var canSubmit = true;
     for (var i = 0; i < f.length; i++) {
        if (f[i].value.length == 0) canSubmit = false;
    }
    if (canSubmit) {
        document.getElementById('submit').disabled = !canSubmit;
    } else {
        document.getElementById('submit').disabled = 'disabled';
    }
}