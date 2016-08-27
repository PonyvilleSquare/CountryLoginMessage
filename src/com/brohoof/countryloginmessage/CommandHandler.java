package com.brohoof.countryloginmessage;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler
{
	private String CLM = ChatColor.WHITE + "[CountryLoginMessage]" + ChatColor.WHITE + " ";
	private String permissionDeniedMessage = CLM + ChatColor.RED + "Access denied.";
	private Settings settings;

	public CommandHandler(Settings pSettings)
	{
		settings = pSettings;
	}

	public boolean processCommand(CommandSender pSender, Command pCommand, String pLabel, String[] pArgs)
	{
		if (pCommand.getName().equalsIgnoreCase("CountryLoginMessage"))
		{
			if (pArgs.length == 0)
				return false;
			if (pArgs[0].equalsIgnoreCase("reload"))
			{
				if (pSender instanceof Player)
				{
					if (pSender.hasPermission("countryloginmessage.reload"))
					{
						settings.reloadSettings();
						pSender.sendMessage(CLM + "Reloaded settings.");
					}
					else
						pSender.sendMessage(permissionDeniedMessage);
				}
				else
				{
					settings.reloadSettings();
					Log.log("Reloaded settings.");
				}
			}
		}
		else
			return false;
		return true;
	}
}
