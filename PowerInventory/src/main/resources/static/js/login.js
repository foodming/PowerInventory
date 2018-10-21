
function LoginSuccess(data){

    if (data.result == 0) {
        showMsg(LoginStatusEnum.LOGIN_SUCCESS);
         window.location.href =  "/main";
    } else {

        showMsg(LoginStatusEnum.LOGIN_FAIL);
    }
}

function LoginFail(data){
    showMsg(LoginStatusEnum.LOGIN_FAIL);
}

var loginData=null;
//验证表单是否为空，若为空则将焦点聚焦在input表单上，否则表单通过，登录成功
function Login(){
    clearMsg();
    accountName = $('#userName').val();
    password = $('#password').val();

    if(!accountName || accountName == ""){
        showMsg(LoginStatusEnum.USER_NULL);
        document.getElementById('userName').focus();
        return false;
    }
    if(!password || password == ""){
        showMsg(LoginStatusEnum.PASSWORD_NULL);
        document.getElementById('password').focus();
        return false;
    }

    var jsonLoginData = {"username":accountName, "password":password};
    AjaxRequest("/login", jsonLoginData, LoginSuccess, LoginFail);

}

//错误信息提醒
var LoginStatusEnum = {
    LOGIN_SUCCESS: 0,
    USER_NULL:1,
    PASSWORD_NULL:2,
    LOGIN_FAIL:3
}

function clearMsg() {
    $("#loginResult").removeClass('alert-danger');
    $("#loginResult").val('');
}
function showMsg(sts){
    switch (sts) {
        case LoginStatusEnum.USER_NULL:
            $("#loginResult").addClass('alert-danger');
            $("#loginResult").val('用户名不能为空');
            break;
        case LoginStatusEnum.PASSWORD_NULL:
            $("#loginResult").addClass('alert-danger');
            $("#loginResult").val('密码不能为空');
            break;
        case LoginStatusEnum.LOGIN_FAIL:
            $("#loginResult").addClass('alert-danger');
            $("#loginResult").val('登录失败，用户名或密码错误');
            break;
        case LoginStatusEnum.LOGIN_SUCCESS:
            $("#loginResult").addClass('alert-success');
            $("#loginResult").val('登录成功');
            break;
    }

}

