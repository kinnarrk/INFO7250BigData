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

        <title>Daily Flights Cancellations</title>

        <!--header-->
        <jsp:include page="header.jsp" />

        <script type="text/javascript">
            google.charts.load("current", {packages: ["calendar"]});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {
                var dataTable = new google.visualization.DataTable();
                dataTable.addColumn({type: 'date', id: 'Date'});
                dataTable.addColumn({type: 'number', id: 'Cancellation Percentage'});
                dataTable.addRows([
                        //[ new Date(2012, 3, 13), 37032 ],
            <% List<DailyDelayTuple> dailyCancelList = (ArrayList)request.getAttribute("dailyCancelList");
                  for (int i = 0; i < dailyCancelList.size(); i++) {
                      DailyDelayTuple obj = (DailyDelayTuple) dailyCancelList.get(i);

            %>
            [ new Date(<%=obj.getYyyymmdd().substring(0, 4)%>, parseInt(<%=obj.getYyyymmdd().substring(4, 6)%>) - 1, <%=obj.getYyyymmdd().substring(6, 8)%>), <%=obj.getCanceledPercentage()%> ] <%if (i < dailyCancelList.size() - 1) {%>,<%}%>

            <%
                    }
            %>
                ]);

                var chart = new google.visualization.Calendar(document.getElementById('calendar_basic'));

                var options = {
                    title: "Daily Flight Cancellation Percentage",
                    width: 1200,
                    height: 2000,
                    colorAxis: {minValue: 0, maxValue: 10, colors: ['#00FFFF', '#FF0000']}
                };

                chart.draw(dataTable, options);
            }
        </script>

    </head>

    <body>

        <div class="wrapper">

            <!--sidebar-->
            <jsp:include page="sidebar.jsp?current=daily_cancel" />

            <!-- Page Content Holder -->
            <div id="content">

                <!--top-->
                <jsp:include page="top.jsp" />

                <!--content-->
                <div id="calendar_basic" class="col-md-12"></div>


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