import kotlin.browser.document
import kotlin.browser.window
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement

fun main() {
    // Run the function after a second to ensure the page has fully loaded
    window.setTimeout({ get_sha(window.location.pathname.substring(1)) }, 1000)
}

// Get the commit sha for the given repo (given that we know it's a repo now)
fun get_sha(loc: String) {
    // Start by trying to find the url to the latest commit in the top of the page
    // We can do this by doing a search through all a tags with "Link--primary" in the class
    // Then searching for the first one that has a url matching the given regex pattern
    var urlPattern = "/$loc/commit/([a-f0-9]+)/?".toRegex()
    var primaryLinkCollection = document.getElementsByClassName("Link--primary")
    var sha = ""
    for (i in 0..primaryLinkCollection.length) {
        var element = primaryLinkCollection.item(i)
        // Skip non links
        if (element?.tagName != "A") continue

        // Compare the href to the regex and if it matches stop iterating
        var href = element.attributes.getNamedItem("href")?.value!!
        var match = urlPattern.matchEntire(href)
        if (match != null) {
            sha = match.groups.get(1)!!.value.take(6)
            break
        }
    }

    // Safety check
    if (sha == "") {
        console.log("colour-my-repo: no sha found, exiting")
        return
    }

    // Now we need to render
    render(sha)
}

// Actually handle the rendering functionality
fun render(sha: String) {
    // With the new GitHub layout, we're going to add the colour like a line under the languages section

    // There should only be one element with the BorderGrid class
    var containerCollection = document.getElementsByClassName("BorderGrid")
    if (containerCollection.length != 1) {
        console.error("colour-my-repo: Cannot find the BorderGrid element to create a new element under.")
    }
    var parent = containerCollection.item(0)!!

    // Firstly, create our progress bar element
    var progress = "<span class=\"Progress\" data-view-component=\"true\"><span class=\"Progress-item\" itemprop=\"colour\" aria-label=\"The repo colour as given by the latest commit\" style=\"background-color:#$sha;width:100%;\" data-view-component=\"true\"></span>"
    // Then create a whole new BorderGrid-Row to insert into the parent node, which contains the progress bar above
    var elementString = "<div class=\"BorderGrid-row\"><div class=\"BorderGrid-cell\"><h2 class=\"h4 mb-3\">Colour My Repo</h2><div class=\"mb-2\">$progress</div></div></div>"

    // Lastly, add our element into the parent
    parent.innerHTML += elementString
}
