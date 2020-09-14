package rybin.jbr.internship.licenses.about

/*
 *  Open and close comments chars.
 */
enum class OneLineComment(val openSigns: String) {
    C1("//"),  // java, kotlin, scala, php, go, js
    C2("--"),  // haskell
    C3("#"),   // python, ruby
}

enum class MultiLineComment(val openSigns: String, val closeSigns: String) {
    C1("\"\"\"", "\"\"\""), // python
    C2("/*", "*/"),         // java, kotlin, scala, php, go, js
    C3("{-", "-}"),         // haskell
    C4("=open", "=end")     // ruby
}
