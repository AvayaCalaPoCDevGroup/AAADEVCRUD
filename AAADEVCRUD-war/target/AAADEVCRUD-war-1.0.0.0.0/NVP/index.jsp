<%-- 
    Document   : index
    Created on : Sep 23, 2019, 3:52:05 PM
    Author     : umansilla
--%>

<%@page import="service.AAADEVCRUD.NVP.Bean.Notificacion"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="templates/header.jsp" %>
    <%
        List<Notificacion> notificaciones = (List<Notificacion>) request.getAttribute("Notificaciones");
        System.out.println(notificaciones.size());
    %>
    <style>
        .swal2-popup {
            display: none;
            position: relative;
            flex-direction: column;
            justify-content: center;
            width: 66em !important;
            max-width: 100%;
            padding: 4.25em !important;;
            border-radius: 3.3125em !important;;
            background: #fff;
            font-family: inherit;
            font-size: 1rem;
            box-sizing: border-box;
        }     
    </style>
    <body>
        <%@include file="templates/navbar.jsp" %>
        <%@include file="templates/jumbotron.jsp" %>

        <div class="container">
            <div class="row">
                <div class="col">
                    <button type="button" class="btn btn-primary btn-block" style="color: white; font-weight: bold" id="crearNotificacion">Crear Notificacion</button>
                </div>
                <div class="col">
                    <button type="button" class="btn btn-info btn-block" style="color: white; font-weight: bold" id="lanzarCampana">Lanzar Campaña</button>
                </div>
            </div>
        </div>
        <hr>

        <div class="container-fluid">
            <table id="example" class="table table-striped table-bordered" style="width:100%">
                <thead>
                    <tr>
                        <th>Numero de Cuenta</th>
                        <th>Nombre</th>
                        <th>eMail</th>
                        <th>Apellido</th>
                        <th>Monto</th>
                        <th>Fecha de Vencimiento</th>
                        <th>Telefono de Casa</th>
                        <th>Telefono Movil</th>
                        <th>Número de Factura</th>
                        <th>Status Actual</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody id="tBody">
                    <%
                        for (int i = 0; i < notificaciones.size(); i++) {%>

                    <tr id="<%= notificaciones.get(i).getNumerodecuenta() + "," + notificaciones.get(i).getNombre()%>">
                        <td><%= notificaciones.get(i).getNumerodecuenta()%></td>
                        <td><%= notificaciones.get(i).getNombre()%></td>
                        <td><%= notificaciones.get(i).getEmail()%></td>
                        <td><%= notificaciones.get(i).getApellido()%></td>
                        <td><%= notificaciones.get(i).getMonto()%></td>
                        <td><%= notificaciones.get(i).getFechadevencimiento()%></td>
                        <td><%= notificaciones.get(i).getTelefonocasa()%></td>
                        <td><%= notificaciones.get(i).getTelefonomovil()%></td>
                        <td><%= notificaciones.get(i).getNumerodefactura()%></td>
                        <td><%= notificaciones.get(i).getStatus()%></td>
                        <td>
                            <div class="row">
                                <div class="col">
                                    <button type="button" class="btn btn-warning btn-block" style="color: black; font-weight: bold" onclick="editar(this)">EDITAR</button>
                                </div>
                                <div class="col">
                                    <button type="button" class="btn btn-danger btn-block" style="color: white; font-weight: bold" onclick="borrar(this)">BORRAR</button>
                                </div>
                            </div>
                        </td>
                    </tr>

                    <% }%>
                </tbody>
                <tfoot>
                    <tr>
                        <th>Número de Cuenta</th>
                        <th>Nombre</th>
                        <th>eMail</th>
                        <th>Apellido</th>
                        <th>Monto</th>
                        <th>Fecha de Vencimiento</th>
                        <th>Telefono de Casa</th>
                        <th>Telefono Movil</th>
                        <th>Número de Factura</th>
                        <th>Status Actual</th>
                        <th>Acciones</th>
                    </tr>
                </tfoot>
            </table>

        </div>
        <%@include file="templates/importScripts.jsp" %>
    </body>
</html>
