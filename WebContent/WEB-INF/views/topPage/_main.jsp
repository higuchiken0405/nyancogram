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
                    <a href="users/index" class="users_index_button">ユーザー一覧</a>
                </div>
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