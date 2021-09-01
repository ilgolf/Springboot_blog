<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form action="/auth/loginProc" method="post">
        <div class="form-group">
            <label for="username">Email address:</label>
            <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <button id="btn-login" class="btn btn-primary">Log in</button>
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=66e9cc86ff0f7c677b3cdc3db944c605&redirect_uri=http:/localhost:8000/auth/kakao/callback
&response_type=code"><img height="38px" src="/image/kakao_login_medium.png" /></a>
    </form>
</div>
<%@ include file="../layout/footer.jsp" %>


