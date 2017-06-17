<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="domain.model.Book" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Update Formulier</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

 	<link rel="stylesheet" media="all" href="css/reset.css">
    <link rel="stylesheet" media="all" href="css/project.css">
</head>
<body>

<!-- Begin Header -->
<%@ include file="header.jspf"  %>
<!-- End Header -->


<main>
<section>
<h2>Update boek</h2>
<form method="POST" action="BookController?action=UPDATEConfirm">
   	<fieldset>
   	<legend>Update je boek in de boekenkast</legend>
   	<%@ include file="errors.jspf" %>
   	<% Book book = (Book) request.getAttribute("bookToUpdate"); %>
    <p>
        <label for="title">Titel*</label>
        <input id="title" name="title" autofocus="" type="text" value="<%= book.getTitle() %>">
    </p>
    <p>
        <label for="author">Auteur</label>
        <input id="author" name="author" size="auto" type="text" value="<%= book.getAuthor() %>">
    </p>      <p>
        <label for="nrpages">Aantal pagina's</label>
        <input class="small" id="nrpages" name="nrpages" autofocus="" size="5" type="number" value="<%= book.getNrOfPages() %>">
    </p> 
           <p>
        <label for="isbn">ISBN*</label>
        <input class="small" id="isbn" name="isbn" autofocus="" size="17" title="ISBN indeling 123-12-123-1234-1" placeholder="123-12-123-1234-1" type="text" readonly value="<%= book.getIsbn() %>">
    </p> <p>
        <input class="small" value="Wijzigen" autofocus="" type="submit">
    </p>
    </fieldset>
</form>
 

</section>
  
</main>

</body>
</html>