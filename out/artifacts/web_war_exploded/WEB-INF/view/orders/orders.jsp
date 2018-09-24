<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <style>
        .container {
            height: 373px;
            overflow: auto;

        }

        .table-striped_1 {
            margin-top: 0px;
            font-size: large;
            font-weight: bold;
            opacity: 0.9;
        }

        .caption {
            caption-side: top;
            text-align: center;
            color: black;
        }

    </style>
</head>
<body>
<div class="container">
    <table class="table table-hover table-dark table-striped_1">
        <caption class="caption">Purchased rooms</caption>
        <tr>
            <th>№</th>
            <th>Order Id</th>
            <th>Account Id</th>
            <th>Total Price</th>

        </tr>
        <c:forEach var="order" items="${orders}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${order.id}</td>
                <td>${order.accountId}</td>
                <td>${order.total}$</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

