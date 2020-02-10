<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% String lang = request.getParameter("lang"); %>
<% String linkToImg = "img/"; %>
<fmt:setLocale value="<%=lang%>"/>
<fmt:setBundle basename="localization/messages"/>

<html lang="<%=lang%>">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="message.title"/></title>
</head>
<body>
<ul>
    <li><a href="?lang=en"><fmt:message key="label.lang.en"/></a></li>
    <li><a href="?lang=ru"><fmt:message key="label.lang.ru"/></a></li>
</ul>
<h2><fmt:message key="message.title"/></h2>
<fmt:message key="message.content"/><br>
<a href="documentation">https://epamdev.herokuapp.com/documentation/</a><br>
<table style="border-spacing: 50px; width: 100%">
    <tr>
        <td style="text-align: center" colspan="3">
            <a href="<%=linkToImg%>project.png"><img src="<%=linkToImg%>project.png" alt="UML" height="80%" width="65%"></a><br>
            Project UML diagram
        </td>
    </tr>
    <tr>
        <td style="text-align: center" colspan="3">
            <a href="<%=linkToImg%>model.png"><img src="<%=linkToImg%>model.png" alt="UML" height="80%" width="65%"></a><br>
            Model UML diagram
        </td>
    </tr>
    <tr>
        <td style="text-align: center" colspan="3">
            <a href="<%=linkToImg%>repository.png"><img src="<%=linkToImg%>repository.png" alt="UML" height="80%" width="50%"></a><br>
            Detail repository UML diagram
        </td>
    </tr>

</table>

</body>
</html>
