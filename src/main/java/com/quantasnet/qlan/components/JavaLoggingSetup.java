package com.quantasnet.qlan.components;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.slf4j.bridge.SLF4JBridgeHandler;

public class JavaLoggingSetup {
	
	private static boolean executed = false;
	private static final Object LOCK = new Object();
	
	public static void setup() {
		synchronized(LOCK) {
			if (!executed) {
				LogManager.getLogManager().reset();
				SLF4JBridgeHandler.removeHandlersForRootLogger();
				SLF4JBridgeHandler.install();
				Logger.getLogger("global").setLevel(Level.FINEST);
				executed = true;
			}
		}
	}
}
