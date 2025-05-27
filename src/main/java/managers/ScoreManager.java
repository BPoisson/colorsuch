package managers;

import org.json.JSONObject;

public class ScoreManager {

    public ScoreManager() {}

    public void saveRound(Round[] rounds) {
        JSONObject gameJSON = JSONManager.createGameJSON(rounds);
        String gameJSONString = JSONManager.prettyString(gameJSON);
        JSONManager.writeJSONFile(gameJSONString);
    }

}
