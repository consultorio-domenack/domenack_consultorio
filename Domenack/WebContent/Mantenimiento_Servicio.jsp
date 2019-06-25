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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
        <title>Administrador - Servicios</title>
        <style>
            
        </style>
    </head>
    <body>
        <%@include file="Template/Cabecera_Admin.jsp"%>
        <div class="container_document">
            <h1 class="titulo-nivel-1" style="color: #555">Mantenimiento de Servicios</h1>
            <table class="egt">
                <thead>
                    <tr class="cabecera-tabla">
                        <th> Servicio </th>
                        <th> Descripción </th>
                        <th> Precio </th>
                        <th> Imagen </th>
                        <th> Cambiar </th>
                        <th> Modificar </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items = "${lista_servicios}" var = "Dato">
                        <tr class='<c:out value = "${Dato.id}" /> cuerpo'>
                            <td> <c:out value = "${Dato.nombre}" /> </td>
                            <td class="descripcion"><p> <c:out value = "${Dato.descripcion}" /> </p></td>
                            <td> <c:out value = "${Dato.costo}" /> </td>
                            <td> <a href = '<c:url value = "C_Servicios?action=Imagen&id=${Dato.id}" />'><i class="fas fa-image icon"></i></a> </td>
                            <td class="mant_servicio">
                                <a href = '<c:url value = "C_Servicios?action=Cambiar&id=${Dato.id}" />' class="button-servicio">Cambiar</a>
                            </td>
                            <td class="mant_servicio">
                                <a href = '<c:url value = "C_Servicios?action=Inhabilitar_Servicio&id=${Dato.id}" />' class="button-servicio">Inhabilitar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <a href="Agregar_Servicios.jsp" class="button-admin">Agregar Servicios</a>
            </table>
        </div>
    </body>
</html>

