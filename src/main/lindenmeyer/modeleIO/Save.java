package lindenmeyer.modeleIO;

import java.nio.file.Path;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

public interface Save 
{
    boolean saveTo(Path path);
}
