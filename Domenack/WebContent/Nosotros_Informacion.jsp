<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="CSS/Cabecera.css">
        <link rel="stylesheet" href="CSS/Nosotros.css">
        <link rel="stylesheet" href="CSS/Miscelanea.css">
        <link rel="stylesheet" href="CSS/EstiloPieDePagina.css">
        <link href="https://fonts.googleapis.com/css?family=Asap&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Allerta&display=swap" rel="stylesheet">
        <title>Domenack - Informacion</title>
    </head>
    <body>
        <%@include file="Template/Cabecera.jsp"%>
        <div class="container_document">
            <section class="contenedor-informacion">
                <article class="article-body">
                    <header class="titulo-nivel-1 titulo_mision">¿Quienes somos?</header>
                    <div class="article-informacion">
                        Somos el consultorio de tratamiento de dolor del Doctor Domenack, una entidad especializada en medicina fisica y rehabilitacion para diferentes dolencias musculoesqueléticas con un procedimiento aplicando un tratamiento de electroestimulación. Contamos con más de 30 años de experiencia en el campo. Te brindamos la mejor ayuda profesional en consultorio, a domicilio y de forma virtual.
                    </div>
                    <div class="mision_vision">
                        <div class="article-informacion">
                            <header class="titulo_mision">MISIÓN</header>
                            <p>
                                Llevar a cabo una atencion rapida de casos crónicos y de alto requerimiento de una terapia desinflamatoria basada en la aplicacion de electroestimulacion de Alto Voltaje sobre Sindromes Miofasciales o Puntos Gatillo los cuales dan origen al dolor reflejo a cualquier parte del cuerpo.
                            </p>
                        </div>
                        <div class="article-informacion">
                            <header class="titulo_mision">VISIÓN</header>
                            <p>
                                Hacer este sistema de mayor embargadura para evitar el uso de medicamentos, ferulas, manipulaciones e incluso las operaciones. 
                            </p>
                        </div>
                    </div>
                    <div class="mision_vision">
                        <div class="article-informacion politica">
                            <header class="titulo_mision">POLÍTICA DE CALIDAD</header>
                            <p>"Como Institución dedicada a la atención de la salud en el Perú, nuestro compromiso con la calidad se orienta a brindar un servicio eficaz y oportuno en Emergencia, Sala de operaciones, Unidad de Cuidados Intensivos y Hospitalización.
                                Con tal fin, velamos por contar con equipos médicos, materiales apropiados; así como con personal calificado y comprometido con la mejora continua. Logrando los objetivos en un ambiente de reconocida calidez”.
                            </p>
                        </div>
                    </div>
                </article>
            </section>
        </div>
        <jsp:include page="Template/PiePagina.jsp" flush="true"/>
    </body>
</html>
