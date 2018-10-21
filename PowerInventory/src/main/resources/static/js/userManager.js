
/////////////////////////////
//添加编辑对话框中的错误提示框
////////////////////////////
//错误信息提醒
var AddUserStatusEnum = {
    ADD_USER_SUCCESS: 0,
    ADD_USER_FAIL: 1,
    USER_ID_NULL: 2,
    USER_NAME_NULL: 3,
    USER_PASSWORD_NULL: 4,
    USER_EXIST_ERR: 5,
    EDIT_USER_FAIL: 6
}

function showAlert(sts) {
    //var contentsDiv = document.getElementById("alertMsg");
    //contentsDiv.setAttribute("value", msg);
    switch (sts) {
        case AddUserStatusEnum.USER_PASSWORD_NULL:
            $("#alertMsg").html("密码不能为空");
            break;
        case AddUserStatusEnum.USER_NAME_NULL:
            $("#alertMsg").html("用户名不能为空");
            break;
        case AddUserStatusEnum.USER_ID_NULL:
            $("#alertMsg").html("用户编码不能为空");
            break;
        case AddUserStatusEnum.ADD_USER_FAIL:
            $("#alertMsg").html("添加用户失败");
        case AddUserStatusEnum.USER_EXIST_ERR:
            $("#alertMsg").html("用户已经存在");
            break;
        case AddUserStatusEnum.EDIT_USER_FAIL:
            $("#alertMsg").html("编辑用户失败");
            break;
    }

    $("#alertDialog").show();
}

function hideAlert() {
    $("#alertDialog").hide();
}

/////////////////////////////
//主页面中的错误提示框
////////////////////////////
function mainShowAlert(msg) {
    //var contentsDiv = document.getElementById("alertMsg");
    //contentsDiv.setAttribute("value", msg);
    $("#mainAlertMsg").html(msg);
    $("#mainAlertDialog").show();
}

function mainHideAlert() {
    $("#mainAlertDialog").hide();
}


//////////////////////////////////
//    添加/编辑对话框处理
//////////////////////////////////

//提交前检查并生成用户对象
function GetUser() {
    var user = new Object();
    user.code = $('#code').val();
    user.userName = $('#userName').val();
    user.password = $('#password').val();
    user.telNo = $('#telNo').val();
    user.remark = $('#remark').val();
    user.status = $('#status').val();

    if (!user.code || user.code == "") {
        showAlert(AddUserStatusEnum.USER_ID_NULL);
        document.getElementById('code').focus();
        return null;
    }

    if (!user.userName || user.userName == "") {
        showAlert(AddUserStatusEnum.USER_NAME_NULL);
        document.getElementById('userName').focus();
        return null;
    }
    if (!user.password || user.password == "") {
        showAlert(AddUserStatusEnum.USER_PASSWORD_NULL);
        document.getElementById('password').focus();
        return null;
    }

    return user;
}



//点击添加用户按钮
function OnAddUserClick(){
    $('#myModalLabel').text("添加用户");
    $('#code').removeAttr("disabled"); //用户编码可输入
    $('#code').val("");
    $('#userName').val("");
    $('#password').val("");
    $('#telNo').val("");
    $('#remark').val("");
    $("#myModal").modal('show');
}

//点击编辑用户按钮
function OnEditUserClick() {
    $('#myModalLabel').text("编辑用户");
    $('#code').attr("disabled", "disabled"); //用户编码不可编辑
    var selData = table.rows('.selected').data()[0];
    if(selData != null){
        $('#code').val(selData[1]);
        $('#userName').val(selData[2]);
        $('#password').val(selData[3]);
        $('#telNo').val(selData[4]);
        $('#remark').val(selData[5]);

        //table 行中名字为switchCheckbox的input的属性是否为checked
        var sts = $(selData[6]).find("input[name='switchCheckbox']").prop("checked");
        if(sts){
            $('#status').val("1");
        }else{
            $('#status').val("0");
        }

        $("#myModal").modal('show');
    }
}

