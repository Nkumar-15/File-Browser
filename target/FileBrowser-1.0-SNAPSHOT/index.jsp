<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16-05-2021
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.example.FileBrowser.FileBrowser" %>



<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>File Browser</title>
        <link rel="icon" href="img/favicon.png"size="64x64">
        <link rel="stylesheet" href="./style.css">
        <!-- Bootstrap -->

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" charset="utf-8"></script>


    </head>
    <body>
        <header>
            <div class="left_area">
                <h3>FILE <span>BROWSER</span></h3>
            </div>
        </header>


        <%


            FileBrowser f=new FileBrowser();
            f.reloadsql();

            ResultSet rs=null;
//            System.out.println("Fullpath :"+FileBrowser.ppath);
//            System.out.println("Prev Path : "+FileBrowser.prevpath);
//            System.out.println("Prev Path : "+FileBrowser.nextpath);
            if(FileBrowser.sortsearchflag ==1)
            {

                rs=f.display(f.start,f.end);
                //System.out.println("In display");

            }
            else if(FileBrowser.sortsearchflag ==2)
            {
                rs=f.sortdisplay(f.start,f.end);
                //System.out.println("In sortdisplay");
            }
            else if(FileBrowser.sortsearchflag==3)
            {
                rs=f.searchdisplay(f.start,f.end);
                FileBrowser.sortsearchflag=1;
            }



            //System.out.print(f.start);
            //System.out.println(f.end);
            int flag=0;
        %>


        <div class="sidebar" style="text-align: center;">
            <a style="margin-top: 60px;padding: 10px;" href="index.jsp"><i class="fas fa-desktop "></i></a>
            <a style="margin-top: 60px;padding: 10px;" href="backupdata.jsp"><i class="fas fa-recycle "></i></a>
        </div>

        <div class="content">


            <div class="row">
                <div class="col-sm-6">
                    <div class="card1">
                        <form action="search" method="post">
                            <input class="search" type="text" id="myInput" name="searchdata"  placeholder="Search for files or folders.." title="Type in a name">
                            <button type="submit" class="btn  btn-outline-dark search_button"  >Search</button>
                        </form>



                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="card1">
                        <div class="dropdown">
