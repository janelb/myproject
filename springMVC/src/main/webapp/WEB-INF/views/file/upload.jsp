<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>文件的上传</title>
</head>
<body>
    <c:if test="${not empty message}" >
        <div style="color: red">
            ${message}
        </div>
    </c:if>
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="name"/><br>
        <input type="file" name="fileName"/><br>
        <button>save</button><br>
    </form>


</body>
</html>