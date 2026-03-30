package lindenmeyer.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenubarLsystem extends JMenuBar implements ActionListener
{
    // show the config info somewhere
    
    // final JMenuBar menuBar = new JMenuBar();

    // idea: generator menu with premade models instead of random
    // idea: explain about lsystem structures in about menu or sth

    // menus
    final JMenu fileMenu = new JMenu("Fichier");
    final JMenu editMenu = new JMenu("Config");
    final JMenu viewMenu = new JMenu("Affichage");
    final JMenu aboutMenu = new JMenu("À propos");

    // file menu items
    JMenuItem newMenuItem = new JMenuItem("New");
    JMenuItem openMenuItem = new JMenuItem("Ouvrir");
    JMenuItem saveMenuItem = new JMenuItem("Sauvegarder");
    JMenuItem exitMenuItem = new JMenuItem("Sortir");

    // config menu items
    JMenuItem configMenuItem = new JMenuItem("Configurations");

    // view menu items
    JMenuItem coulourMenuItem = new JMenuItem("Couleur"); // idea: colour wheel thing
    JMenuItem zoomInMenuItem = new JMenuItem("Zoom +");
    JMenuItem zoomOutMenuItem = new JMenuItem("Zoom -");

    // about menu items
    JMenuItem aboutMenuItem = new JMenuItem("Informations");

    // note: not sure about this
    private ParamDialog paramDialog;
    private InterfaceLsystem interfaceLsystem;

    public MenubarLsystem(InterfaceLsystem interfaceLsystem)
    {
        this.interfaceLsystem = interfaceLsystem;
        // file menu items
        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);

        // config menu items
        this.paramDialog = new ParamDialog(interfaceLsystem);
        configMenuItem.addActionListener(this);

        // affichage menu items
        // for this you need access to the VueLSystem and JFrame
        coulourMenuItem.addActionListener(this);
        zoomInMenuItem.addActionListener(this); // add keyboard shortcut
        zoomOutMenuItem.addActionListener(this);


        // about menu items
        aboutMenuItem.addActionListener(this);

        // add menu items to menu
        // file menu
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // config menu
        editMenu.add(configMenuItem);

        // affichage menu
        viewMenu.add(coulourMenuItem);
        viewMenu.add(zoomInMenuItem);
        viewMenu.add(zoomOutMenuItem);

        // about menu
        aboutMenu.add(aboutMenuItem);

        this.add(fileMenu);
        this.add(editMenu);
        this.add(viewMenu);
        this.add(aboutMenu);
    }
/**
    private void buildMenuBar()
    {
        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);
        configMenuItem.addActionListener(this);
        aboutMenuItem.addActionListener(this);

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        editMenu.add(configMenuItem);

        aboutMenu.add(aboutMenuItem);

        this.add(fileMenu);
        this.add(editMenu);
        this.add(aboutMenu);

        // setJMenuBar(menuBar);
    }

    public JMenuBar createMenuBar()
    {
        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);
        configMenuItem.addActionListener(this);
        aboutMenuItem.addActionListener(this);

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        editMenu.add(configMenuItem);

        aboutMenu.add(aboutMenuItem);

        this.add(fileMenu);
        this.add(editMenu);
        this.add(aboutMenu);

        return menuBar;
    }
        */

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        if (source == newMenuItem)
        {
            JOptionPane.showMessageDialog(this, "Nouveau fichier");
        }
        else if (source == openMenuItem)
        {
            JOptionPane.showMessageDialog(this, "Ouvrir un fichier");
        }
        else if (source == saveMenuItem)
        {
            JOptionPane.showMessageDialog(this, "Sauvegarde du fichier");
        }
        else if (source == exitMenuItem)
        {
            // dispose();
        }
        else if (source == configMenuItem)
        {
            // JOptionPane.showMessageDialog(this, "Ouvrir les configurations");
            this.paramDialog.setVisible(true);
            this.interfaceLsystem.setLongeur(this.paramDialog.getLongueur());
            this.interfaceLsystem.setAngleRotation(this.paramDialog.getAngle());
        }
        else if (source == zoomInMenuItem)
        {
            this.interfaceLsystem.display.zoomIn();
        }
        else if (source == zoomOutMenuItem)
        {
            // JOptionPane.showMessageDialog(this, "Ouvrir les configurations");
            this.interfaceLsystem.display.zoomOut();
        }
        else if (source == aboutMenuItem)
        {
            JOptionPane.showMessageDialog(
                this,
                "Application L-System\nVersion 1.0",
                "À propos",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    public static void main(String[] args)
    {
        new MenubarLsystem(new InterfaceLsystem());
    }
}
