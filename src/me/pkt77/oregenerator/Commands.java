package me.pkt77.oregenerator;

import cn.nukkit.utils.TextFormat;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.Player;

public class Commands implements CommandExecutor {
	private OreGenerator _og;

	public Commands(OreGenerator og) {
		_og = og;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("og")) {
			if (args.length == 0) {
				if (sender.hasPermission("og.reload")) {
					sender.sendMessage(TextFormat.GREEN + "===== OreGenerator Commands ===== \n" + "/og reload   Reloads the config file.");
				} else {
					sender.sendMessage(TextFormat.RED + "You do not have permission to use this command");
				}
				return true;
			} else {
				if (args[0].equalsIgnoreCase("reload")) {
					Player player = null;
					if (sender instanceof Player) {
						player = (Player) sender;
						if (player.hasPermission("og.reload")) {
							_og.reloadConfig();
							sender.sendMessage(TextFormat.GREEN + "OreGenerator config file reloaded.");
						} else {
							sender.sendMessage(TextFormat.RED + "You do not have permission to use this command");
						}
					} else {
						_og.reloadConfig();
						_og.log.info("OreGenerator config file reloaded.");
					}
				}
			}
		}
		return true;
	}
}
