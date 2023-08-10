package me.xiaoxing365.gypchat.config;

import me.xiaoxing365.gypchat.GypChat;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class DefConfig {
    //static Plugin main = GypChat.getProvidingPlugin(GypChat.class);

    public static List<String> getreplaceWords(){
        return GypChat.instance.getConfig().getStringList("replaceWords");
    }
    public static void saveConfig(){
        GypChat.instance.saveConfig();
    }

    public static String getFormat() {
        return GypChat.instance.getConfig().getString("Format");
    }

    public static boolean isChatColor() {
        return GypChat.instance.getConfig().getBoolean("chatColor");
    }

    public static boolean isReplaceEnable() {
        return GypChat.instance.getConfig().getBoolean("ReplaceEnable");
    }
    public static String getReplaceTo() {
        return GypChat.instance.getConfig().getString("ReplaceTo");
    }

    public static List<String> getMuteList() {
        return GypChat.instance.getConfig().getStringList("MuteList");
    }
}
