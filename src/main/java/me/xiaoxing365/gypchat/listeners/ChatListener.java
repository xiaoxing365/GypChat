package me.xiaoxing365.gypchat.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.xiaoxing365.gypchat.GypChat;
import me.xiaoxing365.gypchat.config.DefConfig;
import me.xiaoxing365.gypchat.utils.ReplaceUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class ChatListener implements Listener {

    //Plugin main = GypChat.getProvidingPlugin(GypChat.class);


    @EventHandler(ignoreCancelled = true,priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if (DefConfig.isChatColor()) {
            String getPlayerMessage = event.getMessage();
            event.setCancelled(true);
            String format = ChatColor.AQUA + DefConfig.getFormat() +ChatColor.BLUE+" >>>"+ ChatColor.GRAY + getPlayerMessage;

            if (player.isOp()){
                format = PlaceholderAPI.setPlaceholders(player, ChatColor.RED+"[管理员]"+format);
                //Bukkit.broadcastMessage((String) opformat);
            }
            String setPlayerMessage = ReplaceUtil.ColorReplace(format);
            String chatFormat = PlaceholderAPI.setPlaceholders(player, setPlayerMessage);
            Bukkit.broadcastMessage(chatFormat);
        }else if (!DefConfig.isChatColor()){
            //Player player = event.getPlayer();
            String getPlayerMessage = event.getMessage();
            event.setCancelled(true);
            String format = DefConfig.getFormat() +" >>>"+ getPlayerMessage;

            if (player.isOp()){
                format = PlaceholderAPI.setPlaceholders(player, ChatColor.RED+"[管理员]"+format);
                //Bukkit.broadcastMessage((String) opformat);
            }
            String setPlayerMessage = ReplaceUtil.ColorReplace(format);
            String chatFormat = PlaceholderAPI.setPlaceholders(player, setPlayerMessage);
            Bukkit.broadcastMessage(chatFormat);
        }

    }
}
