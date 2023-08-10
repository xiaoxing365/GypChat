package me.xiaoxing365.gypchat.listeners;


import me.xiaoxing365.gypchat.config.DefConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class ChatFilter implements Listener {
    @EventHandler(ignoreCancelled = true,priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event){
        if (DefConfig.isReplaceEnable()) {
            Player player = event.getPlayer();
            List<String> list = DefConfig.getreplaceWords();
            list.forEach(words -> {
                if (event.getMessage().equalsIgnoreCase(words)) {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.YELLOW+"请合理使用词汇，保持干净的聊天环境！");
                }
            });
        }
    }

}
