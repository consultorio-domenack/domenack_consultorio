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
        <link rel="stylesheet" href="CSS/Servicio.css">
        <link href="https://fonts.googleapis.com/css?family=Asap&display=swap" rel="stylesheet">
        <title>Administrador - Modificar Servicio</title>
    </head>
    <body>
        <%@include file="Template/Cabecera_Admin.jsp"%>
        <c:set var="Dato" value="${requestScope.servicio}" scope="request" />
        <div class="container_document">
            <h1 class="titulo-nivel-1" style="color: #555">Modificar Servicio <c:out value = "${Dato.nombre}" /></h1>
            <form action="C_Servicios?action=Modificar" method="POST" enctype="multipart/form-data" class="formulario_agregar_servicio">
                <input type="hidden" name="id_servicio" placeholder="Ingresar Id" value='<c:out value = "${Dato.id}" />'>
                <input type="text" name="nombre_servicio" placeholder="Ingresar servicio" value='<c:out value = "${Dato.nombre}" />' required>
                <textarea name="descripcion" placeholder="Ingresar servicio" rows="10" cols="50" required><c:out value = "${Dato.descripcion}"/></textarea>
                <input type="text" name="costo" placeholder="Ingresar costo" value='<c:out value = "${Dato.costo}" />' required>
                <label for="file-upload" class="custom-file-upload">
                    <i class="fas fa-image icon"></i> Seleccionar Imagen
                </label>
                <input type="file" name="imagen" id="file-upload" accept="image/x-png,image/gif,image/jpeg">
                <input type="submit" value="Modificar" class="button-mandar">
            </form>
        </div>
    </body>
</html>
