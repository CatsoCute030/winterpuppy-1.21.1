package me.puppy;

import me.puppy.system.managers.ColorManager;
import me.puppy.system.managers.CommandManager;
import me.puppy.system.managers.ConfigManager;
import me.puppy.system.managers.ModuleManager;
import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class Winterpuppy implements ModInitializer {
	public static final String MOD_ID = "winterpuppy";
	public static final String MOD_NAME = "WinterPuppy";
	public static final String VERSION = "0.1";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static IEventBus Events;

	public static ModuleManager moduleManager;
	public static ConfigManager configManager;
	public static CommandManager commandManager;
	public static ColorManager colorManager;

	@Override
	public void onInitialize() {
		LOGGER.info("I LIKE WINTER!　" + MOD_NAME + VERSION);
		Events = new EventBus();
		LOGGER.info("1!　" + MOD_NAME + VERSION);
		Events.registerLambdaFactory("me.puppy", (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
		LOGGER.info("2!　" + MOD_NAME + VERSION);
		Events.subscribe(this);
		LOGGER.info("3!　" + MOD_NAME + VERSION);
		moduleManager = new ModuleManager();
		LOGGER.info("4!　" + MOD_NAME + VERSION);
		configManager = new ConfigManager();
		LOGGER.info("5!　" + MOD_NAME + VERSION);
		commandManager = new CommandManager();
		LOGGER.info("6!　" + MOD_NAME + VERSION);
		colorManager = new ColorManager();
		LOGGER.info("7!　" + MOD_NAME + VERSION);
	}

	public void load() {
		Events = new EventBus();
		Events.registerLambdaFactory("me.puppy", (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
		Events.subscribe(this);
	}
}