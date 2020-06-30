<%-- 
    Document   : index
    Created on : Oct 17, 2019, 11:03:27 AM
    Author     : umansilla
--%>

<%@page import="java.util.List"%>
<%@page import="service.AAADEVCRUD.Entity.Facturacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
    <%@include file="templates/header.jsp" %>
    <body>
        <%@include file="templates/navbar.jsp" %>
        <%@include file="templates/jumbotron.jsp" %>
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
                font-size: 2rem;
                box-sizing: border-box;
            }   
        </style>
        <%
            List<Facturacion> facturasVencidas = (List<Facturacion>) request.getAttribute("Usuarios");
            System.out.println("Facturas = " + facturasVencidas.size());
        %>
        <div class="container">
            <button type="button" class="btn btn-primary btn-block" id="launch"> Lanzar campaña </button>
            <button type="button" class="btn btn-success btn-block" id="settings"> Settings </button>
        </div>
        <hr>
        <div class="container-fluid">
            <table id="example" class="table table-striped table-bordered" style="width:100%">
                <thead>
                    <tr>
                        <th>Numero de cuenta</th>
                        <th>Nombre</th>
                        <th>Email</th>
                        <th>Apellido</th>
                        <th>Telefono de casa</th>
                        <th>Telefono móvil</th>
                        <th>Monto</th>
                        <th>Factura</th>
                        <th>Fecha de Vencimiento</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody id="tbody">
                    <%
                        if (!facturasVencidas.isEmpty()) {
                            for (int i = 0; i < facturasVencidas.size(); i++) {%>
                    <tr id="<%= facturasVencidas.get(i).getNumerodecuenta()%>">
                        <td><%= facturasVencidas.get(i).getNumerodecuenta()%></td>
                        <td><%= facturasVencidas.get(i).getNombre()%></td>
                        <td><%= facturasVencidas.get(i).getEmail()%></td>
                        <td><%= facturasVencidas.get(i).getApellido()%></td>
                        <td><%= facturasVencidas.get(i).getTelefonocasa()%></td>
                        <td><%= facturasVencidas.get(i).getTelefonomovil()%></td>
                        <td><%= facturasVencidas.get(i).getMonto()%></td>
                        <td><%= facturasVencidas.get(i).getNumerodefactura()%></td>
                        <td><%= facturasVencidas.get(i).getFechadevencimiento()%></td>
                        <td><span><%= facturasVencidas.get(i).getStatus()%></span> <button type="button" class="btn btn-primary" onclick="changeStatus(this)">change</button></td>
                    </tr>
                    <% }
                        }
                    %>



                </tbody>
                <tfoot>
                    <tr>
                        <th>Numero de cuenta</th>
                        <th>Nombre</th>
                        <th>Email</th>
                        <th>Apellido</th>
                        <th>Telefono de casa</th>
                        <th>Telefono móvil</th>
                        <th>Monto</th>
                        <th>Factura</th>
                        <th>Fecha de Vencimiento</th>
                        <th>Status</th>
                    </tr>
                </tfoot>
            </table>
        </div>                    
        <div id="settings" style="display: none">
            <p id="accountSid">ACf674eb32816d08d783f148299249fffd</p>
            <p id="accountToken">a3302e802a144238b94d5c4992ab775d</p>
            <p id="email">NO</p>
            <p id="SMS">YES</p>
            <p id="Call">YES</p>
            <p id="WFN">AAADEVNVP</p>
            <p id="Version">1</p>
        </div>
        <%@include file="templates/footerScripts.jsp" %>
    </body>
</html>