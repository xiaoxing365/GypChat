package me.xiaoxing365.gypchat;

import me.xiaoxing365.gypchat.Cmds.MainCmd;
import me.xiaoxing365.gypchat.Cmds.MainTab;
import me.xiaoxing365.gypchat.listeners.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class GypChat extends JavaPlugin {

    private GypChat instance;

    public GypChat getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA+"GYPChat已启动！");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA+"末影阁系列插件");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA+"作者:xiaoxing365");

        this.saveDefaultConfig();

        Bukkit.getPluginCommand("gc").setExecutor(new MainCmd());
        Bukkit.getPluginCommand("gc").setTabCompleter(new MainTab());
        Bukkit.getPluginManager().registerEvents(new ChatListener(),this);

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"检测到PlaceholderAPI插件，开启变量！");
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"插件已卸载，感谢使用！");
    }
}
