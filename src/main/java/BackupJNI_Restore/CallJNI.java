package BackupJNI_Restore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;

@WebServlet(name = "calljni",value = "/calljni")
public class CallJNI extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
//        String tok=request.getParameter("hellojni");
//        System.out.println(tok);
//        BackupJNI b=new BackupJNI();
//        Backupcopydata bc=new Backupcopydata();
////        System.out.println("Hi object");
//        String parentfolderpath="";
//        parentfolderpath=b.Fileupload(tok);
////        b.toFind(parentfolderpath);
////        b.backupcopydata("E:\\hello");
//
////        b.deletefromfiledata(tok,parentfolderpath);
//        bc.backupcopydata(parentfolderpath);
//        System.out.println(parentfolderpath);
//        System.out.println("Hioutside");

        BackupJNI b=new BackupJNI();
        CallJNIpredetails cd=new CallJNIpredetails();
//        String Filename="hello";
//        String pPath="E:\\";
//        String pass="123";
//        String destdir="F:\\";

//        String Filename=CallJNIpredetails.filename;
//        String pPath=CallJNIpredetails.pPath;
        String Filename=request.getParameter("filename");
        String pPath=request.getParameter("ppath");
        String destdir=request.getParameter("destpath");
        String pass=request.getParameter("pass");
//        System.out.println(Filename+" "+pPath+" "+destdir+" "+pass);

        b.backupDataCopyFileupload(Filename,pPath,destdir,pass);
//
        String zipname=destdir+Filename+".zip";
//        String homePath=pPath.substring(0,3);
//        String sourcetocopy=homePath+Filename+".zip";
        String filetobezipped=pPath+Filename;
//        System.out.println(zipname+" "+filetobezipped+" "+sourcetocopy+" "+destdir);
//
//
//        BackupJNI b=new BackupJNI();
//        b.backupDataCopyFileupload(Filename,pPath,zipname,pass);
        Backupcopydata bc=new Backupcopydata();
        bc.backupcopydata(zipname,filetobezipped);

        response.sendRedirect("index.jsp");

    }
}
