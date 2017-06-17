<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Formulier met GET-request</title>
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
<h2>Boek toevoegen</h2>
<form method="POST" action="BookController?action=ADD">
   	<fieldset>
   	<legend>Registreer je boek in de boekenkast</legend>
    <p>
    	<%	if (request.getAttribute("errors") != null) { %>
	    	<%@ include file="errors.jspf" %>
    	<%	}  %>
    	<%@ include file="addBookForm.jspf" %>
        <input class="small" id="addBook" value="Registreer" autofocus="" type="submit">
    </p>
    </fieldset>
</form>
 

</section>
  
</main>

</body>
</html>