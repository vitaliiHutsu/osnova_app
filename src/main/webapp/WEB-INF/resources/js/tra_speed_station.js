$(document).ready(function () {

    var urlService = '/admin/service/traStation';
    var publicUrlService = "/speed/service/traStation";
    var anim = document.getElementById("pic");

    listUrlTraStation(publicUrlService, anim);
    document.getElementById('addImgFile').onclick = function () {
        var element = document.getElementById('btnAddImgStation');
        var file = element.files[0];

        if (window.FormData !== undefined) {
            var data = new FormData;
            data.append('fileIMG', file);
            data.append('idStation', "${station.id}");

            var xhr = new XMLHttpRequest();
            xhr.open('POST', urlService);

            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 || xhr.status == 200) {
                    listUrlTraStation(publicUrlService, anim);
                    $("#btnAddImgStation").prop("value", null);
                    $("#notValid").empty().append("файл добавлен").css("color", "#00FFA9");
                    $("#addImgFile").attr("disabled", true);
                }
            }

            xhr.send(data);
        }
    }

    var filesExt = ['jpg', 'png', 'JPG', 'PNG']; // массив расширений
    $('input[type=file]').change(function () {
        var parts = $(this).val().split('.');
        if (filesExt.join().search(parts[parts.length - 1]) != -1) {
            $("#notValid").empty();
            $("#addImgFile").attr("disabled", false);
            $("#notValid").empty();
        } else {
            // alert('WTF?!');
            $("#notValid").append("только файл .jpg или .png").css("color", "#FF1111");
            $("#addImgFile").attr("disabled", true);

        }
    });


    function showAnim(anim) {
        anim.style.display = "block";
    }

    function stopAnim(anim) {
        anim.style.display = "none";
    }

    function listUrlTraStation(publicUrlService, anim) {
        showAnim(anim);
        $.ajax({
            type: "GET",
            dataType: "json",
            url: publicUrlService + "/${station.id}",
            success: function (jsondate) {
                var data = jsondate;
                stopAnim(anim);
                console.log(data.title);

                $("#img_tra_station").empty().append("<a href='#${station.title_station}' data-toggle='modal' data-target='.pop-up-1'>Схема</a>")

                $("#modalImg").empty().html('<div class="modal fade pop-up-1" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel-1" aria-hidden="true">' +
                    '<div class="modal-dialog modal-lg">' +
                    '<div class="modal-content">' +

                    '<div class="modal-header">' +
                    '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>' +
                    '<h4 class="modal-title" id="myLargeModalLabel-1">${station.title_station}</h4>' +
                    '</div>' +
                    '<div class="modal-body">' +
                    '<img src="/speed/imageStation/' +
                     data.title +
                    '" class="img-responsive img-rounded center-block" alt="">' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>');
            }
        });
        
        stopAnim(anim);
    }

});

