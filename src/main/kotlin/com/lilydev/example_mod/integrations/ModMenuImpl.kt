package com.lilydev.example_mod.integrations

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.option.OptionsScreen

class ModMenuImpl : ModMenuApi {
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> = ConfigScreenFactory {
            parent -> OptionsScreen(parent, MinecraftClient.getInstance().options)
    }
}