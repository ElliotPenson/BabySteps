package babysteps.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout;
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
    
    private JPanel optionList;
    
    /**
     * Constructor. Add the TaskList and option JButtons, resize, and center.
     * 
     * @param model
     */
    public EditFrame(TaskModel model) {
        super(model.getCurrentTask().getTitle(), model);
        model.addObserver(this);
        
        taskList = new TaskList(model);
        setupOptionList();
        establishLayout();
        
        pack();
        centerFrame();
    }
    
    /**
     * Instantiate a GroupLayout. Organize the frame into a single resizable column.
     */
    public void establishLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(taskList, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(optionList))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(taskList, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(optionList)
        );
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
    }

    /**
     * Refresh the title and resizes the frame. Called by the model.
     */
    @Override
    public void update(Observable o, Object arg) {
        setTitle(model.getCurrentTask().getTitle());
        pack();
    }
}
