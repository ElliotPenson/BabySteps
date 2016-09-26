package babysteps.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import babysteps.model.TaskModel;

/**
 * A window for manipulation of the task tree.
 * 
 * @author ejnp
 */
public class EditFrame extends FrameMixin implements Observer {

    private static final long serialVersionUID = 1L;
        
    private TaskList taskList;
    
    /**
     * Constructor. Add the TaskList and option JButtons, resize, and center.
     * 
     * @param model
     */
    public EditFrame(TaskModel model) {
        super(model.getCurrentTask().getTitle(), model);
        model.addObserver(this);
        
        setLayout(new FlowLayout());
        
        taskList = new TaskList(model);
        add(taskList);
        
        setupOptionList();
        
        updateDimensions();
        centerFrame();
    }
    
    /**
     * Increase or decrease the size of the frame to appropriately fit the components.
     */
    public void updateDimensions() {
        int verticalMargin = 20;
        int buttonHeight = 95;
        taskList.maximizeSize();
        Dimension taskListSize = taskList.getPreferredSize();
        setSize(taskListSize.width + verticalMargin,
                taskListSize.height + buttonHeight);
    }
    
    /**
     * Add six buttons to the frame. These buttons can be used to manipulate the current list of
     * Tasks held by the TaskList.
     */
    public void setupOptionList() {
        JPanel optionList = new JPanel();
        optionList.setLayout(new GridLayout(2, 3));
        
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newTitle = JOptionPane.showInputDialog("Enter new title:");
                model.createTask(newTitle);
            }
        });
        optionList.add(createButton);
        
        JButton retitleButton = new JButton("Retitle");
        retitleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newTitle = JOptionPane.showInputDialog("Enter new title:");
                model.retitleTask(taskList.getSelectedIndex(), newTitle);
            }
        });
        optionList.add(retitleButton);
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.removeTask(taskList.getSelectedIndex());
            }
        });
        optionList.add(deleteButton);
        
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.enterTask(taskList.getSelectedIndex());
            }
        });
        optionList.add(enterButton);
        
        JButton finishButton = new JButton("Finish");
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException();
            }
        });
        optionList.add(finishButton);
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.moveUp();
                
            }
        });
        optionList.add(backButton);
        
        add(optionList);
    }

    /**
     * Refresh the title and resizes the frame. Called by the model.
     */
    @Override
    public void update(Observable o, Object arg) {
        setTitle(model.getCurrentTask().getTitle());
        updateDimensions();
    }
}
