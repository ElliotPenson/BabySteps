package babysteps.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.JFrame;

import babysteps.model.TaskModel;

/**
 * Add centering and model save functionality to JFrames.
 * 
 * @author Elliot Penson
 */
public class FrameMixin extends JFrame {

    private static final long serialVersionUID = 1L;

    protected TaskModel model;
    
    /**
     * Constructor.
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
    
    /**
     * Instantiate a new GroupLayout and add it to the frame. The given components are then
     * aligned vertically in a single column.
     * 
     * @param components A variable amount of Component objects
     */
    protected void singleColumnLayout (Component...components) {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        Group horizontal = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        Group vertical = layout.createSequentialGroup();
        for(Component component : components) {
            for (Group group : new Group[] {horizontal, vertical}) {
                group.addComponent(component, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
            }
        }
        
        layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(horizontal));
        layout.setVerticalGroup(vertical);
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
