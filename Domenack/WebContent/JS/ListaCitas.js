$(document).ready(function () {
    var url = 'C_Citas?action=mostrar_citas';
    $.ajax({
        type: 'POST',
        url: url,
        data: null,
        success: function (data)
        {
            $('#listaCita').html(data);
        }
    });
});
