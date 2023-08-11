package me.xiaoxing365.gypchat.Cmds;

import me.xiaoxing365.gypchat.GypChat;
import me.xiaoxing365.gypchat.config.DefConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class MainCmd implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0){
            sender.sendMessage(ChatColor.YELLOW+"/---------GypChat----------");
            sender.sendMessage(ChatColor.YELLOW+"/gc help         显示帮助页面(也就是这个页面)");
            sender.sendMessage(ChatColor.YELLOW+"/gc mute         禁言其他玩家(只有管理员可执行)");
            sender.sendMessage(ChatColor.YELLOW+"/gc reload         重载插件(只有管理员可执行)");
            sender.sendMessage(ChatColor.YELLOW + "---------------------------");
            return true;
        }
        //List<String> mutelist = DefConfig.getMuteList();
        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("GypChat.reload") & sender.isOp()) {
                try {
                    GypChat.instance.reloadConfig();
                    Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "插件已重载！");
                    sender.sendMessage(ChatColor.AQUA + "插件已重载！");
                } catch (Exception e) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "发生错误！");
                    Bukkit.getConsoleSender().sendMessage(e.getMessage());
                }
            } else {
                sender.sendMessage(ChatColor.RED + "你没有权限运行此命令！");
            }
        }
        if (args[0].equalsIgnoreCase("mute")) {
            if (sender.hasPermission("GypChat.mute") & sender.isOp()) {
                String player = args[1].toLowerCase();
                //mutelist.add(player);
                GypChat.instance.getConfig().getStringList("muteList").add(player);
                DefConfig.saveConfig();
                sender.sendMessage(ChatColor.YELLOW + "你已禁言" + player);
            } else {
                sender.sendMessage(ChatColor.RED + "你没有权限运行此命令！");
            }
        }
        if (args[0].equalsIgnoreCase("unmute")) {
            if (sender.hasPermission("GypChat.unmute") & sender.isOp()) {
                String player = args[1];
                //mutelist.remove(player);
                GypChat.instance.getConfig().getStringList("muteList").remove(player);
                DefConfig.saveConfig();
                sender.sendMessage(ChatColor.AQUA + "你已解除" + player + "的禁言！");
            } else {
                sender.sendMessage(ChatColor.RED + "你没有权限运行此命令！");
            }
        }
        if (args[0].isEmpty()) {
            sender.sendMessage(ChatColor.YELLOW + "/---------GypChat----------");
            sender.sendMessage(ChatColor.YELLOW + "/gc help         显示帮助页面(也就是这个页面)");
            sender.sendMessage(ChatColor.YELLOW + "/gc mute         禁言其他玩家(只有管理员可执行)");
            sender.sendMessage(ChatColor.YELLOW + "/gc reload         重载插件(只有管理员可执行)");
            sender.sendMessage(ChatColor.YELLOW + "---------------------------");
        }
        return false;
    }
}
