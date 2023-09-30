package me.xiaoxing365.gypchat.configs;

import me.xiaoxing365.gypchat.Cmds.MainCmd;
import me.xiaoxing365.gypchat.GypChat;
import me.xiaoxing365.gypchat.listeners.ChatMuter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {
    public static File dataFile;
    public static FileConfiguration config;
    public static  void loadData(){
        // 创建数据文件
        dataFile = new File(GypChat.instance.getDataFolder(), "config.yml");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            GypChat.instance.saveResource("config.yml", false);
        }

        // 加载数据文件
        config = new YamlConfiguration();
        try {
            config.load(dataFile);
            ChatMuter.loadMutedPlayers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
