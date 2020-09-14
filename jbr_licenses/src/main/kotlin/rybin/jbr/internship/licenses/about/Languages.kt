package rybin.jbr.internship.licenses.about

/*
 *  Information about file extensions and comments signs
 *  for IntelliJ IDEA supported languages:
 *  Java, Kotlin, Scala, Haskell, Python, Ruby, JS, GO, PHP.
 */
enum class Languages(
        val extensions: List<String>,
        val oneLine: OneLineComment,
        val multiLine: MultiLineComment
) {
    JAVA(
        listOf("java"),
            OneLineComment.C1,
            MultiLineComment.C2
    ),
    KOTLIN(
        listOf("kts", "kt"),
            OneLineComment.C1,
            MultiLineComment.C2,
    ),
    SCALA(
        listOf("scala"),
            OneLineComment.C1,
            MultiLineComment.C2,
    ),
    HASKEL(
        listOf("hs"),
            OneLineComment.C2,
            MultiLineComment.C3
    ),
    PYTHON(
        listOf("py"),
            OneLineComment.C3,
            MultiLineComment.C1
    ),
    RUBY(
        listOf("rb", "rhtml"),
            OneLineComment.C3,
            MultiLineComment.C4
    ),
    JS(
        listOf("js"),
            OneLineComment.C1,
            MultiLineComment.C2
    ),
    GO(
        listOf("go"),
            OneLineComment.C1,
            MultiLineComment.C2
    ),
    PHP(
        listOf("php", "phps3", "php4"),
            OneLineComment.C1,
            MultiLineComment.C2
    )
}
