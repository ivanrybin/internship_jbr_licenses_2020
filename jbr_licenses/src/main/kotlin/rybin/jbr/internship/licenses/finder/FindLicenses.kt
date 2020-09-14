import java.util.*
import java.io.File
import java.lang.StringBuilder

import rybin.jbr.internship.licenses.about.Languages
import rybin.jbr.internship.licenses.about.Licenses
import rybin.jbr.internship.licenses.about.MultiLineComment

import kotlin.collections.HashMap

/*
 * Find first multiline comment in file.
 * Если не использовать сторонние приложения,
 * то необходимо усложнять регулярные выражения,
 * делать их уникальными для каждого вида комментариев.
 */
fun findFirstMultiLineComment(commentType: MultiLineComment, file: File): String {
    val regexOpen = Regex("""^${Regex.escape(commentType.openSigns)}""")
    val regexClose = Regex("""${Regex.escape(commentType.closeSigns)}$""")
    var opened = false
    var last = 0
    val sb = StringBuilder()
    var matchResult: MatchResult?
    for (it in file.readLines()) {
        if (!opened) {
            matchResult = regexOpen.find(it)
            if (matchResult != null) {
                last = matchResult.range.last
                opened = true
            }
        }
        if (opened) {
            sb.append(it)
            matchResult = regexClose.find(it, last)
            if (matchResult != null) {
                break
            }
            sb.append(System.lineSeparator())
            last = 0
        }
    }
    return sb.toString()
}

/*
 * Search particular license in given text.
 */
fun findLicenseInText(license: String, text: String) : Boolean =
        text.indexOf(license) >= 0 && text.toLowerCase().indexOf("license") >= 0

/*
 * Search one of all licenses in given text.
 */
fun findLicenses(text: String): List<Licenses> {
    val result: MutableList<Licenses> = LinkedList()
    var isLicenseInText = false
    mainLoop@ for (license in Licenses.values()) {
        for (word in listOf(license.shortName, license.fullName, license.url)) {
            if (findLicenseInText(word, text)) {
                result.add(license)
                continue@mainLoop
            }
        }
        for (keyWord in license.keyWords) {
            findLicenseInText(keyWord, text)
            continue@mainLoop
        }
    }
    return result
}

/*
 * Search licenses in given files.
 */
fun findFilesWithLicenses(files: Collection<File>): MutableMap<Licenses, MutableList<File>> {
    val multiLineComments: MutableMap<String, MultiLineComment> = HashMap()
    Languages.values().forEach {
        for (ext in it.extensions) {
            multiLineComments[ext] = it.multiLine
        }
    }
    val results: MutableMap<Licenses, MutableList<File>> = HashMap()
    var licenses: List<Licenses>
    var res: String
    for (file in files) {
        multiLineComments[file.extension]?.let {
            res = findFirstMultiLineComment(it, file)
            if (res.isNotEmpty()) {
                licenses = findLicenses(res)
                if (licenses.isNotEmpty()) {
                    licenses.forEach(
                            fun(lit) {
                                if (lit !in results.keys) {
                                    results[lit] = LinkedList()
                                }
                                results[lit]?.add(file)
                            }
                    )
                }
            }
        }
    }
    return results
}

/*
 * Search the project root directory for licenses.
 */
fun rootLicenses(root: File): MutableMap<Licenses, MutableList<File>> {
    val results: MutableMap<Licenses, MutableList<File>> = HashMap()
    Licenses.values().forEach { results[it] = LinkedList() }
    var name: String
    root.walk()
            .maxDepth(1)
            .filter { it.name.toLowerCase().indexOf("license") >= 0 }
            .forEach {
                for (license in Licenses.values()) {
                    name = it.name.toLowerCase()
                    if (name.indexOf(license.shortName.toLowerCase()) >= 0) {
                        results[license]?.add(it)
                        break
                    }
                    for (keyWord in license.keyWords) {
                        if (name.indexOf(keyWord.toLowerCase()) >= 0) {
                            results[license]?.add(it)
                            break
                        }
                    }
                }
            }
    return results
}
