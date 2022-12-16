<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<head>
	<title>Albums</title>
</head>
	<body>
		<h1>All Albums of the artist</h1>
		<form method="post">
		    	<input name="albumname" type="text" required placeholder="type album name here..." autofocus /> 
		    	<input type="submit" value="Add to Artists albumlist" />
		</form>
		<ol>	
	        	<c:forEach items = "${ albums }" var="albums">
	        		<li>
	        			<c:out value="${ albums.getName() } " />
	  				</li>        		
	       		</c:forEach>
	     </ol>	
	</body>
</html>