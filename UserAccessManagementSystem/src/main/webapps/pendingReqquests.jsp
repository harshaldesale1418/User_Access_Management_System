
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pending Access Requests</title>
</head>
<body>
    <h2>Pending Access Requests</h2>
    <form action="ApprovalServlet" method="post">
        <table border="1">
            <tr>
                <th>Employee Name</th>
                <th>Software Name</th>
                <th>Access Type</th>
                <th>Reason</th>
                <th>Action</th>
            </tr>
            <!-- Dynamically populate pending requests here -->
            <tr>
                <td>Employee 1</td>
                <td>Software 1</td>
                <td>Read</td>
                <td>Project requirement</td>
                <td>
                    <button type="submit" name="action" value="approve_1">Approve</button>
                    <button type="submit" name="action" value="reject_1">Reject</button>
                </td>
            </tr>
            <!-- Additional rows will be populated dynamically -->
        </table>
    </form>
</body>
</html>
