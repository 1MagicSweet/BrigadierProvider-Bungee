package com.magicsweet.BrigadierProviderBungee.Handler;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.magicsweet.BrigadierProviderBungee.Main.Main;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class BungeeCommand extends Command implements Listener {
	String command;
	String[] arguments;
	LamCommandExecutor executor;
	boolean force;
	
	public BungeeCommand(String command, boolean force) {
		super(command);
		
		this.force = force;
		
		Plugin plugin = Main.plugin;
		
		plugin.getProxy().getPluginManager().registerCommand(plugin, this);
		plugin.getProxy().getPluginManager().registerListener(plugin, this);
		Main.dispatcher.getRoot().addChild(LiteralArgumentBuilder.<CommandSender>literal(command).build());
		
		this.command = command;
	}
	
	public BungeeCommand withArguments(String... arguments) {
		this.arguments = arguments;
		
		
		for (int i = 0; i < arguments.length; i++) {
			CommandNode<CommandSender> node = Main.dispatcher.getRoot().getChild(command);
			for (int j = 0; j <= i; j++) {
				if (j != i) {
					node = node.getChild(arguments[j]);
				} else {
					node.addChild(LiteralArgumentBuilder.<CommandSender>literal(arguments[j]).build());
				}
			}
		}
		
		return this;
	}
	
	@EventHandler
	public void event(TabCompleteEvent event) throws InterruptedException, ExecutionException {

		Main.dispatcher.getCompletionSuggestions(
				Main.dispatcher.parse(event.getCursor().replaceFirst("/", ""), null)).get()
				.getList().stream()
				.map(sugg -> sugg.getText())
				.collect(Collectors.toList()
			).forEach(string -> {
				event.getSuggestions().add(string);
			});
	}
	
	public void executes(LamCommandExecutor consumer) {
		this.executor = consumer;
	}
	
	@Override
	public void execute(CommandSender arg0, String[] args) {
		if (this.force) if (!Arrays.asList(args).equals(Arrays.asList(this.arguments))) return;
		
		this.executor.run(arg0, args);
		
	}
	
}
