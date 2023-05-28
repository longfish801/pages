/*
 * convert.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */

// 変換元/変換先の相対パス
String rpath = (doc.mpath != null)? "${doc.mpath}/${doc.name}" : doc.name
// 変換元のテキストファイルを格納したフォルダ
File inputDir = new File(scriptFile.parentFile, "../draft/${rpath}")
// 変換先のHTMLファイルを格納するフォルダ
File outputDir = new File("build/docs/${rpath}")

load {
	// 変換資材を読みこみます
	material 'fyakumo', 'thtml', new File(scriptFile.parentFile, 'base')
	// design向けの変換資材があれば読みこみます
	File designMaterialDir = new File(scriptFile.parentFile, doc.design)
	if (designMaterialDir.directory) material designMaterialDir
	// 変換対象のフォルダに変換資材があれば読みこみます
	File eachMaterialDir = new File(inputDir, 'material')
	if (eachMaterialDir.directory) material eachMaterialDir
}

script {
	doFirst {
		// 入力フォルダが存在するかチェックします
		if (!inputDir.exists()){
			throw new IOException("入力フォルダが存在しません。 path=${inputDir.absolutePath}")
		}
		
		// 出力フォルダを作成しておきます
		if (!outputDir.exists() && !outputDir.mkdirs()){
			throw new IOException("出力フォルダの作成に失敗しました。 path=${outputDir.absolutePath}")
		}
	}
	
	// 変換元となる拡張子txtのファイル一覧から拡張子を取り除いて変換対象/結果キーのリストを作成します
	List keys = doc.keys
	if (keys == null){
		def scanner = new AntBuilder().fileScanner {
			fileset(dir: inputDir.path) { include(name: '*.txt') }
		}
		keys = []
		for (File file in scanner){
			keys << file.name.take(file.name.lastIndexOf('.'))
		}
	}
	
	// 補足情報を設定します
	append 'doc', doc
	
	// 拡張子txtのファイルをbltxt形式に変換するよう設定します
	targets {
		keys.each { target it, new File(inputDir, "${it}.txt") }
	}
	
	// 拡張子htmlのファイルを出力するよう設定します
	results {
		keys.each { result it, new File(outputDir, "${it}.html") }
	}
	
	doLast {
		fprint.logs.each { println it }
		assert fprint.warns.size() == 0
	}
}
