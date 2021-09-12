package com.example.servlet_exercise;

import com.fasterxml.jackson.databind.*;

import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.stream.Collectors;

public class HelloServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();

    public void init() {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String userString = "";
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            userString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        }
        ObjectMapper om = new ObjectMapper();
        //Convert the Json String to proper Java Object type
        User u = om.readValue(userString, User.class);
        u = userDAO.addUser(u);
        String userJson = om.writeValueAsString(u);
        //Return the output in JSON format
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(userJson);
        out.close();
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String userString = "";
        if ("PUT".equalsIgnoreCase(request.getMethod())) {
            userString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        }
        ObjectMapper om = new ObjectMapper();
        //Convert the Json String to proper Java Object type
        User u = om.readValue(userString, User.class);
        userDAO.updateUser(u);
        String userJson = om.writeValueAsString(u);
        //Display the Updated User in JSON format
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(userJson);
        out.close();
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String userString = "";
        if ("DELETE".equalsIgnoreCase(request.getMethod())) {
            userString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        }
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(userString);
        Long id = jsonNode.get("id").asLong();
        userDAO.deleteUser(id);
        //According to the REST API convention, DELETE method doesn't have an output, however you can uncomment
        // the following line of code if you want to get an output upon using DELETE method
//        out.println("Deleted user with the following id : " +  Long.toString(id));
        out.close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        List<User> users = userDAO.listUser();
        //Code to print the list of Users
        ObjectMapper om = new ObjectMapper();
        String usersJson = "";
        if (users != null) {
            usersJson = om.writeValueAsString(users);
        }
        //Return the output in JSON format
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(usersJson);
        out.close();
    }

    public void destroy() {
    }
}