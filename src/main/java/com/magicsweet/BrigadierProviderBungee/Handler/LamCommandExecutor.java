package com.magicsweet.BrigadierProviderBungee.Handler;

import net.md_5.bungee.api.CommandSender;

@FunctionalInterface
public interface LamCommandExecutor {
	public void run(CommandSender sender, String[] args);
}
