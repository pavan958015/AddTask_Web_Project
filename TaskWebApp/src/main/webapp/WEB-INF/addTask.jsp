<!-- addTask.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Task</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="grad">
        <nav class="navbar navbar-expand-lg navbar-light">
            <a class="navbar-brand" href="#">Pavan</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="logout.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <h3>Add a Task</h3>
            <form action="addTask" method="post">
                <div class="form-group">
                    <label for="taskName">Task Name</label>
                    <input type="text" class="form-control" id="taskName" name="taskName" placeholder="Task Name" required>
                </div>
                <div class="form-group">
                    <label for="taskPriority">Priority</label>
                    <select class="form-control" id="taskPriority" name="priority" required>
                        <option value="high">High</option>
                        <option value="medium">Medium</option>
                        <option value="low">Low</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Add Task</button>
            </form>
        </div>
    </div>
</body>
</html>
