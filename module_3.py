import firebase_admin
from firebase_admin import credentials, firestore

# Initialize Firebase with your own credentials JSON file
cred = credentials.Certificate("module3-95981-firebase-adminsdk-awy1s-36fa8a6404.json")
firebase_admin.initialize_app(cred)

# Initialize Firestore
db = firestore.client()
# Function to add a task
def add_task(user_id, task):
    task_ref = db.collection('tasks').document()
    task_ref.set({
        'user_id': user_id,
        'task': task
    })

# Function to update a task
def update_task(task_id, updated_task):
    task_ref = db.collection('tasks').document(task_id)
    task_ref.update({
        'task': updated_task
    })

# Function to list tasks for a specific user
def list_tasks(user_id):
    tasks_ref = db.collection('tasks')
    tasks = tasks_ref.where('user_id', '==', user_id).stream()
    task_list = []
    for task in tasks:
        task_data = task.to_dict()
        task_list.append({'id': task.id, 'task': task_data['task']})
    return task_list

# Function to delete a task
def delete_task(task_id):
    task_ref = db.collection('tasks').document(task_id)
    task_ref.delete()

# Function to query tasks based on a keyword
def query_tasks(user_id, keyword):
    tasks_ref = db.collection('tasks')
    query = tasks_ref.where('user_id', '==', user_id).where('task', '>=', keyword)
    tasks = query.stream()
    task_list = []
    for task in tasks:
        task_data = task.to_dict()
        task_list.append({'id': task.id, 'task': task_data['task']})
    return task_list

if __name__ == "__main__":
    user_id = "user123"
    
    # Add tasks
    add_task(user_id, "Buy groceries")
    add_task(user_id, "Finish report")
    
    # List tasks
    tasks = list_tasks(user_id)
    for task in tasks:
        print(f"Task ID: {task['id']}, Task: {task['task']}")

    # Update a task
    task_id_to_update = tasks[0]['id']
    update_task(task_id_to_update, "Buy fresh vegetables")
    print("Task updated.")

    # List tasks again
    updated_tasks = list_tasks(user_id)
    for task in updated_tasks:
        print(f"Task ID: {task['id']}, Task: {task['task']}")

    # Delete a task
    task_to_delete = updated_tasks[1]['id']
    delete_task(task_to_delete)
    print("Task deleted.")

    # Query tasks
    query_keyword = "groceries"
    queried_tasks = query_tasks(user_id, query_keyword)
    for task in queried_tasks:
        print(f"Queried Task ID: {task['id']}, Task: {task['task']}")
