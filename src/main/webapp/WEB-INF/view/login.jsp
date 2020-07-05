<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Login Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
        <form:form modelAttribute="login" method="POST" >
        <c:if test="${status == 'true'}">
            <div class="error">
                 <span>${error}</span>
            </div>
        </c:if>
      <h1>Authentication:</h1>

      <label>Username :</label>
      <form:input path ="user" type="text"/>
      <br/>

      <label>Password :</label>
      <form:input path ="pwd" type="password"/>
      <br/>
       <label for="remember"> Remember me</label>  
       <input type="checkbox" name="remember-me" id="remember-me" />  
    </p>
      <br/>
      <button>login</button>
    </form:form>
</body>
</html>
