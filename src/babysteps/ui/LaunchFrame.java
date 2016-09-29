package babysteps.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import babysteps.model.TaskModel;

/**
 * The first window the user sees.
 * 
 * @author Elliot Penson
 */
public class LaunchFrame extends FrameMixin implements Runnable {

    private static final long serialVersionUID = 1L;
    
    private static final int WIDTH = 205;
    
    private static final int HEIGHT = 93;
    
    /**
     * Constructor.
     * 
     * @param model
     */
    public LaunchFrame(TaskModel model) {
        super("BabySteps", model);
    }
    
    private class EditButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            (new EditFrame(model)).run();
        }
    }
    
    private class StepButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            (new TaskSelectionFrame(model)).run();
        }
    }

    /**
     * Add two buttons to the frame, resize, center, and display.
     */
    @Override
    public void run() {
        JButton stepButton = new JButton("Take a Step");
        stepButton.addActionListener(new StepButtonListener());
        JButton editButton = new JButton("Edit Tasks");
        editButton.addActionListener(new EditButtonListener());
        singleColumnLayout(stepButton, editButton);
        
        setSize(WIDTH, HEIGHT);
        centerFrame();
        setVisible(true);
    }
}
