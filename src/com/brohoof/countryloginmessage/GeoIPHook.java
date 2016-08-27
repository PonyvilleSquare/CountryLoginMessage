package com.brohoof.countryloginmessage;

import uk.org.whoami.geoip.GeoIPLookup;
import uk.org.whoami.geoip.GeoIPTools;

public class GeoIPHook
{
	private CountryLoginMessagePlugin plugin;
	private GeoIPLookup geoiplookup;

	public GeoIPHook(CountryLoginMessagePlugin countryLoginMessagePlugin)
	{
		plugin = countryLoginMessagePlugin;
		try
		{
			GeoIPTools geoipplugin = (GeoIPTools) plugin.getServer().getPluginManager().getPlugin("GeoIPTools");
			if (geoipplugin != null)
			{
				Log.log("Hooked to GeoIPTools");
				geoiplookup = geoipplugin.getGeoIPLookup();
			}
			else
			{
				Log.log("Could not hook to GeoIPTools");
				geoiplookup = null;
			}
		}
		catch (Exception e)
		{
			Log.severeLog("An error occured loading GeoIPTools.");
			e.printStackTrace();
		}
	}

	public GeoIPLookup getGeoIPLookup()
	{
		return geoiplookup;
	}
}
