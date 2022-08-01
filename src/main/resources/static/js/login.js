function acRegister() {
    let form = document.createElement('form');
    form.setAttribute('method', 'get');
    form.setAttribute('action', '/sign-up');
    document.body.appendChild(form);
    form.submit();
}
function enterKey() {
    if (window.event.keyCode == 13) {

        // 엔터키가 눌렸을 때 실행하는 반응
        $("#btn-login").click();
    }
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

function searchPass() {
    var closeBtn = document.getElementById("modalClose");
    closeBtn.click();
}
// 비밀번호 찾기
function emailCheck() {
    $.ajax({
        type: "POST",
        url: "/emailChk",
        data: {"email":email.value},
        contentType : "application/x-www-form-urlencoded; charset=UTF-8",
        success : function(result){
            if(result){
                alert("임시 비밀번호가 입력한 메일로 발송되었습니다.");
                var send = document.getElementById('send');
                send.submit();
                $(location).attr('href', 'http://localhost:8181/');
            }else{
                alert("가입되지 않은 이메일 입니다.");
            }
        },
        error : function(){
            alert("ajax 실패..");
        }
    })
}