<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Something wrong</title>
</head>
<body>
<h2>Something went wrong, please go to the previous or main page</h2>
<a href="main">Main page</a>
<hr>
<h3>Details: exception occurred while processing the request</h3>
<p>Type: <%= exception%></p>
<p>Message: <%= message %></p>
</body>
</html>