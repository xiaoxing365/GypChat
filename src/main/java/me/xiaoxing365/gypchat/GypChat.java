package me.xiaoxing365.gypchat;

import me.xiaoxing365.gypchat.Cmds.MainCmd;
import me.xiaoxing365.gypchat.Cmds.MainTab;
//import me.xiaoxing365.gypchat.configs.Config;
import me.xiaoxing365.gypchat.listeners.ChatListener;
import me.xiaoxing365.gypchat.listeners.ChatMuter;
import net.Zrips.CMILib.Messages.CMIMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

//import static me.xiaoxing365.gypchat.Cmds.MainCmd.mutedPlayers;

public final class GypChat extends JavaPlugin {

    public static GypChat instance;
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        CMIMessages.consoleMessage(ChatColor.AQUA+"GYPChat已启动！");
        CMIMessages.consoleMessage(ChatColor.AQUA+"末影阁系列插件");
        CMIMessages.consoleMessage(ChatColor.AQUA+"作者:xiaoxing365");

        this.getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        Bukkit.getPluginCommand("gc").setExecutor(new MainCmd());
        Bukkit.getPluginCommand("gc").setTabCompleter(new MainTab());
        Bukkit.getPluginManager().registerEvents(new ChatListener(),this);
        Bukkit.getPluginManager().registerEvents(new ChatMuter(),this);
        //Bukkit.getPluginManager().registerEvents(new ChatFilter(),this);

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"检测到PlaceholderAPI插件，开启变量！");
        }

    }

    @Override
    public void onDisable() {
        ChatMuter.saveMutedPlayers();
        CMIMessages.consoleMessage(ChatColor.BLUE+"插件已卸载，感谢使用！");
    }
}
