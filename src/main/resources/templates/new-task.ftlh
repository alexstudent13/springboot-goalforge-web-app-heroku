<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New-Task</title>
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
    <h2>New Task</h2>
    <form action="/save" method="post">
        <!-- Task Name -->
        <div class="form-group">
            <label for="taskName">Task Name/Title:</label>
            <input type="text" id="taskName" name="taskName" class="form-control" required>
        </div>

        <!-- Task Description -->
        <div class="form-group">
            <label for="taskDescription">Description:</label>
            <textarea id="taskDescription" name="taskDescription" class="form-control" rows="5"></textarea>
        </div>

        <!-- Due Date -->
        <div class="form-group">
            <label for="dueDate">Due Date:</label>
            <input type="date" id="dueDate" name="dueDate" class="form-control" required>
        </div>

        <!-- Priority -->
        <div class="form-group">
            <label for="priority">Priority:</label>
            <select id="priority" name="priority" class="form-control" required>
                <option value="High">High</option>
                <option value="Medium">Medium</option>
                <option value="Low">Low</option>
            </select>
        </div>

        <!-- Task Status -->
        <div class="form-group">
            <label for="taskStatus">Status:</label>
            <select id="taskStatus" name="taskStatus" class="form-control" required>
                <option value="Not Started">Not Started</option>
                <option value="In Progress">In Progress</option>
                <option value="Completed">Completed</option>
            </select>
        </div>

        <!-- Task Category -->
        <div class="form-group">
            <label for="category">Category:</label>
            <select id="category" name="category" class="form-control" required>
                <#list categories as category>
                    <option value="${category}">${category}</option>
                </#list>
            </select>
        </div>

        <!-- Task Notifications -->
        <div class="form-group">
            <label for="notifications">Notifications:</label>
            <input type="checkbox" id="notifications" name="notifications">
        </div>
        <button type="submit" class="btn btn-primary">Save Task</button>
        <button type="button" class="btn btn-secondary" onclick="window.location.href='/dashboard-page'">Cancel</button>
    </form>
</div>
</body>
</html>