package babysteps.ui;

import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import babysteps.model.TaskModel;

/**
 * A class to standardize the use of JList for holding tasks.
 * 
 * @author ejnp
 */
public class TaskList extends JList<String> implements Observer {
    
    private static final long serialVersionUID = 1L;
    
    private TaskModel model;
    
    /**
     * Constructor. Make a fresh TaskList with list items from the model.
     * 
     * @param model
     */
    public TaskList(TaskModel model) {
        this.model = model;
        model.addObserver(this);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setFont(new Font("LucidaGrande", Font.PLAIN, 13));
        update(null, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        setListData(model.subtaskNames().toArray(new String[0]));
    }
}