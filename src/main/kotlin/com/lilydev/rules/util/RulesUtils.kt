package com.lilydev.rules.util

import com.lilydev.lilylib.util.LilyParsing


class RulesUtils {
	companion object {
		fun generateRulesString(dataMap: MutableMap<String, Any>): String {
			var rulesString = ""

			val header = dataMap["rules_header"]
			val schema = dataMap["rule_schema"].toString()

			val rulesArray = dataMap["rules"] as List<Map<String, String>>


			if (header != "" && header != null) {
				rulesString = rulesString + header + "\n"
			}

			for ((index, rule) in rulesArray.withIndex()) {
				val ruleTitle: String = rule["title"].toString()
				val ruleDescription: String = rule["description"].toString()

				rulesString += parseRule(schema, index + 1, ruleTitle, ruleDescription)

				if (index != rulesArray.size - 1) {
					rulesString += "\n"
				}
			}

			return rulesString
		}

		private fun parseRule(line: String, ruleNumber: Int, ruleTitle: String, ruleDescription: String): String {
			var rule = LilyParsing.parseStringWithVariable(line, "rule_number", ruleNumber.toString())
			rule = LilyParsing.parseStringWithVariable(rule, "rule_title", ruleTitle)
			rule = LilyParsing.parseStringWithVariable(rule, "rule_description", ruleDescription)
			return rule
		}
	}

}