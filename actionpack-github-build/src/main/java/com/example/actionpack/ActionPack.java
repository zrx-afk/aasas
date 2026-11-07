package com.example.actionpack;

import net.fabricmc.api.ModInitializer;

public class ActionPack implements ModInitializer {
    public static final String MOD_ID = "actionpack";

    @Override
    public void onInitialize() {
        ModItems.initialize();
    }
}
