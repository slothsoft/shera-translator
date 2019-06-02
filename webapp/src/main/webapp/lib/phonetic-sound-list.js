/**
 * This control shows a list of PhoneticSound. It's similar to the class
 * SingleSoundsControl.
 * 
 * @see bundle.js
 * @see common.js
 * @see phonetic-sound.js
 */

customElements.define('phonetic-sound-list',
  class extends HTMLElement {
    constructor() {
        super();
    } 
       
    initFromSoundOrdinals(sounds) {
		this.clearChildren();
		
		// create elements
		var parent = this;
		sounds.forEach(function(element) {
			var soundTemplate = document.createElement("phonetic-sound");
		 	soundTemplate.initFromSoundOrdinal(element);
		 	parent.appendChild(soundTemplate);
		});
    }
    
    initFromSoundObjects(sounds) {
		this.clearChildren();
		
		// create elements
		var parent = this;
		sounds.forEach(function(element) {
			var soundTemplate = document.createElement("phonetic-sound");
		 	soundTemplate.initFromSoundObject(element);
		 	parent.appendChild(soundTemplate);
		});
    }
    
    clearChildren() {
		while (this.firstChild) {
			this.removeChild(this.firstChild);
		}
    }
});