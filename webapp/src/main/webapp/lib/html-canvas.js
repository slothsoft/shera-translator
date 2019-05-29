 class HtmlCanvas {
	 
    constructor(canvas) {
        this.canvas = canvas;
    }

    fillOval(x1, y1, x2, y2) {
    	this.doPaintOval(x1, y1, x2, y2, false);
    }

    drawOval(x1, y1, x2, y2) { 
    	this.doPaintOval(x1, y1, x2, y2, true);
    }

    doPaintOval(x1, y1, x2, y2, stroke) {
    	this.canvas.beginPath();
    	var radiusX = (x2-x1) / 2;
    	var radiusY = (y2-y1) / 2;
    	this.canvas.ellipse((x1 + x2) / 2, (y1 + y2) / 2, radiusX, radiusY, 0, 0, 2 * Math.PI);
    	this.endPath(stroke);
    }

    endPath(stroke) {
    	if (stroke)
    		this.canvas.stroke();
    	else
    		this.canvas.fill();
    }

    fillRectangle(x1, y1, x2, y2) {
    	this.canvas.fillRect(x1, y1, x2-x1, y2-y1);
    }
    
    drawRectangle(x1, y1, x2, y2) {
    	this.canvas.rect(x1, y1, x2-x1, y2-y1);
    }

    fillTriangle(x1, y1, x2, y2, x3, y3) {
    	this.doPaintTriangle(x1, y1, x2, y2, x3, y3, false);
    }

    drawTriangle(x1, y1, x2, y2, x3, y3) {
    	this.doPaintTriangle(x1, y1, x2, y2, x3, y3, true);
    }

    doPaintTriangle(x1, y1, x2, y2, x3, y3, stroke) {
    	this.canvas.beginPath();
    	this.canvas.moveTo(x1, y1);
    	this.canvas.lineTo(x2, y2);
    	this.canvas.lineTo(x3, y3);
    	this.canvas.closePath();
    	this.endPath(stroke);
    }
    
    drawLine(x1, y1, x2, y2) {
    	this.canvas.beginPath();
    	this.canvas.moveTo(x1, y1);
    	this.canvas.lineTo(x2, y2);
    	this.canvas.stroke();
    }
}
HtmlCanvas["__interfaces"] = ["de.slothsoft.shera.dc.Canvas"];