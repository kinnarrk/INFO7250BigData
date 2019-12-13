<%-- 
    Document   : daily_delay
    Created on : Dec 12, 2019, 10:53:56 PM
    Author     : kinnar
--%>

<%@page import="edu.northeastern.kinnarkansara.finalprojectnbweb.model.SrcDestCountTuple"%>
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


    </head>

    <body>

        <div class="wrapper">

            <!--sidebar-->
            <jsp:include page="sidebar.jsp?current=top_src_dest_count" />

            <!-- Page Content Holder -->
            <div id="content">

                <!--top-->
                <jsp:include page="top.jsp" />

                <!--content-->
                <h2>Top 10 Source Destination Pair Counts</h2>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Source</th>
                            <th scope="col">Destination</th>
                            <th scope="col">Count</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% List<SrcDestCountTuple> srcDestCountList = (ArrayList)request.getAttribute("srcDestCountList");
                        for(int i = 0; i < srcDestCountList.size(); i++){
                            SrcDestCountTuple obj = (SrcDestCountTuple)srcDestCountList.get(i);
                            
                        %>                        
                        <tr>
                            <th scope="row"><%=(i+1)%></th>
                            <td><%=obj.getSrc()%></td>
                            <td><%=obj.getDest()%></td>
                            <td><%=obj.getCount()%></td>
                        </tr>                              
                          <%
                          }
                          %>
                        
                    </tbody>
                </table>
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