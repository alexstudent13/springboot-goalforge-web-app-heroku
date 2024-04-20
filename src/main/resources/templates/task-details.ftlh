<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task-Details</title>
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
    </style>
</head>
<body>
<h1>Task Details</h1>
<div class="container">
    <p><strong>Task Name:</strong> ${task.taskName}</p>
    <p><strong>Task Description:</strong> ${task.taskDescription}</p>
    <p><strong>Due Date:</strong> ${task.dueDate}</p>
    <p><strong>Priority:</strong> ${task.priority}</p>
    <p><strong>Status:</strong> ${task.taskStatus}</p>
    <p><strong>Category:</strong> ${task.category}</p>
</div>

<button type="button" class="btn btn-primary" onclick="window.location.href='/dashboard-page'">Back</button>

<!-- Page Footer -->
<footer class="container mt-4 border-top pt-3">
    <p class="text-center">&#169; 2024 GoalForge USA, All rights reserved.</p>
</footer>

</body>
</html>
