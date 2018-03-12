package me.drawlin.staff.utility;

import org.bukkit.ChatColor;

public class ChatUtil {

    /*
    Easier Color Codes
     */
    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
