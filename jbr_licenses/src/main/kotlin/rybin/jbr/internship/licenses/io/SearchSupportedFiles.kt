package rybin.jbr.internship.licenses.io

import rybin.jbr.internship.licenses.about.Languages
import java.io.File
import java.util.*
import kotlin.collections.LinkedHashSet

/*
 *  Get all not hidden directories from
 *  @startDir with depth limit equals 1.
 */
fun getNotHiddenDirsDepthOne(startDir: File, notHiddenDirs: MutableSet<File>, traverseDirsQ: Queue<File>) {
    notHiddenDirs.add(startDir)
    startDir.walk().maxDepth(1).forEach {
        if (it.isDirectory && !it.isHidden && it != startDir) {
            traverseDirsQ.add(it)
            notHiddenDirs.add(it)
        }
    }
}

/*
 *  Get all not hidden directories from
 *  @startDir without depth limit.
 */
fun getAllNotHiddenDirs(startDir: File): List<File> {
    val notHiddenDirs: MutableSet<File> = LinkedHashSet()
    val traverseDirsQ: Queue<File> = LinkedList()
    getNotHiddenDirsDepthOne(startDir, notHiddenDirs, traverseDirsQ)
    var currDir: File
    while (!traverseDirsQ.isEmpty()) {
        currDir = traverseDirsQ.poll()
        getNotHiddenDirsDepthOne(currDir, notHiddenDirs, traverseDirsQ)
    }
    return notHiddenDirs.toList()
}

/*
 *  Get all files from @dirs collection
 *  with supported language extensions.
 */
fun getFilesToSearch(dirs: Collection<File>): MutableList<File> {
    val supportedExtensions: Set<String>
            = Languages.values().flatMap { it -> it.extensions }.toSet()
    val filesToSearch: MutableList<File> = LinkedList()
    for (dir in dirs) {
        dir.walk().maxDepth(1).forEach {
            if (!it.isHidden &&
                !it.isDirectory &&
                it.extension in supportedExtensions
            ) {
                filesToSearch.add(it)
            }
        }
    }
    return filesToSearch
}
