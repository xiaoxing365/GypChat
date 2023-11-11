package me.xiaoxing365.gypchat.bukkit.Cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class MainTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        List<String> list = new ArrayList<>();

        if (args.length == 1){
            //String[] flist = {"reload","mute","unmute","help"};
            list.add("reload");
            list.add("mute");
            list.add("unmute");
            list.add("help");
            return list;
        }

        return null;
    }
}
