document.addEventListener('DOMContentLoaded', () => {
    const taskForm = document.getElementById('taskForm');
    const taskList = document.getElementById('taskList');

    taskForm.addEventListener('submit', (e) => {
        e.preventDefault();
        
        const taskName = document.getElementById('taskName').value;
        const taskPriority = document.getElementById('taskPriority').value;

        const taskItem = createTaskItem(taskName, taskPriority);
        taskList.appendChild(taskItem);

        taskForm.reset();
    });

    function createTaskItem(name, priority) {
        const taskItem = document.createElement('li');
        taskItem.classList.add('list-group-item', 'd-flex', 'justify-content-between', 'align-items-center');

        const taskContent = document.createElement('div');
        taskContent.classList.add('d-flex', 'align-items-center');
        taskContent.appendChild(createPriorityBadge(priority));
        taskContent.appendChild(createTaskText(name));

        const deleteButton = createDeleteButton(taskItem);
        
        taskItem.appendChild(taskContent);
        taskItem.appendChild(deleteButton);

        return taskItem;
    }

    function createPriorityBadge(priority) {
        const badge = document.createElement('span');
        badge.classList.add('badge', 'badge-pill');
        badge.classList.add(priority === 'high' ? 'badge-danger' : priority === 'medium' ? 'badge-warning' : 'badge-primary');
        badge.textContent = priority.charAt(0).toUpperCase() + priority.slice(1);
        return badge;
    }

    function createTaskText(name) {
        const text = document.createElement('span');
        text.textContent = name;
        return text;
    }

    function createDeleteButton(taskItem) {
        const button = document.createElement('button');
        button.textContent = 'Delete';
        button.classList.add('btn', 'btn-danger', 'btn-sm');
        button.addEventListener('click', () => taskList.removeChild(taskItem));
        return button;
    }
});
