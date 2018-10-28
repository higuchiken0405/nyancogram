<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
       <h2>${user.name}さんのページ</h2>
       <ul>
           <li>
               <span>名前</span><span><c:out value="${user.name}" /></span>
           </li>
           <li>
               <span>メールアドレス</span><span><c:out value="${user.email}" /></span>
           </li>
           <li>
               <span>パスワード</span><span><c:out value="${user.password}" /></span>
           </li>
       </ul>
       <p><a href="<c:url value='/users/edit?id=${user.id}'/>">ユーザー情報を編集する</a></p>
       <p><a href="<c:url value='/users/index'/>">一覧に戻る</a></p>
       <div class="post_form">
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
       </div>
       <div class="post_body">
       <div class="post_title">
            <p><c:out value="" /></p>
       </div>
       <div class="post_content">
            <p><c:out value="" /></p>
       </div>
       <div class="post_image">
            <img src="">
       </div>
       </div>
    </c:param>
</c:import>