/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.northeastern.kinnarkansara.finalprojectnbweb.util;


import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.math.BigDecimal;
import javax.swing.*;
import java.awt.*;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DecimalFormat;
import java.text.ParseException;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.lang3.SystemUtils;
/**
 *
 * @author kinnar
 */
public class CommonUtility {
    /** start Application version region ########## VERY IMPORTANT ######### */
    
//    public static 


    /* end  data synchronize host link region */

    public DecimalFormat df = new DecimalFormat("0.00");
    public String get_Return_Type = "";
    public static double diff = 0;
    public static double diff_for_penalty = 0;
    
    int prevYear, prevMonth, prevDay, endMonth = 0;
    int curYear, curMonth, curDay;
    private File DB_Error_File = null;
    private File General_Error_File = null;
    private FileOutputStream DBError = null;
    private PrintStream DBError1 = null;
    private FileOutputStream GenError = null;
    private PrintStream GenError1 = null;
    private Hashtable Pnl_List = new Hashtable();
    private String Pnl_Title = "";
    private GregorianCalendar G_Cal = new GregorianCalendar();
    private String month[] = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    private Date date_Obj = new Date();
    private Calendar Calendar_Obj = new GregorianCalendar().getInstance();
    private Object[] MonthObj = new Object[]{"", "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"};
    private Object[] QuaterObj = new Object[]{"", "January", "April", "July", "October"};
    private Object[] AnnualObj = new Object[]{"", "January"};
    private Hashtable ReportingFrq;
    private Object[] EPTaxValues;
    private int Company_ID = 0;
    private int Return_ID = 0;
    private int DUEDATE_YEAR = 0;
    private static int static_DUEDATE_YEAR = 0;
    private boolean Company_Edit_Mode = false;
    private boolean Return_Edit_Mode = false;
    private int Day_Diff = 0;
    private double Month_Diff = 0;
    private Runtime MainRuntime = null;
    private Process MainProcess = null;
    private static String Return_Type = "";
    private static String Company_Name = "";
    public static int monthstatus = 0;
    //   publssic NJExtraInfo _NJExtraInfo = NJExtraInfo.getSalesUseInstance();
    private static CommonUtility _Utility_Inst = null;

    public static CommonUtility getUtilityInstance() {
        if (_Utility_Inst == null) {
            _Utility_Inst = new CommonUtility();
        }

        //v 3.7.4 Linux implementation
//        if(!SystemUtils.IS_OS_WINDOWS)
//            _Utility_Inst.dirSlash = "/";

        return _Utility_Inst;
    }

    /** Creates a new instance of Common_Utility */
    public CommonUtility() {
        _Utility_Inst = this;
        Error_Log();
    }

//    public String encriptData(String data)
//    {
//        String encriptedData = null;
//        try {
//         // Create key and cipher
//         Key aesKey = new SecretKeySpec(AES_key.getBytes(), "AES");
//         Cipher cipher = Cipher.getInstance("AES");
//
//         // encrypt the text
//         cipher.init(Cipher.ENCRYPT_MODE, aesKey);
//         byte[] encryptedBytes = cipher.doFinal(data.getBytes());
//         encriptedData = DatatypeConverter.printBase64Binary(encryptedBytes);//new String(encryptedBytes);
//      }catch(Exception e) {
//         e.printStackTrace();
//      }
//
//        return encriptedData;
//        
//    }
//    
//    public String decriptData(String data)
//    {
//        String decriptedData = null;
//        try {
//         // Create key and cipher
//         Key aesKey = new SecretKeySpec(AES_key.getBytes(), "AES");
//         Cipher cipher = Cipher.getInstance("AES");
//
//         // decrypt the text
//         cipher.init(Cipher.DECRYPT_MODE, aesKey);
//         decriptedData = new String(cipher.doFinal(DatatypeConverter.parseBase64Binary(data)));
//    
//      }catch(Exception e) {
//         e.printStackTrace();
//      }
//        return decriptedData;
//    }

    public void hideTableColumn(JTable table, int index)
    {
        table.getColumnModel().getColumn(index).setMinWidth(0);
        table.getColumnModel().getColumn(index).setMaxWidth(0);
        table.getColumnModel().getColumn(index).setWidth(0);
        table.getColumnModel().getColumn(index).setPreferredWidth(0);
       
    }
//    public static boolean isInternetReachable()
//    {
//        try {
//            //make a URL to a known source
//            String urlString = url_host+"/update_version/check_update_version.php";
////            String urlString = "https://quickcl.com/update_version/check_update_version.php";
//            URL url = new URL(urlString);//"http://www.google.com");
//
//            //open a connection to that source
//            HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();
//
//            //trying to retrieve data from the source. If there
//            //is no connection, this line will fail
//            Object objData = urlConnect.getContent();
//
//        } catch (UnknownHostException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            e.printStackTrace(new Common_Utility().GenErrorFile());
//            new Common_Utility().GenErrorFileWithSout("Internet", "Not Connected : "+e.getMessage());
//            return false;
//        }
//        catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            e.printStackTrace(new Common_Utility().GenErrorFile());
//            new Common_Utility().GenErrorFileWithSout("Internet", "Not Connected : "+e.getMessage());
//            return false;
//        }
//        return true;
//    }
    public String convertZeroToBlank(String str)
    {
        if(str.trim().equals("0"))
            return " ";
        else if(str.trim().equals("0.0"))
            return " ";
        else if(str.trim().equals("0.00"))
            return " ";
        else if(str.trim().equals("0.000"))
            return " ";
        else
            return str;
    }

    

    public String get_CurrentDate() {
        Date TodaysDate = new java.util.Date();
        SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateformatter.format(TodaysDate);

        return formattedDate;
    }

