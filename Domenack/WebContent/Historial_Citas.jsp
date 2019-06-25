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
        <link rel="stylesheet" href="CSS/EstiloPieDePagina.css">
        <link href="https://fonts.googleapis.com/css?family=Asap&display=swap" rel="stylesheet">
        <title>Domenack - Historial de Citas</title>
        <style>
            .contenedor_historial{
                width: 100%;
            }
            .contenedor_historial > div{
                display: flex;
                background: #3498db;
                color: whitesmoke;
                cursor: pointer;
            }
            .contenedor_historial > div > div{
                flex: 1 4;
                padding: 5px;
            }
            .contenedor_historial > div:hover:not(.vacio){
                background: #0474EE;
            }
            .titulo_historial > div{
                font-weight: bold;
                background: #006699;
                cursor: default;
                user-select:none;
            }
        </style>
    </head>
    <body>
        <%@include file="Template/Cabecera.jsp"%>
        <div class="container_document">
            <h1 class="titulo-nivel-1" style="color: #555">Citas por atender</h1>
            <div class="contenedor_historial">
                <c:if test = "${not empty historial_cita_pendiente}">
                    <div class="titulo_historial">
                        <div>Terapia</div>
                        <div>Fecha</div>
                        <div>Hora</div>
                        <div>Costo</div>
                    </div>
                    <c:forEach items = "${historial_cita_pendiente}" var = "Dato">
                        <div>
                            <div><c:out value = "${Dato.servicio.nombre}" /></div>
                            <div><c:out value = "${Dato.programacionCita.fecha}" /></div>
                            <div><c:out value = "${Dato.programacionCita.hora_ini_valor}" /></div>
                            <div>S/.<c:out value = "${Dato.servicio.costo}" />0</div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test = "${empty historial_cita_pendiente}">
                    <div class="vacio">
                        <div>Aún no hay citas atendidas</div>
                    </div>
                </c:if>
            </div>
            <h1 class="titulo-nivel-1" style="color: #555">Citas atendidas</h1>
            <div class="contenedor_historial">
                <c:if test = "${not empty historial_cita}">
                    <div class="titulo_historial">
                        <div>Terapia</div>
                        <div>Fecha</div>
                        <div>Hora</div>
                        <div>Costo</div>
                    </div>
                    <c:forEach items = "${historial_cita}" var = "Dato">
                        <div>
                            <div><c:out value = "${Dato.servicio.nombre}" /></div>
                            <div><c:out value = "${Dato.programacionCita.fecha}" /></div>
                            <div><c:out value = "${Dato.programacionCita.hora_ini_valor}" /></div>
                            <div>S/.<c:out value = "${Dato.servicio.costo}" />0</div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test = "${empty historial_cita}">
                    <div class="vacio">
                        <div>Aún no hay citas atendidas</div>
                    </div>
                </c:if>
            </div>
        </div>
        <jsp:include page="Template/PiePagina.jsp" flush="true"/>
    </body>
</html>

