package Backup;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "backupprevnext", value = "/backupprevnext")
public class PrevNext extends HttpServlet {

    Backupdata f1=new Backupdata();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {


        String val=request.getParameter("prevnext");
        //System.out.print(val);
        if(val.equals("next"))
        {
            next();
            if(f1.start>=0)
            {

                response.sendRedirect("backupdata.jsp");
            }


        }
        if(val.equals("prev"))
        {
            prev();
            response.sendRedirect("backupdata.jsp");
        }
    }

    void next()  {
        f1.start=f1.start+10;
        f1.end=f1.end+10;
    }

    void prev()  {

        f1.start=f1.start-10;
        f1.end=f1.end-10;
        if(f1.start<0)
        {
            next();
        }
    }
}
