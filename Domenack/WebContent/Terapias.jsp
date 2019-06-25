<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="CSS/Cabecera.css">
        <link rel="stylesheet" href="CSS/Terapias.css">
        <link rel="stylesheet" href="CSS/Miscelanea.css">
        <link rel="stylesheet" href="CSS/Loader.css">
        <link rel="stylesheet" href="CSS/EstiloPieDePagina.css">
        <link href="https://fonts.googleapis.com/css?family=Asap&display=swap" rel="stylesheet">
        <title>Domenack - Terapias</title>
        <script src="JS/JQuery.js"></script>
        <script>
            $(document).ready(function () {
                var url = 'C_Informacion?action=Mandar_Servicio';
                $.ajax({
                    type: 'POST',
                    url: url,
                    data: $('#servicio_info').serialize(),
                    success: function (data)
                    {
                        $('#servicio_informacion').fadeIn(1000).html(data);
                    }
                });
            });
            $(document).on("change", "#servicio", function () {
                var url = 'C_Informacion?action=Mandar_Servicio';
                $('#servicio_informacion').html('<div class="loader"></div>');
                $.ajax({
                    type: 'POST',
                    url: url,
                    data: $('#servicio_info').serialize(),
                    success: function (data)
                    {
                        setTimeout(function () {
                            $('#servicio_informacion').html(data);
                        }, 1000);
                    }
                });
            });
        </script>
    </head>
    <body>
        <%@include file="Template/Cabecera.jsp"%>
        <div class="container_document">
            <form action="#" method="POST" class="formulario_servicio" name="servicio_info" id="servicio_info">
                <label for="servicio">Terapias</label>
                <select name = "servicio" id="servicio" class="servicio">
                    <c:forEach items = "${lista_servicios}" var = "Dato">
                        <option value = "${Dato.id}"> ${Dato.nombre} </option>
                    </c:forEach>
                </select>
            </form>
            <div id="servicio_informacion"></div>
        </div>
        <jsp:include page="Template/PiePagina.jsp" flush="true"/>
    </body>
</html>
