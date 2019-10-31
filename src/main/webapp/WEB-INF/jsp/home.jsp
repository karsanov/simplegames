<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/"/>css/general.css" rel="stylesheet" />
<title>${title}</title>
</head>
<body>
	<div class="app-container">
			<h1 class="page-header">Добро пожаловать в Simple games</h1>

		<div class="centered-block">
			<h2 class="page-header">Выберите своё альтер эго</h2>
			<c:url value="/j_spring_security_check" var="loginUrl" />

			<c:forEach items="${users}" var="user">
				<div class="user-icon-large">
					<form action="${loginUrl}" method="post">
						<input type="hidden" name="j_username" required autofocus
							value="${user.name}"> <input type="hidden"
							name="j_password" required value="1234"> <input
							type="image" class="user-image-large"
							src="<c:url value="/images/avatars/${user.avatarFilename}" />"
							alt="Выбрать ${user.name}" />

					</form>
				</div>
			</c:forEach>

		</div>
	</div>
</body>
</html>