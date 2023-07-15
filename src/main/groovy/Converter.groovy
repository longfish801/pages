/*
 * Converter.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */

import groovyx.gpars.GParsPool
import io.github.longfish801.tpac.TpacServer
import io.github.longfish801.yakumo.Yakumo
import org.slf4j.LoggerFactory

def LOG = LoggerFactory.getLogger('converter.pages')

// 例外のエラーメッセージをその原因となった例外についても再帰的に表示します
Closure printErrorStack
printErrorStack = { Exception exc, int level ->
	if (exc.message == null){
		if (exc.cause != null) printErrorStack(exc.cause, level)
	} else {
		println "${'  ' * level}${exc.message}"
		if (exc.cause != null) printErrorStack(exc.cause, level + 1)
	}
}

// 変換対象を設定ファイルから読みこみます
def docs = new TpacServer()
	.soak(new File('src/yakumo/targets.tpac'))
	.getAt('tpac:targets')
	.findAll(/^doc:.+$/)
	.collect { def doc ->
		return doc
	}

// 変換対象について変換を並列実行します
GParsPool.withPool {
	docs.eachParallel { def doc -> 
		try {
			println "${doc.mpath} - ${doc.name}"
			LOG.info('BGN convert {} - {}', doc.mpath, doc.name)
			new Yakumo().run(new File("src/yakumo/convert.groovy"), [doc: doc])
			LOG.info('END convert {} - {}', doc.mpath, doc.name)
		} catch (exc){
			LOG.error('Failed to convert {} - {}', doc.mpath, doc.name, exc)
			println "Failed to convert ${doc.mpath} - ${doc.name}"
			printErrorStack(exc, 1)
			throw new Exception("Failed to convert ${doc.mpath} - ${doc.name}")
		}
	}
}
