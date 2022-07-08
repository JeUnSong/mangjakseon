function passConfirm(){
    var password = document.getElementById('password');
    var passwordConfirm = document.getElementById('passwordConfirm');
    var confirmMsg = document.getElementById('confirmMsg');
    var correctColor = "#00ff00";
    var incorrectColor = "#ff0000";

    if (password.value == passwordConfirm.value) {
        confirmMsg.style.color = correctColor;
        confirmMsg.innerHTML = "비밀번호 일치";
    } else {
        confirmMsg.style.color = incorrectColor;
        confirmMsg.innerHTML = "비밀번호 불일치";
    }
}