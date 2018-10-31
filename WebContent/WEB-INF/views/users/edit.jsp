<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="container container_edit">
        <form action="<c:url value='/users/update'/>" method="post">
            <div class="form_part">
                <label for="name">名前：</label>
                <input type="text" name="name" value="${user.name}" />
            </div>
            <div class="form_part">
                <label for="email">メールアドレス：</label>
                <input type="text" name="email" value="${user.email}"/>
            </div>
            <div class="form_part">
                <label for="password">パスワード：</label>
                <input type="password" name="password" value="${user.password}"/>
            </div>
            <button type="submit">Update</button>
        </form>
        <p><a href="<c:url value='/'/>">メインへ戻る</a></p>
        </div>
    </c:param>
</c:import>