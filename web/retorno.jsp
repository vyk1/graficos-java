<%-- 
    Document   : retorno
    Created on : 08/11/2018, 16:24:47
    Author     : v3-14
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gráficos</title>
    </head>
    <body>
         <%
            String mensagem=request.getAttribute("mensagem").toString();
            out.print(mensagem);
            %>
            <a href="index.jsp">Clique aqui para voltar</a>
    </body>
</html>
