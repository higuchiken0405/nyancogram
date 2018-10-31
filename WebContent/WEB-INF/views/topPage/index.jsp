<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.login_user != null}">
        <c:import url="_main.jsp" />
    </c:when>
    <c:otherwise>
        <c:import url="_loginSignup.jsp" />
    </c:otherwise>
</c:choose>