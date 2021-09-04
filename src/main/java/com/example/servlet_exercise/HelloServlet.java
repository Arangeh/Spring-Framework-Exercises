package com.example.servlet_exercise;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

public class HelloServlet extends HttpServlet {
    private String message;

    public static void main(String[] args) throws SQLException {
        String uname = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/internship";
        String query = "select * from users";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //TODO Aut-generated catch block
            e.printStackTrace();
        }
        //System.out.println("TEST");
        try {
            Connection con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
            	String UserData = "";
            	for(int i = 1; i <= 6; i++){
            		UserData += result.getString(i) + ':';
				}
            	System.out.println(UserData);
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        message = "Greetings!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        out.println("The list of the users is as follows:");
        //code to print the list of users
    }

    public void destroy() {
    }
}