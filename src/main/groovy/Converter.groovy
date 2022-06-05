/*
 * Converter.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */

import io.github.longfish801.tpac.TpacServer
import io.github.longfish801.yakumo.Yakumo

new TpacServer().soak(new File('src/yakumo/targets.tpac')).getAt('tpac:targets').findAll(/^year:.+$/).each { def year ->
	year.findAll(/^doc:.+$/).each { def doc ->
		new Yakumo().run(new File("src/yakumo/script/${doc.script}/convert.groovy"), [rpath: "${year.name}/${doc.name}"])
	}
}
