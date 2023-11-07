<%-- 
    Document   : index
    Created on : 27/10/2018, 18:47:46
    Author     : v3-14
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel="css/newcss.css">-->
        <title>JSP Page</title>
        <style>
           
input[type=text], select{
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}
input[type=number]{
    width: 50%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}
    input[type=date]{
    width: 30%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;    
    }

input[type=submit] {
    width: 100%;
    background-color: #3333ff;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #ff3333;
}

div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
}
        </style>
    </head>
    <body>
        <div>
            
        <h1>Trabalho de Gráficos em Java</h1>
        
        <form action="./graficos" name="formulario" method="POST">
            <label for="operacao">Operação</label>
            <select id="operacao" name="operacao">
                <option value="1">Comprar</option>
                <option value="0">Vender</option>
            </select>
            <label for="nome">Nome</label> 
                <br> <input type="text" name="nome" id="nome" required><br>
            <label for="valor">Valor</label>
                <br> <input type="number" step="0.01" name="valor" id="valor" min="0" required><br>
            <label for="qtd">Quantidade</label> 
                <br> <input type="number" step="1" name="qtd" id="qtd" min="0" required><br>
            <label for="taxaCorretagem">Taxa de Corretagem</label> 
                <br> <input type="number" step="0.01" name="taxaCorretagem" min="0" id="taxaCorretagem" required><br>
            <label for="outrasTx">Outras Taxas</label> 
                <br> <input type="number" step="0.01" name="outrasTx"min="0" id="outrasTx"><br>
            <label for="data">Data</label> 
                <br><input type="date" name="data" id="data" required><br>
                    <input type="hidden" name="acao" value="cadastrar"> 
                    <input type="submit" value="Enviar" name="cadastrar" id="cadastrar">
        </div>
        </form>
        </body>
</html>
