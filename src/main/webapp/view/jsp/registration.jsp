<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<a  href="main">Main page</a>

<c:choose>
    <c:when test="${sessionScope.signUpErrorMessage != null}">
        <h3>Error message: ${sessionScope.signUpErrorMessage}</h3>
    </c:when>
</c:choose>

<form action="registration" method="post">
    <input name="login" placeholder="login" required/> <br/>
    <input type="password" name="pass" placeholder="password" required/> <br/>
    <input type="password" name="confirmPass" placeholder="confirm pass" required/> <br/>
    <input type="submit" value="sign up"/>
</form>

</body>
</html>
