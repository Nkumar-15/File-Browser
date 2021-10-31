package com.example.FileBrowser;

import FileBrowserDisplay.CallFileBrowserDisplayJNI;
import FileBrowserDisplay.FileBrowserdisplay;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;

@WebServlet(name = "display", value = "/display")
public class FileBrowser extends HttpServlet {
    public static int start=0;
    public static int end=10;
    public int count;
    public static int sortsearchflag = 1;
    public static String Homepath = "E:\\";
    public static String ppath = "E:\\";
    public static String prevpath = "E:\\" ;
    public static String nextpath = "E:\\";
    public static String searchdata ;
    public static int sqlload=1;
    //extra
    static String  sortval;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //extra
        //sortval=request.getParameter("sortval");

        String path=request.getParameter("ppath");
        prevpath=ppath;
        ppath=path;
        ppath=ppath+'\\';
        nextpath=ppath;

        //System.out.print(ppath);

//        if(!sortval.equals("none"))
//        {
//            System.out.println(sortval);
//            sortflag=true;
//        }
//        else if (sortval.equals("none"))
//        {
//            sortflag=false;
//        }
        response.sendRedirect("index.jsp");
        //request.setAttribute("sortflag",sortflag);
   }



    public void reloadsql()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/filebrowser", "root", "toor");
//            Statement st=conn.createStatement();
//            st.executeUpdate("insert into filedata(Filename,Parentpath,Foldercount,Filecount)values('"+filename+"','"+ppath+"','"+foldc+"','"+filec+"')");

            PreparedStatement stmt=conn.prepareStatement("DELETE from filedata");
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            //System.out.print(e);
            e.printStackTrace();
        }

        CallFileBrowserDisplayJNI c=new CallFileBrowserDisplayJNI();
        c.call("E:");

    }


    public ResultSet display(int start , int end)
    {


        ResultSet rs=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/filebrowser", "root", "toor");
//            Statement st=conn.createStatement();
//            st.executeUpdate("insert into filedata(Filename,Parentpath,Foldercount,Filecount)values('"+filename+"','"+ppath+"','"+foldc+"','"+filec+"')");

            PreparedStatement stmt=conn.prepareStatement("select * from filedata where Parentpath=? Limit ?,? ");

            stmt.setString(1,ppath);
            stmt.setInt(2,start);
            stmt.setInt(3,end);

            rs=stmt.executeQuery();
            System.out.println("Hello in display");
            return rs;


        }
        catch(Exception e)
        {
            //System.out.print(e);
            e.printStackTrace();
        }

        return rs;

    }

    public ResultSet sortdisplay(int start, int end)
    {
        ResultSet rs=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/filebrowser", "root", "toor");
//            Statement st=conn.createStatement();
//            st.executeUpdate("insert into filedata(Filename,Parentpath,Foldercount,Filecount)values('"+filename+"','"+ppath+"','"+foldc+"','"+filec+"')");
            System.out.println("In sortdisplay "+sortval);
            PreparedStatement stmt=conn.prepareStatement("select * from filedata where Parentpath=? order by "+sortval+" Limit ?,?");
            //stmt.setString(1,sortval);
            stmt.setString(1,ppath);
            stmt.setInt(2,start);
            stmt.setInt(3,end);

            rs=stmt.executeQuery();

//            HttpServletRequest request = null;
//            HttpSession session=request.getSession();
//            session.setAttribute("sort",1);
//            HttpServletResponse response = null;
//            response.sendRedirect("backend.jsp");
            return rs;
        }
        catch(Exception e)
        {
            //System.out.print(e);
            e.printStackTrace();
        }

        return rs;

    }


    public ResultSet searchdisplay(int start, int end)
    {
        ResultSet rs=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/filebrowser", "root", "toor");
//            Statement st=conn.createStatement();
//            st.executeUpdate("insert into filedata(Filename,Parentpath,Foldercount,Filecount)values('"+filename+"','"+ppath+"','"+foldc+"','"+filec+"')");
            System.out.println("In sortdisplay "+sortval);
            PreparedStatement stmt=conn.prepareStatement("select * from filedata where Filename= ? order by "+sortval+" Limit ?,?");
            //stmt.setString(1,sortval);
            stmt.setString(1,searchdata);
            stmt.setInt(2,start);
            stmt.setInt(3,end);

            rs=stmt.executeQuery();

//            HttpServletRequest request = null;
//            HttpSession session=request.getSession();
//            session.setAttribute("sort",1);
//            HttpServletResponse response = null;
//            response.sendRedirect("backend.jsp");
            return rs;
        }
        catch(Exception e)
        {
            //System.out.print(e);
            e.printStackTrace();
        }

        return rs;

    }
}
