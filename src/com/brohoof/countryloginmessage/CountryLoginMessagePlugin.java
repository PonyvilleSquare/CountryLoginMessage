package com.brohoof.countryloginmessage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import uk.org.whoami.geoip.GeoIPLookup;

public class CountryLoginMessagePlugin extends JavaPlugin 
{
	private GeoIPLookup geoiplookup;
	private Settings settings;
	private CommandHandler commandHandler;

	@Override
    public void onEnable()
	{
		settings = new Settings(this);
		commandHandler = new CommandHandler(settings);
		new Log(this);
		geoiplookup = (new GeoIPHook(this)).getGeoIPLookup();
		commandHandler = new CommandHandler(settings);
		getServer().getPluginManager().registerEvents(new EventManager(geoiplookup, settings), this);
	}

	@Override
	public boolean onCommand(CommandSender pSender, Command pCommand, String pLabel, String[] pArgs)
	{
		return commandHandler.processCommand(pSender, pCommand, pLabel, pArgs);
	}
}
