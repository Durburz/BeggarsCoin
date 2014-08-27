package eu.valkyr.BeggarsCoin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BeggarsCoin extends JavaPlugin{
	
	ItemStack coin = new ItemStack(Material.GOLD_NUGGET, 1);

    public void onEnable(){ 
    	this.getLogger().info("BeggarsCoin has been enabled.");
    }
     
    public void onDisable(){ 
    	this.getLogger().info("BeggarsCoin has been disabled.");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	Player player = null;
    	if (sender instanceof Player) {
    		player = (Player) sender;
    	}
     
    	if (cmd.getName().equalsIgnoreCase("coin")) {
    		if (player == null) {
    			sender.sendMessage(ChatColor.RED + "Dieser Befehl kann nur von einem Spieler ausgeführt werden!");
    			return false;
    		} else {
    			Player other = (Bukkit.getServer().getPlayer(args[0]));
    	        if (other == null) {
    	           sender.sendMessage(ChatColor.RED + "Es ist kein Spieler mit den Namen " + args[0] + " online!");
    	           return false;
    	        }
    	        else {
    	        	if (!player.getInventory().removeItem(coin).isEmpty()) {
    	        		sender.sendMessage(ChatColor.RED + "Du hast keinen Goldnugget im Inventar!");
    	        		return false;
    	        	}
    	        	
    	        	other.getInventory().addItem(coin);
    	        	if (player.equals(other)) {
    	        		player.setHealth(0);
    	        		Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getDisplayName() + ChatColor.GOLD + " starb bei dem Versuch, sich selbst mit einer Münze abzuwerfen!" );
    	        		return true;
    	        	}
    	        	Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getDisplayName() + ChatColor.GOLD + " hat " + other.getDisplayName() + ChatColor.GOLD + " eine Goldmünze hingeworfen!");
    	        }
    		}
    		return true;
    	}
    	return false;
    }


	
}
