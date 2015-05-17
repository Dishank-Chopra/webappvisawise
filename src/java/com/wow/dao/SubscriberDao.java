package com.wow.dao;

import java.sql.*;

public class SubscriberDao {
    

    public  String validateIfSubscribed(String a) {
        
        String queryCheck="";
        String x="";
        
        try
        {

            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:Wise_dsn","","");
            Statement statement=con.createStatement();
            String count="";
            
            if (a!=null && a.length()>0)    
            queryCheck = "SELECT * from Subscription WHERE email = '"+a+"'" ;
            ResultSet rs1 = statement.executeQuery(queryCheck);  
            
            if(rs1.next())
            {
                x="Already Subscribed";            
                            //out.println("Already Subscribed");
            }
            else
            { 
                statement.executeUpdate("insert into Subscription values('"+ a +"')");
            } 
            
            count = "Select count(*) from Subscription" ;
            ResultSet rs = statement.executeQuery(count);
            int i=0;

            while(rs.next())
            {
                count= rs.getString(1);
            }

            x=count+" Users have already Subscribed ";

            //   out.println("No of users subscribed "+ count);
            //  session.setAttribute("count", count);
            // response.sendRedirect("newjsp.jsp");
        }
        catch(Exception ee)
        {
            System.out.println(ee.toString());
        }
        
        return a;
        
    }
}
