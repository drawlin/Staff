package me.drawlin.staff;

import me.drawlin.staff.commands.ReportCommand;
import me.drawlin.staff.commands.RequestCommand;
import me.drawlin.staff.commands.StaffModeCommand;
import me.drawlin.staff.commands.VanishCommand;
import me.drawlin.staff.listeners.JoinListener;
import me.drawlin.staff.listeners.KickListener;
import me.drawlin.staff.listeners.QuitListener;
import me.drawlin.staff.listeners.StaffModeListener;
import me.drawlin.staff.managers.StaffManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Staff extends JavaPlugin {

    private StaffManager staffManager;

    @Override
    public void onEnable() {
        registerManagers();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        getStaffManager().clear();
    }

    private void registerManagers() {
        staffManager = new StaffManager();
    }

    private void registerCommands() {
        new StaffModeCommand(this);
        new VanishCommand(this);
        new ReportCommand(this);
        new RequestCommand(this);
    }

    private void registerListeners() {
        new JoinListener(this);
        new KickListener(this);
        new QuitListener(this);
        new StaffModeListener(this);
    }

    public StaffManager getStaffManager() {
        return staffManager;
    }
}
