package babysteps.model;

import java.util.ArrayList;
import java.util.Optional;

/**
 * An encapsulation of a task and its subtasks. A B-tree structure with parent references is used.
 * Each node is a task. A node's children are the more specific tasks required for completion.
 * 
 * @author Elliot Penson
 */
public class Task implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Name of the task.
     */
    private String title;

    /**
     * Flag to indicate if the task has been finished.
     */
    private boolean completed;
    
    /**
     * The (more general) task above this one in the class tree.
     */
    private Optional<Task> parent;

    /**
     * List of children that represent jobs within this task.
     */
    private ArrayList<Task> subtasks;

    public Task(String title) {
        this.title = title;
        completed = false;
        parent = Optional.empty();
        subtasks = new ArrayList<Task>();
    }
    
    public Task(String title, Task parent) {
        this.title = title;
        completed = false;
        this.parent = Optional.of(parent);
        subtasks = new ArrayList<Task>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public boolean completed() {
        return completed;
    }

    public void setCompleted() {
        completed = true;
    }

    public void setUncompleted() {
        completed = false;
    }
    
    public Optional<Task> getParent() {
        return parent;
    }

    public ArrayList<Task> getSubtasks() {
        return subtasks;
    }
}