package babysteps.ui;

import javax.swing.SwingUtilities;

import babysteps.model.TaskModel;

public class Driver {

    public static void main(String[] args) {
        TaskModel model = new TaskModel();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final IntroFrame view = new IntroFrame(model);
                view.setVisible(true);
            }
        });
    }
}