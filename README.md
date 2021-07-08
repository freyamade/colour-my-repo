# colour-my-repo
A plain and simple browser extension that adds a little splash of SHA-based colour to any repo on GitHub

![example image](https://raw.githubusercontent.com/freyamade/colour-my-repo/master/img/demo.png)

*I just need some icons and stuff and then I can throw this up on the store, but it's basically done right now!*

## How it Works

When the extension detects that you are on a repository homepage, it will grab the latest commit sha from the page, and use that to generate a coloured progress bar in the section to the right (as in the above picture).

## How to Install

Currently you can clone the repo, build the JS and load it as a temp extension in your browser;

1. Clone the repo, or download the source code
2. Ensure that at the very least, a java installation is present on your computer
3. Run `npm` install to install uglifyjs, which mangles the transpiled javascript
4. Run `bash build.sh` which should have the local gradlew script compile the kotlin source and then mangle it via uglifyjs
5. Install the add on locally into your browser, by selecting the `manifest.json` file in the `dist/` directory