//(添加/修改)对话框提交按钮处理
function UserDialogSubmit() {
    var user = GetUser();
    if(user != null) {
        var title = $('#myModalLabel').text();
        if (title == "添加用户") {
            AjaxRequest("/base_data/user/add", user, UserSubmitSuccess, UserSubmitFail);
        } else if (title == "编辑用户") {
            AjaxRequest("/base_data/user/edit", user, UserSubmitSuccess, UserSubmitFail);
        }
    }
}


function UserSubmitSuccess(data) {
    if (data.result == 0) {
        $("#myModal").modal('hide');
        var url = "/base_data/user/get?"
        var pageSize = $('#selPageSize').find("option:selected").text();
        url= ReplaceParamVal("pageSize", pageSize);
        window.location(url);
    } else {
        var title = $('#myModalLabel').text();
        if (title == "添加用户") {
            if(data.result == -2){
                showAlert(AddUserStatusEnum.USER_EXIST_ERR);
            }else{
                showAlert(AddUserStatusEnum.ADD_USER_FAIL);
            }
        }else{
                showAlert(AddUserStatusEnum.EDIT_USER_FAIL);
        }

    }
}

function UserSubmitFail(data) {

}


//////////////////////////////////
//    排序处理（未完）
//////////////////////////////////

function ChangeSortIcon(obj){
    var sortHeader = new Array(
        "colUid",
        "colUserName")


    for(var i = 0; i < sortHeader.length; i++){

        var v = document.getElementById(sortHeader[i]);

        if(v == obj){
            if($(v).hasClass("fa-sort")) {
                $(v).removeClass("fa-sort");
                $(v).addClass("fa-sort-asc");

            }else if($(v).hasClass("fa-sort-asc")) {
                $(v).removeClass("fa-sort-asc");
                $(v).addClass("fa-sort-desc");

            }else if($(v).hasClass("fa-sort-desc")) {
                $(v).removeClass("fa-sort-desc");
                $(v).addClass("fa-sort-asc");
            }

        }else{

            //恢复初值
            if($(v).hasClass("fa-sort-asc")){
                $(v).removeClass("fa-sort-asc");

            }else if($(v).hasClass("fa-sort-desc")) {
                $(v).removeClass("fa-sort-desc");
            }

            if(!$(v).hasClass("fa-sort")){
                $(v).addClass("fa-sort");
            }
        }
    }
}
function Sort(obj) {

    ChangeSortIcon(obj);


}

//////////////////////////////////
//    table 处理
//////////////////////////////////

//table行全选处理
function SelectAllRow(cb) {

    $('#dataTables-example tbody tr').each(function () {
        if($(cb).prop("checked") == true){
            if(!$(this).hasClass('selected')){
                $(this).addClass('selected');
                $(this).find("input[name='colSingleSelect']").prop('checked', true);
            }
        }else{
            if($(this).hasClass('selected')){
                $(this).removeClass('selected');
                $(this).find("input[name='colSingleSelect']").prop('checked', false);
            }
        }

        if(table.rows('.selected').data().length > 1){
            $('#editUser').attr("disabled","disabled");
        }else{
            $('#editUser').removeAttr("disabled");
        }
        var rowId = $(this)._DT_RowIndex;
    });
}


//行的复选框选中处理
function selectTableCheckbox(event){

    //直接使用toggleClass函数，checkbox点击快的时候，checkbox的状态没有变化，但是行已经选择了
    if($(this).prop('checked')){
        $(this).parent().parent().addClass('selected');
    }else{
        $(this).parent().parent().removeClass('selected');
    }

    if(table.rows('.selected').data().length > 1){
        $('#editUser').attr("disabled","disabled");
    }else{
        $('#editUser').removeAttr("disabled");
    }
    event.stopPropagation();
};

