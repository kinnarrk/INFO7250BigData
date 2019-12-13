/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.northeastern.kinnarkansara.finalprojectnbweb.controller;

import edu.northeastern.kinnarkansara.finalprojectnbweb.model.DailyDelayTuple;
import edu.northeastern.kinnarkansara.finalprojectnbweb.model.SrcDestCountTuple;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class TopSrcDestPairController extends HttpServlet {

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
        List<SrcDestCountTuple> srcDestCountList = new ArrayList<>();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet TopSrcDestPairController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet TopSrcDestPairController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
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
                Path file = new Path("hdfs://localhost:9000/project/Top10SourceDestinations/part-r-00000");
                
                br=new BufferedReader(new InputStreamReader(hdfs.open(file)));
                String line;
                line=br.readLine();
                
                
                while (line != null){
//                    System.out.println(line);
                    //Todo here
                    String[] data= line.split("\t");
//                    System.out.println("data:" + Arrays.toString(data));
                    if(data.length == 2){
                        String srcdest = data[0];  //this is year
                        
    //                    Date date = _utility.convertStringToDate(yyyymmdd);
                        int count = Integer.parseInt(data[1]);                        
                        String[] data2= srcdest.split("-");
//                        System.out.println("data2:" + Arrays.toString(data2));
                        //flightsCount, delayedFlightsCount, delayPercentage, canceledFlightsCount, canceledPercentage
                        if(data2.length == 2) {
                            String src = data2[0];
                            String dest = data2[1];
                            //new object, set values and add to list
                            
                            SrcDestCountTuple srcDestCountTuple = new SrcDestCountTuple();
                            srcDestCountTuple.setSrc(src);
                            srcDestCountTuple.setDest(dest);
                            srcDestCountTuple.setCount(count);
                            
                            srcDestCountList.add(srcDestCountTuple);                                                        
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
            
            RequestDispatcher rd = request.getRequestDispatcher("top_src_dest_count.jsp");
            request.setAttribute("srcDestCountList", srcDestCountList);
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
