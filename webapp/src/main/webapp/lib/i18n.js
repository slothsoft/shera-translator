// Locale: EN, default

var i18n = {
	AllSounds: "All Sounds",
	FirstOnesTranslator: "First Ones Translator",
	GitHubProject: "GitHub Project",
	Language: "Language",
	LanguageDe: "German",
	LanguageEn: "English",
	LatinToFirstOnes: "Latin → First Ones",
	PoweredBy: "Powered by",
	ReportIssue: "Report Issue",
	Settings: "Settings",
	Sounds: "Sounds",
	Words: "Words",
};

/**
 * I couldn't find a nice and easy way to get localization to work, so I'm using
 * a simple map. At least I tried to have the locales as similar as possible so
 * I can maintain them more easily.
 * 
 * Here come some util methods / variables:
 */

var locale = getBrowserLanguage();

function getBrowserLanguage() {
	if (navigator.languages && navigator.languages.length) {
		return navigator.languages[0];
	} else {
		return navigator.userLanguage || navigator.language || navigator.browserLanguage || 'en';
	}
}

function pushI18N(requestedLocale, newI18n) {
	if (locale === requestedLocale) {
		for (key in newI18n) {
			if (newI18n.hasOwnProperty(key)) {
				i18n[key] = newI18n[key];
			}
		}
	}
}
