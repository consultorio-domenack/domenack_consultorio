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
        <title>Administrador - Usuarios</title>
    </head>
    <body>
        <%@include file="Template/Cabecera_Admin.jsp"%>
        <div class="container_document">
            <h1 class="titulo-nivel-1" style="color: #555">Lista de usuarios</h1>
            <table class="egt">
                <thead>
                    <tr class="cabecera-tabla">
                        <th> Nombre </th>
                        <th> DNI </th>
                        <th> Correo </th>
                        <th> Informacion </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items = "${lista_usuarios}" var = "Dato">
                        <tr class='<c:out value = "${Dato.usuario.id}" /> cuerpo'>
                            <td> <c:out value = "${Dato.nombre} ${Dato.apellidoPaterno} ${Dato.apellidoMaterno}" /> </td>
                            <td> <c:out value = "${Dato.usuario.dni}" /> </td>
                            <td class="descripcion"><p> <c:out value = "${Dato.usuario.correo}" /> </p></td>
                            <td>
                                <a href = '#' class="button-servicio">Ver Perfil</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
