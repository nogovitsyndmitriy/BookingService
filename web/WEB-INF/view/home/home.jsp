<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Chewy" rel="stylesheet">
    <style>
        .button {
            width: 900px;
            height: 285px;
            display: block;
            position: absolute;
            top: 60%;
            left: 51.5%;
            transform: translate(-50%, -50%);
            outline: none;
        }

        .hi {
            font-family: 'Chewy', cursive;
            font-size: 48px;
            color: whitesmoke;
        }
    </style>
    <title>${title}</title>
</head>
<body class="main_body">
<div>
    <h1 class="hi">Stay in the heart of Rome! Grand Hotel Plaza <sup>&#9733&#9733&#9733&#9733&#9733</sup></h1>
</div>
<a href="frontController?commad=rooms" class="button"></a>
</body>
</html>
