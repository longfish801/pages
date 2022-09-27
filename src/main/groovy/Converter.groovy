/*
 * Converter.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */

import io.github.longfish801.tpac.TpacServer
import io.github.longfish801.yakumo.Yakumo

def targets = new TpacServer().soak(new File('src/yakumo/targets.tpac')).getAt('tpac:targets')
targets.findAll(/^year:.+$/).each { def year ->
	println "YEAR: ${year.name}"
	year.findAll(/^doc:.+$/).each { def doc ->
		println "DOC: ${doc.name}"
		Map vars = [
			rpath: "${year.name}/${doc.name}",
			order: doc.order
		]
		new Yakumo().run(new File("src/yakumo/${doc.script}/convert.groovy"), vars)
	}
}
