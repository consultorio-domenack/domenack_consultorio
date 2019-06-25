<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Template/Redireccionamiento.jsp"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="CSS/Cabecera.css">
        <link rel="stylesheet" href="CSS/Miscelanea.css">
        <link rel="stylesheet" href="CSS/Citas.css">
        <link rel="stylesheet" href="CSS/EstiloPieDePagina.css">
        <link href="https://fonts.googleapis.com/css?family=Asap&display=swap" rel="stylesheet">
        <title>Domenack - Perfil</title>
    </head>
    <body>
        <%@include file="Template/Cabecera.jsp"%>
        <div class="container_document">
            <h1 class="titulo-nivel-1">Perfil</h1>
            <c:set var="Dato" value="${requestScope.informacion}" scope="request" />  
            <div class='informacion-paciente-cita'>
                <span><strong>Nombre Completo: </strong></span>
                <div>
                    <c:out value = "${Dato.nombre} ${Dato.apellidoPaterno} ${Dato.apellidoMaterno}" />
                </div>
                <span><strong>Fecha de nacimiento: </strong></span>
                <div>
                    <fmt:formatDate  value = "${Dato.fechaNacimiento}" pattern="dd"/> de
                    <fmt:formatDate  value = "${Dato.fechaNacimiento}" pattern="MMMM"/> del
                    <fmt:formatDate  value = "${Dato.fechaNacimiento}" pattern="YYYY"/>
                </div>
                <span><strong>DNI: </strong></span>
                <div>
                    <c:out value = "${Dato.sexo}" />
                </div>
                <div>
                    <c:out value = "${Dato.estadoCivil}" />
                </div>
                <div>
                    <c:out value = "${Dato.usuario.dni}" />
                </div>
                <span><strong>Correo: </strong></span>
                <div>
                    <c:out value = "${Dato.usuario.correo}" />
                </div>
            </div>
        </div>
        <jsp:include page="Template/PiePagina.jsp" flush="true"/>
    </body>
</html>
