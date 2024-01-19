import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyShortCuts implements KeyListener {

    Note_And_Image gui;

    public KeyShortCuts(Note_And_Image gui) {
        this.gui = gui;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {

           gui.mfile.Save();
        } else if (e.isShiftDown() && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
            gui.mfile.SaveAs();

        } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O) {
            gui.mfile.Open();
        } else if (e.isShiftDown() && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_N) {
            gui.mfile.New();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
