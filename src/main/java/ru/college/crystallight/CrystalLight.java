package ru.college.crystallight;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier; // Правильный импорт для Fabric
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrystalLight implements ModInitializer {
	public static final String MOD_ID = "crystal-light";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Crystal Light mod initialized!");

		// Инициализация наших блоков
		ModBlocks.initialize();
	}

	// Правильный метод создания ID
	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}