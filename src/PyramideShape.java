package Pyramide.pyramide;

import java.awt.*;
import java.awt.event.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class PyramideShape extends Object
{
	private Shape3D shape;

	private static final float[] verts = {

		// face 1
		-1,0,1, 	1,0,1,		0,2,0,

		// face 2
		1,0,1,		1,0,-1,		0,2,0,

		// face 3
		1,0,-1,  	-1,0,-1,	0,2,0,

		// face 4
		-1,0,-1,  	-1,0,1, 	0,2,0,

		// bottom face 1
		1,0,-1,  	1,0,1,  	-1,0,-1,

		// bottom face 2
		-1,0,1,  	-1,0,-1, 	1,0,1,

	};

	private static final float[] colors = {

		// face 1
		0,0,1,  0,0,1,  0,0,1,

		// face 2
		1,0,0,  1,0,0,  1,0,0,

		// face 3
		0,0,1,  0,0,1,  0,0,1,

		// face 4
		0,1,0,  0,1,0,  0,1,0,

		// bottom face 1
		1,1,0,  1,1,0,  1,1,1,

		// bottom face 2
		1,1,0,  1,1,0, 1,1,0,

	};


	public PyramideShape()
	{
		TriangleArray pyramide = new TriangleArray(18,TriangleArray.COORDINATES);
		//|TriangleArray.COLOR_3);

		pyramide.setCoordinates(0, verts);
		//pyramide.setColors(0,colors);


		ColoringAttributes coloringAttributes = new ColoringAttributes();
		coloringAttributes.setColor(1,1,1);
		coloringAttributes.setShadeModel(ColoringAttributes.SHADE_GOURAUD);

		PolygonAttributes polygonAttributes = new PolygonAttributes();
		polygonAttributes.setPolygonMode(PolygonAttributes.POLYGON_FILL);

		Appearance appearance = new Appearance();
		appearance.setColoringAttributes(coloringAttributes);
		appearance.setPolygonAttributes(polygonAttributes);

		shape = new Shape3D(pyramide,appearance);


	}

	public Shape3D getShape()
	{
		return shape;
	}

} // Enc class PyramideShape

