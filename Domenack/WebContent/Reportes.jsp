<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Template/Redireccionamiento.jsp"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="CSS/Cabecera.css">
        <link rel="stylesheet" href="CSS/Miscelanea.css">
        <link rel="stylesheet" href="CSS/Admin.css">
        <link rel="stylesheet" href="CSS/TablaAdmin.css">
        <link href="https://fonts.googleapis.com/css?family=Asap&display=swap" rel="stylesheet">
        <style>
            .button-reporte{
                display: block;
                background: #555;
                color: whitesmoke;
                width: 95%;
                margin: 20px auto;
                padding: 20px;
                border-radius: 5px;
                text-decoration: none;
            }
            .button-reporte:hover{
                background: #333;
            }
        </style>
        <title>Administrador - Reportes</title>
    </head>
    <body>
        <%@include file="Template/Cabecera_Admin.jsp"%>
        <div class="container_document">
            <h1 class="titulo-nivel-1" style="color: #555">Lista de reportes</h1>
            <a class="button-reporte" href="reportes?action=ranking-servicio">Ranking de servicio más solicitado</a>
            <a class="button-reporte"  href="reportes?action=ganancia-servicio">Ganancia total según servicio</a>
            <a class="button-reporte"  href="reportes?action=dias-servicio">Días más transitados</a>
            <a class="button-reporte"  href="reportes?action=usuarios-servicio">Cantidad de citas según usuario</a>
        </div>
    </body>
</html>
