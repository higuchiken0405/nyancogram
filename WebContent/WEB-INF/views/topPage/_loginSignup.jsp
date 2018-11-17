<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
            <%-- フラッシュメッセージ --%>
            <c:if test="${flush != null}" >
                <div class="message">
                    <div class="flush">
                        <c:out value="${flush}"/>
                    </div>
                </div>
            </c:if>
            <%-- エラーメッセージ --%>
            <c:if test="${errors != null}" >
                <div class="message">
                    <div class="errors_post">
                        <c:forEach var="error" items="${errors}">
                            <span>・ <c:out value="${error}"/></span>&ensp;
                        </c:forEach>
                    </div>
                </div>
            </c:if>
            <%-- ログイン・サインアップ画面 --%>
            <div class="container_top clearfix">
                <%-- メッセージ --%>
                <h2 class="toppage">Welcome to Nyancogram</h2>
                <p class="toppagemsg">This is an application for the cat .</p>
                <%-- ログイン・サインアップボタン --%>
                <div class="index_button">
                    <ul class="index_button_list">
                        <li>
                            <%-- ログインボタン --%>
                            <div class="login_button">
                                <a href ="<c:url value='/login'/>" class="toppage_button">Log in</a>
                            </div>
                            <%-- サインアップボタン --%>
                            <div class="signup_button">
                                <a href ="<c:url value='/signup'/>" class="toppage_button">Sign up</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
    </c:param>
</c:import>
<script>
//フラッシュメッセージをフェードアウト
$(function(){
    $('.flush').fadeOut(6000)
});
//エラーメッセージをフェードアウト
$(function(){
    $('.errors_post').fadeOut(6000);
});
</script>