package me.drawlin.staff.commands;

import me.drawlin.staff.Staff;
import me.drawlin.staff.utility.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffModeCommand implements CommandExecutor{

    private Staff staff;

    public StaffModeCommand(Staff staff) {
        this.staff = staff;
        staff.getCommand("staffmode").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatUtil.color("&cYou are not a player."));
            return true;
        }

        Player player = (Player) sender;

        if(!(player.hasPermission("staff.staffmode"))) {
            player.sendMessage(ChatUtil.color("&cNo permission."));
            return true;
        }

        if(staff.getStaffManager().isStaffMode(player)) {
            staff.getStaffManager().unapplyStaffMode(player);
        }else{
            staff.getStaffManager().applyStaffMode(player);
        }

        return true;
    }

}
