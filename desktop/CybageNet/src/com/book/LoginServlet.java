package com.book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.naming.ldap.Rdn;
import javax.servlet.RequestDispatcher;
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
	private static final long serialVersionUID = 1L;
	Connection con;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				
				 con = DriverManager.getConnection("jdbc:mysql://localhost/test","root","root");
				 System.out.println("Connected Successfully");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			String str=request.getParameter("uname");
			String str1=request.getParameter("pass");
			PreparedStatement st;
			try 
			{
				st = con.prepareStatement("select role from Login where username=? and password=?");
				st.setString(1, str);
				st.setString(2, str1);
				ResultSet rs =  st.executeQuery();
				if(rs.next())
				{
					System.out.println(rs.getString(1));
					if(rs.getString(1).equals("admin"))
					{
							response.sendRedirect("LoginFilter");
					}
					else
					{
							response.sendRedirect("LoginFilter");
						
					}	
			}
			else
			{
				response.sendRedirect("ReLogin.html");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}
}
