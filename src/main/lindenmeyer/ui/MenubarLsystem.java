package lindenmeyer.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Path;

import java.util.ArrayList;
import java.time.LocalDateTime;

import javax.swing.*;

import lindenmeyer.lsystem.LSystem;
import lindenmeyer.modeleIO.Custom;
import lindenmeyer.modeleIO.ModeleIO;
import lindenmeyer.modeleIO.ModeleList;
import lindenmeyer.modeleIO.Preset;

import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// import netscape.javascript.JSObject;


public class MenubarLsystem extends JMenuBar implements ActionListener
{
    // menus
    final JMenu fileMenu = new JMenu("Fichier");
    // file menu items
    JMenuItem newMenuItem = new JMenuItem("New");

    final JMenu openMenu = new JMenu("Ouvrir");
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

    final JMenu aboutMenu = new JMenu("À propos");
    // about menu items
    JMenuItem aboutMenuItem = new JMenuItem("Informations");

    private ParamDialog paramDialog;
    private InterfaceLsystem interfaceLsystem;
    
    String savePathString = System.getProperty("user.home").concat("/saves.json");
    private ModeleList savedArray = new ModeleList(savePathString);

    public MenubarLsystem(InterfaceLsystem interfaceLsystem)
    {
        this.interfaceLsystem = interfaceLsystem;
        // file menu items
        newMenuItem.addActionListener(this);
        // openMenuItem.addActionListener(this);
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

        fileMenu.add(openMenu);
        for (ModeleIO saves : this.savedArray)
        {
            openMenu.add(createMenuItem(saves));
        }

        // fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // config menu
        configMenu.add(configMenuItem);

        // preset menu
        for (ModeleIO preset : this.interfaceLsystem.getPresets().getModeles())
        {
            presetMenu.add(createMenuItem(preset));
        }



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

        else if (source == saveMenuItem)
        {
            LSystem lSystem = this.interfaceLsystem.getLSystem();
            ConfigLsystem config = this.interfaceLsystem.getInterfaceConfig();

            SaveDialog dialog = new SaveDialog(this.interfaceLsystem);
            dialog.setVisible(true);

            String name = ""; 
            if (dialog.isConfirmed()) 
            {
                name = dialog.getName();
                // config.setDescription(dialog.getDescription());
            }

            ModeleIO newSave = new Custom(name, config, lSystem);

            //System.out.println(newSave.toJSON().toString());

            savedArray.add(newSave);
            savedArray.save();

            /**
            try 
            {
                Files.writeString(Path.of("src/main/lindenmeyer/ui/saves.json"), savedArray.toString(2));
            } 
            catch (JSONException error) 
            {
                error.printStackTrace();
            } 
            catch (IOException error) 
            {
                error.printStackTrace();
            }
                 */
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

    public JMenuItem createMenuItem(ModeleIO preset)
    {
        JMenuItem item = new JMenuItem(preset.getName());
        item.addActionListener(e -> {
            applyPreset(preset);
        });
        return item;
    }

    public void applyPreset(ModeleIO preset)
    {
        this.interfaceLsystem.setLSystem(preset.getLSystem());
        this.interfaceLsystem.setInterfaceConfig(preset.getConfig());
        this.interfaceLsystem.getVueLsystem().setLSystem(preset.getLSystem());
    }

    public static void main(String[] args)
    {
        new MenubarLsystem(new InterfaceLsystem());
    }
}
