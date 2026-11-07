package com.example.actionpack;

import java.util.function.Function;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class ModItems {
    public static final Item DASH_BLADE = register("dash_blade", DashBladeItem::new,
            new Item.Properties().sword(ToolMaterial.NETHERITE, 3.0f, -2.4f));

    public static final Item THUNDER_STAFF = register("thunder_staff", ThunderStaffItem::new,
            new Item.Properties().stacksTo(1));

    private static <T extends Item> T register(String name, Function<Item.Properties, T> factory, Item.Properties props) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(ActionPack.MOD_ID, name));
        T item = factory.apply(props.setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(tab -> {
            tab.accept(DASH_BLADE);
            tab.accept(THUNDER_STAFF);
        });
    }
}
