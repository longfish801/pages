/*
 * convert.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */

// 変換元のテキストファイルを格納したフォルダ
File inputDir = new File(scriptFile.parentFile, "../../draft/${rpath}")
// 変換先のHTMLファイルを格納するフォルダ
File outputDir = new File("build/docs/${rpath}")

// 出力フォルダを作成しておきます
if (!outputDir.exists() && !outputDir.mkdirs()){
	throw new IOException("Failed to mkdir: path=${outputDir.absolutePath}")
}

load {
	material 'fyakumo', 'thtml', scriptFile.parentFile, new File(inputDir, 'material')
}

script {
	// 変換元となる拡張子txtのファイル一覧を作成します
	def scanner = new AntBuilder().fileScanner {
		fileset(dir: inputDir.path) { include(name: '*.txt') }
	}
	
	// 拡張子txtのファイルをbltxt形式に変換するよう設定します
	targets {
		for (File file in scanner){
			target file.name.take(file.name.lastIndexOf('.')), file
		}
	}
	
	// 拡張子txtのファイルと一対一で拡張子htmlのファイルを出力するよう設定します
	results {
		for (File file in scanner){
			result file.name.take(file.name.lastIndexOf('.')), new File(outputDir, file.name.replaceFirst(/\.txt$/, /.html/))
		}
	}
	
	doLast {
		fprint.logs.each { println it }
		assert fprint.warns.size() == 0
	}
}
