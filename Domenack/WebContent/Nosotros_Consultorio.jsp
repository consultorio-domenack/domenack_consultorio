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
        <title>Domenack - Consultorio</title>
    </head>
    <body>
        <%@include file="Template/Cabecera.jsp"%>
        <div class="container_document">
            <section class="contenedor-informacion">
                <article class="article-body">
                    <header class="titulo-nivel-1 titulo_mision">Consultorio</header>
                    <div class="article-informacion">
                        <header class="titulo-nivel-2 titulo_mision">Informacion General</header>
                        En el consultorio encontrarás la instrumentaria necesaria para la ejecución de las terapias. Una moderna infraestructura para la atención, atención rápida de un máximo de 20 minutos y un especialista en el campo. Todo esto con la excelencia médica que ofrece el consultorio Domenack.
                    </div>
                    <div class="article-informacion">
                        <header class="titulo-nivel-2 titulo_mision">Ubicacion y horarios</header>
                        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3902.3057766821817!2d-77.03117668464934!3d-12.022457544723371!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x9105cf41407ba805%3A0xb3a37bbd3fddd2d!2sJr+Manuel+Vargas%2C+R%C3%ADmac+15094!5e0!3m2!1ses!2spe!4v1559802633942!5m2!1ses!2spe" width="100%" height="200" frameborder="0" style="border:0" allowfullscreen></iframe>
                    </div>
                    <div class="article-informacion article-datos">
                        <div><p>Direccion:</p><p>Jr Manuel Vargas 142, Ciudad Y Campo, Rímac</p> </div>
                        <div><p>Horario de atencion:</p><p>Atención ambulatoria - Lunes a viernes: De 5:00 pm a 8:00 pm  /Domiciliarias - Sabados de 5:00 pm a 8:00 pm.</p></div>
                        <div><p>Distrito:</p><p>Lima</p></div>
                        <div><p>Telefono:</p><p>991574665</p><p>999395928</p><p>RPM #976037961</p></div>
                    </div>
                </article>
            </section>
        </div>
        <jsp:include page="Template/PiePagina.jsp" flush="true"/>
    </body>
</html>
