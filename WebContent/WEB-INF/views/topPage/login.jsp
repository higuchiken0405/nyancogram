<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="container container_main container_signsup_login">
        <h2>Log in</h2>
        <form action="<c:url value='/login'/>" method="POST">
            <div class="form_part">
                <label for="email">メールアドレス：</label>
                <input type="text" name="email" value="${user.email}"/>
            </div>
            <div class="form_part">
                <label for="password">パスワード：</label>
                <input type="password" name="password" value="${user.password}"/>
            </div>
            <button type="submit">Log in</button>
        </form>
        <a href="<c:url value='/signup'/>" class="to_signup">Signup画面へ</a>
        </div>
    </c:param>
</c:import>