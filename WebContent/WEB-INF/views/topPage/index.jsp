<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    <h2 class="toppage">Welcome to Nyancogram</h2>
    <p class="toppagemsg">This is an application for the cat owner.</p>


    <div class="index_button">
        <ul>
            <li>
                <div class="login_button">
                    <a href ="<c:url value='/login' />" class="toppage_button">Log in</a>
                </div>

                <div class="signup_button">
                    <a href ="<c:url value='/signup' />" class="toppage_button">Sign up</a>
                </div>
            </li>
        </ul>
        </div>
    </c:param>
</c:import>


