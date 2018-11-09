<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="container container_main clearfix">
            <aside class="aside">
                <c:import url="_aside.jsp" />
            </aside>
            <section class="section">
                <div class="users_index">
                    <a href="<c:url value='/users/index' />" class="users_index_button">ユーザー一覧</a>
                </div>
                <div class="my_posts_index">
                    <c:forEach var="post" items="${posts}">
                        <div class="post-container">
                            <h3 class="post_title"><c:out value="${post.title}" /></h3>
                            <p><c:out value="${post.created_at}" /></p>
                            <p class="post_conetnt"><c:out value="${post.content}" /></p>
                            <p class="post_img"><c:out value="${post.image}" /></p>
                        </div>
                    </c:forEach>
                </div>
            </section>
       </div>
    </c:param>
</c:import>