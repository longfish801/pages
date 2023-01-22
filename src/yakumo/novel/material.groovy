/*
 * material.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */

material {
	clmap new File(convDir, 'htmlize.tpac')
	clmap new File(convDir, 'thtml.tpac')
	template 'default', new File(convDir, 'default.html')
	template 'index', new File(convDir, 'index.html')
}
