package Backup;





import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "backupsorting", value = "/backupsorting")
public class Sorting extends HttpServlet {
    Backupdata f2=new Backupdata();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //extra
        f2.sortval=request.getParameter("sortval");
        //System.out.print(f2.sortval);

        if(!f2.sortval.equals("none"))
        {
            System.out.println(f2.sortval);
            f2.sortsearchflag=2;
        }
        else if (f2.sortval.equals("none"))
        {
            f2.sortsearchflag=1;
        }
        response.sendRedirect("backupdata.jsp");
        //request.setAttribute("sortflag",sortflag);


    }
}
