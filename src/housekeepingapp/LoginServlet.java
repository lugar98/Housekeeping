package housekeepingapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private String mymsg;

	   public void init() throws ServletException {
	      mymsg = "Hello World!";
	   }

	   protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
		{
			PrintWriter pw=res.getWriter();
			res.setContentType("text/html");
			
			String user=req.getParameter("userName");
			String pass=req.getParameter("userPassword");
			
			try { 
				  
	            // Initialize the database 
	            Connection con = DatabaseConnection.initializeDatabase(); 
	  
	            // Create a SQL query to insert data into demo table 
	            // demo table consists of two columns, so two '?' is used 
	            PreparStatement st = con 
	                   .prepareStatement("select password from person where UserName='" + user + "' and password=';" + pass + "';"); 
	  
	            
	  
	            // Execute the insert command using executeUpdate() 
	            // to make changes in database 
	            ResultSet rs = st.executeUpdate(); 
	           
	            while(rs.next()){
	                //Retrieve by column name
	                int id  = rs.getInt("id");
	                int age = rs.getInt("age");
	                String first = rs.getString("first");
	                String last = rs.getString("last");

	                //Display values
	                System.out.print("ID: " + id);
	                System.out.print(", Age: " + age);
	                System.out.print(", First: " + first);
	                System.out.println(", Last: " + last);
	             }
	             rs.close();
	             
	  
	            // Close all the connections 
	            st.close(); 
	            con.close(); 
	  
	            // Get a writer pointer  
	            // to display the successful result 
	            PrintWriter out = response.getWriter(); 
	            out.println("<html><body><b>Successfully Inserted"
	                        + "</b></body></html>"); 
	        } 
	        catch (Exception e) { 
	            e.printStackTrace(); 
	        } 


			if(user != null && user.equals("java4s") && pass != null && pass.equals("java4s")) 
	                 pw.println("<h1>Login Success...!</h1>"); 
	        else
	                 pw.println("Login Failed...!");
			
			
			
			pw.close();

		}

	   public void destroy() {
	      /* leaving empty for now this can be
	       * used when we want to do something at the end
	       * of Servlet life cycle
	       */
	   }
}
