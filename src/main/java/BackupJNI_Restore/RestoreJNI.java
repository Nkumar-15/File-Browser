package BackupJNI_Restore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "restore",value = "/restore")
public class RestoreJNI extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

//        System.out.println("Hioutside");
        String filename=request.getParameter("filename");
        String pPath=request.getParameter("ppath");
        String destpath=request.getParameter("destpath");
        String pass=request.getParameter("pass");

        BackupJNI b=new BackupJNI();
//        String filename="hello";
//        String pPath="E:\\";
//        String destpath="F:\\";
//        String pass="123";
        boolean flag=b.passCheck(filename,pPath,destpath,pass);
        if(flag==true)
        {
            System.out.println("Password true");
            String zipname=destpath+filename+".zip";
            //String homePath=destpath.substring(0,3);
            //String sourcetocopy=homePath+filename;
            Restorecopydata rc=new Restorecopydata();
            rc.restorecopydata(zipname,pPath);
            response.sendRedirect("backupdata.jsp");

        }
        else {
//            response.sendRedirect("backupdata.jsp?invalid");
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("location='backupdata.jsp?invalid';");
            out.println("alert('Invalid Password');");
            out.println("</script>");

        }






    }
}
