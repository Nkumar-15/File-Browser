package FileBrowserDisplay;

import com.example.FileBrowser.FileBrowser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

//@WebServlet(name = "reloadsql", value = "/reloadsql")

public class CallFileBrowserDisplayJNI {

    public void call(String searchdir)
    {
//        try {
//            HelloJNI.main(null);
//            System.out.println("Hi");
//            wait();
//            System.out.println("Hi1");
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        String dir=se;
        FileBrowserdisplay fd= new FileBrowserdisplay();
        fd.toFind(searchdir);

    }


//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//    {
//        try
//        {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/filebrowser", "root", "toor");
////            Statement st=conn.createStatement();
////            st.executeUpdate("insert into filedata(Filename,Parentpath,Foldercount,Filecount)values('"+filename+"','"+ppath+"','"+foldc+"','"+filec+"')");
//
//            PreparedStatement stmt=conn.prepareStatement("DELETE from filedata");
//            stmt.executeUpdate();
//        }
//        catch(Exception e)
//        {
//            //System.out.print(e);
//            e.printStackTrace();
//        }
//
//        String dir="E:";
//        FileBrowserdisplay fd= new FileBrowserdisplay();
//        fd.toFind(dir);
//
//    }
}
