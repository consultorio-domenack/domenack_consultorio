<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Template/RedireccionamientoOFF.jsp"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="CSS/Cabecera.css">
        <link rel="stylesheet" href="CSS/Logincs.css">
        <link rel="stylesheet" href="CSS/Miscelanea.css">
        <link rel="stylesheet" href="CSS/EstiloPieDePagina.css">
        <link href="https://fonts.googleapis.com/css?family=Asap&display=swap" rel="stylesheet">
        <script src="JS/JQuery.js"></script>
        <title>Domenack - Inciar Sesion</title>
    </head>
    <body>
        <%@include file="Template/Cabecera.jsp"%>
        <div class="container_document">
            <div class="form">
                <ul class="tab-group">
                    <li class="tab active"><a href="#login">Iniciar Sesion</a></li>
                    <li class="tab"><a href="#signup">Registrarse</a></li>
                </ul>
                <div class="tab-content">
                    <div id="login">   

                        <%                            String error = request.getParameter("Ingreso");
                            if (error != null) {
                        %>
                        <h1 style="color: crimson">DNI o clave de usuario incorrecto</h1>
                        <%
                        } else {
                        %>
                        <h1>¡Bienvenido!</h1>
                        <%
                            }
                        %>
                        <form action="C_Login?action=Iniciar_Sesion" method="post">
                            <div class="field-wrap">
                                <label class="label_login">
                                    DNI<span class="req">*</span>
                                </label>
                                <input type="number" required autocomplete="off" min="0" maxlength="8" class="limite_numero" name="dni"/>
                            </div>
                            <div class="field-wrap">
                                <label class="label_login">
                                    Contraseña<span class="req">*</span>
                                </label>
                                <input type="password" required autocomplete="off" name="clave"/>
                            </div>
                            <div class="input_container" id="cbx_container">
                                <label for="cbx_recordar" class="label-cbx a_login">
                                    <input id="cbx_recordar" type="checkbox" class="invisible" name="recordar_contraseña">
                                    <div class="checkbox">
                                        <svg width="22px" height="22px" viewBox="0 0 20 20">
                                        <path
                                            d="M3,1 L17,1 L17,1 C18.1045695,1 19,1.8954305 19,3 L19,17 L19,17 C19,18.1045695 18.1045695,19 17,19 L3,19 L3,19 C1.8954305,19 1,18.1045695 1,17 L1,3 L1,3 C1,1.8954305 1.8954305,1 3,1 Z">
                                        </path>
                                        <polyline points="4 11 8 15 16 6"></polyline>
                                        </svg>
                                    </div>
                                    <span>Recordar Contraseña</span>
                                </label>
                            </div>
                            <button class="button button-block"/>Ingresar</button>
                            <div><p class="forgot"><a href="#" class="a_login">¿Olvidaste tu contraseña?</a></p></div>
                        </form>
                    </div>
                    <div id="signup">   
                        <h1>Registrate de forma gratuita</h1>
                        <form action="C_Login?action=Registrarse" method="post">
                            <div class="field-wrap">
                                <label class="label_login">
                                    DNI<span class="req">*</span>
                                </label>
                                <input type="number" required autocomplete="off" min="0" maxlength="8" class="limite_numero" name="dni"/>
                            </div>
                            <div class="field-wrap">
                                <label class="label_login">
                                    Nombre<span class="req">*</span>
                                </label>
                                <input type="text" required autocomplete="off" name="nombre_registro"/>
                            </div>
                            <div class="field-wrap">
                                <label class="label_login">
                                    Apellido Paterno<span class="req">*</span>
                                </label>
                                <input type="text" required autocomplete="off" name="apellido_paterno"/>
                            </div>
                            <div class="field-wrap">
                                <label class="label_login">
                                    Apellido Materno<span class="req">*</span>
                                </label>
                                <input type="text" required autocomplete="off" name="apellido_materno"/>
                            </div>
                            <div class="field-wrap">
                                <label class="label_login">
                                    Correo<span class="req">*</span>
                                </label>
                                <input type="email"required autocomplete="off" name="correo_registro"/>
                            </div>
                            <div class="field-wrap">
                                <label class="label_login">
                                    Contraseña<span class="req">*</span>
                                </label>
                                <input type="password"required autocomplete="off" name="clave"/>
                            </div>
                            <div class="field-wrap">
                                <p class="fecha_nacimiento">
                                    Fecha de nacimiento:
                                </p>
                            </div>
                            <div class="nacimiento">
                                <div class="field-wrap">
                                    <label class="label_login">
                                        Dia<span class="req">*</span>
                                    </label>
                                    <input type="number" min="1" max="31" maxlength="2" required autocomplete="off" name="fecha_nacimiento_dia" class="limite_numero"/>
                                </div>
                                <div class="field-wrap">
                                    <label class="label_login">
                                        Mes<span class="req">*</span>
                                    </label>
                                    <input type="number" min="1" max="12" maxlength="2" required autocomplete="off" name="fecha_nacimiento_mes" class="limite_numero"/>
                                </div>
                                <div class="field-wrap">
                                    <label class="label_login">
                                        Año<span class="req">*</span>
                                    </label>
                                    <input type="number" min="1960" max="2005" maxlength="4" required autocomplete="off" name="fecha_nacimiento_anho" class="limite_numero"/>
                                </div>
                            </div>

                            <button type="submit" class="button button-block"/>Registrarse</button>
                        </form>
                    </div>
                </div><!-- tab-content -->
            </div> <!-- /form -->
        </div>
        <jsp:include page="Template/PiePagina.jsp" flush="true"/>
        <script src="JS/Login.js"></script>
    </body>
</html>