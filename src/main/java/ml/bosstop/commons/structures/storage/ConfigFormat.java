package ml.bosstop.commons.structures.storage;

import com.google.gson.JsonObject;

public class ConfigFormat {
    JsonObject chat = new JsonObject();
    JsonObject tablist = new JsonObject();

    public JsonObject getChat() {
        return chat;
    }

    public JsonObject getTablist() {
        return tablist;
    }

    public ConfigFormat setChat(String format, boolean enabled) {
        this.chat.addProperty("format", format);
        this.chat.addProperty("enabled", enabled);
        return this;
    }

    public ConfigFormat setTablist(String format, boolean enabled) {
        this.tablist.addProperty("format", format);
        this.tablist.addProperty("enabled", enabled);
        return this;
    }
}
