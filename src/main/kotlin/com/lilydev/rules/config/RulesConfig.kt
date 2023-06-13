package com.lilydev.rules.config

import com.lilydev.lilylib.config.JsonConfig

open class RulesConfig(modName: String, path: String, fileName: String) : JsonConfig(modName, path, fileName) {
	override fun generateJsonMap(): MutableMap<String, Any> {
		val configMap: MutableMap<String, Any> = HashMap()
		val rule: MutableMap<String, Any> = HashMap()

		configMap["rule_schema"] = "%rule_description%"
		configMap["rules_header"] = ""


		rule["title"] = ""
		rule["description"] = "This server does not have a configured rules.json file yet!\n<gray>" +
				"Learn how to create one <underline><blue><url:'https://github.com/Lilydev-by-jade/Rules'>" +
				"here</url></blue></underline>.</gray>"

		configMap["rules"] = arrayListOf(rule)

		return configMap
	}
}
