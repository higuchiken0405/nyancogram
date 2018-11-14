<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="container container_users_edit">

        <form action="<c:url value='/users/icon'/>" method="POST" enctype="multipart/form-data">
            <div class="form_part">
                <img src="<c:url value='/icon/${user.icon}' />" class="user_icon"><br>
                <label for="file"></label>
                <input type="file" name="icon" accept="image/*">
            </div>
            <button type="submit" class="icon_button">アイコン変更</button>
        </form>

        <form action="<c:url value='/users/update'/>" method="post">
             <c:if test="${errors[0].length() > 0}">
                <p class="error_msg"><c:out value="${errors[0]}" /></p>
            </c:if>
            <div class="form_part">
                <label for="name">名前：</label>
                <input type="text" name="name" value="${user.name}" />
            </div>
            <c:if test="${errors[1].length() > 0}">
                <p class="error_msg"><c:out value="${errors[1]}" /></p>
            </c:if>
            <div class="form_part">
                <label for="email">メールアドレス：</label>
                <input type="text" name="email" value="${user.email}"/>
            </div>
            <c:if test="${errors[2].length() > 0}">
                <p class="error_msg"><c:out value="${errors[2]}" /></p>
            </c:if>
            <div class="form_part">
                <label for="password">パスワード：</label>
                <input type="password" name="password"/>
            </div>
            <input type="hidden" name="_token" value="${_token}">
            <button type="submit">Update</button>
        </form>
        <p><a href="<c:url value='/'/>">メインへ戻る</a></p>
        </div>
    </c:param>
</c:import>