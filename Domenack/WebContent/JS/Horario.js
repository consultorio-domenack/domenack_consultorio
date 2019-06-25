$(document).ready(function () {
    var url = 'C_Citas?action=llenar_tabla';
    $.ajax({
        type: 'POST',
        url: url,
        data: null,
        success: function (data)
        {
            $('#horario_semana').html(data);
        }
    });
    var url = 'C_Citas?action=dia_Actual';
    $.ajax({
        type: 'POST',
        url: url,
        data: $('#formularioBorrar').serialize(),
        success: function (data)
        {
            $('#diaActual').html(data);
        }
    });
    $(document).ajaxComplete(function () {
        if ($(window).width() <= 600) {
            $(".button-celular-on").attr('value', 'Disponible');
            $(".button-celular-off").attr('value', 'Ocupado');
        } else {
            $(".button-celular-on").attr('value', 'Disponible');
            $(".button-celular-off").attr('value', 'Ocupado');
        }
    });
});

$(document).on("click", "#semana-siguiente", function () {
    var url = 'C_Citas?action=avanzar_semana';
    $.ajax({
        type: 'POST',
        url: url,
        data: null,
        success: function (data)
        {
            $('#horario_semana').html(data);
        }
    });
});

$(document).on("click", "#semana-anterior", function () {
    var url = 'C_Citas?action=retroceder_semana';
    $.ajax({
        type: 'POST',
        url: url,
        data: null,
        success: function (data)
        {
            $('#horario_semana').html(data);
        }
    });
});

$(window).on("resize", function () {
    var botonesON = $(".button-celular-on");
    var botonesOFF = $(".button-celular-off");
    if ($(window).width() <= 600) {
        botonesON.val("Disponible");
        botonesOFF.val("Ocupado");
    } else {
        botonesON.val("Disponible");
        botonesOFF.val("Ocupado");
    }
});