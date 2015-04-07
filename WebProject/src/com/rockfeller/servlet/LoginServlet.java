package com.rockfeller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 4084705851373300597L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Testing works");
		
		
		String uName = request.getParameter("fName");
		String password = request.getParameter("password");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Welcome Page</title>");
		out.println("</head>");
		out.println("<body bgcolor=\"White\">");
		out.println("<center>");
		out.println("<h2 style=\"font-family: 'Comic Sans Ms', Times, Arial; color: red;\">Welcome:  </h2>");

		try {
			// Step 1: Connect to the database
			ConnectionManager con = new ConnectionManager();
			User nina = new User();
			Connection conn = con.getConnection();
			String sql = "SELECT * FROM `user` where BINARY `username` = ? and BINARY `password` = ?";

			// Step 2: Create prepared statements
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uName); // bind-parameter 1
			pstmt.setString(2, password); // bind-parameter 2

			// Step 3: Execute the query
			ResultSet rs = pstmt.executeQuery();

			if (rs.absolute(1)) { // do not user rs.next() because it will move
									// the cursor to the next row
				String[][] resultArray; 
				
				rs.beforeFirst();
				
				int count = 0;
				while(rs.next())
				{
					count++;
				}
				
				resultArray = new String[count][6];
				
				System.out.println("Total number of rows : "+count);
				
						
				rs.beforeFirst();
				
				
				if(rs.isBeforeFirst())
				{
					System.out.println("Rs is before first!");
				}
				
/*				rs.next();
				System.out.println(rs.getString("id"));
				rs.next();
				System.out.println(rs.getString("id"));*/
				
				
				for(int i=0; i<count; i++)
				{
				    rs.next();
				    int k = 0;
					for(int j=1; j<=6; j++)
					{		
						resultArray[i][k] = rs.getString(j); 
						System.out.println(resultArray[i][k]);
						//Note: you can only have one getString, if you have both one is not read
						//for some reason
						//System.out.println(rs.getString(j));
						k++;
					}
								
				}
				System.out.println("I am out!");
				
				//Test if you can get 1 and 3 
				System.out.println("First: " + resultArray[0][0]);
				System.out.println("Second:" + resultArray[1][0]);
				
				//Send data to user.jsp page
				HttpSession session = request.getSession();
				session.setAttribute("userDetail", resultArray);
				
				String url="/user.jsp";
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher(url);
	
				request.setAttribute("rows", count);
				request.setAttribute("cols", 6);
				rd.forward(request, response);
				
			} else {
				out.println("Invalid Credentials");
			}

		} catch (Exception e) {
			e.getMessage();
		}



	}
}
