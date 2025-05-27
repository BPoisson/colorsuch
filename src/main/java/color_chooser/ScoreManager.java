package color_chooser;

import managers.Round;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Date;

public class ScoreManager {
    private static final Path jsonPath = Path.of("scores.json");
    private static final String roundsKey = "Rounds";
    private static final String roundKey = "Round";
    private static final String dateKey = "Date";
    private static final String gamesKey = "Games";
    private static final String gameKey = "Game";
    private static final String scoreKey = "Score";
    private static final String colorHKey = "ColorH";
    private static final String colorSKey = "ColorS";
    private static final String colorBKey = "ColorB";
    private static final String averageScoreKey = "AverageScore";

    public ScoreManager() {}

    public void saveRound(Round[] rounds) {
        JSONObject savedJSON = readSavedJSON();
        JSONObject gameJSON = createGameJSON(rounds, savedJSON);

        String gameJSONString = gameJSON.toString(4);

        System.out.println(gameJSONString);

        try {
            Files.writeString(jsonPath, gameJSONString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject readSavedJSON() {
        if (!Files.exists(jsonPath)) {
            return null;
        }

        try {
            String savedContent = new String(Files.readAllBytes(jsonPath));
            return new JSONObject(savedContent);
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

    private JSONObject createGameJSON(Round[] rounds, JSONObject savedJSON) {
        float totalScore = 0;
        JSONObject gamesJSON = new JSONObject();
        JSONObject gameJSON = new JSONObject();
        JSONArray gamesArray = getPreviousGames(savedJSON);
        JSONArray roundJSONArray = new JSONArray();

        for (int i = 0; i < rounds.length; i++) {
            Round round = rounds[i];
            float[] chosenColorHSB = round.getChosenColorHSB();
            totalScore += round.getScore();

            JSONObject roundJSON = new JSONObject();
            roundJSON.put(roundKey, i + 1);
            roundJSON.put(scoreKey, round.getScore());
            roundJSON.put(colorHKey, chosenColorHSB[0]);
            roundJSON.put(colorSKey, chosenColorHSB[1]);
            roundJSON.put(colorBKey, chosenColorHSB[2]);

            roundJSONArray.put(roundJSON);
        }
        gameJSON.put(gameKey, gamesArray.length() + 1);
        gameJSON.put(dateKey, Date.from(Instant.now()));
        gameJSON.put(averageScoreKey, totalScore / rounds.length);
        gameJSON.put(roundsKey, roundJSONArray);
        gamesArray.put(gameJSON);
        gamesJSON.put(gamesKey, gamesArray);

        return gamesJSON;
    }

    private JSONArray getPreviousGames(JSONObject savedJSON) {
        return savedJSON == null ? new JSONArray() : savedJSON.getJSONArray(gamesKey);
    }
}
