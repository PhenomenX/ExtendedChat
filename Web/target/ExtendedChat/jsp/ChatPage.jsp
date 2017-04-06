<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Welcome</title>
</head>
<style>
body{
background-image: url(${pageContext.request.contextPath}/image/light-tile.gif);
}
div {
background-color: white;
	border: solid 2px #424242;
}

div.all {
background-color: white;

	border: solid 0px black;
	left: 20%;
	position: relative;
}

div.messages {
	overflow: scroll;
	width: 600px;
	height: 350px;
	left: 0px;
	top: 70px;
	position: absolute;
}

div.onlineUsers {
	overflow: scroll;
	width: 150px;
	height: 350px;
	left: 602px;
	top: 70px;
	position: absolute;
}

div.messageBox {
	width: 752px;
	height: 50px;
	left: 0px;
	top: 422px;
	position: absolute;
}

input.sending {
	height: 46px;
	top: 2px;
	right: 2px;
	position: absolute;
	background-color: #424242;
    border: none;
    color: white;
    text-decoration: none;
    cursor: pointer;
	
}
input.unkick{
	background-color: #008000;
    border: none;
    color: white;
    text-decoration: none;
    cursor: pointer;
}

.logout {
	top: 51px;
	left: 705px;
	position: absolute;
	background-color: #424242;
    border: none;
    color: white;
    text-decoration: none;
    cursor: pointer;
}

h3 {
	top: 25px;
	position: absolute;
}
.kick {
	background-color: #8B0000;
    border: none;
    color: white;
    text-decoration: none;
    cursor: pointer;
}
p{
font-weight:700;
}
</style>
<body>
	<div class="all">
		<h3>Welcome, ${nick} !</h3>
		<form method="post" action="controller">
		<input type="hidden" name="command" value="logout"/>
		<input class="logout" type="submit" value="Logout" />
		</form>
		<div class="messages">
			 <c:forEach var="message" items="${messages}">
       			 ${message} <br><br>
   			 </c:forEach>
		</div>
		<div class="onlineUsers">
			<p>Logged users:</p>
			<c:forEach var="user" items="${users}">
			<form method="post" action="controller">
       			 ${user.name}
       			 <c:if test="${role eq 'admin'}">
       			 <input type="hidden" name="username" value='${user.name}'/>
       			 <input type="hidden" name="command" value="kick"/>
       			 <input class="kick" type="submit" value="kick" /> 
       			 </c:if>
       			 <br><br>
       		 </form>
   			 </c:forEach>
   			 <c:set var="userRole" value="admin" scope="page"/>
   			 <c:if test="${role eq 'admin'}">  			 
   			 <p>Kicked users:</p>
   			 <c:forEach var="user" items="${kickedUsers}">
			<form method="post" action="controller">
       			 ${user.name}
       			 <input type="hidden" name="username" value='${user.name}'/>
       			 <input type="hidden" name="command" value="unkick"/>
       			 <input class="unkick" type="submit" value="unkick" /> 
       			 <br><br>
       		 </form>
   			 </c:forEach>
   			 </c:if>
		</div>
		<div class="messageBox">
			<form method="post" action="controller">
			<input type="hidden" name="command" value="message"/>
			<textarea name="text" cols ="86" rows ="3"></textarea>
			<input class="sending" type="submit" value="Send Message" />
			</form>
		</div>
	</div>
</body>
</html>