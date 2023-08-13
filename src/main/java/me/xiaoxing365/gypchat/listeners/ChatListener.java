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

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ChatListener implements Listener {
    Set<UUID> mutedPlayers = new HashSet<>();
    //Plugin main = GypChat.getProvidingPlugin(GypChat.class);


    @EventHandler//(ignoreCancelled = true,priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String name = event.getPlayer().getDisplayName();
        if (GypChat.instance.getConfig().getBoolean("chatColor")) {
            if (GypChat.instance.getConfig().getBoolean("replaceEnable")) {
                String getPlayerMessage = event.getMessage();
                event.setCancelled(true);
                String format = ChatColor.AQUA + GypChat.instance.getConfig().getString("Format") +ChatColor.BLUE+" >>> "+ ChatColor.GRAY + getPlayerMessage;

                if (player.isOp()){
                    format =  ChatColor.RED+"[管理员]"+format;
                    //Bukkit.broadcastMessage((String) opformat);
                }
                String setPlayerMessage = ReplaceUtil.ColorReplace(format);
                //Player player = event.getPlayer();
                //List<String> list = DefConfig.getreplaceWords();
                for (String words : GypChat.instance.getConfig().getStringList("replaceWords")) {
                    if (getPlayerMessage.contains(words)) {
                        //event.setCancelled(true);
                        player.sendMessage(ChatColor.YELLOW + "您的话语中含有违禁词语， ");
                        player.sendMessage(ChatColor.YELLOW + "请合理使用词汇，保持干净的聊天环境！");

                        String chatFormat = PlaceholderAPI.setPlaceholders(player, setPlayerMessage);
                        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "玩家" + name + "说出了敏感词汇，内容：" + getPlayerMessage);
                        Bukkit.broadcastMessage(chatFormat);
                    }
                }
            }
        }else if (!GypChat.instance.getConfig().getBoolean("chatColor")){
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
                //Player player = event.getPlayer();
                //List<String> list = DefConfig.getreplaceWords();
                for (String words : GypChat.instance.getConfig().getStringList("replaceWords")) {
                    if (getPlayerMessage.contains(words)) {
                        //event.setCancelled(true);
                        player.sendMessage(ChatColor.YELLOW + "您的话语中含有违禁词语， ");
                        player.sendMessage(ChatColor.YELLOW + "请合理使用词汇，保持干净的聊天环境！");
                        getPlayerMessage.replaceAll(words,"*");
                        String chatFormat = PlaceholderAPI.setPlaceholders(player, setPlayerMessage);
                        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "玩家" + name + "说出了敏感词汇，内容：" + getPlayerMessage);
                        Bukkit.broadcastMessage(chatFormat);
                    }
                }
            }

        }
        /**
         * muter
         */
        String playername = event.getPlayer().getDisplayName();
        for (String ml : GypChat.instance.getConfig().getStringList("muteList")) {
            if (ml.contains(playername)) {
                event.setCancelled(true);
                Player pmsg = event.getPlayer();
                pmsg.sendMessage(ChatColor.RED + "你已被禁言，请联系管理员解除！");
            }
        }

    }
}
