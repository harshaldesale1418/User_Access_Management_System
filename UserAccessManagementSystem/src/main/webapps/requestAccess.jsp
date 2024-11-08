
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request Access</title>
</head>
<body>
    <h2>Request Access to Software</h2>
    <form action="RequestServlet" method="post">
        <label for="softwareName">Software Name:</label>
        <select id="softwareName" name="softwareName" required>
            <!-- Dynamically populate software options -->
            <!-- Example option -->
            <option value="1">Software 1</option>
            <option value="2">Software 2</option>
        </select><br><br>

        <label for="accessType">Access Type:</label>
        <select id="accessType" name="accessType" required>
            <option value="Read">Read</option>
            <option value="Write">Write</option>
            <option value="Admin">Admin</option>
        </select><br><br>

        <label for="reason">Reason for Request:</label>
        <textarea id="reason" name="reason" rows="4" cols="50" required></textarea><br><br>

        <input type="submit" value="Submit Request">
    </form>
</body>
</html>
