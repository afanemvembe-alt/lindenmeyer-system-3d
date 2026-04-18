package lindenmeyer.modeleIO;

import java.nio.file.Path;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

public interface Load 
{
    boolean pathExists(String pathString);

    JSONArray loadFrom(Path path);
}
