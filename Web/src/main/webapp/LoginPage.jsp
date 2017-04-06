<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login page</title>
</head>
<style>
body{
background-image: url(${pageContext.request.contextPath}/image/light-tile.gif);
}
div{
background-color: white;
width: 250 px;
text-align: center;
border: solid 1px black;
margin: 30%;
margin-top: 15%;
}

p{
oolor:red;
}
</style>
<body>
<div>
    Please, enter your login:
    <form method="post" action="controller">
    	<input type="hidden" name="command" value="login"/>
        <input class="login" title="Login:" name="nick" type="text"/>
        <input class="sending" type="submit" value="Log in"/>
    </form>
    <p>${message}</p>
</div>
</body>
</html>