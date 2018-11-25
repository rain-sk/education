#include "../cse452.h"
#include "Shape.h"
#include "ShapesUI.h"
#include "ShapesInterface.h"
#include "../Color.h"
#include <FL/Fl.H>
#include <FL/gl.h>
#include <GL/glu.h>

ShapesUI::ShapesUI() {
    width = height = 0;
	current = 0;
}

ShapesUI::~ShapesUI() {
	if (current != 0)
		delete current;
}

void ShapesUI::resize(int w, int h) {
    width = w;
    height = h;
}

void ShapesUI::draw() {
    // Sets up the viewport and background color
    setup3DDrawing( Color( 0,0,0 ), width, height, true );

    // Changes the way triangles are drawn
    switch ( shapesUI->getDisplayType() ) {
        case DISPLAY_WIREFRAME: {
            glDisable(GL_LIGHTING);
            glPolygonMode(GL_FRONT, GL_LINE);
            glColor3f(1.0f, 1.0f, 1.0f);
        } break;
        case DISPLAY_FLAT_SHADING: {
            glEnable(GL_LIGHTING);
            glPolygonMode(GL_FRONT, GL_FILL);
            glColor3f(1.0f, 1.0f, 1.0f);
            glShadeModel(GL_FLAT);
        } break;
        case DISPLAY_SMOOTH_SHADING: {
            glEnable(GL_LIGHTING);
            glPolygonMode(GL_FRONT, GL_FILL);
            glColor3f(1.0f, 1.0f, 1.0f);
            glShadeModel(GL_SMOOTH);
        } break;
        default: break;
    }

    // Setup the camera
    gluLookAt( 3.5 * cos( shapesUI->getYRot() ) * cos( shapesUI->getXRot() ), 
               3.5 * sin( shapesUI->getYRot() ), 
               3.5 * cos( shapesUI->getYRot() ) * sin( shapesUI->getXRot() ), 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);

	// draw the shape
	current->draw();

    endDrawing();
}

int ShapesUI::handle(int event) {
    return 0;
}

void ShapesUI::changedShape()
{
	changedTessel();
}

void ShapesUI::changedTessel( ) {

	int n = shapesUI->getTessel1();
	int m = shapesUI->getTessel2();

	switch(shapesUI->getShapeType()) {
	case SHAPE_SPHERE :
		current = new Sphere(n);
		break;
	case SHAPE_CYLINDER :
		current = new Cylinder(n, m);
		break;
	case SHAPE_CONE :
		current = new Cone(n,m);
		break;
	case SHAPE_CUBE :
		current = new Cube(n);
		break;
	}
    
    RedrawWindow();
}

