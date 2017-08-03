<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Добавление заметки</title>
</head>
<body>

<form:form id="formCreate" modelAttribute="newNote" method="post" action="submitUpdate">
    Название
    <form:input path="caption"/>
    Текст
    <form:textarea path="text"/>
    <form:hidden path="noteId"/>
    <button type="submit">Сохранить</button>
</form:form>
<span><a href="list">Отмена</a></span>
<span><a href="delete?id=${newNote.noteId}">Удалить</a></span>

</body>
</html>


</body>
</html>
