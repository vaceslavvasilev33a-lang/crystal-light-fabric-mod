package ru.college.crystallight;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    // Регистрируем кристалл. ID: crystal-light:glowing_crystal
    public static final Block GLOWING_CRYSTAL = registerBlock("glowing_crystal",
            new Block(AbstractBlock.Settings.create()
                    .strength(2.0f, 6.0f) // Прочность (как у стекла, но чуть крепче)
                    .sounds(BlockSoundGroup.GLASS) // Звуки стекла/кристалла
                    .luminance(state -> 15) // МАКСИМАЛЬНЫЙ УРОВЕНЬ СВЕТА
                    .nonOpaque() // Для 3D-модели, чтобы не было черных стен
            ));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(CrystalLight.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(CrystalLight.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void initialize() {
        // Добавляем во вкладку "Строительные блоки"
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(GLOWING_CRYSTAL);
        });
    }
}