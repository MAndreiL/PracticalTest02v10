package ro.pub.cs.systems.eim.practicaltest02v10;

import org.json.JSONObject;

public class Pokemon {
    private String name;
    private String abilities;
    private String types;
    private String imageUrl;

    public Pokemon(String name, String abilities, String types, String imageUrl) {
        this.name = name;
        this.abilities = abilities;
        this.types = types;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getAbilities() {
        return abilities;
    }

    public String getTypes() {
        return types;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // Metodă pentru a parsează JSON-ul
    public static Pokemon fromJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String name = jsonObject.getString("name");
            String abilities = jsonObject.getJSONArray("abilities")
                    .getJSONObject(0)
                    .getJSONObject("ability")
                    .getString("name");
            String types = jsonObject.getJSONArray("types")
                    .getJSONObject(0)
                    .getJSONObject("type")
                    .getString("name");
            String imageUrl = jsonObject.getJSONObject("sprites")
                    .getString("front_default");

            return new Pokemon(name, abilities, types, imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
