import java.awt.*;
import java.io.*;

import static java.awt.FileDialog.*;

public class MenuFile extends javax.swing.JFrame {

    Note_And_Image gui;
   String FileName;
    String FileAddress;

    public MenuFile(Note_And_Image gui) {
        this.gui = gui;
    }

    public void New() {
        gui.textArea.setText(" ");
        gui.frame.setTitle("New Window");

        FileName = null;
        FileAddress = null;

    }

    public void Open() {
        FileDialog fd = new FileDialog(gui.frame, "Open", LOAD);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            FileName = fd.getFile();
            FileAddress = fd.getDirectory();
            gui.frame.setTitle(FileName);
        }
        try {

            BufferedReader bf = new BufferedReader(new FileReader(FileAddress + FileName));


           gui.textArea.setText(" ");
            String line = null;

            while ((line = bf.readLine()) != null) {

               gui.textArea.append(line + "\n");

            }


        } catch (Exception e) {
            System.out.println("Not Opened File");
        }


    }

    public void Save() {
       if (FileName == null) {
            SaveAs();
       } else {

            try {
                FileWriter fw = new FileWriter(FileAddress + FileName);
                fw.write(gui.textArea.getText());
               gui.frame.setTitle(FileName);
                fw.close();


            } catch (Exception e) {
                System.out.println("There Is SomeThing Wrong");
            }

        }


    }

    public void SaveAs() {
        FileDialog fd = new FileDialog(gui.frame, "Save As", SAVE);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            FileName = fd.getFile();
            FileAddress = fd.getDirectory();
            gui.frame.setTitle(FileName);
        }
        try {

            FileWriter fw = new FileWriter(FileAddress + FileName);
            fw.write(gui.textArea.getText());
            fw.close();

        } catch (Exception e) {
            System.out.println("There Is SomeThing Wrong");

        }


    }

    public void logout() {
      //  System.exit(0);
       gui.frame.setVisible(false);
        new SecureNote().setVisible(true);
    }

    public void exit() {
        System.exit(0);
    }



}
