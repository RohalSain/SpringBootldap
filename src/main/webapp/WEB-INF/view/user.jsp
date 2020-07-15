<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>ldap User Creation Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
        <form:form modelAttribute="userCreation" method="POST" >
        <c:if test="${status == 'true'}">
            <div class="error">
                 <span>${error}</span>
            </div>
        </c:if>
      <h1>ldap User Creation:</h1>

      <label>Username :</label>
      <form:input path ="username" type="text"/>
      <br/>

      <label>Password :</label>
      <form:input path ="password" type="password"/>
      <br/>  
    </p>
      <br/>
      <button>login</button>
    </form:form>
</body>
</html>
