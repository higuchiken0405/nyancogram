<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="container container_signsup_login">
            <%-- 題名 --%>
            <h2 class="login">Log in</h2>
            <%-- ログインフォーム --%>
            <form action="<c:url value='/login'/>" method="POST">
                <%-- エラーメッセージ(メールアドレス) --%>
                <c:if test="${errors[0].length() > 0}">
                    <p class="error_msg"><c:out value="${errors[0]}" /></p>
                </c:if>
                <%-- フォーム部品(メールアドレス) --%>
                <div class="form_part">
                    <label for="email">メールアドレス：</label>
                    <input type="text" name="email" value="${user.email}" required />
                </div>
                <%-- エラーメッセージ(パスワード) --%>
                <c:if test="${errors[1].length() > 0}">
                    <p class="error_msg"><c:out value="${errors[1]}" /></p>
                </c:if>
                <%-- フォーム部品(パスワード) --%>
                <div class="form_part">
                    <label for="password">パスワード：</label>
                    <input type="password" name="password" value="${user.password}" required />
                </div>
                <%-- 送信ボタン --%>
                <button type="submit">Log in</button>
            </form>
            <%-- サインアップ画面へのリンク --%>
            <a href="<c:url value='/signup'/>" class="to_signup">Signup画面へ</a>
        </div>
    </c:param>
</c:import>