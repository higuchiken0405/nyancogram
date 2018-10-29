<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <img src=""><br>
        <p><c:out value="${SessionScope.login_user.name}"/>さん<c:out value="${SessionScope.login_user.geneder}" /></p>
        <p>生息地域 <c:out value="${SessionScope.login_user.area}" />
        <form action="<c:url value='/posts/create'/>" method="POST" enctype="multipart/form-data">
            <div class="form_part">
                <label for="title">タイトル</label>
                <input type="text" name="title" />
            </div>
            <div class="form_part">
                <label for="content">内容</label>
                <input type="text" name="content">
            </div>
            <div class="form_part">
                <label for="file">画像</label>
                <input type="file" name="file" accept="image/*">
            </div>
            <button type="submit">投稿</button>
        </form>

        <a href="<c:url value='/users/show?id=${SessionScope.login_user.id}'/>">ユーザー詳細へ</a>
    </c:param>
</c:import>