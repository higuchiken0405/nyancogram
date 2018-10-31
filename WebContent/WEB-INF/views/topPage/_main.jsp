<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="container container_main clearfix">
            <aside class="aside_main">
                <img src="uploaded/default.jpg" class="user_icon"><br>
                <p><c:out value="${sessionScope.login_user.name}"/>さん&ensp;<span class="${sessionScope.login_user.gender}"><c:out value="${sessionScope.login_user.gender}" /></span></p>
                <p>生息地域 <c:out value="${sessionScope.login_user.area}" /><br>
                <button>投稿</button>
                <form action="<c:url value='/posts/create'/>" method="POST" enctype="multipart/form-data">
                    <div class="form_part">
                        <label for="title">タイトル</label>
                        <input type="text" name="title" />
                    </div>
                    <div class="form_part">
                        <label for="content">内容</label>
                        <input type="text" name="content">
                    </div>
                    <div class="form_part">
                        <label for="file">画像</label>
                        <input type="file" name="file" accept="image/*">
                    </div>
                    <button type="submit">投稿</button>
                </form>
                <a href="<c:url value='/users/edit?id=${sessionScope.login_user.id}'/>">ユーザー情報編集へ</a>
            </aside>
            <section class="section_main">
                <h2>ポストタイトル</h2>
                <p>ポストタイトル</p>
                <p>ポスト画像</p>
                <h2>ポストタイトル</h2>
                <p>ポストタイトル</p>
                <p>ポスト画像</p>
            </section>
        </div>
    </c:param>
</c:import>