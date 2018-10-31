<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>にゃんこグラム</title>
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <header>
                Nyancogram
            </header>
            <div class="content">
                ${param.content}
            </div>
            <footer>
                <p>copyright</p>
            </footer>

        </div>
    </body>
</html>