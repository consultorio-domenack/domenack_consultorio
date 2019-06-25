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
        <link rel="stylesheet" href="CSS/Servicio.css">
        <link rel="stylesheet" href="CSS/EstiloPieDePagina.css">
        <link href="https://fonts.googleapis.com/css?family=Asap&display=swap" rel="stylesheet">
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
        <title>Administrador - Agregar Servicio</title>
    </head>
    <body>
        <%@include file="Template/Cabecera_Admin.jsp"%>
        <div class="container_document">
            <h1 class="titulo-nivel-1" style="color: #555">Agregar Servicio</h1>
            <form action="C_Servicios?action=Agregar" method="POST" enctype="multipart/form-data" class="formulario_agregar_servicio">
                <input type="text" name="nombre_servicio" placeholder="Ingresar servicio" required>
                <textarea name="descripcion" placeholder="Ingresar informacion del servicio" rows="10" cols="50" required></textarea>
                <input type="text" name="costo" placeholder="Ingresar costo" required>
                <label for="file-upload" class="custom-file-upload">
                    <i class="fas fa-image icon"></i> Seleccionar Imagen
                </label>
                <input type="file" name="imagen" id="file-upload" accept="image/x-png,image/gif,image/jpeg">
                <input type="submit" value="Agregar" class="button-mandar">
            </form>
        </div>
    </body>
</html>
