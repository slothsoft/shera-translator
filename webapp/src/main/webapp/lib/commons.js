
function createSlot(parentElement, slotName, slotContent) {
	var slotNode = document.createElement("p");
	var slotAttribute = document.createAttribute("slot");
	slotAttribute.value = slotName;     
	slotNode.setAttributeNode(slotAttribute);  
	slotNode.appendChild(document.createTextNode(slotContent));                   
	parentElement.appendChild(slotNode);
}