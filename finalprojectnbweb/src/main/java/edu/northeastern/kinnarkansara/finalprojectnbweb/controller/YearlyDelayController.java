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
public class YearlyDelayController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<DailyDelayTuple> dailyDelayList = new ArrayList<>();
        try (PrintWriter out = response.getWriter()) {

            Configuration conf = new Configuration();
            conf.addResource(new org.apache.hadoop.fs.Path("/usr/local/bin/hadoop-3.2.1/conf/core-site.xml"));
            conf.addResource(new Path("/usr/local/bin/hadoop-3.2.1/conf/hdfs-site.xml"));

            BufferedReader br = null;

            try {

                Configuration configuration = new Configuration();
                FileSystem hdfs = FileSystem.get(new URI("hdfs://localhost:9000"), configuration);
                Path file = new Path("hdfs://localhost:9000/project/YearlyDelayCancel/part-r-00000");

                br = new BufferedReader(new InputStreamReader(hdfs.open(file)));
                String line;
                line = br.readLine();

                while (line != null) {
                    String[] data = line.split("\t");
                    if (data.length == 2) {
                        String yyyymmdd = data[0];  //this is year
                        String right = data[1];
                        String[] data2 = right.split("\\s*,\\s*");
                        //flightsCount, delayedFlightsCount, delayPercentage, canceledFlightsCount, canceledPercentage
                        if (data2.length == 5) {
                            int flightsCount = Integer.parseInt(data2[0]);
                            int delayedFlightsCount = Integer.parseInt(data2[1]);
                            double delayPercentage = Double.parseDouble(data2[2]);
                            int canceledFlightsCount = Integer.parseInt(data2[3]);
                            double canceledPercentage = Double.parseDouble(data2[4]);

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
                    line = br.readLine();
                }
            } catch (Exception e) {
            } finally {
                br.close();
            }

            RequestDispatcher rd = request.getRequestDispatcher("yearly_flight_delay_cancel.jsp");
            request.setAttribute("dailyDelayList", dailyDelayList);
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
