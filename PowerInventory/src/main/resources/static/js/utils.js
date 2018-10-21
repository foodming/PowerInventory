/*
    查询Url参数值
    name:参数名称
 */
function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
    if(r!=null)return  unescape(r[2]); return null;
}

//替换指定传入参数的值,paramName为参数,replaceWith为新值
function ReplaceParamVal(paramName,replaceWith) {
    var oUrl = this.location.href.toString();
    var re=eval('/('+ paramName+'=)([^&]*)/gi');
    var nUrl = oUrl.replace(re,paramName+'='+replaceWith);
    return nUrl;
}

function MergeJsonObject(jsonbject1, jsonbject2) {
    var resultJsonObject = {};
    for (var attr in jsonbject1) {
        resultJsonObject[attr] = jsonbject1[attr];
    }
    for (var attr in jsonbject2) {
        resultJsonObject[attr] = jsonbject2[attr];
    }
    return resultJsonObject;
};



function AjaxRequest(url, jsondata, successCallback, failCallBack){

    var rn = false;
    $.ajax({
        type : "POST",
        url : url,// 获取自己系统后台用户信息接口
        data :JSON.stringify(jsondata),
        contentType : "application/json;charset=utf-8",
        dataType : "json",
        success : function(result) {
            if(typeof successCallback=='function'){
                successCallback(result);
                rn = true;
            }
        },
        error : function(data){
            if(typeof failCallBack=='function'){
                failCallBack(data);
                rn = false;
            }
        }
    });

    return rn;
}

function AjaxRequest2(url, jsondata, successCallback, failCallBack){

    var rn = false;
    $.ajax({
        type : "POST",
        url : url,// 获取自己系统后台用户信息接口
        data :JSON.stringify(jsondata),
        contentType : "application/json;charset=utf-8",
        dataType : "html",
        success : function(result) {
            if(typeof successCallback=='function'){
                successCallback(result);
                rn = true;
            }
        },
        error : function(data){
            if(typeof failCallBack=='function'){
                failCallBack(data);
                rn = false;
            }
        }
    });

    return rn;
}

function Trim(str){
    trimStr = str.replace(/^\s+|\s+$/g,"");
    return trimStr;
}