<%--                            <button onclick="dropFunction()" class="dropbtn">choose</button>--%>
<%--                            <div id="myDropdown" class="dropdown-content">--%>
<%--                                <a href="#">Link 1</a>--%>
<%--                                <a href="#">Link 2</a>--%>
<%--                                <a href="#">Link 3</a>--%>
<%--                            </div>--%>
                            <form action="sort" method="POST">
                                <select class="selectsort" name="sortval"  onchange="this.form.submit()">
                                    <option class="selectsortopt" hidden value="choose a value">Choose..</option>
                                    <option class="selectsortopt" value="none">None</option>

                                    <optgroup class="selectsortopt" label="Filename">
                                        <option class="selectsortopt" value="Filename ASC">Ascending Order</option>
                                        <option class="selectsortopt" value="Filename DESC">Descending Order</option>
                                    </optgroup>
                                    <optgroup class="selectsortopt" label="Parentpath">
                                        <option class="selectsortopt" value="Parentpath ASC">Ascending Order</option>
                                        <option class="selectsortopt" value="Parentpath DESC">Descending Order</option>
                                    </optgroup>
                                    <optgroup class="selectsortopt" label="Foldercount">
                                        <option class="selectsortopt" value="Foldercount ASC">Ascending Order</option>
                                        <option class="selectsortopt" value="Foldercount DESC">Descending Order</option>
                                    </optgroup>
                                    <optgroup class="selectsortopt" label="Filecount">
                                        <option class="selectsortopt" value="Filecount ASC">Ascending Order</option>
                                        <option class="selectsortopt" value="Filecount DESC">Descending Order</option>
                                    </optgroup>
                                </select>

                            </form>
                        </div>
                    </div>
                </div>
            </div>



            <div class="row">
                <div class="col-sm-6">
                    <div class="card1">
                        <p class="pathoffile" style="font-size: 20px"><%= f.ppath %></p>

                    </div>

                </div>
                <div class="col-sm-6">
                    <div class="button_prevnextdir">
                        <form action="prevnextdir" method="POST">
                            <button type="submit" class="btn  btn-outline-info pathbutton1" name="prevdir"  value="<%= f.prevpath %>"><</button>
                            <button type="submit" class="btn  btn-outline-info pathbutton1" name="prevdir"  value="next">></button>
                        </form>

                    </div>
                </div>
            </div>





            <table id="myTable" style="width: 80vw; margin-bottom: 30px;" class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">File Type</th>
                    <th scope="col">File Name</th>
                    <th scope="col">Parent path</th>
                    <th scope="col">Folder count</th>
                    <th scope="col">File count</th>
                    <th scope="col">Backup</th>
                </tr>
                </thead>
                <tbody>
                <% while(rs.next()){  flag=1;%>
                <tr>


<%--                    <th scope="row">1</th>--%>
                    <% if(! rs.getString(1).contains(".")) { %>
                        <form action="display" method="post">
                            <td><button style="border: 0" type="submit" name="ppath" value="<%= rs.getString(2)+ rs.getString(1) %>"><i class="far fa-folder"></i></button></td>
                        </form>

                    <% }else {%>
                        <td><i class="far fa-file"></i></td>
                    <% } %>
<%--                    <form action="prev-next" method="POST">--%>
<%--                        <td><input type="submit" name="prevnext" hidden value="<%= rs.getString(1) %>"><%= rs.getString(1) %></td>--%>
<%--                    </form>--%>
                    <td><%= rs.getString(1) %></td>
                    <td><%= rs.getString(2) %></td>
                    <td><%= rs.getInt(3) %></td>
                    <td><%= rs.getInt(4) %></td>
                    <form action="calljnipre" method="POST">
                        <input name="filename" value="<%= rs.getString(1)%>" hidden>
                        <input name="ppath" value="<%= rs.getString(2)%>" hidden>
                    <td><button type="submit" class="show-popup" style="border:0" data-toggle="modal" data-target="#MyModal<%= rs.getString(1) %>" name="hellojni" ><i class="fas fa-file-upload"></i></button></td>
                    </form>
                </tr>
<%--                <div id="#MyModal<%= rs.getString(1) %>" class="modal fade"  role="dialog">--%>
<%--                    <div class="popup" >--%>
<%--                        <div class="close-btn" class="close" data-dismiss="modal" >&times;</div>--%>
<%--                        <form action="calljni" method="POST">--%>
<%--                            <div class="form">--%>
<%--                                <h2>Backup Data</h2>--%>
<%--                                <input name="filename" value="<%= rs.getString(1)%>" hidden>--%>
<%--                                <input name="ppath" value="<%= rs.getString(2)%>" hidden>--%>
<%--                                <div class="form-element">--%>
<%--                                    <label >Destination Path</label>--%>
<%--                                    <input type="text" name="destpath"  placeholder="Enter path">--%>
<%--                                </div>--%>
<%--                                <div class="form-element">--%>
<%--                                    <label >Password</label>--%>
<%--                                    <input type="password"  name="pass" placeholder="Enter password">--%>
<%--                                </div>--%>
<%--                                <div class="form-element">--%>
<%--                                    <button type="submit">Backup</button>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </form>--%>

<%--                    </div>--%>

<%--                </div>--%>


                <% } %>

                <% if(flag==0){%>
                <tr>
                    <td>No Records found</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <% } %>
                </tbody>
            </table>




            <div class="button_prevnext">
                <form action="prev-next" method="POST">
                    <button type="submit" class="btn btn-primary btn-sm btn1" name="prevnext"  value="prev">&#8592; Prev</button>
                    <button type="submit" class="btn btn-secondary btn-sm btn2" name="prevnext"  value="next">Next &#8594;</button>
                </form>

            </div>

        </div>

        <script>
            function myFunction() {
                var input, filter, table, tr, td, i, txtValue;
                input = document.getElementById("myInput");
                filter = input.value.toUpperCase();
                table = document.getElementById("myTable");
                tr = table.getElementsByTagName("tr");
                for (i = 0; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[0];
                    if (td) {
                        txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }
            }


            function dropFunction() {
                document.getElementById("myDropdown").classList.toggle("show");
            }

            // Close the dropdown if the user clicks outside of it
            window.onclick = function(event) {
                if (!event.target.matches('.dropbtn')) {
                    var dropdowns = document.getElementsByClassName("dropdown-content");
                    var i;
                    for (i = 0; i < dropdowns.length; i++) {
                        var openDropdown = dropdowns[i];
                        if (openDropdown.classList.contains('show')) {
                            openDropdown.classList.remove('show');
                        }
                    }
                }
            }


            // document.querySelector(".show-popup").addEventListener("click",function(e){
            //     document.querySelector(".popup").classList.add("active");
            //     console.log(e.target);
            // });
            // document.querySelector(".popup .close-btn").addEventListener("click",function(){
            //     document.querySelector(".popup").classList.remove("active");
            // });
            // function showpop(e){
            //     document.querySelector(".popup").classList.add("active");
            //     // var v=e.target;
            //     document.querySelector(".popup .close-btn").addEventListener("click",function(){
            //         document.querySelector(".popup").classList.remove("active");
            //     });
            //
            //
            // }


        </script>
    </body>
</html>
