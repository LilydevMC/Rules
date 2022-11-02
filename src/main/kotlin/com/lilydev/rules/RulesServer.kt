package com.lilydev.rules

import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import net.minecraft.server.command.CommandManager.*
import net.minecraft.text.LiteralText


object RulesServer : DedicatedServerModInitializer {

    const val MOD_ID: String = "rules"
    const val MOD_NAME: String = "Rules"

    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_NAME)

    override fun onInitializeServer() {
        CommandRegistrationCallback.EVENT.register {
            dispatcher, registryAccess ->
            dispatcher.register(
                literal("rules").executes {
                    context ->
                    context.source.sendFeedback(LiteralText("rules!"), false)
                    return@executes 1
                }
            )
        }
    }
}