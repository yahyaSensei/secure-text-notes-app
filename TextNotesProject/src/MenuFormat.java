import java.awt.*;

public class MenuFormat {
    Note_And_Image gui;
    Font Arial, Garamond, Helvetica;

    String selectedfont = "Arial";

    public MenuFormat(Note_And_Image gui) {
        this.gui = gui;
    }

    public void CreateFonts(int FontSize) {

        Arial = new Font("Arial", Font.PLAIN, FontSize);
        Garamond = new Font("Garamond", Font.PLAIN, FontSize);
        Helvetica = new Font("Halvetica", Font.PLAIN, FontSize);

        setFonts(selectedfont);

    }

    public void setFonts(String font) {
        selectedfont = font;

        switch (selectedfont) {
            case "Arial":
                gui.textArea.setFont(Arial);
                break;
            case "Helvetica":
                gui.textArea.setFont(Helvetica);
                break;
            case "Garamond":
                gui.textArea.setFont(Garamond);
        }
    }


}
