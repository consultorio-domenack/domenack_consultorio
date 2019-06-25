$(document).ready(main);
function main() {
    $(".submenu").click(function () {
        //if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
            $(this).children("ul").slideToggle();
        //}
    });

    $("ul").click(function (p) {
        //if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
            p.stopPropagation();
        //}
    });
}