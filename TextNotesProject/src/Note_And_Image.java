import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Note_And_Image extends javax.swing.JFrame implements ActionListener {
    JFrame frame;

    JTextArea textArea;
    JScrollPane scrollPane;


    JMenuBar menuBar;
    JMenu menuFile, menuEdit;


    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    JMenuItem iUndo, iRedo;

    JMenu menuFormat, font, FontSize;
    JMenuItem iFontArial, iFontHelvetica, iFontGaramond, iFontSize18, iFontSize20, iFontSize22, iFontSize24, iFontSize28;


    MenuFile mfile = new MenuFile(this);
    menuEdit mEdit = new menuEdit(this);
    UndoManager uManager = new UndoManager();

    KeyShortCuts kShortCuts = new KeyShortCuts(this);

    MenuFormat mFormat = new MenuFormat(this);
    FileTreeMapGUI fileTreeMapGUI;

    public Note_And_Image(String directory) {

        CreateFrame();
        CreateTextArea();
        CreateMenuBar();
        CreateFileMenu();
        MenuFormat();
        CreateImageMenu();


        frame.setVisible(true);

        fileTreeMapGUI = new FileTreeMapGUI("./notes/" + SecureNote.username);
        fileTreeMapGUI.setVisible(true);

    }


    public void CreateFrame() {

        frame = new JFrame("Zombistan NotePad");
        frame.setSize(500, 500);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("zombie-logo_10250-2531.png"));
        frame.setIconImage(icon.getImage());

    }

    public void CreateTextArea() {

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setBackground(Color.lightGray);

        textArea.addKeyListener(kShortCuts);

        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                uManager.addEdit(e.getEdit());
            }
        });

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.add(scrollPane);
    }

    public void setTextContent(String content) {
        textArea.setText(content);
    }

    public void CreateImageMenu() {
        JMenu insertMenu = new JMenu("Insert");
        menuBar.add(insertMenu);

        JMenuItem addImageItem = new JMenuItem("Add Path Image");
        addImageItem.addActionListener(new ImageActionListener());
        insertMenu.add(addImageItem);

        frame.setJMenuBar(menuBar);
    }

    private class ImageActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(null);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String imagePath = file.getAbsolutePath();

                // Append the image path to the text area
                textArea.append(imagePath + "\n");
            }
        }
    }


    public void CreateMenuBar() {
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);


    }


    public void CreateFileMenu() {

        iNew = new JMenuItem("New Window");
        menuFile.add(iNew);
        iNew.addActionListener(this);
        iNew.setActionCommand("New Window");


        iOpen = new JMenuItem("Open");
        menuFile.add(iOpen);
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");


        iSave = new JMenuItem("Save ");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);

        iSaveAs = new JMenuItem("Save As");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("Save As");
        menuFile.add(iSaveAs);

        iExit = new JMenuItem("logout");
        iExit.addActionListener(this);
        iExit.setActionCommand("logout");
        menuFile.add(iExit);

        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);

        iOpen = new JMenuItem("exit");
        menuFile.add(iOpen);
        iOpen.addActionListener(this);
        iOpen.setActionCommand("exit");

    }


    public void MenuFormat() {
        font = new JMenu("Font");
        menuFormat.add(font);


        FontSize = new JMenu("Font Size");
        menuFormat.add(FontSize);

        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        font.add(iFontArial);

        iFontHelvetica = new JMenuItem("Helvetica");
        iFontHelvetica.addActionListener(this);
        iFontHelvetica.setActionCommand("Helvetica");
        font.add(iFontHelvetica);

        iFontGaramond = new JMenuItem("Garamond");
        iFontGaramond.addActionListener(this);
        iFontGaramond.setActionCommand("Garamond");
        font.add(iFontGaramond);

        iFontSize18 = new JMenuItem("18");
        iFontSize18.addActionListener(this);
        iFontSize18.setActionCommand("size18");
        FontSize.add(iFontSize18);


        iFontSize20 = new JMenuItem("20");
        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("size20");
        FontSize.add(iFontSize20);


        iFontSize22 = new JMenuItem("22");
        iFontSize22.addActionListener(this);
        iFontSize22.setActionCommand("size22");
        FontSize.add(iFontSize22);


        iFontSize24 = new JMenuItem("24");
        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("size24");
        FontSize.add(iFontSize24);


        iFontSize28 = new JMenuItem("28");
        iFontSize28.addActionListener(this);
        iFontSize28.setActionCommand("size28");
        FontSize.add(iFontSize28);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String Command = e.getActionCommand();

        switch (Command) {
            case "New Window":
                mfile.New();
                break;
            case "Open":
                mfile.Open();
                break;
            case "Save As":
                mfile.SaveAs();
                break;
            case "Save":
                mfile.Save();
                break;
            case "logout":
                fileTreeMapGUI.setVisible(false);
                // this.setVisible(false);
                mfile.logout();
                break;
            case "exit":
                mfile.exit();
                break;
            case "Undo":
                mEdit.Undo();
                break;
            case "Redo":
                mEdit.Redo();
                break;
            case "size18":
                mFormat.CreateFonts(18);
                break;
            case "size20":
                mFormat.CreateFonts(20);
                break;
            case "size22":
                mFormat.CreateFonts(22);
                break;
            case "size24":
                mFormat.CreateFonts(24);
                break;
            case "size28":
                mFormat.CreateFonts(28);
                break;
            case "Arial":
                mFormat.setFonts("Arial");
                break;
            case "Helvetica":
                mFormat.setFonts("Helvetica");
                break;
            case "Garamond":
                mFormat.setFonts("Garamond");
                break;


        }

    }

}
