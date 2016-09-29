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
        createButton.addActionListener(new CreateButtonListener());
        optionList.add(createButton);
        JButton retitleButton = new JButton("Retitle");
        retitleButton.addActionListener(new RetitleButtonListener());
        optionList.add(retitleButton);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new DeleteButtonListener());
        optionList.add(deleteButton);
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterButtonListener());
        optionList.add(enterButton);
        JButton finishButton = new JButton("Finish");
        finishButton.addActionListener(new FinishButtonListener());
        optionList.add(finishButton);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonListener());
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
    
    /**
     * A listener that makes a new task when notified.
     */
    private class CreateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String newTitle = JOptionPane.showInputDialog(null, "Enter new title:", "Title",
                    JOptionPane.PLAIN_MESSAGE);
            model.createTask(newTitle);
        }
    }
    
    /**
     * A listener that retitles the selected task when notified.
     */
    private class RetitleButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String newTitle = JOptionPane.showInputDialog(null, "Enter new title:", "Title",
                    JOptionPane.PLAIN_MESSAGE);
            model.retitleTask(taskList.getSelectedIndex(), newTitle);
        }
    }
    
    /**
     * A listener that delete the selected task when notified.
     */
    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.removeTask(taskList.getSelectedIndex());
        }
    }
    
    /**
     * A listener that opens the selected task when notified.
     */
    private class EnterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.enterTask(taskList.getSelectedIndex());
        }
    }
    
    /**
     * A listener that completes the selected task when notified.
     */
    private class FinishButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.completeTask(taskList.getSelectedIndex());
        }
    }
    
    /**
     * A listener that searches for a parent task. If found, move up the task tree. If no parent
     * exists, exit the frame and move to a new LaunchFrame.
     */
    private class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (model.getCurrentTask().getParent().isPresent()) {
                model.moveUp();
            } else {
                setVisible(false);
                (new LaunchFrame(model)).run();
            }
        }
    }
}
