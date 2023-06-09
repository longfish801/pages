/*
 * material.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */

material {
	clmap new File(convDir, 'htmlize.tpac')
	clmap new File(convDir, 'meta.tpac')
	template 'default', new File(convDir, 'default.html')
}
