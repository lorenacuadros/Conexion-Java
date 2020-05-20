<%-- 
    Document   : Consultar
    Created on : 06-may-2020, 7:44:11
    Author     : Familia
--%>

<%@page import="java.util.List"%>
<%@page import="crud.model.Notificacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="index.jsp">Crud servlet</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="NotificacionServlet?action=show">Consultar <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Registrar.jsp">Registrar</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav>
        
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Mensaje</th>
                    <th scope="col">Fecha Inicio</th>
                    <th scope="col">Fecha Fin</th>
                    <th scope="col">Opcion editar</th>
                    <th scope="col">Opcion eliminar</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Notificacion> lista = (List<Notificacion>) request.getAttribute("list");
                    for(Notificacion notifi: lista) {
                %>
                <tr>
                    <td><%= notifi.getId() %></td>
                    <td><%= notifi.getMensaje() %></td>
                    <td><%= notifi.getInicio() %></td>
                    <td><%= notifi.getFin() %></td>
                    <td><a href="NotificacionServlet?action=showUpdate&id=<%= notifi.getId() %>">Actualizar</a></td>
                    <td><a href="NotificacionServlet?action=delete&id=<%= notifi.getId() %>">Eliminar</a></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>
