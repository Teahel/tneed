$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader( "Content-type", "application/json" );
        xhr.setRequestHeader(header, token);
    });
    $(document).ajaxError(function( event, jqxhr, settings, exception ) {
        if (jqxhr.status == 401 ) {
            window.location = "./login";
        } else {
            alert("Error processing "+ settings.url);
        }
    });
});