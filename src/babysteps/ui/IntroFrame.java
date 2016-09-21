package babysteps.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import babysteps.model.TaskModel;

public class IntroFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private static final int WIDTH = 205;
    
    private static final int HEIGHT = 93;
    
    private TaskModel model;

    /**
     * Constructor. Set up (invisibly) a window with "next" and "edit" buttons.
     * 
     * @param model
     */
    public IntroFrame(TaskModel model) {
        super("BabySteps");
        this.model = model;
        
        setSize(WIDTH, HEIGHT);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton nextButton = new JButton("Next Task");
        nextButton.addActionListener(new NextButtonListener());
        add(nextButton);
        
        JButton editButton = new JButton("Edit Tasks");
        editButton.addActionListener(new EditButtonListener());
        add(editButton);

        centerFrame();
    }
    
    /**
     * Determine the center of the user's screen and place the frame in that location.
     */
    private void centerFrame() {
        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
        int topLeftX = (screenDimensions.width / 2) - (getSize().width / 2);
        int topLeftY = (screenDimensions.height / 2) - (getSize().height / 2);
        setLocation(topLeftX, topLeftY);
    }
    
    public class EditButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            // TODO
        }
    }
    
    public class NextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            // TODO
        }
    }
}
