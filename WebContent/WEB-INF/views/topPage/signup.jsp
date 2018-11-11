<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="container container_main container_signsup_login">
        <h2>Sign up</h2>
        <form action="signup" method="post">
            <c:if test="${errors[0].length() > 0}">
                <p class="error_msg"><c:out value="${errors[0]}" /></p>
            </c:if>
            <div class="form_part">
                <label for="name">名前：</label>
                <input type="text" name="name" value="${user.name}" required />
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
            <c:if test="${errors[1].length() > 0}">
                <p class="error_msg"><c:out value="${errors[1]}" /></p>
            </c:if>
            <div class="form_part">
                <label for="email">メールアドレス：</label>
                <input type="email" name="email" value="${user.email}" required />
            </div>
            <c:if test="${errors[2].length() > 0}">
                <p class="error_msg"><c:out value="${errors[2]}" /></p>
            </c:if>
            <div class="form_part">
                <label for="password">パスワード：</label>
                <input type="password" name="password" required />
            </div>
            <c:if test="${errors[3].length() > 0}">
                <p class="error_msg"><c:out value="${errors[3]}" /></p>
            </c:if>
            <div class="form_part">
                <label for="password_confirmation">パスワード確認：</label>
                <input type="password" name="password_confirmation" required />
            </div>
            <button type="submit">Sign up</button>
        </form>
        <a href="<c:url value='/login'/>" class="to_login">Login画面へ</a>
        </div>
    </c:param>
</c:import>