<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Template/Redireccionamiento.jsp"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
        <title>Administrador - Reporte 2</title>
        <style>
            tbody > tr{
                height: 80px;
            }
        </style>
    </head>
    <body>
        <%@include file="Template/Cabecera_Admin.jsp"%>
        <div class="container_document">
            <h1 class="titulo-nivel-1" style="color: #555">Ganancia total según servicio</h1>
            <table class="egt">
                <thead>
                    <tr class="cabecera-tabla">
                        <th> Ganancia </th>
                        <th> Terapia </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items = "${lista_reporte}" var = "Dato">
                        <tr class='cuerpo'>
                            <td> S/.<c:out value = "${Dato.ganancia}" /> </td>
                            <td> <c:out value = "${Dato.nombre}" /> </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
