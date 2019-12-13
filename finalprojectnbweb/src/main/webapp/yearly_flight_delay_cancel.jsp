<%-- 
    Document   : daily_delay
    Created on : Dec 12, 2019, 10:53:56 PM
    Author     : kinnar
--%>

<%@page import="edu.northeastern.kinnarkansara.finalprojectnbweb.model.DailyDelayTuple"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%//@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Daily Flights Delay</title>

    <!--header-->
    <jsp:include page="header.jsp" />
    
    <style>        
    
    </style>
    <script type="text/javascript">
        google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {

      var data = new google.visualization.DataTable();
      data.addColumn('number', 'X');
      data.addColumn('number', 'Flights');
//      data.addColumn({type: 'string', role: 'tooltip'});
      var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
      data.addRows([
          <% List<DailyDelayTuple> dailyDelayList = (ArrayList)request.getAttribute("dailyDelayList");
            for(int i = 0; i < dailyDelayList.size(); i++){
                DailyDelayTuple obj = (DailyDelayTuple)dailyDelayList.get(i);
//                String date = obj.getYyyymmdd().substring(0,4) + "," + (Integer.parseInt(obj.getYyyymmdd().substring(4,6))-1) + "," + obj.getYyyymmdd().substring(6,8);
          %>                      
                [<%=obj.getYyyymmdd()%>, <%=obj.getDelayedFlightsCount()%>]<%if(i<dailyDelayList.size()-1){%>,<%}%>            
            <%
            }
            %>
        
      ]);

      var options = {
          title: 'Total Delayed flights from 1999 - 2008',
        hAxis: {
          title: 'Year'
        },
        vAxis: {
          title: 'No of Delayed Flights'
        },
//        explorer: {axis: 'horizontal', keepInBounds: true},         
        height: 500
      };

      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

      chart.draw(data, options);
    
   }
   
   
    
    </script>

</head>

<body>

    <div class="wrapper">
        
        <!--sidebar-->
        <jsp:include page="sidebar.jsp?current=yearly_flight_delay" />
        
        <!-- Page Content Holder -->
        <div id="content">

            <!--top-->
            <jsp:include page="top.jsp" />
            
            <!--content-->
            <div id="chart_div"></div>
            
<!--            <div class="line"></div>
            
            <div id="chart_div2"></div>-->
            <!--footer-->
            <jsp:include page="footer.jsp" />
        </div>
    </div>

    <!--footer_scripts-->
    <jsp:include page="footer_scripts.jsp" />
    
    <script type="text/javascript">
        $(document).ready(function () {
            $('#sidebarCollapse').on('click', function () {
                $('#sidebar').toggleClass('active');
                $(this).toggleClass('active');
            });
        });
    </script>
</body>

</html>