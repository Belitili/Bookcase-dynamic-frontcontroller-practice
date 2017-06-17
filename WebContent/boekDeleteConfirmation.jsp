<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="domain.model.Book" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Bevestiging invoer</title>
<link rel="stylesheet" media="all" href="css/reset.css">
<link rel="stylesheet" media="all" href="css/project.css">
</head>
<body>

<!-- Begin Header -->
<%@ include file="header.jspf"  %>
<!-- End Header -->

<main>
<section>
<h2>Bevestiging te verwijderen gegevens</h2>
<p>Je verwijdert volgend boek uit de lijst:</p>
<% Book book = (Book) request.getAttribute("bookToDelete"); %>
<ul>
	<li>Titel: <%= book.getTitle() %></li>
	<li>Auteur: <%= book.getAuthor() %></li>
</ul>
<p>Wil je hiermee doorgaan?</p>   
<form method="POST" action="BookController?action=DELETEConfirm&isbn=<%= book.getIsbn() %>">
	<input id="verwijderJa" type="submit" value="Ja" />
</form>
<form method="POST" action="BookController?action=READ">
	<input id="verwijderNee" type="submit" value="Nee" />
</form>


</section>

</main>

</body>
</html>