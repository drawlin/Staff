package me.drawlin.staff.listeners;

import me.drawlin.staff.Staff;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class KickListener implements Listener {

    private Staff staff;

    public KickListener(Staff staff) {
        this.staff = staff;

        staff.getServer().getPluginManager().registerEvents(this, staff);
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        if(e.getPlayer().hasPermission("staff.staff")) {
            staff.getStaffManager().unapplyStaffMode(e.getPlayer());
        }
    }
}