    public String get_CurrentTime() {
        Date TodaysDate = new java.util.Date();
        String formattedTime = TodaysDate.getHours() + ":" + TodaysDate.getMinutes() + ":" + TodaysDate.getSeconds();

        return formattedTime;
    }
    public String convertDateToYMD(String str) {

        String dd = str.substring(0, 2);
        String mm = str.substring(3, 5);
        String yyyy = str.substring(6, 10);
        str = yyyy + "-" + mm + "-" + dd;

        return str;
    }
    public String convertYMDToDate(String str) {

        String yyyy = str.substring(0, 4);
        String mm = str.substring(5, 7);
        String dd = str.substring(8, 10);
        str = dd + "/" + mm + "/" + yyyy;

        return str;
    }
    
    public Date convertStringToDate(String dateStr) {
        Date date1 = null;
        try{
            date1=new SimpleDateFormat("yyyyMMdd").parse(dateStr);
        } catch(ParseException e){
            
        }
        return date1;
    }

    public void tempErr(String msg) {
        try {
            File ff = new File("D:\\err.htm");
            FileWriter fw = null;
            if (ff.exists()) {
                fw = new FileWriter(ff, true);
            } else {
                fw = new FileWriter(ff, false);
            }
            fw.write(msg);
            fw.write("<br>");
            fw.close();
        } catch (Exception e) {
        }
    }

