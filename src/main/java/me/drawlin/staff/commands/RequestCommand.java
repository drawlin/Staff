package me.drawlin.staff.commands;

import me.drawlin.staff.Staff;
import me.drawlin.staff.utility.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class RequestCommand implements CommandExecutor{

    private Staff staff;
    private HashMap<UUID, Long> cooldown;

    public RequestCommand(Staff staff) {
        this.staff = staff;
        staff.getCommand("request").setExecutor(this);
        this.cooldown = new HashMap<>();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatUtil.color("&cYou are not a player."));
        }else if(args == null){
            sender.sendMessage(ChatUtil.color("&cUsage: /request <message...>"));
        }else if(args.length >= 1) {
            Player player = (Player) sender;
            if (cooldown.containsKey(player.getUniqueId()) && cooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                player.sendMessage(ChatUtil.color("&cYou are currently on request cooldown."));
            } else {
                String request;
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    stringBuilder.append(args[i] + " ");
                }
                request = stringBuilder.toString();

                for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                    if (players.hasPermission("staff.staff")) {
                        players.sendMessage(ChatUtil.color("&9[Request] &b" + sender.getName() + " &9requested &7" + request));
                    }
                }
                sender.sendMessage(ChatUtil.color("&aYou have successfully requested staff."));
                cooldown.put(player.getUniqueId(), System.currentTimeMillis() + (1000 * 120));
            }
        }else{
            sender.sendMessage(ChatUtil.color("&cUsage: /request <message...>"));
        }

        return true;
    }


}
