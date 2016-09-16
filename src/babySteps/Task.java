package babySteps;

import java.util.ArrayList;

/**
 * An encapsulation of a task and its subtasks. A B-tree structure is used.
 * Each node is a task. A node's children are the more specific tasks required
 * for completion.
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
	 * List of children that represent jobs within this task.
	 */
	private ArrayList<Task> subtasks;
	
	public Task(String title) {
		this.title = title;
		completed = false;
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
	
	public void addSubtask(Task subtask) {
		subtasks.add(subtask);
	}
	
	public int numberOfSubtasks() {
		return subtasks.size();
	}
	
	public boolean hasSubtasks() {
		return !subtasks.isEmpty();
	}
	
	public ArrayList<Task> getSubtasks() {
		return subtasks;
	}
}
