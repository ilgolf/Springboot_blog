<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp"%>>

<div class="container">

    <button class="btn btn-secondary" onclick="history.back()">return</button>
    <c:if test="${board.user.id == principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">Modify</a>
        <button id="btn-delete" class="btn btn-danger">delete</button>
    </c:if>
    <br/><br/>
    <div>
        number : <span id="id"><i>${board.id} </i></span>
        writter : <span><i>${board.user.username} </i></span>
    </div>
    <br/><br/>
    <div class="form-group">
        <h3>${board.title}</h3>
    </div>

    <hr />

    <div class="form-group">
        <div>${board.content}</div>
    </div>
    <hr />
</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>


