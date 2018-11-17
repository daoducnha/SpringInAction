<%--
  Created by IntelliJ IDEA.
  User: duc-nha
  Date: 10/11/2018
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spittle</title>
</head>
<body>
    <div class="spittleView">
        <div class="spittleMessage"><c:out value="${spittle.message}"/></div>
        <div>
            <span class="spittleTime"><c:out value="${spittle.time}"/></span>
        </div>
    </div>
</body>
</html>
