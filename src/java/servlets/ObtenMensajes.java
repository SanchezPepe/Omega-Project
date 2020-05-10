/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.management.MalformedObjectNameException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fabia
 */
public class ObtenMensajes extends HttpServlet {

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
            throws ServletException, IOException, InterruptedException, MalformedObjectNameException, JMSException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession mySession = request.getSession();
        if(mySession.getAttribute("username")==null){
            response.sendRedirect("index.jsp");
        }
        
        
        PrintWriter out = response.getWriter();
            
        String yo = (String) mySession.getAttribute("username");
        
        //out.print("<div class=\"row\"");
        
        out.print("<div class='mt-5'>"
                + "<h3>Mensajes actuales:</h3>");
            while(true){
                Mensaje m = ActiveMQMensajes.recibeMensaje(yo);
                if(m!=null){
                    
                    out.print("<div class=\"col-sm-6\">\n" +
"              <div class=\"card\">\n" +
"                <div class=\"card-body\">\n" +
"                  <h5 class=\"card-title\">"+m.getQuienManda()+"</h5>\n" +
"                  <p class=\"card-text\">"+m.getTexto()+"</p>\n" +
"\n" +
"                </div>\n" +
"              </div>\n" +
"            </div>");
     
                }else{
                   break; 
                }
            }
            out.print("</div>");
            
//        ArrayList<Mensaje> alm = ActiveMQMensajes.recibeTODO(yo);
//        out.print("antes<br/>");
//        out.print(alm.toString());
//        out.print("antes<br/>");
//        for (int i = 0; i < alm.size(); i++) {
//            out.print(alm.get(i).getTexto()+": "+alm.get(i).getQuienManda()+"<br/>");
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
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(ObtenMensajes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedObjectNameException ex) {
            Logger.getLogger(ObtenMensajes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(ObtenMensajes.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (InterruptedException ex) {
            Logger.getLogger(ObtenMensajes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedObjectNameException ex) {
            Logger.getLogger(ObtenMensajes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(ObtenMensajes.class.getName()).log(Level.SEVERE, null, ex);
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
