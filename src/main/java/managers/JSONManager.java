package managers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Date;

public abstract class JSONManager {
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

    public static JSONObject createGameJSON(Round[] rounds) {
        float totalScore = 0;
        JSONObject gamesJSON = new JSONObject();
        JSONObject gameJSON = new JSONObject();
        JSONArray gamesArray = getPreviousGames();
        JSONArray roundJSONArray = new JSONArray();

        for (int i = 0; i < rounds.length; i++) {
            Round round = rounds[i];
            float[] chosenColorHSB = round.getChosenColorHSB();
            totalScore += round.getScore();
            roundJSONArray.put(JSONManager.getRoundJSON(i + 1, round.getScore(), chosenColorHSB));
        }
        gameJSON.put(gameKey, gamesArray.length() + 1);
        gameJSON.put(dateKey, Date.from(Instant.now()));
        gameJSON.put(averageScoreKey, totalScore / rounds.length);
        gameJSON.put(roundsKey, roundJSONArray);
        gamesArray.put(gameJSON);
        gamesJSON.put(gamesKey, gamesArray);

        return gamesJSON;
    }

    private static JSONObject getRoundJSON(int roundNum, float score, float[] colorHSB) {
        JSONObject roundJSON = new JSONObject();
        roundJSON.put(roundKey, roundNum);
        roundJSON.put(scoreKey, score);
        roundJSON.put(colorHKey, colorHSB[0]);
        roundJSON.put(colorSKey, colorHSB[1]);
        roundJSON.put(colorBKey, colorHSB[2]);

        return roundJSON;
    }

    public static int getGame(JSONObject jsonObject) {
        return jsonObject.getInt(gameKey);
    }

    public static float getAverageScore(JSONObject jsonObject) {
        return jsonObject.getFloat(averageScoreKey);
    }

    public static JSONArray getPreviousGames() {
        JSONObject savedJSON = readJSONFile();

        return savedJSON == null ? new JSONArray() : savedJSON.getJSONArray(gamesKey);
    }

    public static JSONObject readJSONFile() {
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

    public static void writeJSONFile(String jsonString) {
        try {
            Files.writeString(jsonPath, jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String prettyString(JSONObject jsonObject) {
        return jsonObject.toString(4);
    }
}
