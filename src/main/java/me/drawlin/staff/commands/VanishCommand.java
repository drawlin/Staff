package me.drawlin.staff.commands;

import me.drawlin.staff.Staff;
import me.drawlin.staff.utility.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor{

    private Staff staff;

    public VanishCommand(Staff staff) {
        this.staff = staff;
        staff.getCommand("vanish").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatUtil.color("&cYou are not a player."));
            return true;
        }

        Player player = (Player) sender;

        if(!(player.hasPermission("staff.vanish"))) {
            player.sendMessage(ChatUtil.color("&cNo permission."));
            return true;
        }

        if(args.length == 0) {
            if(staff.getStaffManager().isVanishMode(player)) {
                staff.getStaffManager().unapplyVanishMode(player);
            }else{
                staff.getStaffManager().applyVanishMode(player);
            }
        }else if(args.length == 1) {
            Player target = Bukkit.getServer().getPlayer(args[0]);

            if(target != null) {
                if(staff.getStaffManager().isVanishMode(target)) {
                    staff.getStaffManager().unapplyVanishMode(target);
                }else{
                    staff.getStaffManager().applyVanishMode(target);
                }
                player.sendMessage(ChatUtil.color("&eVanish mode for &b" + target.getName() + " &eis now " + (staff.getStaffManager().isVanishMode(target) ? "&atrue" : "&cfalse") + "&e."));
            }else{
                player.sendMessage(ChatUtil.color("&cPlayer '" + args[0] + "' cannot be found."));
                return true;
            }
        }else{
            player.sendMessage(ChatUtil.color("&cUsage: /vanish [player]"));
        }

        return true;
    }

}
