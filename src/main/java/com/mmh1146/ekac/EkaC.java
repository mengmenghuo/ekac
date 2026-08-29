package com.mmh1146.ekac;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EkaC implements ModInitializer {
	public static final String MOD_ID = "ekac";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Identifier EKAC_ID = Identifier.fromNamespaceAndPath(MOD_ID, "ekac");

	public static final Block EKAC_BLOCK = new EkaCBlock(
		BlockBehaviour.Properties.of()
			.noOcclusion()
			.strength(0.5F)
			.sound(SoundType.WOOL)
			.setId(ResourceKey.create(Registries.BLOCK, EKAC_ID))
	);

	public static final Item EKAC_ITEM = new BlockItem(
		EKAC_BLOCK,
		new Item.Properties().useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, EKAC_ID))
	);

	@Override
	public void onInitialize() {
		Registry.register(BuiltInRegistries.BLOCK, EKAC_ID, EKAC_BLOCK);
		Registry.register(BuiltInRegistries.ITEM, EKAC_ID, EKAC_ITEM);

		CreativeModeTabEvents.modifyOutputEvent(
			ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.withDefaultNamespace("food_and_drinks"))
		).register(output -> output.insertAfter(Items.CAKE, EKAC_ITEM));

		LOGGER.info("EkaC mod initialized!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
