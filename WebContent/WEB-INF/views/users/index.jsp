<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    <%-- ユーザー一覧全体を囲む --%>
    <div class="container container_users_index clearfix">
            <%-- アサイド --%>
            <aside class="aside">
                <c:import url="../topPage/_aside.jsp" />
            </aside>
            <%-- セクション --%>
            <section class="section clearfix">
                <%-- ボタンリスト --%>
                <ul class=main_button_list>
                    <li>
                        <%-- 投稿一覧へ --%>
                        <div class="posts_index">
                            <a href="<c:url value='/posts/index' />" class="posts_index_button">投稿一覧</a>
                        </div>
                    </li>
                </ul>
                <c:forEach var="user" items="${users}" >
                    <c:if test="${user.id != sessionScope.login_user.id}">
                    <%-- ユーザー一覧リスト --%>
                    <ul class="user_index_list">
                        <li>
                            <a href="<c:url value='/users/show?id=${user.id}'/>" class="users_idex_link">
                            <span class="users_index">
                                <img src="<c:url  value='/icon/${user.icon}'/>" class="users_index_icon">
                                <c:out value="${user.name}" />&nbsp;さん
                            </span>
                            <c:choose>
                                <c:when test="${user.gender == '♂'}">
                                    <span class="users_index gender_male"><c:out value="${user.gender}" /> </span>
                                </c:when>
                                <c:otherwise>
                                    <span class="users_index gender_female"><c:out value="${user.gender}" /></span>
                                </c:otherwise>
                            </c:choose>
                            <span class="users_index users_index_area">生息地域 : <c:out value="${user.area}" /></span>
                            </a>
                        </li>
                    </ul>
                    </c:if>
                </c:forEach>
            </section>
        </div>
    </c:param>
</c:import>