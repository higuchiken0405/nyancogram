<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <img src="<c:url  value='/icon/${user.icon}'/>" class="user_icon"><br>
    <p>
       <c:out value="${user.name}"/>&ensp;さん
       <span>
            <c:choose>
                <c:when test="${user.gender == '♂'}">
                    <span class="users_index gender_male"><c:out value="${user.gender}" /> </span>
                </c:when>
                <c:otherwise>
                    <span class="users_index gender_female"><c:out value="${user.gender}" /></span>
                </c:otherwise>
            </c:choose>
        </span>
     </p>
     <p>生息地域 ： <c:out value="${sessionScope.user.area}" /><br>