package me.xiaoxing365.gypchat.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.xiaoxing365.gypchat.GypChat;
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

    Plugin main = GypChat.getProvidingPlugin(GypChat.class);

    @EventHandler(ignoreCancelled = true,priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if (main.getConfig().getBoolean("chatcolor")) {
            String getPlayerMessage = event.getMessage();
            event.setCancelled(true);
            String format = ChatColor.AQUA + main.getConfig().getString("format") +ChatColor.BLUE+">>>"+ ChatColor.GRAY + getPlayerMessage;
            String setPlayerMessage = ReplaceUtil.ColorReplace(format);

            if (player.isOp()){
                //String setPlayerMessage = ReplaceUtil.ColorReplace(ChatColor.AQUA + main.getConfig().getString("format") +ChatColor.BLUE+">>>"+ ChatColor.GRAY + getPlayerMessage);
                String chatFormat = PlaceholderAPI.setPlaceholders(player, ChatColor.RED+"[管理员]"+setPlayerMessage);
                //Bukkit.broadcastMessage((String) opformat);
            }
            String chatFormat = PlaceholderAPI.setPlaceholders(player, setPlayerMessage);
            Bukkit.broadcastMessage((String) chatFormat);
        }else {
            //Player player = event.getPlayer();
            String getPlayerMessage = event.getMessage();
            String setPlayerMessage = ReplaceUtil.ColorReplace(ChatColor.AQUA + main.getConfig().getString("format") +ChatColor.BLUE+">>>"+ ChatColor.GRAY + getPlayerMessage);
            Object format = PlaceholderAPI.setPlaceholders(player, setPlayerMessage);
            if (player.isOp()){
                String setOPMessage = ReplaceUtil.ColorReplace(ChatColor.RED+"[管理员]"+ChatColor.AQUA + main.getConfig().getString("format") +ChatColor.BLUE+">>>"+ ChatColor.GRAY + getPlayerMessage);
                Object opformat = PlaceholderAPI.setPlaceholders(player, setOPMessage);
                Bukkit.broadcastMessage((String) opformat);
            }
            Bukkit.broadcastMessage((String) format);
        }

        main.getConfig().getStringList("mutelist").forEach(ml->{
            Player mutedPlayer = event.getPlayer();
            if (ml.equals(mutedPlayer)){
                event.setCancelled(true);
                mutedPlayer.sendMessage(ChatColor.RED+"你已被禁言，请联系管理员解除！");
            }
        });

    }
}
