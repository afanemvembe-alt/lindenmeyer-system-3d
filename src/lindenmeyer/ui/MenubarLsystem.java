package lindenmeyer.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenubarLsystem extends JMenuBar implements ActionListener
{
    // final JMenuBar menuBar = new JMenuBar();

    final JMenu fileMenu = new JMenu("Fichier");
    final JMenu editMenu = new JMenu("Config");
    final JMenu aboutMenu = new JMenu("À propos");

    JMenuItem newMenuItem = new JMenuItem("New");
    JMenuItem openMenuItem = new JMenuItem("Ouvrir");
    JMenuItem saveMenuItem = new JMenuItem("Sauvegarder");
    JMenuItem exitMenuItem = new JMenuItem("Sortir");
    JMenuItem configMenuItem = new JMenuItem("Configurations");
    JMenuItem aboutMenuItem = new JMenuItem("Informations");

    public MenubarLsystem()
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
            JOptionPane.showMessageDialog(this, "Ouvrir les configurations");
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
        new MenubarLsystem();
    }
}
