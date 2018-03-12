package me.drawlin.staff.commands;

import me.drawlin.staff.Staff;
import me.drawlin.staff.utility.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class RequestCommand implements CommandExecutor {

    private Staff staff;
    private HashMap<UUID, Long> cooldown;

    public RequestCommand(Staff staff) {
        this.staff = staff;
        staff.getCommand("request").setExecutor(this);
        this.cooldown = new HashMap<>();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtil.color("&cYou must be a player."));
            return true;
        }

        Player player = (Player) sender;

        if(!(args.length >= 1)) {
            player.sendMessage(ChatUtil.color("&cUsage: /request <message...>"));
            return true;
        }

        if(onCooldown(player)) {
            player.sendMessage(ChatUtil.color("&cYou are currently on cooldown."));
            return true;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            stringBuilder.append(args[i] + " ");
        }

        staff.getStaffManager().sendMessage("&9[Request] &b" + player.getName() + " &9requests help: &7" + stringBuilder.toString());
        applyCooldown(player);
        return true;
    }

    private boolean onCooldown(Player player) {
        if(cooldown.containsKey(player.getUniqueId()) && cooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    private void applyCooldown(Player player) {
        cooldown.put(player.getUniqueId(), System.currentTimeMillis() + (1000 * 120));
    }

}
