/*
 * This file contains functions that are necessary for more than one other JS file. 
 * It's also kinda like a trashcan. (I'm not good at structuring web apps yet.)
 */

/**
 * Not sure if strictly necessary, but this class fills a slot on the
 * "phonetic-sound.js" template.
 * 
 * @param parentElement - the element this sloth should be added to
 * @param slotName - name of the slot
 * @param slotContent - content of the slot
 * @returns nothing
 * @since 0.1.0
 */

function createSlot(parentElement, slotName, slotContent) {
	var slotNode = document.createElement("p");
	var slotAttribute = document.createAttribute("slot");
	slotAttribute.value = slotName;
	slotNode.setAttributeNode(slotAttribute);
	slotNode.appendChild(document.createTextNode(slotContent));
	parentElement.appendChild(slotNode);
}

/**
 * Uses the CSS style to create something that looks a lot like a tab folder.
 * 
 * @param evt -
 *            onclick of a button
 * @param tabId -
 *            the ID of the tab to open
 * @returns nothing
 * @since 0.2.0
 */

function openTab(evt, tabId) {
	// remove all tabs from display
	var tabContent = document.getElementsByClassName("tab-content");
	for (var i = 0; i < tabContent.length; i++) {
		tabContent[i].style.display = "none";
	}
	// show the tab content with the specified ID
	document.getElementById(tabId).style.display = "block";

	// deactivate the button that was clicked
	var tabButtons = document.getElementsByClassName("tab-button");
	for (i = 0; i < tabButtons.length; i++) {
		tabButtons[i].className = tabButtons[i].className
				.replace(" active", "");
	}
	// activate the button of the event
	if (evt != null) {
		evt.currentTarget.className += " active";
	}
}

/**
 * Prepares the 2D context of a canvas for painting symbols. Makes the
 * background blue and the foreground white.
 * 
 * @param canvas -
 *            the canvas in question
 * @returns the context
 * @since 0.2.0
 */

function prepare2DContextOfCanvas(canvas) {
	var ctx = canvas.getContext('2d');
	ctx.fillStyle = "#26215D";
	ctx.fillRect(0, 0, canvas.width, canvas.height);
	ctx.fillStyle = "white";
	ctx.strokeStyle = "white";
	return ctx;
}
