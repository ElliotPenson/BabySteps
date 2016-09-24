package babysteps.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import babysteps.model.TaskModel;

public class FrameMixin extends JFrame {

    private static final long serialVersionUID = 1L;

    protected TaskModel model;
    
    /**
     * Constructor. Adds the ability
     * 
     * @param model
     */
    public FrameMixin(String title, TaskModel model) {
        super(title);
        this.model = model;
        this.addWindowListener(new SaveListener());
    }
    
    /**
     * Determine the center of the user's screen and place the frame in that location.
     */
    protected void centerFrame() {
        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
        int topLeftX = (screenDimensions.width / 2) - (getSize().width / 2);
        int topLeftY = (screenDimensions.height / 2) - (getSize().height / 2);
        setLocation(topLeftX, topLeftY);
    }
    
    public class SaveListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    model.serialize(); 
                    System.exit(0);
                }
            }).start();
        }
    }
}
