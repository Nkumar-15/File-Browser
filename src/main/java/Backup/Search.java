package Backup;

import com.example.FileBrowser.FileBrowser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "backupsearch", value = "/backupsearch")
public class Search extends HttpServlet  {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String sdata=request.getParameter("searchdata");
        Backupdata.searchdata=sdata;
        Backupdata.sortsearchflag=3;

        response.sendRedirect("backupdata.jsp");

    }
}


