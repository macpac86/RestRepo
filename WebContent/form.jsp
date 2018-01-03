<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="rest/users" method="POST">
		<label for="id">ID</label>
		<input name="id" />
		<br/>
		<label for="name">Nome</label>
		<input name="name" />
		<br/>
		<label for="surname">Cognome</label>
		<input name="surname" />
		<label for="place">Indirizzo</label>
		<input name="place" />
		<label for="cap">Cap</label>
		<input name="cap" />
		<label for="city">Città</label>
		<input name="city" />
		<br/>
		
		<input type="submit" value="Inserisci Utente" />
	</form>
	
	<form action="rest/deleteUsers" method="POST">
		<label for="id">ID</label>
		<input name="id" />
		
		<input type="submit" value="Elimina utente" />
	</form>

</body>
</html>