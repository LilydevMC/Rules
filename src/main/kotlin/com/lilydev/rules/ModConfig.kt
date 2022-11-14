package com.lilydev.rules

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File
import java.io.FileNotFoundException

@Serializable
data class Config(
    @SerialName("rules_header")
    val rulesHeader: String?,
    @SerialName("rule_schema")
    val rulesSchema: String,
    @SerialName("rules")
    val rules: List<Rule>
)
@Serializable
data class Rule(
    val title: String,
    val description: String
)

class ModConfig {
    companion object {
        private var config: Config? = null
        var rules: String? = null

        fun load() {
            val fileContents: String
            try {
                fileContents = readFile()
            } catch (e: FileNotFoundException) {
                createFile()
                load()
                return
            }
            config = Json.decodeFromString<Config>(fileContents)
            rules = formatRules()
        }

        private fun readFile(): String {
            return File("config/rules.json").readText()
        }

        private fun formatRules(): String {
            var rulesString = ""

            if (config?.rulesHeader != "" && config?.rulesHeader != null) {
                rulesString += config?.rulesHeader + "\n"
            }

            for ((index, rule) in config?.rules!!.withIndex()) {
                val schema = config?.rulesSchema
                rulesString += schema
                    ?.replace("${'$'}{rule_number}", (index + 1).toString())
                    ?.replace("${'$'}{rule_title}", rule.title)
                    ?.replace("${'$'}{rule_description}", rule.description)
                if (index != config?.rules!!.size - 1) {
                    rulesString += "\n"
                }
            }

            return rulesString
        }

        private fun createFile() {
            val jsonFormat = Json { prettyPrint = true }

            val json = Config(
                "",
                "\${rule_description}",
                listOf(Rule("Rule Title",
                    "This server does not have a configured rules.json file yet!\n" +
                            "<gray>Learn how to create one " +
                            "<underline><blue><url:'https://docs.lilydev.com/docs/Mods/Rules'>here</url></blue></underline>.</gray>"
                ))
            )

            val jsonAsString = jsonFormat.encodeToString(json)
            val configFile = File("config/rules.json")

            configFile.createNewFile()
            configFile.writeText(jsonAsString)
        }
    }
}
