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
    <p>
        <a href="/create">Добавить новую</a>
    </p>

    <tr>
        <th>Id</th>
        <th>Название</th>
        <th>Текст</th>
        <th>Последняя модификация</th>
    </tr>
    <c:forEach items="${list}" var="item">
        <tr>
            <td><c:out value="${item.noteId}"/></td>
            <td><c:out value="${item.caption}"/></td>
            <td><c:out value="${item.text}"/></td>
            <td><c:out value="${item.lastEdit}"/></td>
            <td>
                <span><a href="update?id=${item.noteId}">edit</a></span>
                <span><a href="delete?id=${item.noteId}">delete</a></span>
            </td>

        </tr>
    </c:forEach>


</table>

</body>
</html>
