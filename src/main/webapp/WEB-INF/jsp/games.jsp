<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/"/>css/general.css" rel="stylesheet" />
<link href="<c:url value="/"/>css/gamesmenu.css" rel="stylesheet" />
<title>Games page</title>
</head>
<body>
	<div class="app-container">
		<div>
			<p class="current-user">
			<a href="<c:url value="/"/>logout">Выйти (${user.name})</a>
			</p>
			<h1 class="page-header">Выберите игру</h1>

		</div>

		<div class="centered-block">
		
			<div class="game-menu-item">
			<h3 class="game-title">Блэкджэк</h3>
			
			<a href="<c:url value="/games/blackjack" />">
			<img src="<c:url value="/images/gameicons/gnome-blackjack.png" />">
			</a>
			<br /><span class="sub">(Рекомендуемое)</span>
			</div>
		</div>
	</div>
</body>
</html>