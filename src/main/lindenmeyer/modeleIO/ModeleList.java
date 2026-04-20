package lindenmeyer.modeleIO;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Cette classe permet de collectionner un ensemble des modèles Lindenmeyer {@link ModeleIO}.
 * Elle contient les méthodes pour faciliter le chargement et enregistre des modèles
 * dans le format JSON
 */
public class ModeleList implements Iterable<ModeleIO>
{
    private List<ModeleIO> modeles = new ArrayList<>();

    public ModeleList() {}

    /**
     * Construire une liste des modèles à partir d'un fichier JSON
     * @param pathString    le chemin du fichier JSON
     */
    public ModeleList(String pathString)
    {
        Path path = Path.of(pathString);
        String savePathString = System.getProperty("user.home").concat("/saves.json");

        if (Files.notExists(path)) {
            if (pathString.equals(savePathString))
            {
                this.modeles = new ArrayList<>();
                return;
            }

            else
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

                // System.out.println(modeleObject.toString());

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

    /**
     * Ajoute un modele
     * @param modeleIO  le modèle à ajouter
     */
    public void add(ModeleIO modeleIO) { this.modeles.add(modeleIO); }

    public List<ModeleIO> getModeles() { return this.modeles; }

    /**
     * Enregistre un modèle au chemin prédéfini.
     * @return  Renvoie true si l'enregistrement est réussi. false sinon.
     */
    public boolean save()
    {
        String savePathString = System.getProperty("user.home").concat("/saves.json");

        JSONArray saveArray = new JSONArray();
        for (ModeleIO modele : this.modeles)
        {
            saveArray.put(modele.toJSON());
        }
        JSONObject saveObject = new JSONObject();
        saveObject.put("modeles", saveArray);
        // System.out.println(saveObject.toString());

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
