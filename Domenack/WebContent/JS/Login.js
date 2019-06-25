$('.form').find('input, textarea').on('keyup blur focus', function (e) {
    var $this = $(this),
            label = $this.prev('.label_login');
    if (e.type === 'keyup') {
        if ($this.val() === '') {
            label.removeClass('active highlight');
        } else {
            label.addClass('active highlight');
        }
    } else if (e.type === 'blur') {
        if ($this.val() === '') {
            label.removeClass('active highlight');
        } else {
            label.removeClass('highlight');
        }
    } else if (e.type === 'focus') {
        if ($this.val() === '') {
            label.removeClass('highlight');
        } else if ($this.val() !== '') {
            label.addClass('highlight');
        }
    }
});
$('.tab a').on('click', function (e) {
    e.preventDefault();
    $(this).parent().addClass('active');
    $(this).parent().siblings().removeClass('active');
    target = $(this).attr('href');
    $('.tab-content > div').not(target).hide();
    $(target).fadeIn(600);
});

$(document).ready(function () {
    //called when key is pressed in textbox
    $(".limite_numero").keypress(function (e) {

        var maxlengthNumber = parseInt($(this).attr('maxlength'));
        var inputValueLength = $(this).val().length + 1;
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
        if (maxlengthNumber < inputValueLength) {
            return false;
        }
    });
});