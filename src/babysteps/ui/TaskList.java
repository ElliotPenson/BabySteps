package babysteps.ui;

import java.awt.Component;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Stream;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import babysteps.model.TaskModel;

/**
 * A class to standardize the use of JList for holding tasks.
 * 
 * @author Elliot Penson
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
        setCellRenderer(new DisabledItemListCellRenderer());
        update(null, null);
    }

    /**
     * Update the list of task titles. Called when a change has been made to the model. 
     */
    @Override
    public void update(Observable o, Object arg) {
        setListData(model.subtaskNames().toArray(new String[0]));
    }
    
    /**
     * Disables all given buttons if no task is currently selected. Once the user clicks on a
     * task in the list, all given buttons are enabled.
     * 
     * @param buttons
     */
    public void toggleButtonsOnSelection(JButton...buttons) {
        Stream.of(buttons).forEach(c -> c.setEnabled(false));
        addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (getSelectedIndex() == -1) {
                    Stream.of(buttons).forEach(c -> c.setEnabled(false));
                } else {
                    Stream.of(buttons).forEach(c -> c.setEnabled(true));
                }
            }
        });
    }

    /**
     * This renderer grays out completed tasks. Uncompleted tasks assume a default appearance.
     */
    private class DisabledItemListCellRenderer extends DefaultListCellRenderer {

        private static final long serialVersionUID = 1L;

        /**
         * Make a list item disabled is it's a completed task. This method serves as a decorator
         * on DefaultListCellRenderer's getListCellRendererComponent method. 
         */
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            Component toReturn = super.getListCellRendererComponent(list, value, index,
                    isSelected, cellHasFocus);
            if (model.taskCompleted(index)) {
                toReturn.setEnabled(false);
            }
            return toReturn;
        }
    }
}