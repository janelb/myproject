<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"

         pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page isELIgnored="false"%>

<html>

<head>

    <title>Title</title>

</head>

<body>

<%-- enctype="multipart/form-data" 文件上传必须有这个 --%>

<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">

    姓名:<input type="text" name="name" value="${imageTestVo.name}"><br/><br/>

    年龄:<input type="text" name="age" value="${imageTestVo.age}"><br/><br/>

    <c:if test="${imageTestVo.img_src != null && imageTestVo.img_src != ''}">

        < img src="/upload5/${imageTestVo.img_src}" width="100px",height="100px">

    </c:if>

    头像:<input type="file" name="pic_src" ><br/><br/>

    <input type="submit" value="submit"><br/><br/>

</form>

</body>

</html>