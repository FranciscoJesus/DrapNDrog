/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginServlet;

import Entities.Alumno;
import Entities.Problema;
import Entities.Usuario;
import service.LoginAluJerseyClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Pulgy
 */
@WebServlet(name = "LoginAlumnoServlet", urlPatterns = {"/LoginAlumnoServlet"})
public class LoginAlumnoServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginUsuarioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginUsuarioServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        
        String nombre = request.getParameter("usuario");
        String password = request.getParameter("password");
        int rol = 1;

        Usuario user = new Usuario();
        user.usuario = nombre;
        user.password = password;
        user.rol = rol;
        
        LoginAluJerseyClient servicio = new LoginAluJerseyClient();
        Alumno alu= (Alumno) servicio.LoginAlumno_JSON(user, Alumno.class);
        
        HttpSession miSesion = request.getSession(true);
        
        if (alu == null) {
           miSesion.setAttribute("Log","error");
           RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
           dispatcher.forward(request, response);
        } else {
            
            Object currentUser = miSesion.getAttribute("alumno");


            if (currentUser != null) {
                miSesion.invalidate();
            }
            
            miSesion.setAttribute("alumno", alu);
            List<Problema> listproblemas=null;
         //   List<Problema>  listproblemas = (List<Problema>) servicio.LoginAlumno_JSON(, null);
            //miSesion.setAttribute("listaProblemas",listproblemas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("listaEnunciados.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
