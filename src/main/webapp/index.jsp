<html>
<head>
<title>Starting page</title>
</head>
<body>
<h2>Enter your credentials</h2>

<script>
function validate(){
	var username = document.getElementById("lid").value;
	var password = document.getElementById("lpass").value;
	
	if(username=="admin" && password=="$12345")
		window.location = "home.jsp"; 
	
	else
		alert("incorrect username or password");
}
</script>

<form name="loginn" method="post">
<p>
Login id <input type="text" id="lid" required>
<p>
Password <input type="password" id="lpass" required>
<p>
<input type="button" value="Login" onclick="validate()"/>
</form>
</body>
</html>
