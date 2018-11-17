<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
            <%-- フラッシュメッセージ --%>
            <c:if test="${flush != null}">
                <div class="message">
                    <div class="flush">
                        <c:out value="${flush}"/>
                    </div>
                </div>
            </c:if>
            <%-- エラーメッセージ --%>
            <c:if test="${errors != null}" >
                <div class="message">
                    <div class="errors_post">
                        <c:forEach var="error" items="${errors}">
                            <span>・ <c:out value="${error}"/></span>&ensp;
                        </c:forEach>
                    </div>
                </div>
            </c:if>
            <%--  全体のコンテナ --%>
            <div class="container container_main clearfix">
                <%-- アサイド --%>
                <aside class="aside">
                    <c:import url="_aside.jsp" />
                </aside>
                <%-- セクション --%>
                <section class="section">
                    <%-- ボタンリスト --%>
                    <ul class="main_button_list">
                        <li>
                            <div class="post_form">
                                <a href="#" class="post_form_button">投稿フォーム</a>
                            </div>
                            <div class="users_index">
                                <a href="<c:url value='/users/index'/>" class="users_index_button">登録猫一覧</a>
                            </div>
                            <div class="posts_index">
                                <a href="<c:url value='/posts/index'/>" class="posts_index_button">投稿一覧</a>
                            </div>
                        </li>
                    </ul>
                    <%-- 投稿フォーム全体を囲う --%>
                    <div class="post_form_container">
                        <%-- 投稿フォーム --%>
                        <form action="<c:url value='/posts/create'/>" method="POST" enctype="multipart/form-data">
                            <%-- フォーム部品(タイトル) --%>
                            <div class="form_part">
                                <label for="title">タイトル</label><br>
                                <input type="text" name="title" required />
                            </div>
                            <%-- フォーム部品(投稿内容) --%>
                            <div class="form_part">
                                <label for="content">内容</label><br>
                                <textarea name="content" class="aside_textarea" wrap="soft" required ></textarea>
                            </div>
                            <%-- フォーム部品(ファイル) --%>
                            <div class="form_part">
                                <label for="file" class="image_select">
                                    + 投稿画像を選択
                                    <input type="file" name="file" accept="image/*" id="file" style="display:none;" >
                                </label>
                            </div><br>
                            <%-- トークン --%>
                             <input type="hidden" name="_token" value="${_token}">
                             <%-- 投稿ボタン --%>
                            <button type="submit" class="post_submit_button">投稿</button>
                    </form>
                </div><%-- div.post_form_comtainer --%>
                <%-- 投稿一覧を囲うdiv --%>
                <div class="my_posts_index">
                    <c:forEach var="post" items="${posts}">
                        <%-- 投稿とコメントを囲うコンテナ --%>
                        <div class="post_comment_container">
                            <%-- 投稿を囲うコンテナ --%>
                            <div class="post_container">
                                <%-- 投稿時間・削除アイコンのリスト --%>
                                <ul class="post_container_list">
                                    <li>
                                        <%-- 投稿時間　条件分岐 --%>
                                        <c:choose>
                                            <%-- new表示 --%>
                                            <c:when test="${post.time_msg.equals('new')}">
                                                <span class="time_msg_new">${post.time_msg}</span>
                                            </c:when>
                                            <%-- ~時間前表示 --%>
                                            <c:when test="${post.time_msg.contains('時間前')}" >
                                                <span class="time_msg_oneday">${post.time_msg}</span>
                                            </c:when>
                                            <%-- 投稿時間表示 --%>
                                            <c:otherwise>
                                                <span class="time_msg">${post.time_msg}</span>
                                            </c:otherwise>
                                        </c:choose>
                                        <%-- ログインユーザーが投稿したポストの時 --%>
                                        <c:if test="${post.user.id == sessionScope.login_user.id}">
                                            <%-- 投稿削除のアイコン --%>
                                            <div class="destroy_icon">
                                                <a href="#" onclick="confirmDestroy();">
                                                    <span class="cross">×</span>
                                                </a>
                                            </div>
                                            <form action="<c:url value='/posts/destroy'/>" method="POST">
                                                <input type="hidden" name="_token" value="${_token}">
                                                <input type="hidden" name="post_id" value="${post.id}">
                                            </form>
                                        </c:if>
                                    </li>
                                </ul>
                                <%-- 投稿タイトル --%>
                                <h3 class="post_title"><c:out value="${post.title}" /></h3>
                                <%-- 投稿内容 --%>
                                <c:forEach var="content" items="${post.content_array}" >
                                    <p class="post_conetnt"><c:out value="${content}" /></p>
                                </c:forEach>
                                <%-- 投稿画像 --%>
                                <c:if test="${post.image != null}" >
                                    <img src="<c:url value='/uploaded/${post.image}'/>" class="post_img">
                                </c:if>
                                <%-- 投稿関係のアイコンリスト --%>
                                <ul class="post_icon_list">
                                    <li>
                                        <%-- コメントアイコン --%>
                                        <img src="<c:url value='/images/comment.png' />" class="comment_icon">
                                        <%-- いいねアイコン --%>
                                        <img src="<c:url value='/images/heart.png' />" class="favorite_icon_my">
                                        <%-- いいねカウント --%>
                                        <span class="favorite_count"><c:out value="${post.favorite_count}" /></span>
                                    </li>
                                </ul>
                            </div><%-- div.post_container --%>
                            <%-- コメントとフォームを囲うdiv --%>
                            <div class="comment">
                                <%-- コメントを囲うコンテナ --%>
                                <div class="comment_container">
                                    <c:forEach var="comment" items="${comments}" >
                                        <c:if test="${comment.post.id == post.id}">
                                            <table>
                                                <tr>
                                                    <td>
                                                        <%-- コメントしたユーザーアイコンと名前 --%>
                                                        <a href="<c:url value='/users/show?id=${comment.user.id}'/>" class="post_index_comment_user">
                                                            <img src="<c:url value='/icon/${comment.user.icon}'/>" class="comment_user_icon">
                                                            <c:out value="${comment.user.name}" />
                                                        </a>
                                                    </td>
                                                    <td>
                                                        <p> : </p>
                                                    </td>
                                                    <td class="post_index_comment_body">
                                                        <%-- コメント内容 --%>
                                                        <p><c:out value="${comment.body}" /></p>
                                                    </td>
                                                </tr>
                                            </table>
                                        </c:if>
                                    </c:forEach>
                                </div><%-- div.comment_container --%>
                                <%-- コメントフォームを囲うコンテナ --%>
                                <div class="comment_form_container">
                                    <%-- コメントフォーム --%>
                                    <form action="comments/create" method="POST">
                                        <%-- フォーム部品(コメント) --%>
                                        <div class="form_part">
                                            <label for="body">コメント</label><br>
                                            <input type="text" name="body" class="comment_form" required ></input>
                                        </div><br>
                                        <%-- 投稿ポストのID --%>
                                        <input  type="hidden" name="post_id" value="${post.id}">
                                        <%-- 投稿ボタン --%>
                                        <button type="submit" class="post_submit_button">投稿</button>
                                    </form>
                                </div><%-- div.comment_form_container --%>
                            </div><%-- div.comment --%>
                        </div><%-- div.post_comment_container --%>
                    </c:forEach>
                </div><%-- div.posts_index --%>
            </section>
        </div><%-- container --%>
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
function confirmDestroy() {
    if(confirm("本当に削除してよろしいですか？")) {
        document.forms[1].submit();
    }
}
$(function(){
	$('.flush').fadeOut(6000)
});
$(function(){
    $('.errors_post').fadeOut(6000);
});
</script>