package com.wow.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wow.dao.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SubscriberServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
		
                
               // public  String validateIfSubscribed(String email) {
                String queryCheck="";
                String check="";
                String email = request.getParameter("email");
                
                try {
                    
                    
                        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                        Connection con=DriverManager.getConnection("jdbc:odbc:Wise_dsn","","");
                        Statement statement=con.createStatement();
                        String count="";

                        if (email!=null && email.length()>0)    
                        queryCheck = "SELECT * from Subscription WHERE email = '"+email+"'" ;
                        ResultSet rs1 = statement.executeQuery(queryCheck);  

                        if(rs1.next())
                        {
                            //check="Already Subscribed";            
                            out.println("Already Subscribed");
                        }
                        else
                        { 
                            statement.executeUpdate("insert into Subscription values('"+ email +"')");
                        } 

                        count = "Select count(*) from Subscription" ;
                        ResultSet rs = statement.executeQuery(count);
                        int i=0;

                        while(rs.next())
                        {
                            count= rs.getString(1);
                        }

                       // check=count+" Users have already Subscribed ";

			out.println(count+ " Users have already Subscribed ");

			// SubscriberDao dao = new SubscriberDao();
			// String subscriber=dao.validateIfSubscribed(email);

		} 
                catch (Exception e) 
                {
			e.printStackTrace();		
                }

                
           // return email;
          // }
    }

	 
}