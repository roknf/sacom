<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글작성</title>
</head>
<body>
    <h1>글작성</h1>
    
    <form action="PostController" method="POST">
        
        <label for="name">작성자:</label>
        <input type="text" id="name" name="name" required><br>
        
        <label for="title">지목:</label>
        <input type="text" id="title" name="title" required><br>
        
        <label for="content">내용:</label><br>
        <textarea id="content" name="content" rows="5" required></textarea><br>
        
        <input type="submit" value="등록">
    </form>
    
    <br>
    <a href="http://localhost:8081/PostController">리스트로 돌아감</a>
</body>
</html>
