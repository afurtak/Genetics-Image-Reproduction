import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int retVal = fileChooser.showOpenDialog(null);
        if (retVal == JFileChooser.APPROVE_OPTION) {
            new MainWindow(fileChooser.getSelectedFile());
        }
    }
}
