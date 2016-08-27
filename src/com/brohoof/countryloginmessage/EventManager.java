package com.brohoof.countryloginmessage;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import uk.org.whoami.geoip.GeoIPLookup;

public class EventManager implements Listener
{
    private Settings settings;
    private GeoIPLookup geoiplookup;

    public EventManager(GeoIPLookup pGeoIPLookup, Settings pSettings)
    {
        settings = pSettings;
        geoiplookup = pGeoIPLookup;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        String message = settings.loginMessageFail;
        if (geoiplookup != null)
        {
            String country = geoiplookup.getCountry(player.getAddress().getAddress()).getName();
            if (!country.equals("N/A"))
            {
                if (settings.equinePlayers != null)
                {
                    if (country.equals("Korea, Republic of"))
                    {
                        country = "Republic of Korea (South Korea)";
                    }
                    if (country.equals("Korea, Democratic People's Republic of"))
                    {
                        country = "Democratic People's Republic of Korea (North Korea)";
                    }
                    if (settings.equinePlayers.contains(player.getName()))
                    {
                        country = settings.loginMessage.replace("{COUNTRY}", "Equestria");
                    }
                    country = settings.loginMessage.replace("{COUNTRY}", country);
                }
            }
        }
        event.setJoinMessage(message.replace("{PLAYER}", player.getDisplayName()));
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        if (settings.leaveMessage.isEmpty())
        {
            return;
        }
        event.setQuitMessage(settings.leaveMessage.replace("{PLAYER}", event.getPlayer().getDisplayName()));
    }
}
