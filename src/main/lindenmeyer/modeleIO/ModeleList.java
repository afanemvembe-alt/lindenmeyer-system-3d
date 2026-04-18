package lindenmeyer.modeleIO;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lindenmeyer.lsystem.LSystem;
import lindenmeyer.ui.ConfigLsystem;

import java.util.Iterator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public abstract class ModeleList implements Iterable<ModeleIO>
{
    private List<ModeleIO> modeles = new ArrayList<>();

    public ModeleList() {}

    public ModeleList(String pathString)
    {
        Path path = Path.of(pathString);
        String savePathString = System.getProperty("user.home").concat("/saves.json");

        if (Files.notExists(path)) {
            throw new IllegalArgumentException("File does not exist: " + pathString);
        }

        try 
        {
            String fileString = Files.readString(path);

            JSONObject root = new JSONObject(fileString);
            JSONArray modelesArray = root.getJSONArray("modeles");


            for (int i=0; i<modelesArray.length(); i++)
            {
                JSONObject modeleObject = modelesArray.getJSONObject(i);

                if (pathString.equals("src/resources/lindenmeyer/ui/presets.json"))
                    this.modeles.add(new Preset(modeleObject));
                else if (pathString.equals(savePathString))
                    this.modeles.add(new Custom(modeleObject));
            }
        } 
        catch (IOException e) 
        {
            throw new RuntimeException("Failed to read presets.json", e);
        }
    }

    public void add(ModeleIO modeleIO) { this.modeles.add(modeleIO); }

    public List<ModeleIO> getModeles() { return this.modeles; }

    public boolean save()
    {
        String savePathString = System.getProperty("user.home").concat("/saves.json");

        JSONArray saveArray = new JSONArray(this.modeles);
        JSONObject saveObject = new JSONObject();
        saveObject.put("modeles", saveArray);

        try 
        {
            Files.writeString(Path.of(savePathString), saveObject.toString(2));
            return true;
        } 
        catch (JSONException error) 
        {
            error.printStackTrace();
            return false;
        } 
        catch (IOException error) 
        {
            error.printStackTrace();
            return false;
        }
    }

    @Override
    public Iterator<ModeleIO> iterator() { return modeles.iterator(); }
}
