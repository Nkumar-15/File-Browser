<%@ page import="BackupJNI_Restore.CallJNIpredetails" %>
<%@ page import="BackupJNI_Restore.RestoreJNIpredetails" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        * {
            margin:0px;
            padding:0px;
            box-sizing:border-box;
        }
        body {
            background:linear-gradient(to right, #3a7bd5, #3a6073);
            font-family:"Raleway",sans-serif;
            height:100vh;
        }
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
            /*height: 70%;*/
        }
        .popup {
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
<div class="popup" >
    <!--                         <div class="close-btn" class="close" data-dismiss="modal" >&times;</div> -->
    <form action="restore" method="POST">
        <div class="form">
            <h2>Restore Data</h2>
            <div class="form-element">
                <label >File Name</label>
                <input type="text" name="filename" value="<%= RestoreJNIpredetails.filename %>" readonly>
            </div>
            <div class="form-element">
                <label >Parent Path</label>
                <input  type="text" name="ppath" value="<%= RestoreJNIpredetails.pPath %>" readonly>
            </div>

            <div class="form-element">
                <label >Destination Path</label>
                <input  type="text" name="destpath" value="<%= RestoreJNIpredetails.destdir %>" readonly>
            </div>
            <div class="form-element">
                <label >Password</label>
                <input type="password"  name="pass" placeholder="Enter password">
            </div>
            <div class="form-element">
                <button type="submit">Restore</button>
            </div>
        </div>
    </form>

</div>

</body>
</html>