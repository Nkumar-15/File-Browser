package Backup;

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





@WebServlet(name = "backupdisplay", value = "/backupdisplay")
public class Backupdata extends HttpServlet {
    public static int start=0;
    public static int end=10;
    public int count;
    public static String ppath = "E:\\";

    public static int sortsearchflag = 1;
    public static String searchdata ;
    //extra
    static String  sortval;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        response.sendRedirect("backupdata.jsp");
        //request.setAttribute("sortflag",sortflag);


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

            PreparedStatement stmt=conn.prepareStatement("select DISTINCT  * from backupdata Limit ?,? ");

//            stmt.setString(1,ppath);
            stmt.setInt(1,start);
            stmt.setInt(2,end);
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
            PreparedStatement stmt=conn.prepareStatement("select DISTINCT * from backupdata order by "+sortval+" Limit ?,?");
            //stmt.setString(1,sortval);

            stmt.setInt(1,start);
            stmt.setInt(2,end);

            rs=stmt.executeQuery();
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
            System.out.println("In sortdisplay "+searchdata);
            PreparedStatement stmt=conn.prepareStatement("select * from backupdata where Filename= ? order by "+sortval+" Limit ?,?");
            //stmt.setString(1,sortval);
            stmt.setString(1,searchdata);
            stmt.setInt(2,start);
            stmt.setInt(3,end);

            rs=stmt.executeQuery();
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
