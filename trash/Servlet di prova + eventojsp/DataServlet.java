package controller;

import java.io.*;

import java.util.*;

import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class DataServlet extends HttpServlet{

  private ServletConfig config;

  //Setting JSP page
  String page="DataPage.jsp";

  public void init(ServletConfig config) throws ServletException{

  this.config = config;
}

  public void doGet(HttpServletRequest request, HttpServletResponse response)

    throws ServletException,IOException
{

  PrintWriter out = response.getWriter();

  //Establish connection to MySQL database
  String connectionURL = "jdbc:mysql://127.0.0.1/mercurydb";

  Connection connection = null;
  ResultSet rs;
  response.setContentType("text/html");

  
  
  List dataList = new ArrayList(); 

  try {

 // Load the database driver
  Class.forName("com.mysql.cj.jdbc.Driver");
  connection = DriverManager.getConnection(connectionURL, "root", "intecs"); 

  //Select the data from the database

  String sql = "SELECT * from evento";

  Statement s = connection.createStatement();

  s.executeQuery (sql);

  rs = s.getResultSet();

  while (rs.next ()){

  //Add records into data list
  dataList.add(rs.getString("nome"));
  dataList.add(rs.getString("Descrizione"));


  }

  rs.close ();

  s.close ();

  }catch(Exception e){

  System.out.println("Exception is ;"+e);

  }

  request.setAttribute("data",dataList);

  //Disptching request

  RequestDispatcher dispatcher = request.getRequestDispatcher(page);

  if (dispatcher != null){

  dispatcher.forward(request, response);

  } 

  }

}