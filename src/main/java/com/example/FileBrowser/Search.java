package com.example.FileBrowser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "searching", value = "/search")
public class Search extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String sdata=request.getParameter("searchdata");
        FileBrowser.searchdata=sdata;
        FileBrowser.sortsearchflag=3;

        response.sendRedirect("index.jsp");

    }
}
