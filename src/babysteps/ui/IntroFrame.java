package babysteps.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import babysteps.model.TaskModel;

/**
 * The first window the user sees.
 * 
 * @author ejnp
 */
public class IntroFrame extends FrameMixin implements Runnable {

    private static final long serialVersionUID = 1L;
    
    private static final int WIDTH = 205;
    
    private static final int HEIGHT = 93;
    
    /**
     * Constructor.
     * 
     * @param model
     */
    public IntroFrame(TaskModel model) {
        super("BabySteps", model);
    }
    
    public class EditButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            (new EditFrame(model)).run();
        }
    }
    
    public class NextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            // TODO
        }
    }

    /**
     * Add two buttons to the frame, resize, center, and display.
     */
    @Override
    public void run() {
        JButton nextButton = new JButton("Take a Step");
        nextButton.addActionListener(new NextButtonListener());
        JButton editButton = new JButton("Edit Tasks");
        editButton.addActionListener(new EditButtonListener());
        singleColumnLayout(nextButton, editButton);
        
        setSize(WIDTH, HEIGHT);
        centerFrame();
        setVisible(true);
    }
}
