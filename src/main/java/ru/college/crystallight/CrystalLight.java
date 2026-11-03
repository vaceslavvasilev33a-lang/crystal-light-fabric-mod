package ru.college.crystallight;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrystalLight implements ModInitializer {
	public static final String MOD_ID = "crystal-light";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("CrystalLight успешно инициализирован!");
		ModBlocks.initialize();

		// Регистрация дополнительного поведения (клик по блоку)
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			BlockPos pos = hitResult.getBlockPos();

			// Проверяем, что действие происходит на сервере и кликнули именно по нашему блоку
			if (!world.isClient() && world.getBlockState(pos).isOf(ModBlocks.GLOWING_CRYSTAL)) {

				// 1. Воспроизводим звук колокольчика аметиста
				world.playSound(null, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 1.0F, 1.0F);

				// 2. Создаем магические частицы вокруг блока
				if (world instanceof ServerWorld serverWorld) {
					serverWorld.spawnParticles(ParticleTypes.END_ROD,
							pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, // Координаты
							15, // Количество частиц
							0.5, 0.5, 0.5, // Разброс по осям X, Y, Z
							0.05); // Скорость
				}

				return ActionResult.SUCCESS; // Успешное взаимодействие
			}
			return ActionResult.PASS; // Для остальных блоков ничего не делаем
		});
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}