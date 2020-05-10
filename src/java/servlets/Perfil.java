/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fabia
 */
public class Perfil extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession mySession = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            if(mySession.getAttribute("username")==null){
                response.sendRedirect("index.jsp");
            }
            
            
            String yo = (String) mySession.getAttribute("username");
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
                /* TODO output your page here. You may use following sample code. */
                Connection con = DriverManager.getConnection(
                        "jdbc:derby://localhost:1527/MyDatabase",
                        "root",
                        "root"); 
                //System.out.println(con);
                //System.out.println("xD");
                Statement query = con.createStatement();
            ResultSet rs;
            
            ActiveMQMensajes messageHandler = new ActiveMQMensajes();
            
            if(request.getParameter("mensaje")!= null && request.getParameter("mensaje")!=""){
                        
                rs = query.executeQuery("SELECT * FROM seguidores WHERE seguido='"+yo+"'");
            
                //out.print(rs.toString());
                Mensaje m = new Mensaje(yo,request.getParameter("mensaje"));
                
                while(rs.next()){
                    messageHandler.mandaMensaje(m,rs.getString("seguidor"));
                }
            }
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Perfil</title>"); 
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">\n" +
"<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n" +
"            <a class=\"navbar-brand\" href=\"#\">Chirruper</a>\n" +
"            <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
"              <span class=\"navbar-toggler-icon\"></span>\n" +
"            </button>\n" +
"            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
"              <ul class=\"navbar-nav\">\n" +
"                <li class=\"nav-item active\">\n" +
"                  <a class=\"nav-link\" href=\"buscar.jsp\">Buscar personas<span class=\"sr-only\">(current)</span></a>\n" +
"                </li>               \n" +
"                <li class=\"nav-item active\">\n" +
"                  <a class=\"nav-link\" href=\"salir.jsp\">Cerrar Sesion<span class=\"sr-only\">(current)</span></a>\n" +
"                </li>               \n" +
"              </ul>\n" +
"            </div>\n" +
"          </nav>");
            
            out.print("<div class='row '>");
            
            
            
            
            
            
            
            //usuarios que lo siguen
            out.print("<div class='col-md-3 border-right'>");
            out.print("<h4>Usuarios que te siguen</h4>");
            
            rs = query.executeQuery("SELECT * FROM seguidores WHERE seguido='"+yo+"'");
            
            //out.print(rs.toString());
            
            while(rs.next()){
                
                out.print(rs.getString("seguidor"));
                out.print("<br/>");
            }
            
            
            
            
            out.print("</div>");
            
            //contenido del centro
            out.print("<div class='col-md-6'>");
            
            out.print("<div class=' col-12'>");
                out.print("<form method='POST' class='container pt-1 pb-1'>"
                        + "<h4>Madar un mensaje</h4>"
                        + "<input type='text' name='mensaje'  style='width:70%;' value='' placeholder='escribe el mensaje que quieres compartir' required/>"
                        + "<input type='submit' class='btn btn-success ml-3' value='Mandar mensaje' />"
                        + "");
                
                
            //aqui van todos los mensajes que lea de la cola
            
            
            String recursoAIncluir = "/ObtenMensajes";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(recursoAIncluir);
            dispatcher.include(request, response);
            
            
            
            
            
            
            
            
            
            
            
                
            out.print("</div>");
            
            
            out.print("</div>");
            
            
            
            //usuarios a los que sigue
            out.print("<div class='col-md-3 border-left'>");
            out.print("<h4>Usuarios a los que sigues</h4>");
            
            rs = query.executeQuery("SELECT * FROM seguidores WHERE seguidor='"+yo+"'");
            
            //out.print(rs.toString());
            
            while(rs.next()){
                
                out.print(rs.getString("seguido")+"  (<a class='' href='buscar.jsp?borrar="+rs.getString("seguido")+"' >Dejar de seguir</a>)");
                out.print("<br/>");
            }
            
            
            out.print("</div>");
            
            
            
            out.print("</div>");
            
            //out.println("<h1>Servlet Perfil at " + request.getContextPath()+" "+mySession.getAttribute("username") + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Perfil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Perfil.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Perfil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Perfil.class.getName()).log(Level.SEVERE, null, ex);
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
