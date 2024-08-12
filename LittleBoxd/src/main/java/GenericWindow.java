import javax.swing.*;

public abstract class GenericWindow {
    protected JFrame frame;

    public GenericWindow(String title, int width, int height) {
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle(title);
        frame.setLocationRelativeTo(null);
    }

    public void show() {
        frame.setVisible(true);
    }
}
