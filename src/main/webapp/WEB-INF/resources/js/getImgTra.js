
$(document).ready(function () {

    $(".station-title").click(function (event) {
        var idImgStation = event.target.id.slice(1);

        $.ajax({
            type: "GET",
            dataType: "json",
            url: "/speed/service/traStation/" + idImgStation,
            success: function (data) {
                // console.log("hello");
                    $(".imgTag-" + idImgStation).attr("src", "/speed/imageStation/" + data.title);

            },
            complete : function (jqXHR, textStatus) {
                if (textStatus == "parsererror"){
                    $(".imgTag-" + idImgStation).attr("src", "../../resources/images/notFoundImage.png");
                }
            }
        })
    });
});

