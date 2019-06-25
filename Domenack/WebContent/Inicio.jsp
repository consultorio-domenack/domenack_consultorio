<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="CSS/Cabecera.css">
        <link rel="stylesheet" href="CSS/Miscelanea.css">
        <link rel="stylesheet" href="CSS/EstiloPieDePagina.css">
        <link href="https://fonts.googleapis.com/css?family=Asap&display=swap" rel="stylesheet">
        <style>
            .imagen_inicio{
                position: static;
                width: 100%;
                height: 100%;
            }
        </style>
        <title>Domenack - Inicio</title>
    </head>
    <body>
        <%@include file="Template/Cabecera.jsp"%>
        <div class="container_document">
            <img class="imagen_inicio" src="Imagenes/Fondos/1.jpg">
        </div>
        <jsp:include page="Template/PiePagina.jsp" flush="true"/>
    </body>
</html>

