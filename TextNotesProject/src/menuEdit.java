public class menuEdit {

    Note_And_Image gui;

    public menuEdit(Note_And_Image gui) {

        this.gui = gui;


    }

    public void Undo() {
        gui.uManager.undo();
    }

    public void Redo() {
        gui.uManager.redo();
    }
}
