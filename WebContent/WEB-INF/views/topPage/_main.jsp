<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="container container_main clearfix">
            <aside class="aside">
                <c:import url="_aside.jsp" />
            </aside>
            <section class="section">
                <ul class=main_button_list>
                    <li>
                    <div class="post_form">
                        <a href="#" class="post_form_button">投稿フォーム</a>
                    </div>
                    <div class="users_index">
                        <a href="users/index" class="users_index_button">登録猫一覧</a>
                    </div>
                    <div class="posts_index">
                        <a href="posts/index" class="posts_index_button">投稿一覧</a>
                    </div>
                    </li>
                </ul>
                    <div class="post_form_container">
                        <form action="<c:url value='/posts/create'/>" method="POST" enctype="multipart/form-data">
                        <div class="form_part">
                            <label for="title">タイトル</label><br>
                            <input type="text" name="title" />
                        </div>
                        <div class="form_part">
                            <label for="content">内容</label><br>
                            <textarea name="content" class="aside_textarea" wrap="hard"></textarea>
                        </div>
                        <div class="form_part">
                            <label for="file" class="image_select">
                                + 投稿画像を選択
                                <input type="file" name="file" accept="image/*" id="file" style="display:none;">
                            </label>
                        </div><br>
                    <button type="submit" class="post_submit_button">投稿</button>
                    </form>
                </div>
                <div class="my_posts_index">
                    <c:forEach var="post" items="${posts}">
                        <div class="post_comment_container">
                            <div class="post_container">
                                <h3 class="post_title"><c:out value="${post.title}" /></h3>
                                <p><c:out value="${post.created_at}" /></p>
                                <p class="post_conetnt"><c:out value="${post.content}" /></p>
                                <p class="post_img"><c:out value="${post.image}" /></p>
                                <ul class="post_icon_list">
                                    <li>
                                        <img src="<c:url value='/images/comment.png' />" class="comment_icon">
                                        <img src="<c:url value='/images/heart.png' />" class="favorite_icon">
                                    </li>
                                </ul>
                            </div>
                            <div class="comment_container">
                                <form action="/comments/create" method="POST">
                                    <div class="form_part">
                                        <label for="body">コメント</label><br>
                                        <textarea name="body" class="aside_textarea" wrap="hard"></textarea>
                                    </div><br>
                                    <button type="submit" class="post_submit_button">投稿</button>
                                </form>
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
</script>