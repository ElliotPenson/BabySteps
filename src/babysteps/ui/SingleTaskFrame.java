package babysteps.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import babysteps.model.Task;
import babysteps.model.TaskModel;

/**
 * A frame for the presentation of one task. SingleTaskFrame attempts to find the most specific
 * subtask with the help of the model. This task represents the next "baby step." If such a task
 * cannot be found, the frame tells the user.
 * 
 * @author Elliot Penson
 */
public class SingleTaskFrame extends FrameMixin implements Runnable {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * 
     * @param model
     */
    public SingleTaskFrame(TaskModel model) {
        super("Baby step", model);
    }

    /**
     * Ask the model for the next baby step. If available, display to the user and allow
     * completion. If no tasks remain, notify the user.
     */
    @Override
    public void run() {
        Optional<Task> task = model.simplestTask();
        if (task.isPresent()) {
            JLabel preamble = new JLabel("Your next baby step:");
            JLabel taskTitle = new JLabel(task.get().getTitle());
            JButton completeButton = new JButton("Complete");
            completeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    task.get().setCompleted();
                    setVisible(false);
                    (new SingleTaskFrame(model)).run();
                }
            });
            singleColumnLayout(preamble, taskTitle, completeButton);
        } else {
            JLabel alert = new JLabel("No tasks remaining!");
            JLabel affirmation = new JLabel("Great work!", SwingConstants.CENTER);
            singleColumnLayout(alert, affirmation);
        }

        pack();
        centerFrame();
        setVisible(true);
    }
}
