import kotlin.browser.document
import kotlin.browser.window
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement

fun main() {
    // Run the function after a second to ensure the page has fully loaded
    var location = window.location.pathname.substring(1).split("/")
    if (location.size == 2) {
        window.setTimeout(
            ::render,
            1000
        )
    }
}

// Actually handle the rendering functionality
fun render() {
    // Try to find the tease sha
    var commitTeaseShaCollection = document.getElementsByClassName("commit-tease-sha")
    if (commitTeaseShaCollection.length == 1) {
        // We've found the tease sha element, now get the first 6 characters of it
        var commitTeaseShaElement = commitTeaseShaCollection.item(0) as HTMLElement
        var colour = commitTeaseShaElement.innerText.substring(0, 6)

        // Attempt to find the set of social buttons at the top right
        var buttonContainerCollection = document.getElementsByClassName("pagehead-actions")
        if (buttonContainerCollection.length == 1) {
            // Create our colour button and add it to the existing list
            var elementString = "<li><p class=\"btn btn-sm btn-with-count\">Colour</p><p class=\"social-count js-social-count\" style=\"background-color:#$colour !important;\">&nbsp;</p></li>"
            var buttonContainer = buttonContainerCollection.item(0) as Element
            buttonContainer.innerHTML = elementString + buttonContainer.innerHTML
        }
    }
}
