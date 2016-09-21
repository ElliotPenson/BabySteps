package babysteps.model;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.stream.Collectors;

public class TaskModel {
    
    /**
     * Top of the task B-tree. All subtasks should be contained within this instance.
     */
    private Task currentTask;
    
    /**
     * Constructor.
     */
    public TaskModel() {
        currentTask = new Task("Tasks");
    }
    
    /**
     * Provide all of the subtask titles.
     * 
     * @return a List of strings
     */
    public List<String> subtaskNames() {
        return currentTask.getSubtasks().stream()
                .map(Task::getTitle)
                .collect(Collectors.toList());
    }
    
    /**
     * Return the head of the task tree.
     * 
     * @return a Task instance
     */
    public Task getCurrentTask() {
        return currentTask;
    }
    
    /**
     * Add a child to the current task.
     * 
     * @param title
     */
    public void createTask(String title) {
        currentTask.getSubtasks().add(new Task(title, currentTask));
    }
    
    /**
     * Move down the tree to a specific task.
     * 
     * @param taskNumber
     */
    public void enterTask(int taskNumber) {
        currentTask = currentTask.getSubtasks().get(taskNumber);
    }
    
    /**
     * Go to the task above this one in the task tree.
     */
    public void moveUp() {
        currentTask.getParent().ifPresent(t -> currentTask = t);
    }
    
    /**
     * Find the top of the task tree. i.e. discover the most general task.
     * 
     * @return the parent task
     */
    private Task headTask() {
        Task place = currentTask;
        while(place.getParent().isPresent()) {
            place = place.getParent().get();
        }
        return place;
    }
    
    /**
     * Save all tasks (including parents) to the given file.
     * 
     * @param file
     * @return true if successful, false otherwise
     */
    public boolean serialize(String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(headTask());
            out.close();
            fileOut.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Overwrite the entire model with a new task tree. The previous task tree is not saved.
     * 
     * @param file
     * @return true if successful, false otherwise
     */
    public boolean deseriablize(String path) {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            currentTask = (Task) in.readObject();
            in.close();
            fileIn.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
