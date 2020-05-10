<%-- 
    Document   : salir
    Created on : 9/05/2020, 10:26:05 PM
    Author     : fabia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession mySession = request.getSession();
    if(mySession.getAttribute("username")!=null){
        //redirecciona al perfil
        mySession.setAttribute("username", null);
        response.sendRedirect("index.jsp");
    }else{
        response.sendRedirect("index.jsp");
    }
%>
