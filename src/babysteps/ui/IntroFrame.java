package babysteps.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import babysteps.model.TaskModel;

/**
 * The first window the user sees.
 * 
 * @author ejnp
 */
public class IntroFrame extends FrameMixin {

    private static final long serialVersionUID = 1L;
    
    private static final int WIDTH = 205;
    
    private static final int HEIGHT = 93;
    
    /**
     * Constructor. Set up (invisibly) a window with "next" and "edit" buttons.
     * 
     * @param model
     */
    public IntroFrame(TaskModel model) {
        super("BabySteps", model);

        setSize(WIDTH, HEIGHT);
        setLayout(new FlowLayout());
        
        JButton nextButton = new JButton("Next Task");
        nextButton.addActionListener(new NextButtonListener());
        add(nextButton);
        
        JButton editButton = new JButton("Edit Tasks");
        editButton.addActionListener(new EditButtonListener());
        add(editButton);
        
        centerFrame();
    }
    
    public class EditButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            EditFrame edit = new EditFrame(model);
            edit.setVisible(true);
        }
    }
    
    public class NextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            // TODO
        }
    }
}
