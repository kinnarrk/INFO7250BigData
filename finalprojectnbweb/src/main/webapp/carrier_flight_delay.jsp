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
            google.charts.load('current', {packages: ['corechart', 'bar']});
            google.charts.setOnLoadCallback(drawBasic2);

            function drawBasic2() {

                var data2 = new google.visualization.DataTable();
                data2.addColumn('string', 'Carriers');
                data2.addColumn('number', 'Total counts');
                data2.addColumn('number', 'Delayed counts');
    //      data2.addColumn({type: 'string', role: 'tooltip'});
    //      var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
                data2.addRows([
            <% List<DailyDelayTuple> dailyDelayList = (ArrayList)request.getAttribute("dailyDelayList");
                  for (int i = 0; i < dailyDelayList.size(); i++) {
                      DailyDelayTuple obj = (DailyDelayTuple) dailyDelayList.get(i);
//                String date = obj.getYyyymmdd().substring(0,4) + "," + (Integer.parseInt(obj.getYyyymmdd().substring(4,6))-1) + "," + obj.getYyyymmdd().substr    ing(6,8);
    %>
            ["<%=obj.getYyyymmdd()%>", <%=obj.getFlightsCount()%>, <%=obj.getDelayedFlightsCount()%>]<%if (i < dailyDelayList.size() - 1) {%>,<%}%>
            <%
                    }
            %>
                ]);

                var options2 = {
                    title: 'Delayed vs Total Count of Flights by Carriers',
                    axes: {
                        x: {
                            0: {side: 'top'}
                        }
                    },
                    hAxis: {
                        title: 'Carrier Name'
                    },
                    vAxis: {
                        title: 'Count of flights'
                    },
    //        width: 3000,
                    height: 500
                };

                var chart2 = new google.visualization.ColumnChart(
                        document.getElementById('chart_div'));

                chart2.draw(data2, options2);
            }

        </script>
    </head>
    <body>
        <div class="wrapper">

            <!--sidebar-->
            <jsp:include page="sidebar.jsp?current=carrier_delay" />

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