    private void Error_Log() {
        try {
//            //String Path = System.getProperty("d:\\") + "\\DBError.txt";
//            String Path = "d:\\DBError.txt";
//            this.DB_Error_File = new File(Path);
//            if (!this.DB_Error_File.exists()) {
//                this.DB_Error_File.createNewFile();
//            }
//            DBError = new FileOutputStream(this.DB_Error_File);
//            DBError1 = new PrintStream(DBError);
//
//            Path = System.getProperty("d:\\") + "\\GeneralError.txt";
            String path = System.getProperty("user.dir");
            File file = new File(path+"/ErrorLog");
            if(!file.exists())
                file.mkdir();

            String fileName = this.get_CurrentDate()+".txt";
            this.General_Error_File = new File(path+"/ErrorLog/"+fileName);


//            this.General_Error_File = new File(path);
            if (!this.General_Error_File.exists()) {
                this.General_Error_File.createNewFile();
            }
            GenError = new FileOutputStream(this.General_Error_File, true);
            GenError1 = new PrintStream(GenError);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PrintStream GenErrorFile(String curfilename) {
        try {
            String Line = "---------------------------:-" + new Date() + "FOR CURRFILE" + curfilename;
            GenError1.write(Line.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GenError1;
    }

    public PrintStream GenErrorFile() {
        try {
            String Line = "---------------------------:- " + new Date() +" -:---------------------------\r\n";
            GenError1.write(Line.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GenError1;
    }
    public void GenErrorFileWithSout(String subject,String msg)
    {
         try {
            String Line = "---------------------------:- " + new Date() +" -:---------------------------\r\n"
                    + "Subject : "+subject+"\r\n"
                    + "Message : "+msg+"\r\n";
            GenError1.write(Line.toString().getBytes());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public PrintStream GenDBErrorFile() {
        try {
            String Line = "---------------------------:-" + new Date();

            DBError1.write(Line.toString().getBytes());
        } catch (Exception e) {
        }
        return DBError1;
    }

//    public String get_CurrentYear() {
//        return (StateProperty.getStateProperty_Instance().get_Year());
//    }
    //************* To Get and Set Company_ID;

    public String get_Company_ID() {
        return String.valueOf(Company_ID).trim();
    }

    public void set_Company_ID(String str) {
        Company_ID = Integer.parseInt(str);
    }

    public String get_Return_ID() {
        return String.valueOf(Return_ID).trim();
    }

    public void set_Return_ID(String str) {
        Return_ID = Integer.parseInt(str);
    }

    public String get_Return_Type() {
        return String.valueOf(Return_Type).trim();
    }

    public void set_Return_Type(String str) {
        Return_Type = str;
    }

    public String get_Company_Name() {
        return String.valueOf(Company_Name).trim();
    }

    public void set_Company_Name(String str) {
        Company_Name = str;
    }

    public boolean get_Company_Edit_Mode() {
        return Company_Edit_Mode;
    }

    public void set_Company_Edit_Mode(boolean flag) {
        Company_Edit_Mode = flag;
    }

    public boolean get_Return_Edit_Mode() {
        return Return_Edit_Mode;
    }

    public void set_Return_Edit_Mode(boolean flag) {
        Return_Edit_Mode = flag;
    }

    public int get_Day_Diff() {
        return Day_Diff;
    }

    public void set_Day_Diff(int days) {
        Day_Diff = days;
    }

    public double get_Month_Diff() {
        return Month_Diff;
    }

    public void set_Month_Diff(double days) {
        Month_Diff = days;
    }

    public String get_Current_Month() {
        return (month[G_Cal.get(GregorianCalendar.MONTH)]);
    }

    public int get_Current_Day() {
        return (G_Cal.get(GregorianCalendar.DAY_OF_MONTH));
    }

    public int get_Current_Year() {
        return (G_Cal.get(GregorianCalendar.YEAR));
    }

    // parin Get Current Date:
    public String get_Current_Date() {
        GregorianCalendar gc = new GregorianCalendar();
        int mnth = gc.get(GregorianCalendar.MONTH);
        int day = gc.get(GregorianCalendar.DAY_OF_MONTH);
        int yr = gc.get(GregorianCalendar.YEAR);
        String result, smonth = "", sday = "";

        //for month
        switch (mnth) {
            case 0: {
                smonth = "01";
                break;
            }
            case 1: {
                smonth = "02";
                break;
            }
            case 2: {
                smonth = "03";
                break;
            }
            case 3: {
                smonth = "04";
                break;
            }
            case 4: {
                smonth = "05";
                break;
            }
            case 5: {
                smonth = "06";
                break;
            }
            case 6: {
                smonth = "07";
                break;
            }
            case 7: {
                smonth = "08";
                break;
            }
            case 8: {
                smonth = "09";
                break;
            }
            default: {
                smonth = Integer.toString((mnth + 1));
                break;
            }
        }

        //for day
        switch (day) {
            case 1: {
                sday = "01";
                break;
            }
            case 2: {
                sday = "02";
                break;
            }
            case 3: {
                sday = "03";
                break;
            }
            case 4: {
                sday = "04";
                break;
            }
            case 5: {
                sday = "05";
                break;
            }
            case 6: {
                sday = "06";
                break;
            }
            case 7: {
                sday = "07";
                break;
            }
            case 8: {
                sday = "08";
                break;
            }
            case 9: {
                sday = "09";
                break;
            }
            default: {
                sday = Integer.toString((day));
                break;
            }
        }
        result = " " + smonth + "/" + sday + "/" + Integer.toString(yr);
        return result;
    }
    //

    public Object[] get_EPTaxValues() {
        if (EPTaxValues == null) {
            EPTaxValues = new Object[]{
                        "0", "0.00", "0", "0.00", "0", "0.00", "0", "0.00", "0", "0.00",
                        "0", "0.00", "0", "0.00", "0", "0.00", "0", "0.00", "0", "0.00"
                    };
        }
        return EPTaxValues;
    }

    public void set_EPTaxValues(Object[] data) {
        EPTaxValues = new Object[]{
                    data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9],
                    data[10], data[11], data[12], data[13], data[14], data[15], data[16], data[17], data[18], data[19]
                };
    }

    public Hashtable getReportingFrq() {
        ReportingFrq = new Hashtable();
        //        ReportingFrq.put("Monthly",MonthObj);
        //        ReportingFrq.put("Quarterly",QuaterObj);
        ReportingFrq.put("Annually", AnnualObj);
        return ReportingFrq;
    }

    public String getEndMonth(int freq, int month) {
        int NowMonth = 0;
        if (freq == 1) {
            NowMonth = 12;//month;
        }
        //        else if(freq==2){NowMonth=(month+(month*freq));}
        //        else{NowMonth=12;}
        return (MonthObj[NowMonth].toString());
    }

//    public String getDueDate(int freq, int month) {
//        String Due_Date = "";
//        try {
////            int year = Integer.parseInt(StateProperty.getStateProperty_Instance().get_Year());
//            int Month = get_Num_Month(getEndMonth(freq, month));
//            String DueMonth = String.valueOf(++Month);
//            if (DueMonth.length() == 1) {
//                DueMonth = "0" + DueMonth;
//            }
//
//            if (freq == 1) {
//                /*            if(month==4){
//                Due_Date=DueMonth+"-21-"+String.valueOf(year);
//                }else if(month==9){
//                Due_Date=DueMonth+"-22-"+String.valueOf(year);
//                }else if(month==12){
//                Due_Date="01"+"-22-"+String.valueOf(++year);
//                }else{
//                Due_Date=DueMonth+"-20-"+String.valueOf(year);
//                }
//                }else if(freq==2){
//                if(DueMonth.equalsIgnoreCase("04") || DueMonth.equalsIgnoreCase("07")){
//                Due_Date=DueMonth+"-20-"+String.valueOf(year);
//                }else if(DueMonth.equalsIgnoreCase("10")){
//                Due_Date=DueMonth+"-22-"+String.valueOf(year);
//                }else{
//                Due_Date="01"+"-22-"+String.valueOf(++year);
//                }
//                }else{
//                 */
////            year = year-1;
////            Due_Date="05"+"-01-"+String.valueOf(++year);
//            }
//        } catch (Exception e) {
//            e.printStackTrace(GenErrorFile());
//        }
//        return Due_Date;
//    }

    public int get_Num_Month(String Month) {
        int Mon = 0;
        for (int i = 0; i < MonthObj.length; i++) {
            if (MonthObj[i].toString().equalsIgnoreCase(Month)) {
                Mon = i;
            }
        }
        return Mon;
    }

    public int get_Num_Quarter(String Month) {
        int Qtr = get_Num_Month(Month);
        if (Qtr < 4) {
            return 1;
        } else if (Qtr > 3 && Qtr <= 6) {
            return 2;
        } else if (Qtr > 7 && Qtr <= 9) {
            return 3;
        } else {
            return 4;
        }
    }

    public String getRoundNumber(double number, int precision) {
        return new BigDecimal(number).setScale(precision, BigDecimal.ROUND_HALF_UP).toString();
    }

    public double getRoundNumberDouble(double number, int precision) {
        return new BigDecimal(number).setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public String getRoundValue(String val) {
        double firstPart = 0;
        int secondPart = 0;
        
        val = getRoundNumber(Double.parseDouble(val), 2);
        firstPart = Double.parseDouble(val.substring(0, val.length() - 3));
        secondPart = Integer.parseInt(val.substring(val.length() - 2));

        if (secondPart >= 50) {
            firstPart = Double.parseDouble(val.substring(0, val.length() - 3));
            firstPart = firstPart + 1;
            return getRoundNumber(firstPart, 2);
        }

        return getRoundNumber(firstPart, 2);
    }

    public String getWeightRoundValue(String val) {
//        System.out.println("Val :"+val);
        double firstPart = 0;
        int secondPart = 0;

        val = getRoundNumber(Double.parseDouble(val), 2);
//        System.out.println("Val 1 :"+val);
        firstPart = Double.parseDouble(val.substring(0, val.length() - 3));
        secondPart = Integer.parseInt(val.substring(val.length() - 2));

        if (secondPart >= 0.01) {
            firstPart = Double.parseDouble(val.substring(0, val.length() - 3));
            firstPart = firstPart + 1;
            return getRoundNumber(firstPart, 2);
        }

        return getRoundNumber(firstPart, 2);
    }
    //###########################################
    //###########################################

    public boolean hasOnlyDigits(String s) {
        int i = 0;
        for (i = 0; i < s.length(); i++) {
            //            if(!s.charAt(i).isDigit()) return false;
        }
        return true;
    }
    //###########################################
    //###########################################

    public String getRound_String(String str) {
        if (str.equalsIgnoreCase("") || str == null) {
            str = "0.00";
        }
        double d = Double.parseDouble(str);
        return (getRoundNumber(d, 2));
    }

    /*
     * This Method Calculates day as well as month diffrence between filingdate and duedate.
     * @ param 1 : FilinDate
     * @ param 2 : DueDate
     */
    public void Company_Date(String FilingDate, String DueDate) {
        try {
            set_Day_Diff(DayDifference(DueDate, FilingDate));
            double mon = compareDate(FilingDate, DueDate);
            set_Month_Diff(mon);
        } catch (Exception e) {
            e.printStackTrace(GenErrorFile());
        }
    }
    // Day difference between duedate and filing date

    public int DayDifference(String sdate1, String sdate2) {
        int days = 0;
        int Months = 0;
        try {
            sdate2 = sdate2.replaceAll("-", "/"); // Filing date
            sdate1 = sdate1.replaceAll("-", "/"); // due date

            if (!sdate1.equals("") && !sdate2.equals("")) {
                java.util.Date D1 = new java.util.Date(sdate1);
                java.util.Date D2 = new java.util.Date(sdate2);
                if (D2.before(D1)) {
                    days = 0;
                } else {
                    days = (int) ((D2.getTime() - D1.getTime()) / (1000 * 60 * 60 * 24));
                }
                if (days > 0) {
                    Calendar_Obj.setTime(D1);
                    int days_in_Month = Calendar_Obj.getActualMaximum(Calendar.DAY_OF_MONTH);
                    if (days < days_in_Month || (days % days_in_Month) == 0) {
                        Months = 1;
                    } else {
                        Months = (days / days_in_Month);
                        ++Months;
                    }
                    set_Month_Diff(Months);
                } else {
                    set_Month_Diff(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(GenErrorFile());
        }
        return days;
    }

    public double getfilingmonth() {
        return curMonth;
    }

    public int getfilingdays() {

        return manipulateDate(curMonth, curYear, curDay);
    }

    public double compareDate(String FilingDate, String DueDate) {
        diff = 0;
        double DateDiff = 0.00;
        double addFraction = 0.00;
        int monthDayLimit = 0;
        splitFilingDate("" + FilingDate.trim());
        splitDueDate("" + DueDate.trim());
        int diffdd = curDay - prevDay;
        int diffmm = curMonth - prevMonth;
        int diffyr = curYear - prevYear;
//        System.out.println("year difference is :=\t" + diffyr);
        if (curYear > prevYear) {
            int monthdiff = (12 * (curYear - prevYear)) - prevMonth + curMonth;
            diff = monthdiff;         //            if(curDay>prevDay)    diff++;
            monthDayLimit = manipulateDate(prevMonth, prevYear, prevDay);
            if (prevDay != curDay) {
                if (curDay < monthDayLimit && prevDay < monthDayLimit) {
                    if (prevDay < curDay) {
                        addFraction = curDay - prevDay;
                    }
                }
            }
            if (addFraction == 10 || addFraction == 20 || addFraction == 30) {
                double tmp = 0.00;//new BigDecimal(addFraction/100).;

                if (this.monthstatus != 0) {
                    this.monthstatus = 0;
                }
                this.monthstatus = (int) addFraction;

                DateDiff = diff;//+(addFraction/100);
            } else {
                DateDiff = diff + (addFraction / 100);
            }

//            System.out.println("yearlydiffdd is "+diffdd+" and diffmm is "+diffmm );
//            System.out.println("yearlycurYear is "+curYear+" and prevYear is "+prevYear );
//            System.out.println("yearlycurDay is "+curDay+" and prevDay is "+prevDay );
//            System.out.println("yearlyprevMonth is "+prevMonth+" and curMonth is "+curMonth );
//            System.out.println("-----> diff is "+df.format(DateDiff));
            return DateDiff;
        }
//        System.out.println("##diffdd is "+diffdd+" and diffmm is "+diffmm );
//        System.out.println("##curYear is "+curYear+" and prevYear is "+prevYear );
//        System.out.println("##curDay is "+curDay+" and prevDay is "+prevDay );
//        System.out.println("##prevMonth is "+prevMonth+" and curMonth is "+curMonth );
        if (diffdd > 0 || diffmm > 0) {
            for (int i = prevYear; i <= curYear; i++) {
                if (i == curYear) {//IF BOTH DATES FROM CURRENT YEAR
                    if (prevYear == curYear) {
                        while (prevMonth < curMonth) /*1<6*/ {
                            prevMonth++;
                            diff++;
                        }
                    } else {
                        while (prevMonth > curMonth) {
                            prevMonth = prevMonth;
                            diff = diff;
                        }
                    }
                } else { //end of if
                    if (prevMonth >= curMonth) {
                        while (prevMonth <= 12) {
                            prevMonth++;
                            diff++;
                        } //end of while
                        if (prevMonth >= 13) {
                            prevMonth = 1;
                        }
                    } else {
                        while (prevMonth < curMonth) {
                            prevMonth++;
                            diff++;
                        } //end of while loop
                        if (prevMonth >= 13) {
                            prevMonth = 1;
                        }
                    } //end of else
                }
                //   prevYear++;
            }
            monthDayLimit = manipulateDate(prevMonth, prevYear, prevDay);
            if (prevDay != curDay) {
                if (curDay < monthDayLimit && prevDay < monthDayLimit) {
                    if (prevDay < curDay) {
                        addFraction = curDay - prevDay;
                    }
                }
            }
            if (addFraction == 10 || addFraction == 20 || addFraction == 30) {
                if (this.monthstatus != 0) {
                    this.monthstatus = 0;
                }
                this.monthstatus = (int) addFraction;
                DateDiff = diff;//+(addFraction/100);
            } else {
                DateDiff = diff + (addFraction / 100);
            }

//            System.out.println("diffdd is "+diffdd+" and diffmm is "+diffmm );
//            System.out.println("curYear is "+curYear+" and prevYear is "+prevYear );
//            System.out.println("curDay is "+curDay+" and prevDay is "+prevDay );
//            System.out.println("prevMonth is "+prevMonth+" and curMonth is "+curMonth );
//            System.out.println("RETURN 2 : " +diff);
//            System.out.println("And DateDiff is RETURN 2 : " +DateDiff);

            return DateDiff;
        } else {
            diff = 0;
            return diff;
        }
    } //end of method

    public double compareDateSPECIAL(String FilingDate, String DueDate) {
        diff = 0;
        double DateDiff = 0.00;
        double addFraction = 0.00;
        int monthDayLimit = 0;
        splitFilingDate("" + FilingDate.trim());
        splitDueDate("" + DueDate.trim());
        int diffdd = curDay - prevDay;
        int diffmm = curMonth - prevMonth;
        int diffyr = curYear - prevYear;

        if (curYear > prevYear) {
            int monthdiff = (12 * (curYear - prevYear)) - prevMonth + curMonth;
            diff = monthdiff;         //            if(curDay>prevDay)    diff++;
            monthDayLimit = manipulateDate(prevMonth, prevYear, prevDay);
            if (prevDay != curDay) {
                if (curDay < monthDayLimit && prevDay < monthDayLimit) {
                    if (prevDay < curDay) {
                        addFraction = curDay - prevDay;
                    }
                }
            }
//BELOW CODE IS COMMENTED AS NOT NEEDED HERE OTHERWISE IMPORTANT FOR FRACTION OF MONTH CALCULATION            
/*            
            if(addFraction ==10 || addFraction==20 || addFraction==30){
            double tmp = 0.00;//new BigDecimal(addFraction/100).;

            if(this.monthstatus!=0) this.monthstatus =0;
            this.monthstatus = (int)addFraction;

            DateDiff = diff;//+(addFraction/100);
            }else{
            DateDiff = diff+(addFraction/100);
            }
             */
            if (addFraction > 0) {
                diff++;

            }
            DateDiff = diff;
//            System.out.println("yearlydiffdd is "+diffdd+" and diffmm is "+diffmm );
//            System.out.println("yearlycurYear is "+curYear+" and prevYear is "+prevYear );
//            System.out.println("yearlycurDay is "+curDay+" and prevDay is "+prevDay );
//            System.out.println("yearlyprevMonth is "+prevMonth+" and curMonth is "+curMonth );
            //System.out.println("-----> diff is "+df.format(DateDiff));
            return DateDiff;
        }
//        System.out.println("##diffdd is "+diffdd+" and diffmm is "+diffmm );
//        System.out.println("##curYear is "+curYear+" and prevYear is "+prevYear );
//        System.out.println("##curDay is "+curDay+" and prevDay is "+prevDay );
//        System.out.println("##prevMonth is "+prevMonth+" and curMonth is "+curMonth );
        if (diffdd > 0 || diffmm > 0) {
            for (int i = prevYear; i <= curYear; i++) {
                if (i == curYear) {//IF BOTH DATES FROM CURRENT YEAR
                    if (prevYear == curYear) {
                        while (prevMonth < curMonth) /*1<6*/ {
                            prevMonth++;
                            diff++;
                        }
                    } else {
                        while (prevMonth > curMonth) {
//                            prevMonth = prevMonth;
//                            diff = diff;
                        }
                    }
                } else { //end of if
                    if (prevMonth >= curMonth) {
                        while (prevMonth <= 12) {
                            prevMonth++;
                            diff++;
                        } //end of while
                        if (prevMonth >= 13) {
                            prevMonth = 1;
                        }
                    } else {
                        while (prevMonth < curMonth) {
                            prevMonth++;
                            diff++;
                        } //end of while loop
                        if (prevMonth >= 13) {
                            prevMonth = 1;
                        }
                    } //end of else
                }
                //   prevYear++;
            }
            monthDayLimit = manipulateDate(prevMonth, prevYear, prevDay);
            if (prevDay != curDay) {
                if (curDay < monthDayLimit && prevDay < monthDayLimit) {
                    if (prevDay < curDay) {
                        addFraction = curDay - prevDay;

                    }
                }
            }

//BELOW CODE IS COMMENTED AS NOT NEEDED HERE OTHERWISE IMPORTANT FOR FRACTION OF MONTH CALCULATION
/*            if(addFraction ==10 || addFraction==20 || addFraction==30){
            if(this.monthstatus!=0) this.monthstatus =0;
            this.monthstatus = (int)addFraction;
            System.out.println("adraction102030"+DateDiff);
            DateDiff = diff;//+(addFraction/100);
            System.out.println("adraction102030 DateDiff"+DateDiff);
            System.out.println("adraction102030 diff"+diff);

            }else{

            DateDiff = diff+(addFraction/100);
            System.out.println("INELSEadraction102030 DateDiff"+DateDiff);
            System.out.println("INELSEadraction102030 diff"+diff);
            }
             */
            if (addFraction > 0) {
                diff++;

            }
            DateDiff = diff;

            return DateDiff;
        } else {
            diff = 0;
            return diff;
        }
    } //end of method

    public void splitFilingDate(String selDate) {
        try {
            StringTokenizer stz = new StringTokenizer(selDate, "-");
            int noOfTokens = stz.countTokens();
            if (noOfTokens == 3) {
                curMonth = Integer.parseInt(stz.nextToken());
                curDay = Integer.parseInt(stz.nextToken());
                curYear = Integer.parseInt(stz.nextToken());
            } else if (noOfTokens == 1) {
                if (selDate.length() == 6) {
                    selDate = selDate.substring(0, 2) + "-" + selDate.substring(2, 4) + "-" + selDate.substring(4, 6);
                } else if (selDate.length() == 8) {
                    selDate = selDate.substring(0, 2) + "-" + selDate.substring(2, 4) + "-" + selDate.substring(4, 8);
                }
                stz = new StringTokenizer(selDate, "-");
                curMonth = Integer.parseInt(stz.nextToken());
                curDay = Integer.parseInt(stz.nextToken());
                curYear = Integer.parseInt(stz.nextToken());
            } else {
                stz = new StringTokenizer(selDate, "-");
                curMonth = Integer.parseInt(stz.nextToken());
                curDay = Integer.parseInt(stz.nextToken());
                curYear = Integer.parseInt(stz.nextToken());
            }
        } catch (Exception e) {
            e.printStackTrace(GenErrorFile());
        }
    }

    public void splitDueDate(String selDate) {
        try {
            StringTokenizer stz = new StringTokenizer(selDate, "-");
            int noOfTokens = stz.countTokens();
            if (noOfTokens == 3) {
                prevMonth = Integer.parseInt(stz.nextToken());
                prevDay = Integer.parseInt(stz.nextToken());
                prevYear = Integer.parseInt(stz.nextToken());

            } else if (noOfTokens == 1) {
                if (selDate.length() == 6) {
                    selDate = selDate.substring(0, 2) + "-" + selDate.substring(2, 4) + "-" + selDate.substring(4, 6);
                } else if (selDate.length() == 8) {
                    selDate = selDate.substring(0, 2) + "-" + selDate.substring(2, 4) + "-" + selDate.substring(4, 8);
                }

                stz = new StringTokenizer(selDate, "-");
                prevMonth = Integer.parseInt(stz.nextToken());
                prevDay = Integer.parseInt(stz.nextToken());
                prevYear = Integer.parseInt(stz.nextToken());
            } else {
                stz = new StringTokenizer(selDate, "-");
                prevMonth = Integer.parseInt(stz.nextToken());
                prevDay = Integer.parseInt(stz.nextToken());
                prevYear = Integer.parseInt(stz.nextToken());
            }
        } catch (Exception e) {
            e.printStackTrace(GenErrorFile());
        }
    }

    public void Start_Process(String Command) {
        try {
            Stop_Process();
            MainRuntime = Runtime.getRuntime();
            MainRuntime.gc();
            MainProcess = MainRuntime.exec("rundll32 url.dll,FileProtocolHandler " + Command);
        } catch (Exception e) {
            e.printStackTrace(GenErrorFile());
        }
    }

    public void Stop_Process() {
        try {
            if (MainProcess != null) {
                MainProcess.destroy();
                MainProcess.waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace(GenErrorFile());
        }
        return;
    }

    public int valSSN(JTextField SSN) {
        String strSSN = SSN.getText();
        if ((strSSN != null && strSSN.length() != 0 && strSSN.length() != 11)) {
            SSN.requestFocus();
            return 0;
        } else if (strSSN != null && !strSSN.equals("") && strSSN.indexOf('#') == -1) {
            int issn1 = Integer.parseInt(strSSN.substring(0, 3));
            int issn2 = Integer.parseInt(strSSN.substring(4, 6));
            int issn3 = Integer.parseInt(strSSN.substring(7, 11));
            String ssn1 = strSSN.substring(0, 1);
            String ssn2 = strSSN.substring(0, 3);

            if ((strSSN.equalsIgnoreCase("123-45-6789")) || (strSSN.equalsIgnoreCase("111-11-1111")) || (strSSN.equalsIgnoreCase("333-33-3333")) || (ssn1.equalsIgnoreCase("8")) || (ssn1.equalsIgnoreCase("9")) || (ssn2.equalsIgnoreCase("000"))) {
                return 1;
            }
            if ((issn2 == 0 || issn3 == 0) || issn1 == 0 || (issn1 > 695 && issn1 < 700) || (issn1 > 733 && issn1 < 750)) {
                return 2;
            }
        }
        return 3;
    }

    //Date Related Validation
    public String convertStringtodate(String str) {
        String smm = "", sdd = "", syy = "";
        String dateofreturn = str;
        StringTokenizer stok = null;
        if (dateofreturn.indexOf("/") != -1) {
            stok = new StringTokenizer(dateofreturn, "/");
        } else {
            stok = new StringTokenizer(dateofreturn, "-");
        }
        while (stok.hasMoreElements()) {
            try {
                smm = stok.nextToken();
                sdd = stok.nextToken();
                syy = stok.nextToken();
                if (smm.trim().length() == 1) {
                    smm = "0" + smm.trim();
                }
                if (sdd.trim().length() == 1) {
                    sdd = "0" + sdd.trim();
                }
                if (syy.trim().length() == 2) {
                    syy = "20" + syy.trim();
                }
            } catch (Exception er) {
                {
                    er.printStackTrace(GenErrorFile());
                }
            }
        }

        return smm + "-" + sdd + "-" + syy;
    } // end of method

    public boolean compDate(String strMonth, String fillingDate, int monthAdd) {
        int monthNo = 0;
        for (int i = MonthObj.length; --i >= 0;) {
            if (strMonth.equalsIgnoreCase(MonthObj[i].toString())) {
                monthNo = i;
                break;
            }
        }
        int str1_1, str1_2, str1_3;
        int first, second, third;

        first = fillingDate.indexOf("-");
        str1_2 = Integer.parseInt(fillingDate.substring(0, first)) - 1;
        fillingDate = fillingDate.substring(first + 1);

        second = fillingDate.indexOf("-");
        str1_1 = Integer.parseInt(fillingDate.substring(0, second));
        fillingDate = fillingDate.substring(second + 1);
        str1_3 = Integer.parseInt(fillingDate.substring(fillingDate.indexOf("-") + 1)) + monthAdd;
        java.util.GregorianCalendar fillingGGG = new java.util.GregorianCalendar(str1_3, str1_2, str1_1);
        java.util.GregorianCalendar g = new java.util.GregorianCalendar();
        java.util.GregorianCalendar gg = new java.util.GregorianCalendar(static_DUEDATE_YEAR, monthNo, g.get(java.util.GregorianCalendar.DAY_OF_MONTH));
        java.util.GregorianCalendar tempG = new java.util.GregorianCalendar(static_DUEDATE_YEAR, monthNo, gg.getActualMaximum(java.util.GregorianCalendar.DAY_OF_MONTH));

        if (fillingGGG.after(tempG)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean compDate_New(String FDate, String Pend) {
        boolean flg = true;
        try {
            String sdate1 = FDate.replaceAll("-", "/");
            String sdate2 = Pend.replaceAll("-", "/");
            java.util.Date d1 = new java.util.Date(sdate1);
            java.util.Date d2 = new java.util.Date(sdate2);
            if (d1.before(d2)) {
                flg = true;
            } else {
                flg = false;
            }
        } catch (Exception e) {
            e.printStackTrace(GenErrorFile());
        }
        return flg;
    }

    public final String Email(JTextField email) {
        String messageContent = "";
        final String chkString = email.getText();
        final char at = '@';
        final char dot = '.';
        final int atind = chkString.indexOf(at);
        final int strlen = chkString.length();
        final int dotind = chkString.indexOf(dot);

        if (chkString.indexOf(at) == -1 || chkString.indexOf(dot) == -1) {
            if (messageContent.trim().length() == 0) {
                messageContent = "must contains at least one '@' and one '.' .";
            }
        } else if (chkString.indexOf(at) == 0 || chkString.indexOf(at) == strlen) {
            if (messageContent.trim().length() == 0) {
                messageContent = "can not start or end with '@'";
            }
        } else if (chkString.indexOf(dot) == 0 || chkString.indexOf(dot) == strlen) {
            if (messageContent.trim().length() == 0) {
                messageContent = "can not start or end with '.'";
            }
        } else if (chkString.charAt(atind + 1) == '.' || chkString.charAt(strlen - 2) == '.') {
            if (messageContent.trim().length() == 0) {
                messageContent = "has Invalid '.' position.";
            }
        } else if (!customValidChars(chkString, true, true, "_@.-", "", "")) {
            if (messageContent.trim().length() == 0) {
                messageContent = "contains invalid character";
            }
        } else {
            messageContent = "";
        }
        return messageContent;
    }
    //val-idation for website

    public final String Website(JTextField website) {
        String messageContent = "";
        final String chkString = website.getText();
        final char at = '@';
        final char dot = '.';
        final int atind = chkString.indexOf(at);
        final int strlen = chkString.length();
        final int dotind = chkString.indexOf(dot);

        if (chkString.indexOf(dot) == -1) {
            if (messageContent.trim().length() == 0) {
                messageContent = "must contains at least one '.' .";
            }
        } else if (chkString.indexOf(at) == 0 || chkString.indexOf(at) == strlen) {
            if (messageContent.trim().length() == 0) {
                messageContent = "can not start or end with '@'";
            }
        } else if (chkString.indexOf(dot) == 0 || chkString.indexOf(dot) == strlen) {
            if (messageContent.trim().length() == 0) {
                messageContent = "can not start or end with '.'";
            }
        } else if (chkString.charAt(atind + 1) == '.' || chkString.charAt(strlen - 2) == '.') {
            if (messageContent.trim().length() == 0) {
                messageContent = "has Invalid '.' position.";
            }
        } else if (!customValidChars(chkString, true, true, "_@.-", "", "")) {
            if (messageContent.trim().length() == 0) {
                messageContent = "contains invalid character";
            }
        } else {
            messageContent = "";
        }
        return messageContent;
    }

    public final boolean customValidChars(final String chkString, final boolean alpha, final boolean numeric, final String specialChars, final String messageTitle, String messageContent) {
        int i;
        final int len = chkString.length();
        char c;
        boolean interAlphaFlag, interNumericFlag, interSpecialCharFlag;
        int ascii;
        for (i = 0; i < len; i++) {
            interAlphaFlag = interNumericFlag = interSpecialCharFlag = false;
            c = chkString.charAt(i);
            ascii = (int) c;
            if ((ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122) && alpha) {
                interAlphaFlag = true;
            }
            if ((ascii >= 48 && ascii <= 57) && numeric) {
                interNumericFlag = true;
            }
            if (ascii == 32) {
                interNumericFlag = true;
            }
            if (specialChars.indexOf(c) > -1) {
                interSpecialCharFlag = true;
            }
            if (!interAlphaFlag && !interNumericFlag && !interSpecialCharFlag) {
                if (messageTitle.trim().length() != 0) {
                    if (messageContent.trim().length() == 0) {
                        messageContent = "Contains invalid character ' " + c + " '.";
                    }
                }
                return false;
            }
        }
        return true;
    }
    //added By Nilson for Screen Resolution
//    public boolean isDefaultResolution(MainScreenSalesTax main) {
//        Dimension d = main.getSize();
//        double height=d.getHeight();
//        double width=d.getWidth();
//        if (height >=740 && width >=1030){return true;}
//        else {return false;}
//    }

    public boolean isResolution() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        double height = d.getHeight();
        double width = d.getWidth();
        if (height >= 768 && width >= 1024) {
            return true;
        } else {
            return false;
        }
    }

    //for int to string conversion of month
    public String getStrMonth(int mm) {

        String month = (String) MonthObj[mm];
        return month;
    }

    public int manipulateDate(int month, int years, int day) {
        int monthDays = 0;
        switch (month) {
            case 1://January
                monthDays = 31;
                break;
            case 2://February
                if (month == 2 && ((years % 400 == 0) || (years % 4 == 0) && years % 100 != 0)) {
                    monthDays = 29;
                } else {
                    monthDays = 28;
                }
                break;
            case 3://March
                monthDays = 31;
                break;
            case 4://April
                monthDays = 30;
                break;
            case 5://May
                monthDays = 31;
                break;
            case 6://Jun
                monthDays = 30;
                break;
            case 7://July
                monthDays = 31;
                break;
            case 8://August
                monthDays = 30;
                break;
            case 9://September
                monthDays = 31;
                break;
            case 10://Octomber
                monthDays = 30;
                break;
            case 11://November
                monthDays = 31;
                break;
            case 12://December
                monthDays = 31;
                break;
            default:
                monthDays = 0;

        }
        return monthDays;
    }

    public boolean validDate(String chkString) {
        chkString = chkString.trim();
        int i, len = 0;
        int daysPerMonth[] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int day, month, year;
        boolean flag = false;
        try {
            chkString = convertStringtodate(chkString);
            len = chkString.length();
            if (len != 10) {
                flag = false;
            } else {
                char c2 = chkString.charAt(2);
                char c5 = chkString.charAt(5);
                month = Integer.parseInt(chkString.substring(0, 2));
                day = Integer.parseInt(chkString.substring(3, 5));
                year = Integer.parseInt(chkString.substring(6, 10));
                if (month < 1 || day < 1 || year < 1800) {
                    flag = false;
                } else {
                    if (month > 12) {
                        flag = false;
                    } else {
                        if (day > daysPerMonth[month]) {
                            flag = false;
                        } else {
                            if (month == 2 && day == 29 && !(year % 400 == 0 || ((year % 4) == 0 && (year % 100) != 0))) {
                                flag = false;
                            } else {
                                if (c2 != c5 && (c2 != '-' || c2 != '/')) {
                                    flag = false;
                                } else {
                                    flag = true;
                                }
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException nfe) {
            flag = false;
        } catch (Exception e) {
            flag = false;
        }
        if (flag) {
            return true;
        } else {
            return false;
        } //end of else
    }// end of month

    /**  Following method is used to return end date of given month  */
    public static String getEndDate(String strMonth, int dYear) {
        int monthNo = getMonthNo(strMonth);

        java.util.GregorianCalendar g = new java.util.GregorianCalendar(dYear, monthNo, 1);
        java.util.GregorianCalendar tempG = new java.util.GregorianCalendar(dYear, monthNo, g.getActualMaximum(java.util.GregorianCalendar.DAY_OF_MONTH));

        return (tempG.get(java.util.GregorianCalendar.MONTH) + 1) + "-" + tempG.get(java.util.GregorianCalendar.DATE) + "-" + tempG.get(java.util.GregorianCalendar.YEAR);
    }

    /** this method takes input in three letter's of month and return month number , number start from 0  */
    public static int getMonthNo(String strMonth) {
        String mon[] = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        int monthNo = 0;

        for (int i = mon.length; --i >= 0;) {
            if (strMonth.equalsIgnoreCase(mon[i])) {
                monthNo = i;
                break;
            }
        }
        return monthNo;
    }

    public String setDecimal(java.lang.Object obj, int precision) {
        try {
            double dlgVal = Double.parseDouble(String.valueOf(obj));
            return new BigDecimal(dlgVal).setScale(precision, BigDecimal.ROUND_HALF_UP).toString();
        } catch (Exception e) {
            return new BigDecimal(0).setScale(precision, BigDecimal.ROUND_HALF_UP).toString();
        }
    }

    public boolean validateChar(char ch) {
        int k = ch;

        if (k >= 33 && k <= 126) {
            return true;
        } else {
            return false;
        }
    }
    //To Get Return Type
    //private void  Data_Collector(){
    //        if(get_Company_Edit_Mode()){
    //            Vector GAExtra_Data = _model.NJExtraInfo_View();
    //            Iterator Row = GAExtra_Data.iterator();
    //            while(Row.hasNext()){
    //                set_All_Data((Vector)Row.next());
    //            }
    //        }else{
    //
    //        }
    //    }
    // // Set All_Data
    //    public void set_All_Data(Vector Sent_Data){
    //            get_Return_Type=Sent_Data.get(1).toString();
    //    }
    //public String Return_Type = _NJExtraInfo.get_Return_Type();
}
