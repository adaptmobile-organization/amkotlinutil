#!/bin/bash

find amkotlinutil -name "*.md" -type f > test.md

awk -F ":" '{ print "* [" $1 "](../../../" tolower($1) ")" }' test.md > test_new.md && mv test_new.md test.md

sed "s/\(\[amkotlinutil\/dk.adaptmobile.amkotlinutil.\)/\[/g" test.md > /tmp/test_replace.md

sed "s/\(\[amkotlinutil\/\)/\[/g" /tmp/test_replace.md > /tmp/test_replace_final.md

mv /tmp/test_replace_final.md test.md

mv test.md _sidebar.md

cd ..

# docsify serve docs



