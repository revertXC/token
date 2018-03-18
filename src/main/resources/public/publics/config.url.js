var rootPath="http://127.0.0.1/token";


function ajaxX(json){
    var ajaxUrl = rootPath+json.url;
    if(json.url.indexOf("http:") >= 0){
        ajaxUrl = json.url;
    }
    var ajaxType = json.type ? json.type : "GET";
    var ajaxSuccess = json.success;
    $.ajax({
        url:ajaxUrl,
        beforeSend: function(request) {
            request.setRequestHeader("jwtToken", $.cookie("platform_token"));
            request.setRequestHeader("Access-Control-Allow-Origin", "*");
        },
        async:json.async ? json.async : true,
        data:json.data,
        type:ajaxType,
        success:ajaxSuccess
    });
}