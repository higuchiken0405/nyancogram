<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>ユーザー一覧</h2>
        <c:forEach var="user" items="${users}" >
            <li>
                <span><c:out value="${user.id}" /></span>
                <span><c:out value="${user.name}" /></span>
                <span><c:out value="${user.email}" /></span>
                <span><c:out value="${user.password}" /></span>
                <span><a href="<c:url value='/users/show?id=${user.id}' />">詳細へ</a></span>
            </li>
        </c:forEach>
    </c:param>
</c:import>