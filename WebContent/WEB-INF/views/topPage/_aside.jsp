<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <img src="<c:url  value='/icon/${sessionScope.login_user.icon}'/>" class="user_icon"><br>
    <a href="<c:url value='/users/edit?id=${sessionScope.login_user.id}'/>" class="to_users_edit">ユーザー情報編集</a><br>
    <p>
       <c:out value="${sessionScope.login_user.name}"/>&ensp;さん
       <span>
            <c:choose>
                <c:when test="${sessionScope.login_user.gender == '♂'}">
                    <span class="users_index gender_male"><c:out value="${sessionScope.login_user.gender}" /> </span>
                </c:when>
                <c:otherwise>
                    <span class="users_index gender_female"><c:out value="${sessionScope.login_user.gender}" /></span>
                </c:otherwise>
            </c:choose>
        </span>
     </p>
     <p>生息地域 ： <c:out value="${sessionScope.login_user.area}" /><br>