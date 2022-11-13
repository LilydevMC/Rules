package com.lilydev.rules

import eu.pb4.placeholders.TextParser
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import net.minecraft.server.command.CommandManager.*


object RulesServer : DedicatedServerModInitializer {

    const val MOD_ID: String = "rules"
    const val MOD_NAME: String = "Rules"

    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_NAME)

    override fun onInitializeServer() {

        ModConfig.load()

        CommandRegistrationCallback.EVENT.register {
            dispatcher, registryAccess ->
            dispatcher.register(
                literal("rules").executes {
                    context ->
                    context.source.sendFeedback(TextParser.parse(ModConfig.rules), false)
                    return@executes 1
                }.then(
                    literal("reload").executes {
                        context ->
                        ModConfig.load()
                        context.source.sendFeedback(TextParser.parse("<italic><gray>Rules reloaded!"), true)
                        return@executes 1
                    }
                )
            )
        }
    }
}