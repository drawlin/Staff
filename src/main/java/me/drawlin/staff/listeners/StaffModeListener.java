package me.drawlin.staff.listeners;

import me.drawlin.staff.Staff;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class StaffModeListener implements Listener{

    private Staff staff;

    public StaffModeListener(Staff staff) {
        this.staff = staff;

        staff.getServer().getPluginManager().registerEvents(this, staff);
    }

    @EventHandler
    public void onBlock(BlockBreakEvent e) {
        if(staff.getStaffManager().isStaffMode(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if(staff.getStaffManager().isStaffMode(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if(staff.getStaffManager().isStaffMode(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHitEntity(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();
            if(staff.getStaffManager().isStaffMode(player)) {
                e.setCancelled(true);
            }
        }
     }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if(staff.getStaffManager().isStaffMode(player)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getWhoClicked() instanceof Player) {
            Player player = (Player) e.getWhoClicked();

            if(staff.getStaffManager().isStaffMode(player)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        if(staff.getStaffManager().isStaffMode(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    /*
    Staff Tools
     */

    @EventHandler
    public void onInspect(PlayerInteractEntityEvent e) {
        if(e.getRightClicked() instanceof Player) {
            Player player = e.getPlayer();
            Player target = (Player) e.getRightClicked();

            player.openInventory(target.getInventory());
        }
    }

}
