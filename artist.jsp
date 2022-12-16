<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<head>
	<title>Artists</title>
</head>
<body>
	<h1>All artists</h1>
	<form method="post">
	    	<input name="name" type="text" required placeholder="type artist name here..." autofocus /> 
	    	<input type="submit" value="Add to Artist list" />
	    	<br>
	    	<a href="/searchArtist">Search from Artist list</a>
	</form>
	
	<ol>	
        	<c:forEach items = "${ artists }" var="artist">
        		<li>
        			<a href="/albums?ArtistId=${ artist.getId() }"><c:out value="${ artist.getName() } " /></a>
  				</li>        		
       		</c:forEach>
     </ol>	
</body></html>