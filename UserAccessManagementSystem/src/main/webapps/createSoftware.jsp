
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Software</title>
</head>
<body>
    <h2>Create Software Application</h2>
    <form action="SoftwareServlet" method="post">
        <label for="softwareName">Software Name:</label>
        <input type="text" id="softwareName" name="softwareName" required><br><br>

        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="4" cols="50" required></textarea><br><br>

        <label>Access Levels:</label><br>
        <input type="checkbox" name="accessLevel" value="Read"> Read<br>
        <input type="checkbox" name="accessLevel" value="Write"> Write<br>
        <input type="checkbox" name="accessLevel" value="Admin"> Admin<br><br>

        <input type="submit" value="Add Software">
    </form>
</body>
</html>
