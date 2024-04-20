<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update-Task</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">

    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
          rel="stylesheet">

    <!-- Bootswatch Theme (Morph) -->
    <link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/morph/bootstrap.min.css"
          rel="stylesheet">
    <style>
        .container {
            margin-top: 50px;
        }
        .form-group {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Edit Task</h2>
    <h3>Task Name: ${task.taskName}</h3>
    <form action="/update" method="post">
        <!-- Hidden fields for task ID, task name, and task description -->
        <input type="hidden" id="taskId" name="taskId" value="${task.taskId}">
        <input type="hidden" id="taskName" name="taskName" value="${task.taskName}">
        <input type="hidden" id="taskDescription" name="taskDescription" value="${task.taskDescription}">

        <!-- Due Date -->
        <div class="form-group">
            <label for="dueDate">Due Date:</label>
            <input type="date" id="dueDate" name="dueDate" class="form-control" value="${task.dueDate?string("yyyy-MM-dd")}" required>
        </div>

        <!-- Priority -->
        <div class="form-group">
            <label for="priority">Priority:</label>
            <select id="priority" name="priority" class="form-control" required>
                <option value="High" <#if task.priority == 'High'>selected</#if>>High</option>
                <option value="Medium" <#if task.priority == 'Medium'>selected</#if>>Medium</option>
                <option value="Low" <#if task.priority == 'Low'>selected</#if>>Low</option>
            </select>
        </div>

        <!-- Task Status -->
        <div class="form-group">
            <label for="taskStatus">Status:</label>
            <select id="taskStatus" name="taskStatus" class="form-control" required>
                <option value="Not Started" <#if task.taskStatus == 'Not Started'>selected</#if>>Not Started</option>
                <option value="In Progress" <#if task.taskStatus == 'In Progress'>selected</#if>>In Progress</option>
                <option value="Completed" <#if task.taskStatus == 'Completed'>selected</#if>>Completed</option>
            </select>
        </div>

        <!-- Task Category -->
        <div class="form-group">
            <label for="category">Category:</label>
            <select id="category" name="category" class="form-control" required>
                <#list categories as cat>
                    <option value="${cat}" <#if task.category == cat>selected</#if>>${cat}</option>
                </#list>
            </select>
        </div>

        <!-- Task Notifications -->
        <div class="form-group">
            <label for="notifications">Notifications:</label>
            <input type="checkbox" id="notifications" name="notifications">
        </div>
        <button type="submit" class="btn btn-primary">Update Task</button>
        <button type="button" class="btn btn-secondary" onclick="window.location.href='/dashboard-page'">Cancel</button>
    </form>
</div>

<!-- Page Footer -->
<footer class="container mt-4 border-top pt-3">
    <p class="text-center">&#169; 2024 GoalForge USA, All rights reserved.</p>
</footer>

</body>
</html>
