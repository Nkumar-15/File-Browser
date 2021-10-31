package BackupJNI_Restore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "restorejnipre",value = "/restorejnipre")

public class RestoreJNIpredetails extends HttpServlet {

    public static String filename;
    public static String pPath;
    public static String destdir;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        filename=request.getParameter("filename");
        pPath=request.getParameter("ppath");
        destdir=request.getParameter("destpath");
        response.sendRedirect("restoredataform.jsp");

    }
}
