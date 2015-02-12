/*
* This file is part of Sponge, licensed under the MIT License (MIT).
*
* Copyright (c) SpongePowered.org <http://www.spongepowered.org>
* Copyright (c) contributors
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

package org.spongepowered.cookbook.plugin.commands;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.event.state.InitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.util.event.Subscribe;

import com.google.inject.Inject;

import org.spongepowered.cookbook.plugin.commands.CommandHandler;

@Plugin(id = Commands.NAME, name = "Commands", version = "0.1")
public class Commands {
	public static final String NAME = "Commands";
	private Game game;
	private Optional<Server> server;
	private Optional<PluginContainer> pluginContainer;
	private static Logger logger;
	private Commandhandler cmdHandler;

	public Commands() {
	}
	
	public static Logger getLogger() {
		return logger;
	}
	
  // This is the appropriate place to perform command initialization. 
	// At this stage, the server is preparing to load the world, and most things we might need
	// access to is available. As this is only called once in the lifetime of the server, this
	// prevents us from running into situations where a command might be registered twice.
	@Subscribe
	public void onInitialization(InitializationEvent event) {
		// These variables are often initialized in the PreInitialization phase. In this example,
		// we initialize them here for brevity.
		this.game = event.getGame();		
		this.server = event.getGame().getServer();
		pluginContainer = game.getPluginManager().getPlugin(Commands.NAME);
		this.logger=game.getPluginManager().getLogger(pluginContainer.get());

		getLogger().info("[Commands]: In InitializationEvent.");
		// Get our instance of CommandDispatcher, and assign our callable command handler to a
		// variable. Then register our command with the dispatcher.
		CommandService cmdService = game.getCommandDispatcher();
		this.cmdHandler = new Commandhandler();
		getLogger().info("[Commands]: Calling register for command.");
		// This command registers the command (and its aliases, if any). You can pass the aliases
		// as either a list, or as individual strings. The first alias passed becomes the main
		// command name.
		cmdService.register(plugin,cmdHandler,"helloworld","hithere");
	}
}