document.write("<script type='text/javascript' src='utils.js'></script>");

function naviTo(url) {
    var username = GetQueryString("userName");
    var token = GetQueryString("token");
    // window.location.href =  url + "?username="+ username + "&token=" + token;
    window.location.href =  url ;
}