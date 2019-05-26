# She-Ra Translator (WIP)

[![MIT Licence](https://img.shields.io/github/license/jenkinsci/java-client-api.svg?label=License)](http://opensource.org/licenses/MIT) [![Build Status](https://travis-ci.org/slothsoft/shera-translator.svg?branch=master)](https://travis-ci.org/slothsoft/shera-translator)

- **Author:** [Stef Schulz](mailto:s.schulz@slothsoft.de)
- **Repository:** <https://github.com/slothsoft/shera-translator>
- **Open Issues:** <https://github.com/slothsoft/shera-translator/issues>
- **Wiki:** _<none>_

The new [She-Ra series on Netflix](https://www.netflix.com/title/80179762) has its own language, so naturally I'm playing around with it trying make it easier for me to write my name in this symbols. Dreamworks has a [handy guide](http://www.dreamworkstv.com/wp-content/uploads/2015/07/SheRa_FirstOnes_Language.pdf) for understanding the language.

I actually wanted to try my hands at web development for this, but I slipped and now it's Java again. This might change in the future. 



## Language Features

The official sounds are implemented to look like this:

![all sounds](https://github.com/slothsoft/shera-translator/master/readme/all-sounds.png)

There are far from perfect and only a first draft created in a couple hours.


### English

English doesn't really have rules for the pronunciation, and since my focus is the generation of the language images it'll always be necessary to write phonetically. 

### German

German fares a lot better here, since it's always pronounced the same. But it has sounds that English doesn't (and vice versa) so the symbols are lacking. I'd still love to get a second opinion on how to translate English sounds to German ones. The current attempt is located [here](https://github.com/slothsoft/shera-translator/blob/master/core/src/main/java/de/slothsoft/shera/mapper/GermanSoundMapper.java).

### Other

I don't speak any other languages so if anyone wants to create another mapping, go ahead.



## License

This project is licensed under the MIT License - see the [MIT license](https://opensource.org/licenses/MIT) for details.
