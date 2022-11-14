package com.lilydev.rules

import eu.pb4.placeholders.api.TextParserUtils
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
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
            dispatcher, _, _ ->
            dispatcher.register(
                literal("rules").requires { source ->
                    source.hasPermissionLevel(0)
                }.executes {
                    context ->
                    context.source.sendFeedback(TextParserUtils.formatText(ModConfig.rules), false)
                    return@executes 1
                }.then(
                    literal("reload").requires { source ->
                        source.hasPermissionLevel(3)
                    }.executes {
                        context ->
                        ModConfig.load()
                        context.source.sendFeedback(TextParserUtils.formatText("<italic><gray>Rules reloaded!"), true)
                        return@executes 1
                    }
                )
            )
        }
    }
}