package com.myclient;

import com.myclient.core.ModuleManager;
import com.mojang.logging.LogUtils;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

@Mod(MyClientMod.MOD_ID)
public final class MyClientMod {
    public static final String MOD_ID = "myclient";
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final String KEY_CATEGORY = "key.categories.myclient";
    private static final KeyMapping OPEN_MENU = new KeyMapping(
            "key.myclient.open_menu",
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            KEY_CATEGORY
    );

    private final ModuleManager moduleManager = new ModuleManager();

    public MyClientMod(FMLJavaModLoadingContext context) {
        context.getModEventBus().addListener(this::onRegisterKeyMappings);
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("MyClient initialized with {} modules", moduleManager.getModules().size());
    }

    private void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_MENU);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        while (OPEN_MENU.consumeClick()) {
            LOGGER.info("Open menu key pressed (hook your Screen implementation here)");
        }
    }
}
