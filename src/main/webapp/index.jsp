<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
</head>
<body>
    <h1>게시판</h1>
    
    <form action="PostController" method="GET">
        <input type="text" name="keyword" placeholder="검색어를 입력하세요">
        <input type="submit" value="검색">
    </form>
    <br>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>작성자</th>
            <th>제목</th>
            <th>내용</th>
            <th>작성일1</th>
        </tr>
        <c:forEach var="post" items="${posts}">
            <tr>
                <td>${post.getId()}</td>
                <td>${post.getName()}</td>
                <td>${post.getTitle()}</td>
                <td>${post.getContent()}</td>
                <td>${post.getDates()}</td>
            </tr>
        </c:forEach>
    </table>
    
    <br>
    <a href="add-post.jsp">글 작성</a>
</body>
</html>
