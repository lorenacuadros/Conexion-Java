/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.*;

import java.sql.SQLException;
import java.util.List;

import crud.dao.NotifDAO;
import crud.model.Notificacion;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "NotificacionServlet", urlPatterns = {"/NotificacionServlet"})
public class NotificacionServlet extends HttpServlet {
    public NotifDAO notifDAO;
    
    public NotificacionServlet() {
        notifDAO = new NotifDAO();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet NotificacionServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet NotificacionServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String action = request.getParameter("action");
        System.out.println(action);
        
        try {
            switch (action) {
                case "register":
                    register(request, response);
                    break;
                case "show":
                    show(request, response);
                    break;
                case "delete":
                    delete(request, response);
                    break;
                case "showUpdate":
                    showUpdate(request, response);
                    break;
                case "update":
                    update(request, response);
                    break;
                default:
                    break;
            }
        }
        catch (SQLException e) {
            e.getStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        doGet(request, response);
    }
    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Consultar.jsp");
        List<Notificacion> listNotifi = notifDAO.listNotifi();
        System.out.println(listNotifi);
        request.setAttribute("list", listNotifi);
        dispatcher.forward(request, response);
        
    }
    
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        LocalDateTime inicio = LocalDateTime.parse(request.getParameter("inicio"), formato);
        LocalDateTime fin = LocalDateTime.parse(request.getParameter("fin"), formato);
        
        try {
            Notificacion notifi = new Notificacion(0, request.getParameter("mensaje"), inicio, fin, 1);
            boolean value = notifDAO.insert(notifi);
            String mensaje = "";
            if(value) {
                mensaje = "Notificacion creada con exito";
            }
            else {
                mensaje = "Opss.. Hubo un problema al registrar";
            }
            request.setAttribute("registroEstado", mensaje);
        }
        
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("id");
        Notificacion notificacion = notifDAO.getById(Integer.parseInt(id));
        
        if (notificacion == null) {
            response.getWriter().append("no existe la notificacion, es nula");
        }
        
        else {
            try {
                notifDAO.delete(notificacion);
            }
            
            catch (Exception ex) {
                response.getWriter().append(ex.toString());
                return;
            }
        }
        
        response.sendRedirect("NotificacionServlet?action=show");
    }

    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void showUpdate (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
        Notificacion notificacion = notifDAO.getById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("notificacion", notificacion);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Editar.jsp");
        dispatcher.forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        LocalDateTime inicio = LocalDateTime.parse(request.getParameter("inicio"), formato);
        LocalDateTime fin = LocalDateTime.parse(request.getParameter("fin"), formato);
        int id = Integer.parseInt(request.getParameter("id"));
        
        try {
            Notificacion notificacion = new Notificacion(id, request.getParameter("mensaje"), inicio, fin, 1);
            boolean value = notifDAO.update(notificacion);
            String mensaje = "";
            if(value) {
                mensaje = "Notificacion actualizada con exito";
            }
            else {
                mensaje = "Opss.. Hubo un problema al actualizar";
            }
            request.setAttribute("registroEstado", mensaje);
        }
        
        catch (Exception ex) {
            ex.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

}
