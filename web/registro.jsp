<%-- 
    Document   : index
    Created on : 9/05/2020, 08:45:10 PM
    Author     : fabia
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chirruper</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </head>
    <body >
        <%
         String username, password;
         username= request.getParameter("username");
         password = request.getParameter("password");
         if(username!=null && password!=null){
            //verificar que está en la base de datos
            //out.print("<script>alert('se quiso registrar')</script>");
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
                /* TODO output your page here. You may use following sample code. */
                Connection con = DriverManager.getConnection(
                        "jdbc:derby://localhost:1527/MyDatabase",
                        "root",
                        "root"); 
                //System.out.println(con);
                //System.out.println("xD");
                Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("SELECT username FROM usuarios WHERE username='"+ username +"'");
            
            
            if(!rs.next()){
                //se registra y guarda en la base de de datos
                //out.print("<script>alert('**Se procederia al registro***')</script>");
                query = con.createStatement();
                try{
                    query.execute("INSERT INTO USUARIOS VALUES('"+username+"','"+password+"')");
                    out.print("<script>alert('***Registro exitoso***')</script>");
                }catch(Exception e){
                    out.print("<script>alert('***Error al registrar, intente nuevamente***')</script>");
                }
 
            }else{
                out.print("<script>alert('***Nombre de usuario no disponible*** Intente con otro')</script>");
            }
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
                  <a class="nav-link" href="index.jsp">Iniciar Sesion<span class="sr-only">(current)</span></a>
                </li>               
              </ul>
            </div>
          </nav>
        <div class="container mt-5 row mx-auto">

            <form method="POST" class="col-12 col-md-6 border shadow p-3 mb-5 bg-white rounded  ">
                <h1 class="center ">Registro</h1>
                <div class="form-group">
                  <input type="text" name = "username" class="form-control" id="username" placeholder="Escribe tu nombre de usuario" aria-describedby="username" required>
          
                </div>
                <div class="form-group">

                  <input type="password" name="password" class="form-control" placeholder="Escribe tu contraseña" id="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Continuar</button>
              </form>
            
        </div>
        
        
    </body>
</html>
