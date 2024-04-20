<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GoalForge - Password Recovery</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/morph/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            max-width: 400px;
            margin: 100px auto;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Forgot Password</h2>
    <#if error??>
        <div>
            <p class="text-danger">${error}</p>
        </div>
    </#if>
    <#if message??>
        <div>
            <p class="text-warning">${message}</p>
        </div>
    </#if>
    <form action="/forgot_password" method="post" style="max-width: 420px; margin: 0 auto;">
        <div class="border border-secondary rounded p-3">
            <div>
                <p>We will be sending a reset password link to your email.</p>
            </div>
            <div>
                <p>
                    <input type="email" name="email" class="form-control" placeholder="Enter your e-mail" required autofocus/>
                </p>
                <p class="text-center">
                    <input type="submit" value="Send" class="btn btn-primary"/>
                </p>
            </div>
        </div>
    </form>
</div>

<!-- Page Footer -->
<footer class="container mt-4 border-top pt-3">
    <p class="text-center">&#169; 2024 GoalForge USA, All rights reserved.</p>
</footer>
</body>
</html>
