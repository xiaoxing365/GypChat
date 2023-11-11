package me.xiaoxing365.gypchat.bukkit.listeners;

import me.xiaoxing365.gypchat.bukkit.GypChat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ChatMuter implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String playername = event.getPlayer().getDisplayName();
        Player player = event.getPlayer();
        GypChat.instance.getConfig().getStringList("muteList").forEach(ml->{
            if (ml.contains(playername)) {
                event.setCancelled(true);
            }
        });
        // 检查是否被禁言
        if (GypChat.instance.getConfig().getStringList("muteList").contains(playername)) {
            player.sendMessage(ChatColor.RED+"你被禁止发言!");
        }
    }




}
