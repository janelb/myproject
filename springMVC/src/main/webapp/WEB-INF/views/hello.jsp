<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

</head>
<body>
<img src="/static/img/1.jpg" alt="">


<script src="/static/js/moment-with-locales.js"></script>
    <div>

    moment().format('MMMM Do YYYY, h:mm:ss a');

    </div>
<script>
    moment().format('dddd');
    moment().format("MMM Do YY");
    moment().format('YYYY [escaped] YYYY');
    moment().format();
</script>





<h1>hello springMvc!</h1>
id值为:${id}
页码为:${p}

</body>
</html>