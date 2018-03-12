package me.drawlin.staff.listeners;

import me.drawlin.staff.Staff;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener{

    private Staff staff;

    public QuitListener(Staff staff) {
        this.staff = staff;

        staff.getServer().getPluginManager().registerEvents(this, staff);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(e.getPlayer().hasPermission("staff.staff")) {
            staff.getStaffManager().unapplyStaffMode(e.getPlayer());
        }
    }

}
