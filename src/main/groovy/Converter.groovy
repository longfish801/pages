/*
 * Converter.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */

import groovyx.gpars.GParsPool
import io.github.longfish801.tpac.TpacServer
import io.github.longfish801.yakumo.Yakumo

def docs = new TpacServer()
	.soak(new File('src/yakumo/targets.tpac'))
	.getAt('tpac:targets')
	.findAll(/^doc:.+$/)
	.collect { def doc ->
		return doc
	}

GParsPool.withPool {
	docs.eachParallel { def doc ->
		println "${doc.mpath} - ${doc.name}"
		new Yakumo().run(new File("src/yakumo/convert.groovy"), [doc: doc])
	}
}
