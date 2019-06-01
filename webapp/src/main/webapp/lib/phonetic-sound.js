/**
 * This control shows a single PhoneticSound. It's probably similar to the class
 * PhoneticSoundImage.
 */

// template

document.body.innerHTML +=
'<template id="phonetic-sound-template"">' + 
'	<div>' +
'		<canvas id="phonetic-sound-canvas" width="1" height="1"></canvas>' +
'		<p>' +
'			<slot name="sound-id">X</slot>' +
'		</p>' +
'	</div>' +
'</template>';

// logic

customElements.define('phonetic-sound',
  class extends HTMLElement {
    constructor() {
      super();
      
      this.symbolSize = 75;

      const template = document.getElementById('phonetic-sound-template');
      const templateContent = template.content;

      this.shadow = this.attachShadow({mode: 'open'});

      const style = document.createElement('style');
      
      // visual representation
      style.textContent = `
        * { margin: 0; padding: 0; }
        div { padding: 5px; border: 1px solid gray; width: `+this.symbolSize+`px; margin: 5px; float: left;}
        canvas { border:1px solid #26215D; width: `+this.symbolSize+`px; height: `+this.symbolSize+`px; margin: auto;}
        p { text-align: center;}
      `;

      this.shadow.appendChild(style);
      this.shadow.appendChild(templateContent.cloneNode(true));
      
      this.canvas = this.shadow.getElementById("phonetic-sound-canvas");
      this.canvas.width = this.symbolSize;
      this.canvas.height = this.symbolSize;
    }
    
    initFromSound(soundOrdinal) {
    	var soundObject = de.slothsoft.shera.PhoneticSound["_$wrappers"][soundOrdinal];
		createSlot(this, "sound-id", soundObject.getDisplayName());
		
	    // prepare the canvas
	    var ctx = this.canvas.getContext('2d');
	    ctx.fillStyle = "#26215D";
    	ctx.fillRect(0, 0, this.symbolSize, this.symbolSize);
	    ctx.fillStyle = "white";
	    ctx.strokeStyle = "white";
	    
	    // paint the symbols
		var dc = new de.slothsoft.shera.dc.DrawingContext(new HtmlCanvas(ctx)).height(this.symbolSize).width(this.symbolSize);
		soundObject.drawOn(dc);
    }
});