package com.brohoof.countryloginmessage;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Settings
{
    private CountryLoginMessagePlugin plugin;
    private FileConfiguration config;
    public String leaveMessage = "";
    public String loginMessage = "&6{PLAYER} &7has joined the game from &6{COUNTRY}&7.";
    public String loginMessageFail = "&6{PLAYER} &7has joined the game.";
    public List<String> equinePlayers;

    public Settings(CountryLoginMessagePlugin pPlugin)
    {
        plugin = pPlugin;
        config = plugin.getConfig();
        config.options().copyDefaults(true);
        readSettings(config);
        plugin.saveConfig();
    }

    public void reloadSettings()
    {
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        equinePlayers.clear();
        readSettings(config);
    }

    private void readSettings(FileConfiguration pConfig)
    {
        leaveMessage = ChatColor.translateAlternateColorCodes('&', pConfig.getString("message-leave"));
        loginMessage = ChatColor.translateAlternateColorCodes('&', pConfig.getString("message"));
        loginMessageFail = ChatColor.translateAlternateColorCodes('&', pConfig.getString("message-fail"));
        equinePlayers = pConfig.getStringList("equine-players");
    }
}
