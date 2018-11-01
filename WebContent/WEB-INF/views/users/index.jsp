<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    <div class="container container_users_index clearfix">
            <aside class="aside">
                <c:import url="../topPage/_aside.jsp" />
            </aside>
        <section class="section clearfix">
        <c:forEach var="user" items="${users}" >
            <c:if test="${user.id != sessionScope.login_user.id}">
            <li>
                <a href="<c:url value='/users/show?id=${user.id}' />" class="users_idex_link">
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
                    <span class="users_index">生息地域 : <c:out value="${user.area}" /></span>
                </a>
            </li>
            </c:if>
        </c:forEach>
        </section>
    </div>
    </c:param>
</c:import>