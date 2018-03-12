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

public class ReportCommand implements CommandExecutor {

    private Staff staff;
    private HashMap<UUID, Long> cooldown;

    public ReportCommand(Staff staff) {
        this.staff = staff;
        staff.getCommand("report").setExecutor(this);
        this.cooldown = new HashMap<>();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatUtil.color("&cYou are not a player."));
        }else if((args.length == 0) || (args.length == 1)){
            sender.sendMessage(ChatUtil.color("&cUsage: /report <player> <reason...>"));
        }else if(args.length >= 1) {
            Player player = (Player) sender;
            if(cooldown.containsKey(player.getUniqueId()) && cooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                player.sendMessage(ChatUtil.color("&cYou are currently on report cooldown."));
            }else {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target != null) {
                    String reason;
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        stringBuilder.append(args[i] + " ");
                    }
                    reason = stringBuilder.toString();

                    for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                        if (players.hasPermission("staff.staff")) {
                            players.sendMessage(ChatUtil.color("&9[Report] &b" + sender.getName() + " &9reported &b" + target.getName() + "&9."));
                            players.sendMessage(ChatUtil.color("&9Reason: &7" + reason));
                        }
                    }
                    sender.sendMessage(ChatUtil.color("&aYou have successfully reported " + target.getName() + "."));
                    cooldown.put(player.getUniqueId(), System.currentTimeMillis() + (1000 * 120));
                }else{
                    player.sendMessage(ChatUtil.color("&cPlayer '" + args[0] + "' cannot be found."));
                }
            }
        }

        return true;
    }

}
