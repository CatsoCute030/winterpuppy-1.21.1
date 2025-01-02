package me.puppy;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Winterpuppy implements ModInitializer {
	public static final String MOD_ID = "winterpuppy";
	public static final String MOD_NAME = "WinterPuppy";
	public static final String VERSION = "0.1";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("I LIKE WINTER!　" + MOD_NAME + VERSION);
	}
}