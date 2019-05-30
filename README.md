# She-Ra Translator

[![MIT Licence](https://img.shields.io/github/license/jenkinsci/java-client-api.svg?label=License)](http://opensource.org/licenses/MIT) [![Build Status](https://travis-ci.org/slothsoft/shera-translator.svg?branch=master)](https://travis-ci.org/slothsoft/shera-translator)

- **Author:** [Stef Schulz](mailto:s.schulz@slothsoft.de)
- **Repository:** <https://github.com/slothsoft/shera-translator>
- **Open Issues:** <https://github.com/slothsoft/shera-translator/issues>
- **Web Application:** [http://app.slothsoft.de](http://app.slothsoft.de/shera/)

The new [She-Ra series on Netflix](https://www.netflix.com/title/80179762) has its own language, so naturally I'm playing around with it trying make it easier for me to write my name in this symbols. Dreamworks has a [handy guide](http://www.dreamworkstv.com/wp-content/uploads/2015/07/SheRa_FirstOnes_Language.pdf) for understanding the language.

I actually wanted to try my hands at web development for this, and it's as bad as you'd assume from a senior Java developer. Still I'm working on my skills.

The last release of the web application can be found [here](http://app.slothsoft.de/shera/).



## Language Features

The official sounds are implemented to look like this:

![all sounds](https://github.com/slothsoft/shera-translator/raw/master/readme/all-sounds.png)

There are far from perfect and only a first draft created in a couple hours.


### English

English doesn't really have rules for the pronunciation, and since my focus is the generation of the language images it'll always be necessary to write phonetically. 

### German

German fares a lot better here, since it's always pronounced the same. But it has sounds that English doesn't (and vice versa) so the symbols are lacking. I'd still love to get a second opinion on how to translate English sounds to German ones. The current attempt is located [here](https://github.com/slothsoft/shera-translator/blob/master/core/src/main/java/de/slothsoft/shera/mapper/GermanSoundMapper.java).

### Other

I don't speak any other languages so if anyone wants to create another mapping, go ahead.


## Versions

I'm self-organizing using [GitHub issues](https://github.com/slothsoft/shera-translator/issues). And since this is a project for me to learn new stuff, I try to have my work process visible.

| Version | Release | Web Link |
| ------- |---------| -------- |
| *[0.2.0](https://github.com/slothsoft/shera-translator/milestone/2?closed=1)* | &lt;WIP&gt; | [Web Link](http://app.slothsoft.de/shera-dev/)  |
| *[0.1.0](https://github.com/slothsoft/shera-translator/milestone/1?closed=1)* | [Proof of Concept](https://github.com/slothsoft/shera-translator/releases/tag/0.1.0) | [Web Link](http://app.slothsoft.de/shera-old/0.1.0/) |



## Release

The release contains the following files and folders:

- *webapp/* - all the files necessary to run this project on a web server
- *LICENSE* - the license
- *README.html* - this file as HTML
- *shera-translator.jar* - the Java application as runnable JAR (if you have Java installed)

Releasing is done with the following steps:

1. remove the snapshot from the files *pom.xml*
1. create a tag with the version as a name
1. execute `mvn clean install`
1. the finished ZIP  is *shera-release/target/shera-release-&lt;version&gt;.zip*; it's contents are in *shera-release/target/release/*
1. update the version to the next development version and commit / push all these changes
1. copy the webapp to <http://app.slothsoft.de/shera>
1. copy the ZIP to <https://github.com/slothsoft/shera-translator/releases>




## License

This project is licensed under the MIT License - see the [MIT license](https://opensource.org/licenses/MIT) for details.
