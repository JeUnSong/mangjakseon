function acRegister() {
    let form = document.createElement('form');
    form.setAttribute('method', 'get');
    form.setAttribute('action', '/sign-up');
    document.body.appendChild(form);
    form.submit();
}

function validCheck() {
    var check = document.getElementById("check");

    $.ajax({
        type : "post",
        url : "/loginChk",
        data : {"email":username.value,"password":password.value},
        contentType : "application/x-www-form-urlencoded; charset=UTF-8",
        success : function(result){
            //alert(result);
            if(result){
                let form = document.getElementById("formCheck");
                form.submit();
            }else{
                //alert("없는 계정");
                check.style.display = 'block';
            }
        },
        error : function(){
            alert("ajax 실패..");
        }
    })
}