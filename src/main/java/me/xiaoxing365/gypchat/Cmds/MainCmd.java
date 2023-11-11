package me.xiaoxing365.gypchat.Cmds;

import me.xiaoxing365.gypchat.GypChat;
//import me.xiaoxing365.gypchat.configs.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class MainCmd implements CommandExecutor {
    //public static  Set<UUID> mutedPlayers = new HashSet<>();
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
                Player player = Bukkit.getPlayer(args[1]);
                if (player == null) {
                    sender.sendMessage("找不到该玩家!");
                    return true;
                }
                try {
                    int sec = Integer.parseInt(args[2]);
                    // 添加禁言玩家
                    GypChat.instance.getConfig().getStringList("muteList").add(player.getDisplayName());
                    sender.sendMessage("成功禁言玩家 " + player.getName());
                    //转换秒为Tick
                    GypChat.instance.getServer().getScheduler().runTaskLater(GypChat.instance, () -> {
                        GypChat.instance.getConfig().getStringList("muteList").remove(player.getDisplayName());
                        sender.sendMessage("成功解除禁言玩家 " + player.getName());
                    }, sec * 20); // 转换为tick
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED+"禁言时长必须是一个整数!");
                    return true;
                }

            } else {
                sender.sendMessage(ChatColor.RED + "你没有权限运行此命令！");
            }
        }
        if (args[0].equalsIgnoreCase("unmute")) {
            if (sender.hasPermission("GypChat.unmute") & sender.isOp()) {
                Player player = Bukkit.getPlayer(args[1]);
                GypChat.instance.getConfig().getStringList("muteList").remove(player.getDisplayName());
                sender.sendMessage(ChatColor.YELLOW+ "成功解除禁言玩家 " + player.getName());
            } else {
                sender.sendMessage(ChatColor.RED + "你没有权限运行此命令！");
            }
        }
        if (args[0].isEmpty()) {
            sender.sendMessage(ChatColor.YELLOW + "/---------GypChat-----------------------------");
            sender.sendMessage(ChatColor.YELLOW + "/gc help                                 显示帮助页面(也就是这个页面)");
            sender.sendMessage(ChatColor.YELLOW + "/gc mute <玩家名字> <禁言时长（秒）>         禁言其他玩家(只有管理员可执行)");
            sender.sendMessage(ChatColor.YELLOW + "/gc unmute <玩家名字>                     禁言其他玩家(只有管理员可执行)");
            sender.sendMessage(ChatColor.YELLOW + "/gc reload                               重载插件(只有管理员可执行)");
            sender.sendMessage(ChatColor.YELLOW + "----------------------------------------------");
        }
        if (args.length == 3)
            sender.sendMessage(ChatColor.RED+"请输入禁言多少秒");


        return true;
    }
}
