package babysteps.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import babysteps.model.TaskModel;

/**
 * A frame that allows the user to choose a single subtask. The model provides the list of tasks.
 * Once a task is chosen, TaskSelectionFrame makes way for a SingleTaskFrame.
 * 
 * @author ejnp
 */
public class TaskSelectionFrame extends FrameMixin implements Runnable {

    private static final long serialVersionUID = 1L;
    
    private TaskList taskList;
    
    /**
     * Constructor.
     * 
     * @param model
     */
    public TaskSelectionFrame(TaskModel model) {
        super("Next", model);
    }
    
    /**
     * A listener that alerts the model of a chosen task and runs a new SingleTaskFrame.
     */
    private class EnterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.enterTask(taskList.getSelectedIndex());
            setVisible(false);
            (new SingleTaskFrame(model)).run();
        }
    }

    /**
     * Add all components to a GroupLayout, resize the window, center, and display.
     */
    @Override
    public void run() {
        JLabel selection = new JLabel("Please choose a main task.");
        taskList = new TaskList(model);
        JButton button = new JButton("Enter");
        button.addActionListener(new EnterButtonListener());
        singleColumnLayout(selection, taskList, button);
        
        pack();
        centerFrame();
        setVisible(true);
    }
}
