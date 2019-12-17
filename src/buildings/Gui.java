package buildings;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFactory;
import buildings.dwelling.DwellingFloor;
import buildings.office.OfficeFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class Gui extends JFrame {

    private JSplitPane splitPane;
    private JPanel infoPane;
    private JPanel viewPane = new JPanel();
    private JScrollPane viewScrollPane = new JScrollPane(viewPane);

    private Label infoLabel = new Label();

    private JTextPane buildingInfo = new JTextPane();
    private JTextPane floorInfo = new JTextPane();
    private JTextPane spaceInfo = new JTextPane();
    private int spaceCounter = 0;

    private Building currentBuilding;

    public Gui(){
        super("Building App");
        this.setBounds(0,0,1000,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();

        infoPane = new JPanel();
        infoPane.setLayout(new FlowLayout());
        viewPane.setLayout(new BoxLayout(viewPane, BoxLayout.Y_AXIS));
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                infoPane, viewScrollPane);


        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem dwellingMenu = new JMenuItem("Open dwelling…");
        dwellingMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Open dwelling");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    Buildings.setBuildingFactory(new DwellingFactory());
                    File file = fileopen.getSelectedFile();
                    spaceCounter = 0;
                    try {
                        currentBuilding = Buildings.inputBuilding(new FileInputStream(file));
                        buildingInfo.setText(currentBuilding.toView());
                        for(int i = 0; i < currentBuilding.getFloorCount(); i++){
                            viewPane.add(createFloorPanel(currentBuilding.getFloor(i), i));
                        }
                    } catch (IOException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });

        JMenu lookMenu = new JMenu("Look and Feel");
        for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
            JMenuItem menuItem = new JMenuItem(info.getName());
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                        SwingUtilities.updateComponentTreeUI(container);
                        //Gui.pack();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (InstantiationException ex) {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    } catch (UnsupportedLookAndFeelException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            lookMenu.add(menuItem);
        }


        fileMenu.add(dwellingMenu);
        JMenuItem officeMenu = new JMenuItem("Open office building…");
        officeMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Open dwelling");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    Buildings.setBuildingFactory(new OfficeFactory());
                    File file = fileopen.getSelectedFile();
                    spaceCounter = 0;
                    try {
                        currentBuilding = Buildings.inputBuilding(new FileInputStream(file));
                        buildingInfo.setText(currentBuilding.toView());
                        for(int i = 0; i < currentBuilding.getFloorCount(); i++){
                            viewPane.add(createFloorPanel(currentBuilding.getFloor(i), i));
                        }
                    } catch (IOException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });
        fileMenu.add(officeMenu);

        menuBar.add(fileMenu);
        menuBar.add(lookMenu);
        this.setLayout(new BoxLayout (container, BoxLayout.Y_AXIS));


        infoLabel.setText("Information");
        //JPanel infoPanel = new JPanel();
        infoPane.add(infoLabel);
        infoPane.add(buildingInfo);
        infoPane.add(floorInfo);
        infoPane.add(spaceInfo);

        JPanel floorPanel = new JPanel();
        viewPane.add(floorPanel);

        container.add(menuBar);
        container.add(splitPane);

    }

    private JPanel createFloorPanel(Floor floor, int j) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        for(int i = 0; i < floor.getSpaceCount(); i++){
            int numberSpace = spaceCounter;
            JButton button = new JButton("Space " + spaceCounter++);
            Space space = floor.getSpace(i);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    spaceInfo.setText("Space Number: " + numberSpace + "\nRoomCount: " + space.getRoomCount() + "\nArea: " + space.getArea());
                }
            });
            panel.add(button);
        }
        panel.setMaximumSize(new Dimension(1500, 500));
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                panel.setBackground(Color.BLACK);
                floorInfo.setText(new String("Floor Number: " + j + "\nSpaceCount: " + floor.getSpaceCount() +
                        "\nTotal Area: " + floor.getArea()));
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(Color.WHITE);
            }
        });
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        return panel;
    }



    public static void main(String[] args) throws IOException {
        Dwelling dwelling = new Dwelling(new DwellingFloor(1), new DwellingFloor(1));
        Buildings.writeBuilding(dwelling, new FileWriter("build.txt"));
        Gui gui = new Gui();
        gui.setVisible(true);
    }
}

