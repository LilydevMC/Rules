package com.lilydev.example_mod

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ExampleMod : ModInitializer {

    const val MOD_ID: String = "example_mod"
    const val MOD_NAME: String = "Example Mod"

    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_NAME)

    override fun onInitialize() {
        LOGGER.info("Hello Fabric world from $MOD_NAME")
    }
}