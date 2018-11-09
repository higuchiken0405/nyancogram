<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="container container_main">
        <form action="signup" method="post">
            <div class="form_part">
                <label for="name">名前：</label>
                <input type="text" name="name" value="${user.name}" />
            </div>
           <div class="form_part">
                <label for="gender">性別：</label>
                <input type="radio" name="gender" value="♂" checked/>♂
                <input type="radio" name="gender" value="♀" />♀
            </div>
            <div class="form_part">
                <label for="area">生息地域：</label>
                <c:import url="_area.jsp" />
            </div>
            <div class="form_part">
                <label for="email">メールアドレス：</label>
                <input type="text" name="email" value="${user.email}"/>
            </div>
            <div class="form_part">
                <label for="password">パスワード：</label>
                <input type="password" name="password" value="${user.password}"/>
            </div>
            <button type="submit">Sign up</button>
        </form>
        </div>
    </c:param>
</c:import>