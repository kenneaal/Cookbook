package org.spongepowered.cookbook.plugin.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.spongepowered.api.Server;
import org.spongepowered.api.text.message.Messages;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandSource;

import com.google.common.base.Optional;

public class Commands implements CommandCallable{
	private Optional<Server> server;
	private final Optional<String> desc = 
	  Optional.of("A simple test command that outputs hello world as a server broadcast.");
	private final Optional<String> help = 
	  Optional.of("A simple test command that outputs hello world as a server broadcast.");
	
	public Commands(){
		this.server = SpongePlots.getGame().getServer();
	}

	public List<String> getSuggestions(CommandSource source, String arguments)
			throws CommandException {
		return Arrays.asList("foo","bar","baz");
	}
  // This is where the actual command processing happens. Although the command accepts
  // arguments, we don't do anything with them here.
	public boolean call(CommandSource source, String arguments,
			List<String> parents) throws CommandException {
		server.get().broadcastMessage(Messages.of("Hello world!"));
		return false;
	}

	public boolean testPermission(CommandSource source) {
		return source.hasPermission("cookbook.Command");
	}

	public Optional<String> getShortDescription() {
		return desc;
	}

	public Optional<String> getHelp() {
		return help;
	}

	public String getUsage() {
		return "/<command> <message>";
	}
}
