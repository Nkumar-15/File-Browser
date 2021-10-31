package com.example.FileBrowser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "prevnext", value = "/prev-next")
public class PrevNext extends HttpServlet {

    FileBrowser f1=new FileBrowser();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {


        String val=request.getParameter("prevnext");
        //System.out.print(val);
        if(val.equals("next"))
        {
            next();
            if(f1.start>=0)
            {

                response.sendRedirect("index.jsp");
            }


        }
        if(val.equals("prev"))
        {
            prev();
            response.sendRedirect("index.jsp");
        }



    }

     void next()  {
        f1.start=f1.start+10;
        f1.end=f1.end+10;
        //System.out.print("Hello");
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
