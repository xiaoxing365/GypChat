package me.xiaoxing365.gypchat.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.xiaoxing365.gypchat.Cmds.MainCmd;
import me.xiaoxing365.gypchat.GypChat;
import me.xiaoxing365.gypchat.utils.ReplaceUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ChatListener implements Listener {

    //Plugin main = GypChat.getProvidingPlugin(GypChat.class);
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String playername = event.getPlayer().getDisplayName();
        Player player = event.getPlayer();
        for (String ml : GypChat.instance.getConfig().getStringList("muteList")) {
            if (ml.contains(playername)) {
                event.setCancelled(true);
            }
        }
        // 检查是否被禁言
        if (MainCmd.mutedPlayers.contains(player.getUniqueId())) {
            player.sendMessage(ChatColor.RED+"你被禁止发言!");
        }
    }
    @EventHandler//(ignoreCancelled = true,priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event){

        Player player = event.getPlayer();
        if (GypChat.instance.getConfig().getBoolean("chatColor")) {
            /*
            启动了颜色
             */
            if (GypChat.instance.getConfig().getBoolean("replaceEnable")) {
                String getPlayerMessage = event.getMessage();
                event.setCancelled(true);
                String format = ChatColor.AQUA + GypChat.instance.getConfig().getString("Format") +ChatColor.BLUE+" >>> "+ ChatColor.GRAY + getPlayerMessage;

                if (player.isOp()){
                    format =  ChatColor.RED+"[管理员]"+format;
                    //Bukkit.broadcastMessage((String) opformat);
                }
                String setPlayerMessage = ReplaceUtil.ColorReplace(format);
                String chatFormat = PlaceholderAPI.setPlaceholders(player, setPlayerMessage);
                Bukkit.broadcastMessage(chatFormat);
            }
        }else if (!GypChat.instance.getConfig().getBoolean("chatColor")){
            /*
            没有启动颜色
             */
            //Player player = event.getPlayer();
            String getPlayerMessage = event.getMessage();
            event.setCancelled(true);
            String format = GypChat.instance.getConfig().getString("Format") +" >>> "+ getPlayerMessage;
            if (player.isOp()){
                format = ChatColor.RED+"[管理员]" +format;
                //Bukkit.broadcastMessage((String) opformat);
            }
            String setPlayerMessage = ReplaceUtil.ColorReplace(format);
            if (GypChat.instance.getConfig().getBoolean("replaceEnable")) {
                String chatFormat = PlaceholderAPI.setPlaceholders(player, setPlayerMessage);
                 Bukkit.broadcastMessage(chatFormat);
            }

        }


    }
}
