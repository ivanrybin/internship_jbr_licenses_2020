package rybin.jbr.internship.licenses.about

/*
 *  Information about licenses names, search keywords and urls.
 */
enum class Licenses(
        val shortName: String,
        val fullName: String,
        val url: String,
        val keyWords: List<String>
) {
    APACHE(
        "Apache",
        "Apache License 2.0",
        "http://www.apache.org/licenses/LICENSE-2.0",
        listOf("Apache-2.0")
    ),
    GPL(
        "GPL",
        "GNU General Public License v3.0",
        "http://www.gnu.org/licenses/gpl-3.0",
        listOf("GPL-3.0-only", "GPL-3.0-or-later")
    ),
    MIT(
        "MIT",
        "MIT License",
        "http://opensource.org/licenses/MIT-LICENSE",
        listOf()
    ),
    BSD(
        "BSD",
        "BSD 3-Clause \"New\" or \"Revised\" License",
        "https://opensource.org/licenses/BSD-3-Clause",
        listOf("BSD-3-Clause"),
    ),
    LGPL(
        "LGPL",
        "GNU Lesser General Public License v3.0",
        "https://www.gnu.org/licenses/lgpl-3.0",
        listOf("LGPL-3.0")
    )
}
