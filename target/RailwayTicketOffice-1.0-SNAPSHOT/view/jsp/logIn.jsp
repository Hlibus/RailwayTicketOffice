<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<html>
<head>
    <title>Authorization</title>
</head>
<body>

<a href="main">Main page</a>

<c:choose>
    <c:when test="${sessionScope.logInErrorMessage != null}">
        <h3>Error message: ${sessionScope.logInErrorMessage}</h3>
    </c:when>
</c:choose>

<form action="logIn" method="post">
    <input name="login" placeholder="login" required/> <br/>
    <input type="password" name="pass" placeholder="password" required/> <br/>
    <input type="submit" value="log in"/>
</form>

</body>
</html>
