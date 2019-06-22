/**
 * This control shows a single PhoneticSound. It's probably similar to the class
 * PhoneticSoundImage.
 * 
 * @see bundle.js
 * @see common.js
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
    
    initFromSoundOrdinal(soundOrdinal) {
		this.initFromSoundObject(de.slothsoft.shera.PhoneticSound["_$wrappers"][soundOrdinal]);
    }
    
    initFromSoundObject(soundObject) {
		createSlot(this, "sound-id", soundObject.getDisplayName());

	    // paint the symbols
		var border = 2;
		var nettoSymbolSize = this.symbolSize - 2 * border;

		var dc = new de.slothsoft.shera.dc.DrawingContext(new de.slothsoft.shera.dc.LogCanvas()).height(nettoSymbolSize).width(nettoSymbolSize);
		var nextDrawing = soundObject.drawOn(dc);

	    var ctx = prepare2DContextOfCanvas(this.canvas);
	    var canvas = new HtmlCanvas(ctx);
	    canvas.translate(border - nextDrawing.getStartPointX(), border + (nettoSymbolSize - nextDrawing.getStartPointY()) / 2);
		dc = new de.slothsoft.shera.dc.DrawingContext(canvas).height(nettoSymbolSize).width(nettoSymbolSize);
		soundObject.drawOn(dc);
    }
});