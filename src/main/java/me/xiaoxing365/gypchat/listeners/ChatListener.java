package me.xiaoxing365.gypchat.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.xiaoxing365.gypchat.Cmds.MainCmd;
import me.xiaoxing365.gypchat.GypChat;
//import me.xiaoxing365.gypchat.configs.Config;
import me.xiaoxing365.gypchat.utils.ReplaceUtil;
import net.Zrips.CMILib.Messages.CMIMessages;
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
        if (GypChat.instance.getConfig().getStringList("muteList").contains(playername)) {
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
            String getPlayerMessage = event.getMessage();
            String format = ChatColor.AQUA + GypChat.instance.getConfig().getString("Format") +ChatColor.BLUE+" >>> "+ ChatColor.GRAY + getPlayerMessage;
            if (player.isOp()){
                format =  ChatColor.RED+"[管理员]"+format;
                //Bukkit.broadcastMessage((String) opformat);
            }
            String setPlayerMessage = ReplaceUtil.ColorReplace(format);
            String chatFormat = PlaceholderAPI.setPlaceholders(player, setPlayerMessage);
            event.setFormat(chatFormat);
        }else if (!GypChat.instance.getConfig().getBoolean("chatColor")){
            /*
            没有启动颜色
             */
            String getPlayerMessage = event.getMessage();
            String format = GypChat.instance.getConfig().getString("Format") +" >>> "+ getPlayerMessage;
            String setPlayerMessage = ReplaceUtil.ColorReplace(format);
            String chatFormat = PlaceholderAPI.setPlaceholders(player, setPlayerMessage);
            if (player.isOp()){
                format = ChatColor.RED+"[管理员]" +format;
                //Bukkit.broadcastMessage((String) opformat);
            }
            event.setFormat(chatFormat);

        }


    }
}
