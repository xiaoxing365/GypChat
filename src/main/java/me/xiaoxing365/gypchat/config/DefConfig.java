package me.xiaoxing365.gypchat.config;

import me.xiaoxing365.gypchat.GypChat;

import java.util.List;

public class DefConfig {

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


    public static List<String> getMuteList() {
        return GypChat.instance.getConfig().getStringList("MuteList");
    }
}
