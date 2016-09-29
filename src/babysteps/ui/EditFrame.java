package babysteps.ui;

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
 * @author Elliot Penson
 */
public class EditFrame extends FrameMixin implements Observer, Runnable {

    private static final long serialVersionUID = 1L;
        
    private TaskList taskList;
    
    private JPanel optionList;
    
    /**
     * Constructor. Connect this frame to the model and instantiate components.
     * 
     * @param model
     */
    public EditFrame(TaskModel model) {
        super(model.getCurrentTask().getTitle(), model);
        model.addObserver(this);
        taskList = new TaskList(model);
        setupOptionList();
    }
    
    /**
     * Add six buttons to the frame. These buttons can be used to manipulate the current list of
     * Tasks held by the TaskList.
     */
    public void setupOptionList() {
        optionList = new JPanel();
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
        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.completeTask(taskList.getSelectedIndex());
            }
        });
        optionList.add(finishButton);
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (model.getCurrentTask().getParent().isPresent()) {
                    model.moveUp();
                } else {
                    setVisible(false);
                    (new LaunchFrame(model)).run();
                }
            }
        });
        optionList.add(backButton);
        
        taskList.toggleButtonsOnSelection(retitleButton, deleteButton, enterButton, finishButton);
    }

    /**
     * Refresh the title and resizes the frame. Called by the model.
     */
    @Override
    public void update(Observable o, Object arg) {
        setTitle(model.getCurrentTask().getTitle());
        pack();
    }
    
    /**
     * Add the TaskList and option JButtons in a GroupLayout, resize, and center.
     */
    @Override
    public void run() {
        singleColumnLayout(taskList, optionList);
        pack();
        centerFrame();
        setVisible(true);
    }
}
