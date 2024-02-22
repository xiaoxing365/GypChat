package me.xiaoxing365.gypchat.bukkit.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.xiaoxing365.gypchat.bukkit.GypChat;
import me.xiaoxing365.gypchat.bukkit.utils.SendUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ChatListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if (GypChat.instance.getConfig().getBoolean("chatColor")) {
            /*
            启动了颜色
             */
            String getPlayerMessage = event.getMessage();
            String format = ChatColor.AQUA + GypChat.instance.getConfig().getString("Format") +ChatColor.BLUE+" >>> "+ ChatColor.GRAY + getPlayerMessage;
            if (player.isOp()){
                format =  ChatColor.RED+"[管理员]"+format;
                //Bukkit.broadcastMessage((String) opformat);
            }
            String setPlayerMessage = SendUtil.ColorReplace(format);
            String chatFormat = PlaceholderAPI.setPlaceholders(player, setPlayerMessage);
            event.setFormat(chatFormat);
        }else if (!GypChat.instance.getConfig().getBoolean("chatColor")){
            /*
            没有启动颜色
             */
            String getPlayerMessage = event.getMessage();
            String format = GypChat.instance.getConfig().getString("Format") +" >>> "+ getPlayerMessage;
            String setPlayerMessage = SendUtil.ColorReplace(format);
            String chatFormat = PlaceholderAPI.setPlaceholders(player, setPlayerMessage);
            if (player.isOp()){
                format = ChatColor.RED+"[管理员]" +format;
                //Bukkit.broadcastMessage((String) opformat);
            }
            event.setFormat(chatFormat);

        }


    }
}
