/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.northeastern.kinnarkansara.finalprojectnbweb.controller;

import edu.northeastern.kinnarkansara.finalprojectnbweb.model.DailyDelayTuple;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 *
 * @author kinnar
 */
public class DailyTotalFlightsVsDepartureDelay extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        List<DailyDelayTuple> dailyDelayList = new ArrayList<>();
        String button = request.getParameter("button");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DailyTotalFlightsVsDepartureDelay</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DailyTotalFlightsVsDepartureDelay at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            Configuration conf = new Configuration();
            conf.addResource(new org.apache.hadoop.fs.Path("/usr/local/bin/hadoop-3.2.1/conf/core-site.xml"));
            conf.addResource(new Path("/usr/local/bin/hadoop-3.2.1/conf/hdfs-site.xml"));
            
//            Path filePath = new Path("/project/DailyDelayCancel/part-r-00000");
//
//            FileSystem fs = filePath.getFileSystem(conf);
            BufferedReader br = null;
            
            try {
                 

                Configuration configuration = new Configuration();
                FileSystem hdfs = FileSystem.get( new URI( "hdfs://localhost:9000" ), configuration );
                Path file = new Path("hdfs://localhost:9000/project/DailyDelayCancel/part-r-00000");
                
                br=new BufferedReader(new InputStreamReader(hdfs.open(file)));
                String line;
                line=br.readLine();
                
                
                while (line != null){
//                    System.out.println(line);
                    //Todo here
                    String[] data= line.split("\t");
//                    System.out.println("data:" + Arrays.toString(data));
                    if(data.length == 2 && data[0].startsWith("2008")){
                        String yyyymmdd = data[0];
    //                    Date date = _utility.convertStringToDate(yyyymmdd);
                        String right = data[1];
                        String[] data2= right.split("\\s*,\\s*");
//                        System.out.println("data2:" + Arrays.toString(data2));
                        //flightsCount, delayedFlightsCount, delayPercentage, canceledFlightsCount, canceledPercentage
                        if(data2.length == 5) {
                            int flightsCount = Integer.parseInt(data2[0]);
                            int delayedFlightsCount = Integer.parseInt(data2[1]);
                            double delayPercentage = Double.parseDouble(data2[2]);
                            int canceledFlightsCount = Integer.parseInt(data2[3]);
                            double canceledPercentage = Double.parseDouble(data2[4]);
                            
                            //new object, set values and add to list
                            
                            DailyDelayTuple dailyDelayTuple = new DailyDelayTuple();
                            dailyDelayTuple.setYyyymmdd(yyyymmdd);
                            dailyDelayTuple.setFlightsCount(flightsCount);
                            dailyDelayTuple.setDelayPercentage(delayPercentage);
                            dailyDelayTuple.setDelayedFlightsCount(delayedFlightsCount);
                            dailyDelayTuple.setCanceledFlightsCount(canceledFlightsCount);
                            dailyDelayTuple.setCanceledPercentage(canceledPercentage);
                            
                            dailyDelayList.add(dailyDelayTuple);                                                        
                        }
                    }
                    //Not to remove beyond this line
                    line=br.readLine();                    
                }
                
            } catch(Exception e){
                
            } finally {
                // you should close out the BufferedReader
                br.close();
            }
            
            RequestDispatcher rd = request.getRequestDispatcher("daily_flights_vs_departure_delay.jsp");
            request.setAttribute("dailyDelayList", dailyDelayList);
//            System.out.println("daily delay list: " + dailyDelayList);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
