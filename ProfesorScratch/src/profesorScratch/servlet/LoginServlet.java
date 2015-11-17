/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package profesorScratch.servlet;

import Entities.Asignatura;
import Entities.Profesor;
import Entities.Usuario;
import service.LoginJerseyClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.AsignaturaJerseyClient;

/**
 *
 * @author Sobremesa
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        
        HttpSession sesion = request.getSession();
        
        response.setContentType("text/html;charset=UTF-8");
        
        Usuario u = new Usuario();
        LoginJerseyClient service = new LoginJerseyClient();
        AsignaturaJerseyClient asignaturaClient = new AsignaturaJerseyClient();
        
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        u.nombreUsuario = user;
        u.password = pass;
        u.rol = 2;
        
        Profesor p = (Profesor)service.LoginProfesor_JSON(u,Profesor.class);
        
        //System.out.println(p);

        // Comprobar que los datos devueltos del servicio RESTFull son correctos
        if(p != null){
            // Almacenar en la sesion los datos del profesor previamente antes de hacer la redirección.
            List<Asignatura> asignaturas = new ArrayList<Asignatura>();
            asignaturas = (List<Asignatura>)asignaturaClient.getAsignaturasProfesor(p.id);
            sesion.setAttribute("asignaturas", asignaturas);
            
            sesion.setAttribute("usuario", p);
            RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
            dispatcher.forward(request, response);
        }else{
            // @todo - generar alerta en bootstrap mediante js
            request.setAttribute("loginState", "0");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
        
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
