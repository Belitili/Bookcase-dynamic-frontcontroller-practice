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
<form >
   <fieldset>
   <legend>Registreer je boek in de boekenkast</legend>
    <p>
        <label for="">Titel*</label>
        <input id="" name="title" autofocus="" type="text">
    </p>
    <p>
        <label for="">Auteur</label>
        
        <input id="" name="author" size="auto" type="text">
    </p>      <p>
        <label for="">Aantal pagina's</label>
        <input class="small" id="" name="nrpages" autofocus="" size="5" type="number">
    </p> 
           <p>
        <label for="">ISBN*</label>
        <input class="small" id="" name="isbn" autofocus="" size="17" title="ISBN indeling 123-12-123-1234-1" placeholder="123-12-123-1234-1" type="text">
    </p> <p>
        <input class="small" value="Registreer" autofocus="" type="submit">
    </p>
    </fieldset>
</form>
 

</section>
  
</main>

</body>
</html>