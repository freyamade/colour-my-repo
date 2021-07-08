#!/bin/bash
# Build the extension

# First build the kotlin files
./gradlew runDceKotlinJs
# Then uglify them into the dist folder
npx uglifyjs --compress --mangle -o dist/content.js -- build/kotlin-js-min/main/colour-my-repo.js
npx uglifyjs --compress --mangle -o dist/kotlin.js -- build/kotlin-js-min/main/kotlin.js
