

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Deleção</title>
    </head>
    <body>
        <form action="./graficos" name="formulario" method="POST">
            <h1>Você tem certeza que deseja apagar a ação de ID <%=request.getParameter("id")%>?</h1>
            <input type="hidden" value="apagar" name="acao">
            <input type="hidden" value="<%=request.getParameter("id")%>" name="idAcao">
            <input type="submit" value="Apagar">
            <a href="index.jsp">Voltar</a>
            
        </form>
    </body>
</html>
