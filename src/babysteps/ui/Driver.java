package babysteps.ui;

import javax.swing.SwingUtilities;

import babysteps.model.TaskModel;

/**
 * Main function. Creates a model and loads the view.
 * 
 * @author Elliot Penson
 */
public class Driver {

    public static void main(String[] args) {
        TaskModel model = new TaskModel();
        SwingUtilities.invokeLater(new LaunchFrame(model));
    }
}