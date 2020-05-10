<%-- 
    Document   : index
    Created on : 9/05/2020, 08:45:10 PM
    Author     : fabia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chirruper</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </head>
    <body>
        
        <%
            HttpSession mySession = request.getSession();
            if(mySession.getAttribute("username")!=null){
                //redirecciona al perfil
                //response.sendRedirect("Perfil");
            }else{
                response.sendRedirect("index.jsp");
            }
            


            
            
        
        %>
        
        
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Chirruper</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
              <ul class="navbar-nav">
                <li class="nav-item active">
                  <a class="nav-link" href="Perfil">Perfil<span class="sr-only">(current)</span></a>
                </li>  
                <li class="nav-item active">
                  <a class="nav-link" href="salir.jsp">Cerrar Sesion<span class="sr-only">(current)</span></a>
                </li>  
              </ul>
            </div>
          </nav>
        
        
        
        <div class="container">
            <div class="mt-5">
                <form>
                    <input type="text" name="usuario" style="width:500px;" placeholder="Escribe el nombre de un usuario">
                    <input type="submit" value="Aceptar">
                </form>
            </div>
        
        
            <div class="mt-5">
        <%
            
            
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
            
            if(request.getParameter("agregar")!=null){
                ResultSet rs = query.executeQuery("SELECT * FROM seguidores WHERE seguidor='"+  yo +"' AND seguido='"+request.getParameter("agregar")+"'");
                if(rs.next()){
                    out.print("<script>alert('***Ya sigues a "+request.getParameter("agregar")+"***')</script>");
                }else{
                
                query.execute("INSERT INTO SEGUIDORES VALUES('"+yo+"','"+request.getParameter("agregar")+"')");
                out.print("<script>alert('***Ahora sigues a "+request.getParameter("agregar")+"***')</script>");
                }
                
                //response.sendRedirect("buscar.jsp");
            }
            
            
            if(request.getParameter("borrar")!=null){
                
                
                query.execute("DELETE FROM SEGUIDORES WHERE seguidor='"+yo+"' AND seguido = '"+request.getParameter("borrar")+"'");
                //out.print("<script>alert('***Ahora sigues a "+request.getParameter("agregar")+"***')</script>");
                
                
                response.sendRedirect("Perfil");
            }
            
            
            
            
            if(request.getParameter("usuario")!=null && request.getParameter("usuario")!=""){
                
                
                
                String strQuery = "SELECT u.* FROM usuarios u WHERE u.username LIKE '%"+request.getParameter("usuario")+"%' AND u.username !='"+yo+"'";
                //AND u.username !='"+ username +"' AND u.username NOT IN (SELECT s.seguido FROM seguidores s WHERE s.seguidor = '"+ username +"')
                ResultSet rs = query.executeQuery(strQuery);
                
                out.print("<h4>Resultados de su busqueda</h4><br/>");
                
                while(rs.next()){
                    out.print("<a class='' href='buscar.jsp?agregar="+rs.getString("username")+"' >Seguir</a> --> "+rs.getString("username"));
                    out.print("<br/>");
                }
            }
        %>
        
        </div>
        
        </div>
        
    </body>
</html>
