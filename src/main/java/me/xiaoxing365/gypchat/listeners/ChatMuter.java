package me.xiaoxing365.gypchat.listeners;

import me.xiaoxing365.gypchat.Cmds.MainCmd;
import me.xiaoxing365.gypchat.configs.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static me.xiaoxing365.gypchat.configs.Config.dataFile;

public class ChatMuter implements Listener {

    public static  void loadMutedPlayers() {
        if (Config.config.contains("mutedPlayers")) {
            for (String uuidString : Config.config.getStringList("mutedPlayers")) {
                UUID uuid = UUID.fromString(uuidString);
                MainCmd.mutedPlayers.add(uuid);
            }
        }
    }

    public static void saveMutedPlayers() {
        Config.config.set("mutedPlayers", new HashSet<>(MainCmd.mutedPlayers));
        try {
            Config.config.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
