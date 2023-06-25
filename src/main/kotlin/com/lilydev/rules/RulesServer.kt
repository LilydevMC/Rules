package com.lilydev.rules

import com.lilydev.rules.config.RulesConfig
import com.lilydev.rules.util.RulesUtils
import com.mojang.brigadier.Command
import eu.pb4.placeholders.api.TextParserUtils
import net.fabricmc.api.DedicatedServerModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import me.lucko.fabric.api.permissions.v0.Permissions
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager.literal


object RulesServer : DedicatedServerModInitializer {

    const val MOD_ID: String = "rules"
    const val MOD_NAME: String = "Rules"

    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_NAME)

    override fun onInitializeServer() {

        val config = RulesConfig(MOD_NAME, "config", MOD_ID)

        config.init()

        CommandRegistrationCallback.EVENT.register {
            dispatcher, _, _ ->
            dispatcher.register(
                literal("rules")
                .requires(Permissions.require("rules.command.rules", 0))
                .executes { ctx ->
                    ctx.source.sendFeedback(
                        TextParserUtils.formatText(
                            RulesUtils.generateRulesString(config.data)
                        ),
                        false
                    )
                    return@executes Command.SINGLE_SUCCESS
                }.then(
                    literal("reload")
                        .requires(Permissions.require("rules.command.rules.reload", 3))
                        .executes {
                            config.load()
                            return@executes Command.SINGLE_SUCCESS
                        }
                )
            )
        }
    }
}