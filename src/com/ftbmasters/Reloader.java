package com.ftbmasters;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Reloader extends JavaPlugin {
	
	protected Plugin plugin;
	protected static PluginManager serverPM ;
	
	public void onEnable() {
		this.plugin = this;
	}

	private static void disablePlugin(CommandSender sender, Plugin targetPlugin) {
        String pluginName = targetPlugin.getDescription().getName();
        boolean error = false;

        if (!(sender.hasPermission("masters.plugin.op"))) {
            sender.sendMessage(ChatColor.RED + "Sorry, you lack sufficient privileges to perform this action.");
            return;
        }
        if (targetPlugin.isEnabled() == false) {
            sender.sendMessage(ChatColor.RED + "This plugin isn't currently enabled.");
            error = true;
        }
        
        if (!error) {
            serverPM.disablePlugin(targetPlugin);
            if (!targetPlugin.isEnabled()) {
               sender.sendMessage(ChatColor.GREEN + pluginName + " has been disabled.");
            } else {
            	sender.sendMessage(ChatColor.RED + "An unknown error occurred while disabling " + pluginName + ".");
            }
        }
	}
        

public static void reloadMasters(Player player) {
	if (!(player.hasPermission("masters.plugin.op"))) {
    	player.sendMessage(ChatColor.RED + "Sorry, you lack sufficient privileges to perform this action.");
        return;
    }
	
	serverPM = Bukkit.getServer().getPluginManager();
	
    disablePlugin(player, serverPM.getPlugin("Masters"));
    enablePlugin(player, serverPM.getPlugin("Masters"));
}

 private static void enablePlugin(CommandSender sender, Plugin targetPlugin) {
        String pluginName = targetPlugin.getDescription().getName();
        boolean error = false;

        if (!(sender.hasPermission("masters.plugin.op"))) {
        	 sender.sendMessage(ChatColor.RED + "Sorry, you lack sufficient privileges to perform this action.");
            return;
        }
        if (targetPlugin.isEnabled() == true) {
        	sender.sendMessage(ChatColor.RED + "This plugin is already enabled.");
            error = true;
        }
        if (!error) {
           
                serverPM.enablePlugin(targetPlugin);
                if (targetPlugin.isEnabled()) {
                	 sender.sendMessage(ChatColor.GREEN + pluginName + " has been enabled.");
                } else {
            	    sender.sendMessage(ChatColor.RED + "An unknown error occurred while enabling " + pluginName + ".");
		}
	}
}
}
