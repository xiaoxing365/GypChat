package me.xiaoxing365.gypchat.config;

import me.xiaoxing365.gypchat.GypChat;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class DefConfig {
    static Plugin main = GypChat.getProvidingPlugin(GypChat.class);

    public static List<String> getreplaceWords(){
        return main.getConfig().getStringList("replaceWords");
    }
    public static void saveConfig(){
        main.saveConfig();
    }

    public static String getFormat() {
        return main.getConfig().getString("Format");
    }

    public static boolean isChatColor() {
        return main.getConfig().getBoolean("chatColor");
    }

    public static boolean isReplaceEnable() {
        return main.getConfig().getBoolean("ReplaceEnable");
    }
    public static String getReplaceTo() {
        return main.getConfig().getString("ReplaceTo");
    }

    public static List<String> getMuteList() {
        return main.getConfig().getStringList("MuteList");
    }
}
