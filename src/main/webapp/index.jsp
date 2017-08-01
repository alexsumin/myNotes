<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="NoteBean" class="ru.alexsumin.notes.NoteBean" scope="application"/>
<html>
<head>
    <title>MyNotes service</title>
</head>
<body>
<h2>Список заметок:</h2>
<table border="2px">
    <tr>
        <th>Id</th>
        <th>Text</th>
        <th>LastModified</th>
    </tr>
    <c:forEach items="${list}" var="item">
        <tr>
            <td><c:out value="${item.NoteId}"/></td>
            <td><c:out value="${item.Text}"/></td>
            <td><c:out value="${item.LastEdit}"/></td>


        </tr>
    </c:forEach>


</table>

</body>
</html>
