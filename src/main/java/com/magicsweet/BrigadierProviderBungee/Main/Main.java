package com.magicsweet.BrigadierProviderBungee.Main;

import com.magicsweet.BrigadierProviderBungee.Handler.BungeeCommand;
import com.mojang.brigadier.CommandDispatcher;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
	public static Plugin plugin;
	public static CommandDispatcher<CommandSender> dispatcher;
	
	
	@Override
	public void onLoad() {
		plugin = this;
	}
	
	@Override
	public void onEnable() {
		CommandDispatcher<CommandSender> dispatcher = new CommandDispatcher<>();
		Main.dispatcher = dispatcher;
		
		
	}
	
}
