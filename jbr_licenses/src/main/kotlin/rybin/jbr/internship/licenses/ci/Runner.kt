package rybin.jbr.internship.licenses.ci

import java.io.File

import rootLicenses
import findFilesWithLicenses
import rybin.jbr.internship.licenses.about.Licenses
import rybin.jbr.internship.licenses.io.getAllNotHiddenDirs
import rybin.jbr.internship.licenses.io.getFilesToSearch

fun printLicenses(license: Licenses, files: List<File>) {
    println("${license.fullName}: ")
    println("\t${files.map{ it.name }}")
    println("------------------------------")
}

fun runLicensesSearch(args: Array<String>) {
    val rootDir = if (args.isEmpty()) {
        println("Input project directory:")
        readLine().toString()
    } else {
        args[0]
    }
    if (!File(rootDir).exists() || !File(rootDir).isDirectory) {
        println("Directory doesn't exist")
        return
    }
    var isRootEmpty = true
    val resultsRoot = rootLicenses(File(rootDir))
    for ((license, files) in resultsRoot){
        if (files.isEmpty()) {
            continue
        }
        if (isRootEmpty) {
            println("Root directory licenses:")
        }
        isRootEmpty = false
        printLicenses(license, files)
    }
    
    val goodDirs = getAllNotHiddenDirs(File(rootDir))
    val goodFiles = getFilesToSearch(goodDirs)
    val results = findFilesWithLicenses(goodFiles)

    if (!results.isEmpty()) {
        println("Headers licenses:")
    }
    for ((license, files) in results) {
        printLicenses(license, files)
    }

    if (results.isEmpty() and isRootEmpty) {
        println("Licenses are not found.")
    }
}
