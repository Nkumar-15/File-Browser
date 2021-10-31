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
<%@ page import="Backup.Backupdata" %>


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
    <style>

        /*POP up*/
        .center {
            position:absolute;
            top:50%;
            left:50%;
            transform:translate(-50%,-50%);
        }
        .center button {
            padding:10px 20px;
            font-size:15px;
            font-weight:600;
            color:#222;
            background:#f5f5f5;
            border:none;
            outline:none;
            cursor:pointer;
            border-radius:5px;
        }
        .popup {
            position:absolute;
            top:-150%;
            left:50%;
            opacity:0;
            transform:translate(-50%,-50%) scale(1.25);
            width:380px;
            padding:20px 30px;
            background:#fff;
            box-shadow:2px 2px 5px 5px rgba(0,0,0,0.15);
            border-radius:10px;
            transition:top 0ms ease-in-out 200ms,
            opacity 200ms ease-in-out 0ms,
            transform 200ms ease-in-out 0ms;
        }
        .popup.active {
            top:50%;
            opacity:1;
            transform:translate(-50%,-50%) scale(1);
            transition:top 0ms ease-in-out 0ms,
            opacity 200ms ease-in-out 0ms,
            transform 200ms ease-in-out 0ms;
        }
        .popup .close-btn {
            position:absolute;
            top:10px;
            right:10px;
            width:15px;
            height:15px;
            background:#888;
            color:#eee;
            text-align:center;
            line-height:15px;
            border-radius:15px;
            cursor:pointer;
        }
        .popup .form h2 {
            text-align:center;
            color:#222;
            margin:10px 0px 20px;
            font-size:25px;
        }
        .popup .form .form-element {
            margin:15px 0px;
        }
        .popup .form .form-element label {
            font-size:14px;
            color:#222;
        }
        .popup .form .form-element input[type="text"],
        .popup .form .form-element input[type="password"] {
            margin-top:5px;
            display:block;
            width:100%;
            padding:10px;
            outline:none;
            border:1px solid #aaa;
            border-radius:5px;
        }
        .popup .form .form-element input[type="checkbox"] {
            margin-right:5px;
        }
        .popup .form .form-element button {
            width:100%;
            height:40px;
            border:none;
            outline:none;
            font-size:16px;
            background:#222;
            color:#f5f5f5;
            border-radius:10px;
            cursor:pointer;
        }
        .popup .form .form-element a {
            display:block;
            text-align:right;
            font-size:15px;
            color:#1a79ca;
            text-decoration:none;
            font-weight:600;
        }
    </style>

</head>
<body>
<header>
    <div class="left_area">
        <h3>FILE <span>BROWSER</span></h3>
    </div>
</header>


<%
    //int i=(int) session.getAttribute("sort");
//    System.out.println(session.getAttribute("sort"));

    Backupdata f=new Backupdata();

    ResultSet rs=null;
//    System.out.println("Fullpath :"+FileBrowser.ppath);
//    System.out.println("Prev Path : "+FileBrowser.prevpath);
//    System.out.println("Prev Path : "+FileBrowser.nextpath);
    if(Backupdata.sortsearchflag ==1)
    {
        rs=f.display(f.start,f.end);
        //System.out.println("In display");

    }
    else if(Backupdata.sortsearchflag ==2)
    {
        rs=f.sortdisplay(f.start,f.end);
        //System.out.println("In sortdisplay");
    }
    else if(Backupdata.sortsearchflag==3)
    {
        rs=f.searchdisplay(f.start,f.end);
        Backupdata.sortsearchflag=1;
    }
    //System.out.print(f.start);
    //System.out.println(f.end);
    int flag=0;
%>


<div class="sidebar" style="text-align: center;">
    <a style="margin-top: 60px;padding: 10px;" href="index.jsp"><i class="fas fa-desktop"></i></a>
    <a style="margin-top: 60px;padding: 10px;" href="backupdata.jsp"><i class="fas fa-recycle"></i></a>

</div>

<div class="content">


    <div class="row">
        <div class="col-sm-6">
            <div class="card1">
                <form action="backupsearch" method="post">
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
                    <form action="backupsorting" method="POST">
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



<%--    <div class="row">--%>
<%--        <div class="col-sm-6">--%>
<%--            <div class="card1">--%>
<%--                <p class="pathoffile" style="font-size: 20px"><%= f.ppath %></p>--%>

<%--            </div>--%>

<%--        </div>--%>
<%--        <div class="col-sm-6">--%>
<%--            <div class="button_prevnextdir">--%>
<%--                <form action="prevnextdir" method="POST">--%>
<%--                    <button type="submit" class="btn  btn-outline-info pathbutton1" name="prevdir"  value="<%= f.prevpath %>"><</button>--%>
<%--                    <button type="submit" class="btn  btn-outline-info pathbutton1" name="prevdir"  value="next">></button>--%>
<%--                </form>--%>

<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>



    <table id="myTable" style="width: 80vw; margin-bottom: 30px;" class="table table-hover">
        <thead>
        <tr>
            <th scope="col">File Type</th>
            <th scope="col">File Name</th>
            <th scope="col">Parent path</th>
            <th scope="col">Folder count</th>
            <th scope="col">File count</th>
            <th scope="col">Restore</th>
        </tr>
        </thead>
        <tbody>
        <% while(rs.next()){  flag=1;%>
        <tr>


            <% if(! rs.getString(1).contains(".")) { %>
            <form action="display" method="post">
                <td><i class="far fa-folder"></i></td>
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
            <form action="restorejnipre" method="POST">
                <input name="filename" value="<%= rs.getString(1)%>" hidden>
                <input name="ppath" value="<%= rs.getString(2)%>" hidden>
                <input type="text" name="destpath" value="<%= rs.getString(5)%>"  hidden>
                <td><button type="submit" class="show-popup" style="border: 0" name="hellojni"  ><i class="fas fa-trash-restore"></i></button></td>

            </form>


        </tr>

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
        <form action="backupprevnext" method="POST">
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

    document.querySelector(".show-popup").addEventListener("click",function(){
        document.querySelector(".popup").classList.add("active");
    });
    document.querySelector(".popup .close-btn").addEventListener("click",function(){
        document.querySelector(".popup").classList.remove("active");
    });

</script>
</body>
</html>
