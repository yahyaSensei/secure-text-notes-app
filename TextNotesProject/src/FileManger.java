import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;


class FileTreeMapGUI extends JFrame {

    private JTree fileTree;
    private JButton helloButton;
    private JTextField noteTextField; // Added text field
    //static int notenumber;
    String newNote;
    public FileTreeMapGUI(String rootDirectory) {
        setTitle("File Tree Map");
        setSize(400, 300);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new File(rootDirectory));
        fileTree = new JTree(root);

      //  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("zombie-logo_10250-2531.png"));
        setIconImage(icon.getImage());


        JScrollPane scrollPane = new JScrollPane(fileTree);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        generateFileTree(new File(rootDirectory), root);
        fileTree.expandRow(0);

        fileTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    int selectedRow = fileTree.getRowForLocation(e.getX(), e.getY());
                    if (selectedRow != -1) {
                        TreePath selectedPath = fileTree.getPathForLocation(e.getX(), e.getY());
                        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();
                        File selectedFile = (File) selectedNode.getUserObject();

                        if (selectedFile != null && selectedFile.isFile() && selectedFile.getName().toLowerCase().endsWith(".txt")) {

                            openTxtFile(selectedFile);
                        }
                    }
                }
            }
        });




        // Initialize the text field
        noteTextField = new JTextField(10);

        // Initialize the button and add ActionListener
        helloButton = new JButton("Add New Note");
        helloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the text from the text field
                newNote = noteTextField.getText();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("./notes/"+ SecureNote.username+"/"+newNote+".txt"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                repaint();
                System.out.flush();

            }
        });

        // Add the text field and button to the layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("New Note:"));
        inputPanel.add(noteTextField);
        inputPanel.add(helloButton);

        // Add the input panel to the layout
        getContentPane().add(inputPanel, BorderLayout.SOUTH);
    }


    private void generateFileTree(File file, DefaultMutableTreeNode parentNode) {
        if (file.isDirectory()) {
            DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(file);
            parentNode.add(dirNode);
            File[] files = file.listFiles();
            if (files != null) {
                for (File child : files) {
                    generateFileTree(child, dirNode);
                }
            }
        } else {
            parentNode.add(new DefaultMutableTreeNode(file));
        }
    }

    private void openTxtFile(File selectedFile) {
        SwingUtilities.invokeLater(() -> {
            try {
                String content = Files.readString(selectedFile.toPath(), StandardCharsets.UTF_8);

                // Assuming you have a GUI class with a method to display content
                // Replace 'GUI' with the actual name of your class
                Note_And_Image notepadFrame = new Note_And_Image("C:");
                notepadFrame.setTextContent(content);
                notepadFrame.frame.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
