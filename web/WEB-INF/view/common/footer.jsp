<%@ page contentType="text/html; UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        .footer {
            width: 100%;
            margin: 30px 0 0;
            background-color: rgba(189, 183, 107, 0.3);
            color: white;
            font-size: 12px;
            padding: 20px 0;
            position: absolute;
            bottom: 0;
            left: 0;
        }

        .footer__text {
            color: black;
            margin: 0;
            padding-left: 20px;
            width: 50%;
        }

        .clock {
            padding-right: 20px;
            color: black;
        }
    </style>
</head>
<body>
<footer class="footer">
    <div class="footer__text">&copy; 2018. Created by Dmitriy</div>
    <div class="clock" align="right"><%= new java.util.Date() %>
    </div>
</footer>
</body>
</html>
