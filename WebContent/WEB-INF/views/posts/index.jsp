<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="container container_main clearfix">
            <aside class="aside">
                <c:import url="../topPage/_aside.jsp" />
            </aside>
            <section class="section">
                <ul class=main_button_list>
                    <li>
                        <div class="users_index">
                            <a href="<c:url value='/users/index' />" class="users_index_button">登録猫一覧</a>
                        </div>
                    </li>
                </ul>
                <div class="posts_index">
                    <c:forEach var="post" items="${posts}">
                        <div class="post_comment_container">
                            <div class="post_index_container">
                                <ul class="post_index_user_time">
                                    <li>
                                        <a href="<c:url value='/users/show?id=${post.user.id}' />">
                                            <span class="post_index_user">
                                                <img src="<c:url value='/icon/${post.user.icon}' />" class="post_user_icon">
                                                <c:out value="${post.user.name}" />
                                            </span>
                                        </a>
                                        <span class="post_index_time"><c:out value="${post.created_at}" /></span>
                                    </li>
                                </ul>
                                <ul class="clearfix">
                                    <li>
                                        <div class="post_index_title_content">
                                            <p class="post_index_title"><c:out value="${post.title}" /></p>
                                            <c:forEach var="content" items="${post.content_array}" >
                                                <p class="post_conetnt"><c:out value="${content}" /></p>
                                            </c:forEach>
                                        </div>
                                        <c:if test="${post.image != null}" >
                                            <img src="<c:url value='/uploaded/${post.image}'/>" class="post_index_img">
                                        </c:if>
                                    </li>
                                </ul>
                                <ul class="post_icon_list">
                                    <li>
                                        <img src="<c:url value='/images/comment.png' />" class="comment_icon">
                                        <img src="<c:url value='/images/heart.png' />" class="favorite_icon">
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
                                        <input type="text" name="body" class="comment_form"></input>
                                    </div><br>
                                    <input  type="hidden" name="post_id" value="${post.id}">
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
    $(".comment_icon").on("click", function() {
        $(this).parents(".post_index_container").next().slideToggle();
    });
});
</script>