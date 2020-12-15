<%-- 
    Document   : newjsp
    Created on : 12/11/2018, 16:32:54
    Author     : v3-14
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Acoes.Model.AcoesModel"%>

<!DOCTYPE html>
<html>
    <head>
        
        <style>
            
table {
	font-family: Arial;
    border-collapse: collapse;
    width: 100%;
    align-content: center;
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
    background-color: #3333ff;
    color: white;
}
</style>
<style>#site{
  /* Definimos a largura da p�gina */
  width:100%;
  /* Definirmos a altura da p�gina*/
  height:50%;
  /* Aqui definiremos a posi��o que a p�gina ocupar� na tela, nesse caso estamos definindo que fique centralizado na tela*/
  /*top : posi��o de cima */
  margin-top:0;
  /* left: posi��o de esquerda*/
  margin-left:auto;
  /* bottom : posi��o de baixo */
  margin-bottom:0;
  /* right : posi��o de direita */
  margin-right:auto;
  /* Aqui definiremos a cor de fundo da p�gina */
  background-color:#ffffff;
}
#conteudo-left{
  /*Novamente definimos a largura da div*/
  width:80%;
  /* altura da div */
  height:50%;
  /* O atributo Float � utilizado para fazermos o nosso bloco(div) literalmente flutue e se posicione onde queremos na p�gina, quando escolhemos left, dizemos que esse bloco ir� se posicionar na parte esquerda da p�gina */
  float:left;
  /* Cor de fundo da div */
  background-color:#FFFFFF;
  }
#conteudo-right{
  /*Novamente definimos a largura da div*/
  width:20%;
  /* altura da div */
  height:50%;
  /* Pode parecer meio estranho usarmos o mesmo atributo left para o bloco em que queremos posicionar na direita, mas como sabemos, o CSS � um estilo em cascata, nossa div conte�do definimos a largura de 1000px e na conteudo-left 500px, 
automaticamente ao definirmos o conteudo-right com 500px e � esquerda tamb�m, ele ficou � direita do conteudo-left, pois o m�ximo que a div filha poder� ter � 1000px, sendo 500+500=1000px */
  float:left;
  /* Cor de fundo da div */
  background-color:blue;
  }

#conteudo{
  /*Novamente definimos a largura da div*/
  width:1000px;
  /* altura da div */
  height:500px;
  /* Cor de fundo da div */
  background-color:red;  
} 
</style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem</title>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <script type="text/javascript">

