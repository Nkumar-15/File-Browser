package com.example.FileBrowser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "prevnextdir", value = "/prevnextdir")
public class PrevNextDir extends HttpServlet {

    FileBrowser f2=new FileBrowser();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //extra

        String path=request.getParameter("prevdir");
        if(!path.equals("next"))
        {
            FileBrowser.nextpath=FileBrowser.ppath;
            FileBrowser.ppath=path;


            FileBrowser.prevpath=f2.Homepath;
            //System.out.println(f2.ppath);


            path = path.replaceAll("[^\\w\\s]"," ");

            String[] stringarray = path.split("\\s");
//        for(int i=2; i< stringarray.length-1; i++)
//        {
//            f2.prevpath=f2.prevpath+stringarray[i]+'\\';
//
//        }

            if(stringarray.length>2)
            {
                for(int i=2; i< stringarray.length-1; i++)
                {
                    f2.prevpath=f2.prevpath+stringarray[i]+'\\';

                }
            }

            System.out.println("prevpath "+ FileBrowser.prevpath);

        }
        else
        {
            FileBrowser.ppath=FileBrowser.nextpath;
        }

        //request.setAttribute("sortflag",sortflag);
        response.sendRedirect("index.jsp");
    }
}
