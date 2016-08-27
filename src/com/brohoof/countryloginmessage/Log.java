package com.brohoof.countryloginmessage;

import org.bukkit.plugin.Plugin;

public class Log
{
	// Control variables
	private static Log instance = null;

	private Plugin plugin = null;

	/* Initialise the log */
	public Log(Plugin pPlugin)
	{
		instance = this;
		plugin = pPlugin;
	}

	/** Logs a message to the console */
	public static void log(String pMessage)
	{
		if (instance != null && instance.plugin != null)
			instance.plugin.getLogger().info(pMessage);
	}

	/** Sends a warning to the console */
	public static void warnLog(String pMessage)
	{
		if (instance != null && instance.plugin != null)
			instance.plugin.getLogger().warning(pMessage);
	}

	public static void severeLog(String pMessage)
	{
		if (instance != null && instance.plugin != null)
			instance.plugin.getLogger().severe(pMessage);
	}
}