google.charts.load('current', {'packages':['corechart']});
google.charts.load('current', {'packages':['bar']});
  
    function contagem(array, valor){
            var contagem=0;
            for(var g=0;g<array.length;g++){
                if(array[g]==valor){
                    contagem++;
                }
            }
                return contagem;
                }
       
    function drawQtdActPerYearChart(){

            var ar=new Array();

            $("#table .dataPM").each(function (){
            var dtPM=($(this).html());
            let filtro=dtPM.substring(0,4);
            ar.push(parseInt(filtro));
            });

            var uniqueNames = [];
            $.each(ar, function(i, el){
            if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
            });


            var arF=new Array();
            for(var x=0;x<uniqueNames.length;x++){
                arF.push(contagem(ar,uniqueNames[x]));
            }


      var p=new Array();
      p[0]=["Ano", "Quantidade"];
      
      let i=0;
          for(var v=0; v<uniqueNames.length;v++){
                p[i+1]=[uniqueNames[v].toString(), (arF[v])] ;
                  i++;
          }

        var data = google.visualization.arrayToDataTable(p,false);

        var options = {
          chart: {
            title: 'Balan�o de A��es por ano',
            subtitle: 'Vendas e Compras'
          },
          bars: 'horizontal' // Required for Material Bar Charts.
        };

        var chart = new google.charts.Bar(document.getElementById('n3'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
      
  }
        function drawChart() {
            array = new Array();
            arraNovo = new Array();

            
         $("#table .acaoPM").each(function(){
           var acaoPM=($(this).html());
           array.push(acaoPM);
       });
                       
        $("#table .dataPM").each(function(){
            var dataPM=($(this).html());
            arraNovo.push(dataPM);    
        });            
         
        eustaquio = new Array();
            
        let i=0;
        eustaquio[0]=['Data', 'Valor'];
          for(var v=0; v<array.length;v++){
                eustaquio[i+1]=[arraNovo[v], parseFloat(array[v])] ;
                  i++;
          }

        var data = google.visualization.arrayToDataTable(eustaquio, false);
 
        var options = {
          title: 'Balan�o de a��es',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
              function drawPieChart(){
                var array2=new Array();
                var compra=0, venda=0, i;
            
            $("#table .operacaoPM").each(function(){
                var op=($(this).html());
                array2.push(op);
            });
                for(i=0;i<array2.length;i++){
                 
                    if(array2[i]=="Compra"){
                        compra++;
                    }else{
                        venda++;
                    }
                }
        
            var data = google.visualization.arrayToDataTable([
          ['Opera��o', 'Quantidade'],
          ['Compra', compra],
          ['Venda', venda]
        ]);

        var options = {
          title: 'Propor��o de Compra e Venda'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);

        }

    </script>
    </head>
    <body>
        <!--<div id="site">-->
 


            <div id="conteudo-left">
                <table id="table">
            <thead>
                <tr>
                    
                    <th>ID</th>
                    <th>Operacao</th>
                    <th>Nome</th>
                    <th>Qtd</th>
                    <th>Pre�o</th>
                    <th>Saldo</th>
                    <th>Pre�o M�dio</th>
                    <th>Data</th>
                    <th>A��es</th>
                </tr>
            </thead>
            <tbody>
                <%ArrayList<AcoesModel> lista = (ArrayList<AcoesModel>) request.getAttribute("lista");

                if(lista.isEmpty()){
                       out.println("<h2>N�o h� a��es cadastradas</h2>");
                   }else{
                    for (AcoesModel acoesModel : lista){
//                        out.println(lista.indexOf(acoesModel));
                        
                        String operacaoString;
                        if(acoesModel.getOperacao()==1){
                           operacaoString="Compra";
                        }else{
                        operacaoString="Venda";
                        }
                %>
                <tr>
                    <td><%=acoesModel.getId() %></td>
                    <td class="operacaoPM"><%=operacaoString%></td>
                    <td><%=acoesModel.getNome()%></td>
                    <td><%=acoesModel.getQtd()%></td>
                    <td><%=acoesModel.getTotal()%></td>
                    <td><%=acoesModel.getSaldo()%></td>
                    <td class="acaoPM"><%=acoesModel.getPM()%></td>
                    <td class="dataPM"><%=acoesModel.getData()%></td>
                        
                    <%
                        
                        if(lista.size()-1==lista.indexOf(acoesModel)){
                        %>
                        <td><a href="apagar.jsp?id=<%=acoesModel.getId()%>">Apagar</a></td>
                        <%}
                        %>

                    
                </tr>
                    <%} 
                }%>
            </tbody>
        </table>
            </div>
            <div id="conteudo-right">                
                <div id="curve_chart" style="width: 275%; height: 100%"></div>
                <div id="piechart" style="width: 275%; height: 100%"></div>
                <div id="n3" style="width: 275%; height: 100%"></div>
            </div>


            
            <a href="index.jsp">Voltar para index</a>
            
            <input type="submit" value="Gerar Gr�ficos - Pre�o M�dio" name="gerarGraficos" id="gerarGraficos" onclick="drawChart()"/>
            <input type="submit" value="Gerar Gr�ficos - Compras (piechart)" name="gerarGraficos1" id="gerarGraficos1" onclick="drawPieChart()"/>
            <input type="submit" value="Gerar Gr�ficos - Quantidade de a��es vendidas por ano" name="gerarGraficos2" id="gerarGraficos2" onclick="drawQtdActPerYearChart()"/>
            <!--</div>-->

    </body>
    
</html>
