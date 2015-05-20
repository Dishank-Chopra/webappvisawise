
<%@page import="java.sql.*, java.util.* "%>
<html>


    <head>

        <script type="text/javascript" src="VisaSubscripts.js"></script>

        <title> Visa_Wise </title>
 
        <link rel="stylesheet" type="text/css" href="SubscriberStyle.css">

    </head>
    <body>

        <table>        
            <tr>
        <!-- <form action="SubscriberServlet" method="post" >    --> 

        <form name="form" method="post" onSubmit="return ValidateEmail()" >  

            <div  style="height:665px; width:1365px; background:#E0FEFE; top:0px; left: 0px; position: absolute;  "   >
                
                <%
                    // out.println("abc");
                
            String queryCheck = "";
            String check = "";

            String emailId = request.getParameter("email");

            try {

                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection con = DriverManager.getConnection("jdbc:odbc:Wise_dsn", "", "");
                Statement statement = con.createStatement();
                String count = "";

                count = "Select count(*) from Subscription";
               // ResultSet rs = statement.executeQuery(count);
               // int i = 0;

                //while (rs.next()) {
                  //  count = rs.getString(1);
                //}
                
                //out.println(" " + count + " Users have been Subscribed ");
                
                if (emailId != null && emailId.length() > 0) {
                    queryCheck = "SELECT * from Subscription WHERE email = '" + emailId + "'";
                 
                ResultSet rs1 = statement.executeQuery(queryCheck);

                if (rs1.next()) {
                    check = "<br>  <br> You have Already Subscribed ";
                    // out.println("You have already Subscribed");

                } else {
                    statement.executeUpdate("insert into Subscription values('" + emailId + "')");
                    out.println("  ThankYou for Subscribing!!  " + " <br> <br>" );   
                }
                }
                ResultSet rs = statement.executeQuery(count);
                  

                while (rs.next()) {
                    count = rs.getString(1);
                }
                
                out.println("  " + count + " Users have been Subscribed ");
                
            
                                // check=count+" Users have already Subscribed ";
                // out.println(" " + count + " Users have been Subscribed ");
                out.println(" " + check);

                                // SubscriberDao dao = new SubscriberDao();
                // String subscriber=dao.validateIfSubscribed(email);
            } catch (Exception e) {
                out.println(e.toString());
            }

        %>
                <div style="height:160px; width:350px; background:#00a5a5; top:200px; left: 100px; position: absolute;
                     border-radius:10px 10px 10px 10px;   "   >

                    <div class="box" style="position: absolute; top: 20px; height: 50px; width: 50px; left: 18px"  >
                        <label id="label" for="name" style="font-weight: bold; color: #333333 ">Email</label>
                        <input id="Text" type="text" name="email" value="" class="textbox" required/>
                    </div>

                    <input id="Button" type="submit" value="Subscribe" style="position: absolute; height: 40px; width: 310px; top: 90px; left: 18px; border: 0px; background-color: #333333; color: #FFFFFF; font-family: Arial, Helvetica, sans-serif; font-size: x-large; border-radius:5px 5px 5px 5px;" />

                </div>
            </div>
        
       
        </form>
        </tr>
    </table>
        
    </body>
</html>