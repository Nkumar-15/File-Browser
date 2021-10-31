package BackupJNI_Restore;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "calljnipre",value = "/calljnipre")

public class CallJNIpredetails extends HttpServlet{

    public static String filename;
    public static String pPath;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        filename=request.getParameter("filename");
        pPath=request.getParameter("ppath");
        response.sendRedirect("backupform.jsp");

    }
}
