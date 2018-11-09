<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>にゃんこグラム</title>
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    </head>
    <body>
        <div id="wrapper">
            <header>
                <a href="">Nyancogram</a>
            </header>
            <div class="content">
                ${param.content}
            </div>
        </div>
    </body>
</html>