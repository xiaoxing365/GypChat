package me.xiaoxing365.gypchat.listeners;

import me.xiaoxing365.gypchat.GypChat;
import me.xiaoxing365.gypchat.config.DefConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatMuter implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        String player = event.getPlayer().getDisplayName();
        DefConfig.getMuteList().forEach(ml->{
            if (ml.equals(player)){
                event.setCancelled(true);
                Player pmsg = event.getPlayer();
                pmsg.sendMessage(ChatColor.RED+"你已被禁言，请联系管理员解除！");
            }
        });
    }
}
