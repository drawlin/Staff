package me.drawlin.staff.managers;

import me.drawlin.staff.utility.ChatUtil;
import me.drawlin.staff.utility.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class StaffManager {

    private HashMap<UUID, ItemStack[]> inventoryContents;
    private HashMap<UUID, ItemStack[]> armorContents;
    private HashMap<UUID, Location> location;
    private HashMap<UUID, GameMode> gameMode;
    private HashSet<UUID> staffModePlayers;
    private HashSet<UUID> vanishModePlayers;

    public StaffManager() {
        this.inventoryContents = new HashMap<>();
        this.armorContents = new HashMap<>();
        this.location = new HashMap<>();
        this.gameMode = new HashMap<>();
        this.staffModePlayers = new HashSet<>();
        this.vanishModePlayers = new HashSet<>();
    }

    public void applyStaffMode(Player player) {
        getStaffModePlayers().add(player.getUniqueId());
        applyVanishMode(player);

        getInventoryContents().put(player.getUniqueId(), player.getInventory().getContents());
        getArmorContents().put(player.getUniqueId(), player.getInventory().getArmorContents());

        getLocation().put(player.getUniqueId(), player.getLocation());
        getGameMode().put(player.getUniqueId(), player.getGameMode());

        player.setGameMode(GameMode.CREATIVE);

        player.getInventory().clear();

        ItemStack teleportTool = new ItemUtil(Material.COMPASS).name(ChatUtil.color("&9Teleporter")).build();
        ItemStack handHiderTool = new ItemUtil(Material.CARPET).name(ChatUtil.color("&9Hand Hider")).build();
        ItemStack inspectTool = new ItemUtil(Material.BOOK).name(ChatUtil.color("&9Inspector")).build();
        ItemStack worldEditWand = new ItemUtil(Material.WOOD_AXE).name(ChatUtil.color("&9World Edit Wand")).build();

        player.getInventory().setItem(0, teleportTool);
        player.getInventory().setItem(1, handHiderTool);
        player.getInventory().setItem(7, worldEditWand);
        player.getInventory().setItem(8, inspectTool);

        player.sendMessage(ChatUtil.color("&eStaff Mode: &aEnabled"));

    }

    public void unapplyStaffMode(Player player) {
        getStaffModePlayers().remove(player.getUniqueId());
        unapplyVanishMode(player);

        player.setGameMode(getGameMode().get(player.getUniqueId()));

        player.teleport(getLocation().get(player.getUniqueId()));

        player.getInventory().clear();

        player.getInventory().setContents(getInventoryContents().get(player.getUniqueId()));
        player.getInventory().setArmorContents(getArmorContents().get(player.getUniqueId()));

        player.sendMessage(ChatUtil.color("&eStaff Mode: &cDisabled"));
    }

    public void applyVanishMode(Player player) {
        getVanishModePlayers().add(player.getUniqueId());

        for(Player players : Bukkit.getServer().getOnlinePlayers()) {
            if(!(players.hasPermission("staff.vanish"))) {
                players.hidePlayer(player);
            }
        }

        player.sendMessage(ChatUtil.color("&eVanish Mode: &aEnabled"));
    }

    public void unapplyVanishMode(Player player) {
        getVanishModePlayers().remove(player.getUniqueId());

        for(Player players : Bukkit.getServer().getOnlinePlayers()) {
            if(!(players.hasPermission("staff.vanish"))) {
                players.showPlayer(player);
            }
        }

        player.sendMessage(ChatUtil.color("&eVanish Mode: &cDisabled"));
    }

    public boolean isStaffMode(Player player) {
        return getStaffModePlayers().contains(player.getUniqueId());
    }

    public boolean isVanishMode(Player player) {
        return getVanishModePlayers().contains(player.getUniqueId());
    }

    public void sendMessage(String message) {
        for(Player players : Bukkit.getServer().getOnlinePlayers()) {
            if(players.hasPermission("staff.staff")) {
                players.sendMessage(ChatUtil.color(message));
            }
        }
    }

    public void clear() {
        getLocation().clear();
        getGameMode().clear();
        getInventoryContents().clear();
        getArmorContents().clear();
        getStaffModePlayers().clear();
        getVanishModePlayers().clear();
    }

    public HashMap<UUID, ItemStack[]> getInventoryContents() {
        return inventoryContents;
    }

    public HashMap<UUID, ItemStack[]> getArmorContents() {
        return armorContents;
    }

    public HashMap<UUID, Location> getLocation() {
        return location;
    }

    public HashMap<UUID, GameMode> getGameMode() {
        return gameMode;
    }

    public HashSet<UUID> getStaffModePlayers() {
        return staffModePlayers;
    }

    public HashSet<UUID> getVanishModePlayers() {
        return vanishModePlayers;
    }
}