Command-line text search
===

Command line tool to recursively search all text files in a given path for matching phrases 

## Getting Started

The project is build with Gradle so Java is required



- Building the project

`./gradlew clean installDist`

- Running the application


`./build/install/text-search/bin/text-search <path_to_index>`

## Using the seach command line

The command line interface takes a search term in the form of a set of space-separated words and returns at most 
ten files that has the highest matching rank.

The matching rank is caculated as the percentage of the number of searched word(s) appearing in the file over 
the total number of words in the search term
- A complete match of 100% means all words in the search term appear in the file
- A no-match of 0% means no words appear in the file

Punctuations are not considered part of any word.

Type `:q` to quit

### Examples

Give a file with the following content

```
one line
two line
three line
a line with a comma, a 'quote' and a dot.
Here are multi  spaces and a    tab
```
`search> line one` return 100%`

`search> two four` return 50%

`search> a comma` return 100%

`search> a tab` return 100%

`search> multi spaces` return 100%
