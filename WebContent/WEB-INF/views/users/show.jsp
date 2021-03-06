<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
            <c:if test="${flush != null}" >
                <div class="message">
                    <div class="flush">
                        <c:out value="${flush}" />
                    </div>
                </div>
            </c:if>
            <c:if test="${errors != null}" >
                <div class="message">
                    <div class="errors_post">
                        <c:forEach var="error" items="${errors}">
                            <span>・ <c:out value="${error}" /></span>&ensp;
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        <div class="container container_main clearfix">
            <aside class="aside">
                <c:import url="_aside.jsp" />
            </aside>
            <section class="section">
                <ul class=main_button_list>
                    <li>
                    <div class="users_index">
                        <a href="<c:url value='/users/index' />" class="users_index_button">登録猫一覧</a>
                    </div>
                    <div class="posts_index">
                        <a href="<c:url value='/posts/index' />" class="posts_index_button">投稿一覧</a>
                    </div>
                    </li>
                </ul>
                <div class="my_posts_index">
                    <c:forEach var="post" items="${posts}">
                        <div class="post_comment_container">
                            <div class="post_container">
                                <h3 class="post_title"><c:out value="${post.title}" /></h3>
                                <c:choose>
                                    <c:when test="${post.time_msg.equals('new')}">
                                        <span class="time_msg_new">${post.time_msg}</span>
                                    </c:when>
                                    <c:when test="${post.time_msg.contains('時間前')}" >
                                        <span class="time_msg_oneday">${post.time_msg}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="time_msg">${post.time_msg}</span>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach var="content" items="${post.content_array}" >
                                    <p class="post_conetnt"><c:out value="${content}" /></p>
                                </c:forEach>
                                <c:if test="${post.image != null}" >
                                    <img src="<c:url value='/uploaded/${post.image}'/>" class="post_img">
                                </c:if>
                                <ul class="post_icon_list">
                                    <li>
                                        <img src="<c:url value='/images/comment.png' />" class="comment_icon">
                                        <a href="<c:url value='/favorites/create?post_id=${post.id}' />" >
                                            <img src="<c:url value='/images/heart.png' />" class="favorite_icon">
                                        </a>
                                        <span class="favorite_count"><c:out value="${post.favorite_count}" /></span>
                                    </li>
                                </ul>
                            </div>
                            <div class="comment">
                            <div class="comment_container">
                                <c:forEach var="comment" items="${comments}" >
                                    <c:if test="${comment.post.id == post.id}">
                                        <table>
                                            <tr>
                                                <td>
                                                    <a href="<c:url value='/users/show?id=${comment.user.id}'/>" class="post_index_comment_user">
                                                        <img src="<c:url value='/icon/${comment.user.icon}'/>" class="comment_user_icon">
                                                        <c:out value="${comment.user.name}" />
                                                    </a>
                                                </td>
                                                <td>
                                                    <p> : </p>
                                                </td>
                                                 <td class="post_index_comment_body">
                                                   <p><c:out value="${comment.body}" /></p>
                                                </td>
                                            </tr>
                                        </table>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="comment_form_container">
                                <form action="<c:url value='/comments/create' />" method="POST">
                                    <div class="form_part">
                                        <label for="body">コメント</label><br>
                                        <input type="text" name="body" class="comment_form" required ></input>
                                    </div><br>
                                    <input  type="hidden" name="post_id" value="${post.id}">
                                    <input  type="hidden" name="user_id" value="${user.id}">
                                    <button type="submit" class="post_submit_button">投稿</button>
                                </form>
                            </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </section>
       </div>
    </c:param>
</c:import>
<script>
$(function(){
    $(".post_form_button").on("click", function() {
        $(".post_form_container").slideToggle();
    });
});
$(function(){
    $(".comment_icon").on("click", function() {
        $(this).parents(".post_container").next().slideToggle();
    });
});
$(function(){
    $('.flush').fadeOut(6000)
});
$(function(){
    $('.errors_post').fadeOut(6000);
});
</script>