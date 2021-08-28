package com.example.servlet_exercise;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

public class HelloServlet extends HttpServlet {
    private String message;

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
    }

    public void destroy() {
    }
}