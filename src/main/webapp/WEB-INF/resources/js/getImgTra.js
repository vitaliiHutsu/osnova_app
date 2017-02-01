/**
 * Created by vesa-pk on 31.01.2017.
 */
$(document).ready(function () {
    $(".station-title").click(function (event) {
        var idImgStation = event.target.id.slice(1);

        $.ajax({
                    type: "GET",
                    dataType: "json",
                    url: "/speed/service/traStation/" + idImgStation,
                    success: function (data) {
                        $(".imgTag-"+ idImgStation).attr("src", "/speed/imageStation/" + data.title);
                    }
                })
    })
    // function clickImg(el) {
    //     var idImgStation = $(el).attr("href").slice(1);
    //     console.log(idImgStation);
    //
    //     $.ajax({
    //         type: "GET",
    //         dataType: "json",
    //         url: "/speed/service/traStation/" + idImgStation,
    //         success: function (data) {
    //             $("#modalId").empty().append('<div class="modal fade pop-up-1" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel-1" aria-hidden="true">' +
    //                 '<div class="modal-dialog modal-lg">' +
    //                 '<div class="modal-content">' +
    //
    //                 '<div class="modal-header">' +
    //                 '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>' +
    //                 '</div>' +
    //                 '<div class="modal-body">' +
    //                 '<img src="/speed/imageStation/' +
    //                 data.title +
    //                 '" class="img-responsive img-rounded center-block" alt="">' +
    //                 '</div>' +
    //                 '</div>' +
    //                 '</div>' +
    //                 '</div>');
    //         }
    //     })
    //
    // }
});

