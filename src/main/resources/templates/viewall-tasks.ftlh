<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View All Tasks</title>
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
        table {
            margin: auto;
            width: 80%;
        }
        tbody tr {
            border-bottom: 1px solid #dee2e6;
        }
        tbody td {
            border-left: none;
            border-right: none;
        }
        tbody tr:last-child {
            border-bottom: none;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .delete-button-container {
            margin-bottom: 10px;
        }

        .delete-button-container button {
            margin-right: 10px;
        }

        .dropdown-container {
            display: flex;
            align-items: center;
        }

        .dropdown {
            margin-right: 10px;
        }

        .dropdown-toggle {
            color: black; /* Adjust the color of the toggle button text */
        }
    </style>
</head>
<body>
<!-- Navigation Bar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/dashboard-page"><i class="bi bi-globe"></i> Goal Forge </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/dashboard-page">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Calendar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/viewall-tasks">View All Tasks</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Settings</a>
                </li>
            </ul>
            <form class="d-flex" action="/logout" method="post">
                <button class="btn btn-outline-danger me-2" type="submit">Logout</button>
            </form>
        </div>
    </div>
</nav>
<h1>All Tasks</h1>
<div class="button-container">
    <form class="delete-button-container" id="deleteForm" action="/delete-multiple" method="POST" enctype="multipart/form-data">
        <input type="hidden" id="taskIdsInput" name="taskIds" value="">
        <button type="button" class="btn btn-danger" onclick="deleteSelected()">Delete Selected</button>
    </form>

    <div class="new-task-button">
        <button type="button" class="btn btn-primary" onclick="location.href='new-task'">New Task</button>
    </div>
</div>

<table class="table">
    <thead>
    <tr>
        <th>Delete</th>
        <th>Task Name</th>
        <th>Due Date</th>
        <!-- Priority Dropdown Menu-->
        <th>
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="priorityDropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                    Priority
                </button>
                <ul class="dropdown-menu" aria-labelledby="priorityDropdownMenuButton">
                    <li><a class="dropdown-item" href="/viewall-tasks">All Priorities</a></li>
                    <li><a class="dropdown-item" href="/view-by-priority?priority=High">High</a></li>
                    <li><a class="dropdown-item" href="/view-by-priority?priority=Medium">Medium</a></li>
                    <li><a class="dropdown-item" href="/view-by-priority?priority=Low">Low</a></li>
                </ul>
            </div>
        </th>
        <th>Status</th>
        <!-- Category Dropdown Menu-->
        <th>
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="categoryDropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                    Category
                </button>
                <ul class="dropdown-menu" aria-labelledby="categoryDropdownMenuButton">
                    <li><a class="dropdown-item" href="/viewall-tasks">All Categories</a></li>
                    <#list categories as category>
                        <li><a class="dropdown-item" href="/view-by-category?category=${category}">${category}</a></li>
                    </#list>
                </ul>
            </div>
        </th>
    </tr>
    </thead>
    <tbody>
    <#list tasks?sort_by(['dueDate']) as task>
        <#if !selectedCategory?has_content || task.category == selectedCategory>
            <tr>
                <td><input type="checkbox" name="taskIds" value="${task.taskId}"></td>
                <td><a href="/task-details/${task.taskId}">${task.taskName}</a></td>
                <td>${task.dueDate}</td>
                <td>${task.priority}</td>
                <td>${task.taskStatus}</td>
                <td>${task.category}</td>
                <td>
                    <form action="/edit/${task.taskId}" method="get">
                        <input type="hidden" name="taskId" value="${task.taskId}">
                        <button type="submit" class="btn btn-dark">Edit Task</button>
                    </form>
                </td>
            </tr>
        </#if>
    </#list>
    </tbody>
</table>

<!-- Page Footer -->
<footer class="container mt-4 border-top pt-3">
    <p class="text-center">&#169; 2024 GoalForge USA, All rights reserved.</p>
</footer>

<!-- Bootstrap JavaScript -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous">
</script>

<!-- Delete task script-->
<script>
    function deleteSelected() {
        const checkboxes = document.querySelectorAll('input[name="taskIds"]:checked');
        if (checkboxes.length === 0) {
            alert("Please select at least one task to delete.");
        } else {
            if (confirm("Are you sure you want to delete the selected tasks?")) {
                const taskIds = Array.from(checkboxes).map(checkbox => checkbox.value);
                fetch('/delete-multiple', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ taskIds: taskIds })
                })
                    .then(response => {
                        if (response.ok) {
                            location.reload(); // Reload the page after successful deletion
                        } else {
                            throw new Error('Failed to delete tasks');
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('Failed to delete tasks');
                    });
            }
        }
    }
</script>
</body>
</html>