//选中行时也设置选中状态
function selectTableRow(){
    $(this).find("input[name='colSingleSelect']").click();
};


//查询时的处理
function searchUser(event) {

    if(event.keyCode == 13){
        var code = $('#srchUserCode').val();
        var userName = $('#srchUserName').val();
        var password = $('#srchPassword').val();
        var telNo = $('#srchTelNo').val();
        var remark = $('#srchRemark').val();
        var status = $('#srchStatus').val();
        var strCreateTime = $('#srchCreateTime').val();
        var strModifyTime = $('#srchModifyTime').val();
        var currentPage = 1;
        var pageSize = 20;
        var url = "/base_data/user/get?";
        var firstParam = true;

        if(code.length > 0){
            url+=("&code="+code);
        }
        if(userName.length > 0){
            url+=("&userName="+userName);
        }
        if(password.length > 0){
            url+=("&password="+password);
        }
        if(telNo.length > 0){
            url+=("&telNo="+telNo);
        }
        if(remark.length > 0){
            url+=("&remark="+remark);
        }

        if(status.length > 0){
            url+=("&status="+status);
        }
        if(strCreateTime.length > 0){
            url+=("&strCreateTime="+strCreateTime);
        }
        if(strModifyTime.length > 0){
            url+=("&strModifyTime="+strModifyTime);
        }

        url+=("&currentPage="+currentPage);
        url+=("&pageSize="+pageSize);

        window.location(url);
    }
};

//删除时的处理
function OnDelUserClick() {
    var selDataList = table.rows('.selected').data();
    var codeList = [];

    for(var i = 0; i < selDataList.length; i++){
        codeList.push(selDataList[i][1]);
    }

    var aa = JSON.stringify(codeList);
    // $.ajax({
    //     url: "/base_data/user/del",
    //     dateType: 'json',
    //     type: 'Post',
    //     data: __list,
    //     async: false,
    //     success: function (data) {
    //     }
    // });

    AjaxRequest("/base_data/user/del", codeList, DelUserSuccess, DelUserFail);
}
function DelUserSuccess(data){
    if (data.result == 0) {
        var url = "/base_data/user/get?"
        var pageSize = $('#selPageSize').find("option:selected").text();
        url= ReplaceParamVal("pageSize", pageSize);
        window.location(url);
        mainShowAlert("删除用户成功");
    } else {
        mainShowAlert("删除用户失败");

    }
}

function DelUserFail() {

}

function changeStatus() {
    // if ($(this).bootstrapSwitch('state')) {
    //     $(this).bootstrapSwitch('state', true);
    // }
    //所在行
    var tr = $(this).parents("tr");
    var code = tr.find("td[name='code']").text();
    if($(this).prop("checked")){
        var status = 1;
    }else{
        var status = 0;
    }

    var user = new Object();
    user.code = code;
    user.status = status;
    AjaxRequest("/base_data/user/setStatus", user, UpdateStatusSuccess, UpdateStatusFail);
}
function UpdateStatusSuccess(data) {
    if(data.result != 0){
        mainShowAlert("更新用户状态失败");
    }
}
function UpdateStatusFail() {
    mainShowAlert("更新用户状态失败");
}
//////////////////////////////////
//    分页相关处理
//////////////////////////////////

//切换每页显示条数的处理
function PageSizeChange(){
    var val = $(this).find("option:selected").text();
    var nUrl = ReplaceParamVal("pageSize", val);
    this.location = nUrl;
    window.location.href=nUrl;
}

//页跳转（下）
function jumpPage1() {
    var pageNum = $('#jumpPageNum1').val();
    if(pageNum > 0){
        var url = $(this).attr('href') + "&currentPage=" + pageNum;
        window.location(url);
    }
};
//页跳转（上）
function jumpPage2() {
    var pageNum = $('#jumpPageNum2').val();
    if(pageNum > 0){
        var url = $(this).attr('href') + "&currentPage=" + pageNum;
        window.location(url);
    }
};




