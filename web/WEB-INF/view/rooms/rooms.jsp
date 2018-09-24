<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/addReduce.js" type="text/javascript">
        <jsp:text/>
    </script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <style type="text/css">
        .main {
            width: 80%;
            min-width: 600px;
            margin: auto;
        }

        table {
            margin-top: 50px;
            border-collapse: collapse;
            border-width: 1px;
            text-align: center;
            max-height: 100px;
            font-size: large;
            font-weight: bold;
            opacity: 0.9;
        }

        .btn-primary {
            margin-top: 0px;
            padding: 0px;
        }

        .addRoomBtn, .ReduceRoomBtn {
            width: 20px;
            height: 20px;
            font-size: 10px;
        }

        .buy {
            display: block;
            float: right;
            color: black;
            margin-top: 2px;
            margin-right: 20px;
            width: 115px;
            height: 35px;
            line-height: 35px;
            background-color: khaki;
            border: 1px solid gray;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
        }

        .buy:hover, .buy:active {
            text-decoration: none;
            outline: none;
            animation: button .5s forwards;
        }

        @keyframes button {
            0% {
                transform: scale(1);
                background-color: rgba(240, 230, 140, .3);
            }
            100% {
                transform: scale(1.05);
                background-color: rgba(240, 230, 140, 1);
            }
        }

        .stripes:hover {
            background-color: lightgrey
        }

        .caption {
            padding: 0px;
            color: black;
        }

        .image {
            width: 40px;
            height: 50px;
        }
    </style>
    <title>${title}</title>
</head>
<body>
<main class="main">
    <table class="table table-hover table-dark">
        <caption class="caption">Select your Room!</caption>
        <tr class="stripes">
            <td>№</td>
            <td>Class</td>
            <td>Places</td>
            <td>Cost</td>
            <td>Available</td>
            <td>Purchase</td>
        </tr>
        <c:forEach var="room" items="${rooms}">
            <tr class="stripes">
                <td>${room.id}</td>
                <td>${room.roomClasss}</td>
                <td>${room.places}</td>
                <td>${room.cost}$</td>
                <td>${room.isAvalible}</td>
                <td><a class="buy" onclick="alert('You booked the room!')"
                       href="${pageContext.request.contextPath}/frontController?command=createOrder&roomId=${room.id}">${navName}Book
                    Now!</a></td>
            </tr>
        </c:forEach>
    </table>
    <div class="image">
        <source src="https://s-ec.bstatic.com/images/hotel/max1280x900/132/132065344.jpg">
    </div>
</main>
</body>
</html>
