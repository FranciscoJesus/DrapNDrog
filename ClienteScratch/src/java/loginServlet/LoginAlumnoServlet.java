/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package loginServlet;


import Entities.Alumno;
import Entities.Usuario;
import service.LoginJerseyClient;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
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
        
       LoginJerseyClient servicio= new LoginJerseyClient();
         
        HttpSession sesion = request.getSession();
        
        
        String nombre = request.getParameter("usuario");
        String password = request.getParameter("password");
        int rol =1;
        
        Usuario user = new Usuario();
        user.usuario=nombre;
        user.password= password;
        user.rol=rol;
        
        Alumno respuestaServidor = (Alumno)servicio.Login_JSON(user, Alumno.class);
        if (respuestaServidor==null) {
            out.println("El usuario o contraseña introducidos son incorrectos");
        } else {
            Alumno alu= null ;
            
            HttpSession miSesion = request.getSession(true);
            Object currentUser = miSesion.getAttribute("usuario");

            if (currentUser != null) {
                miSesion.invalidate();
            }

            miSesion.setAttribute("Alumno", alu);
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
