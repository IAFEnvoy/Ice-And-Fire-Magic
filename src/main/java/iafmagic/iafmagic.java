package iafmagic;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import iafmagic.entity.registry;
import iafmagic.registry.items;
import iafmagic.registry.ores;
import net.fabricmc.api.ModInitializer;

public class iafmagic implements ModInitializer {
  public static Logger LOGGER = LogManager.getLogger();

  public static final String MOD_ID = "iafmagic";
  public static final String MOD_NAME = "Ice And Fire : Magic";

  @Deprecated
  @Override
  public void onInitialize() {
    log(Level.INFO, "Initializing...");
    items.inititems();
    registry.init();
  }

  public static void log(Level level, String message) {
    LOGGER.log(level, "[" + MOD_NAME + "] " + message);
  }
}
