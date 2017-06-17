<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="domain.model.Book" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Overzicht deelnemers</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <link rel="stylesheet" media="all" href="css/reset.css">
    <link rel="stylesheet" media="all" href="css/project.css">
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
</head>
<body>

<!-- Begin Header -->
<%@ include file="header.jspf"  %>
<!-- End Header -->

<main>
	<section>
		<h2>Mijn boekenkast</h2>
		<table>
			<thead>
				<tr>
					<th>Titel</th>
					<th>Auteur</th>
					<th>Aantal pg.</th>
					<th>ISBN</th>
					<th>Verwijder?</th>
				</tr>
			</thead>
			<tbody>
			<% 	Collection<Book> books = (Collection<Book>) request.getAttribute("books");
				for(Book book: books) { %>
					<th><%= book.getTitle() %></th>
					<th><%= book.getAuthor() %></th>
					<th><%= book.getNrOfPages() %></th>
					<th><%= book.getIsbn() %></th>
					<th>Verwijder TODO</th>
			<%	} %>
			</tbody>
		</table>
	</section>
</main>

</body>
</html>