<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="container container_signsup_login">
            <%-- 題名 --%>
            <h2 class="signup">Sign up</h2>
            <%-- ユーザー登録フォーム --%>
            <form action="signup" method="post">
                <%-- エラーメッセージ(名前) --%>
                <c:if test="${errors[0].length() > 0}">
                    <p class="error_msg"><c:out value="${errors[0]}" /></p>
                </c:if>
                <%-- フォーム部品(名前) --%>
                <div class="form_part">
                    <label for="name">名前：</label>
                    <input type="text" name="name" value="${user.name}" required />
                </div>
                <%-- フォーム部品(性別) --%>
                <div class="form_part">
                    <label for="gender">性別：</label>
                    <input type="radio" name="gender" value="♂" checked/>♂
                    <input type="radio" name="gender" value="♀" />♀
                </div>
                <%-- フォーム部品(生息地域) --%>
                <div class="form_part">
                    <label for="area">生息地域：</label>
                    <c:import url="_area.jsp" />
                </div>
                 <%-- エラーメッセージ(メールアドレス) --%>
                <c:if test="${errors[1].length() > 0}">
                    <p class="error_msg"><c:out value="${errors[1]}" /></p>
                </c:if>
                <%-- フォーム部品(メールアドレス) --%>
                <div class="form_part">
                    <label for="email">メールアドレス：</label>
                    <input type="email" name="email" value="${user.email}" required />
                </div>
                 <%-- エラーメッセージ(パスワード) --%>
                <c:if test="${errors[2].length() > 0}">
                    <p class="error_msg"><c:out value="${errors[2]}" /></p>
                </c:if>
                <%-- フォーム部品(パスワード) --%>
                <div class="form_part">
                    <label for="password">パスワード：</label>
                    <input type="password" name="password" required />
                </div>
                <%-- フォーム部品(パスワード確認) --%>
                <div class="form_part">
                    <label for="password_confirmation">パスワード確認：</label>
                    <input type="password" name="password_confirmation" required />
                </div>
                <%-- エラーメッセージ(パスワード) --%>
                <c:if test="${error.length() > 0}">
                    <p class="error_msg"><c:out value="${error}" /></p>
                </c:if>
                <%-- 送信ボタン --%>
                <button type="submit">Sign up</button>
            </form>

            <%-- ログイン画面へのリンク --%>
            <a href="<c:url value='/login'/>" class="to_login">Login画面へ</a>
        </div><%-- div.container --%>
    </c:param>
</c:import>