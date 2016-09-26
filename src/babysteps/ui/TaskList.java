package babysteps.ui;

import java.awt.Dimension;
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
     * Smallest possible width for the component.
     */
    private static final int MINIMUM_WIDTH = 250;
    
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
        maximizeSize();
    }
    
    /**
     * Extend the height of the JList to fit all tasks. Change the width to fit the largest title.
     */
    public void maximizeSize() {
        setVisibleRowCount(model.numberOfSubtasks());
        Dimension fittedListSize = getPreferredScrollableViewportSize();
        int listWidth = Math.max(fittedListSize.width, MINIMUM_WIDTH);
        setPreferredSize(new Dimension(listWidth, fittedListSize.height));
    }

    @Override
    public void update(Observable o, Object arg) {
        setListData(model.subtaskNames().toArray(new String[0]));
    }
}