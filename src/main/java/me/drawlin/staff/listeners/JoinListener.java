package me.drawlin.staff.listeners;

import me.drawlin.staff.Staff;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener{

    private Staff staff;

    public JoinListener(Staff staff) {
        this.staff = staff;

        staff.getServer().getPluginManager().registerEvents(this, staff);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(e.getPlayer().hasPermission("staff.staff")) {
            staff.getStaffManager().applyStaffMode(e.getPlayer());
        }

        for(Player players : Bukkit.getServer().getOnlinePlayers()) {
            if(!(e.getPlayer().hasPermission("staff.vanish"))) {
                if(staff.getStaffManager().getVanishModePlayers().contains(players.getUniqueId())) {
                    e.getPlayer().hidePlayer(players);
                }
            }
        }
    }
}
