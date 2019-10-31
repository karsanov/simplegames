<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/"/>css/general.css" rel="stylesheet" />
<link href="<c:url value="/"/>css/games/blackjack.css" rel="stylesheet" />
<title>Black Jack</title>
</head>
<body>
	<div class="app-container">
	<p class="current-user">
	<a href="<c:url value="/"/>games">К списку игр</a> | 
	<a href="<c:url value="/"/>logout">Выйти (${user.name})</a>
	</p>
		<h1 class="page-header">Black Jack</h1>

		<div class="table">
			<div class="dealer-table-part">
				<c:forEach items="${dealersHand}" var="card">
					<div class="card">
						<img class="card-image"
							src="<c:url value="/images/playingcards/${card}.png" />" />
					</div>
				</c:forEach>
			</div>

			<div class="player-table-part">
				<c:forEach items="${playersHand}" var="card">
					<div class="card">
						<img class="card-image"
							src="<c:url value="/images/playingcards/${card}.png" />" />
					</div>
				</c:forEach>
			</div>
			
			<div class="dealers-block">
			<div class="user-icon-middle">
			<img src="<c:url value="/images/avatars/dealer.png" />" class="user-image-large" />
			</div>
			<div class="scores">
			<c:choose>
					<c:when test="${isGameFinished==true}">
					${dealersScores}
					</c:when>
					<c:otherwise>
					<br />
					</c:otherwise>
				</c:choose>
			
			</div>
			</div>
			
			
			<div class="action-menu">
				<c:choose>
					<c:when test="${isGameFinished==true}">
				 	
					<div class="result">
					${gameResult}
					</div>
					
					<div class="action-button-block">
						<form action="<c:url value="/"/>games/blackjack" method="post">
						<input type="hidden" name="action" required value="newgame">
						<input type="submit" value="Новая игра">
						</form>
					</div>
					</c:when>
					<c:otherwise>
					<div class="action-button-block">
						<form action="<c:url value="/"/>games/blackjack" method="post">
						<input type="hidden" name="action" required value="hit">
						<input type="submit" value="Ещё">
						</form>
						</div>
						<div class="action-button-block">
						<form action="<c:url value="/"/>games/blackjack" method="post">
						<input type="hidden" name="action" required value="stand">
						<input type="submit" value="Хватит">
						</form>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			
			<div class="players-block">
			<div class="scores">
			${playersScores}
			</div>
			
			<div class="user-icon-middle">
			<img src="<c:url value="/images/avatars/${user.avatarFilename}" />" class="user-image-large" />
			</div>
			</div>
			
		</div>
	</div>
</body>
</html>