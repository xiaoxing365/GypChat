package me.xiaoxing365.gypchat.bukkit.utils;

import org.bukkit.entity.Player;

public class SendUtil {
    public static String ColorReplace(String msg){
        return msg.replace("&","§");
    }
    public static void sendToPlayer(Player player,String msg){
    	player.sendMessage(msg);
    }
    public static void sendToConsole(String msg){
    }
}
