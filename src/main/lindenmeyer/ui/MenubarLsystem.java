package lindenmeyer.ui;

import java.awt.*;
import java.awt.event.*;
import java.nio.file.Path;

import java.util.ArrayList;


import javax.swing.*;

import lindenmeyer.lsystem.LSystem;

// import netscape.javascript.JSObject;


public class MenubarLsystem extends JMenuBar implements ActionListener
{
    // show the config info somewhere
    
    // final JMenuBar menuBar = new JMenuBar();

    // idea: generator menu with premade models instead of random
    // idea: explain about lsystem structures in about menu or sth
    // idea: what if the preset configs are read at launch

    // JSONObject root = new JSONParser().parse(new FileReader("JSONExample.json"));
    // private ArrayList<Preset> presets = new ArrayList<>();

    // menus
    final JMenu fileMenu = new JMenu("Fichier");
    // file menu items
    JMenuItem newMenuItem = new JMenuItem("New");
    JMenuItem openMenuItem = new JMenuItem("Ouvrir");
    JMenuItem saveMenuItem = new JMenuItem("Sauvegarder");
    JMenuItem exitMenuItem = new JMenuItem("Sortir");

    final JMenu configMenu = new JMenu("Config");
    // config menu items
    JMenuItem configMenuItem = new JMenuItem("Configurations");

    final JMenu viewMenu = new JMenu("Affichage");
    // view menu items
    JMenuItem coulourMenuItem = new JMenuItem("Couleur"); // idea: colour wheel thing
    JMenuItem zoomInMenuItem = new JMenuItem("Zoom +");
    JMenuItem zoomOutMenuItem = new JMenuItem("Zoom -");

    final JMenu presetMenu = new JMenu("Presets");
    JMenuItem addPresetMenuItem = new JMenuItem("Ajouter preset");

    final JMenu aboutMenu = new JMenu("À propos");
    // about menu items
    JMenuItem aboutMenuItem = new JMenuItem("Informations");

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
        configMenu.add(configMenuItem);

        // preset menu
        for (Preset preset : this.interfaceLsystem.getPresets())
        {
            presetMenu.add(crateMenuItem(preset));
        }
        presetMenu.addSeparator();
        presetMenu.add(addPresetMenuItem);


        // affichage menu
        viewMenu.add(coulourMenuItem);
        viewMenu.add(zoomInMenuItem);
        viewMenu.add(zoomOutMenuItem);

        // about menu
        aboutMenu.add(aboutMenuItem);

        this.add(fileMenu);
        this.add(configMenu);
        this.add(presetMenu);
        this.add(viewMenu);
        this.add(aboutMenu);
    }


    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        // suggest converting this to switch statement
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
            interfaceLsystem.dispose();
        }
        else if (source == configMenuItem)
        {
            // JOptionPane.showMessageDialog(this, "Ouvrir les configurations");
            this.paramDialog.setVisible(true);
            this.interfaceLsystem.getInterfaceConfig().setAngle(this.paramDialog.getLongueur());
            this.interfaceLsystem.getInterfaceConfig().setPas(this.paramDialog.getAngle());
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

    public JMenuItem crateMenuItem(Preset preset)
    {
        JMenuItem item = new JMenuItem(preset.getName());
        item.addActionListener(e -> {
            applyPreset(preset);
        });
        return item;
    }

    public void applyPreset(Preset preset)
    {
        this.interfaceLsystem.setLSystem(preset.getLSys());
        this.interfaceLsystem.setConfig(preset.getConfig());
        this.interfaceLsystem.getVueLsystem().setLSystem(preset.getLSys());
    }

    public static void main(String[] args)
    {
        new MenubarLsystem(new InterfaceLsystem());
    }
}
