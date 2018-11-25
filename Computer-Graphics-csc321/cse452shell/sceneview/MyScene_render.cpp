#include "../cse452.h"
#include "../sceneview/MyScene.h"
#include "../rendering/RenderingInterface.h"
#include <FL/gl.h>
#include <cfloat>

void MyScene::render(int type, int width, int height, unsigned char* pixels) {
    if (!isLoaded) {
		progress = 1.0;
        return;
    }
	bDoRender = true;
    // Add your rendering code here.
    // Keep track of your progress as a value between 0 and 1
    // so the progress bar can update as the rendering progresses
	progress = 0.0;
    switch (type) {
        case RenderingUI::RENDER_SCANLINE:  scanline(width, height, pixels); break;
        case RenderingUI::RENDER_RAY_TRACING:  raytrace(width, height, pixels); break;
        case RenderingUI::RENDER_PATH_TRACING:  break;
        default: break;
    }
	progress = 1.0;
}

void MyScene::stopRender()
{
    // Because this is threaded code, this function
    // can be called in the middle of your rendering code.
    // You should then stop at the next scanline
	bDoRender = false;
}

double MyScene::getRenderProgress() {
    // return the current progress as a value between 0 and 1
    return progress;
}

// add extra methods here
void MyScene::scanline(int w, int h, unsigned char* pixels) {
	resize(w, h);

	glPushAttrib( GL_ALL_ATTRIB_BITS );
	glEnable(GL_DEPTH_TEST);
	glEnable(GL_CULL_FACE);
	glEnable(GL_LIGHTING);
	glEnable(GL_NORMALIZE);

	glClearColor( background[0], background[1], background[2], 1.0f);

	glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
	glViewport(0, 0, w, h);
	glMatrixMode(GL_PROJECTION);
	glLoadMatrixd( &getCamera().getProjection()(0,0) );
	glMatrixMode(GL_MODELVIEW);
	glLoadMatrixd( &getCamera().getWorldToCamera()(0,0) );

	glEnable(GL_LIGHTING);
	glPolygonMode(GL_FRONT, GL_FILL);

	draw();

	glPopAttrib();
	glReadPixels( 0, 0, w, h, GL_RGB, GL_UNSIGNED_BYTE, &pixels[0] );
	progress = 1.0;
}

void MyScene::raytrace(int w, int h, unsigned char* pixels) {
	resize(w, h);

	for (int y=0; y < h; y++) {
		if (bDoRender == false)
			break;

		for (int x = 0; x < w; x++) {
			// determine the color of the pixel (x,y) by raytracing

			// form the ray
			Point3 pixel (x, y, -1.0);
			pixel[0] = -1.0 + 2.0 * pixel[0] / (camera.getWidth() - 1);
			pixel[1] = -1.0 + 2.0 * pixel[1] / (camera.getHeight() - 1);
			pixel = camera.getCameraToWorld() * pixel;
			Vector3 dir = pixel - camera.getEye();
			Point3 o = camera.getEye();

			// trace the ray
			Color c = traceRay(o, dir, 0);

			// clamp and store the color value
			c[0] = (c[0] > 0.0) ? ((c[0] < 1.0) ? c[0] : 1.0) : 0.0;
			c[1] = (c[1] > 0.0) ? ((c[1] < 1.0) ? c[1] : 1.0) : 0.0;
			c[2] = (c[2] > 0.0) ? ((c[2] < 1.0) ? c[2] : 1.0) : 0.0;
			*pixels++ = (unsigned char) (c[0] * 255.0);
			*pixels++ = (unsigned char) (c[1] * 255.0);
			*pixels++ = (unsigned char) (c[2] * 255.0);
		}

		progress = (double) y / (double) h;
		Fl::check();
	}

	progress = 1.0;
}

Color MyScene::traceRay(Point3& o, Vector3& dir, int level) {
	if (level > 5)
		return Color();

	FirstHitRecord fhr = masters->get("root")->intersect(o, dir);
	if (!fhr.hit())
		return background;

	Color c = fhr.node->object->ambient;
	Color cli;
	fhr.n.normalize();

	for (int i = 0; i < lights.size(); i++) {
		Vector3 l;  // light vector pointing from point to source
		l = lights[i].getPos() - fhr.p;
        double lightDistance = l.length();
		l.normalize();

		// check if in front
		double d = fhr.n * l;
		// check if outside of shadow
		FirstHitRecord shadowCheck = masters->get("root")->intersect(fhr.p + 0.1 * l, l * lightDistance);
		if (d > 0 && !shadowCheck.hit()){
			// diffuse 
			Point3 fallOff = lights[i].getFalloff();
			double fAtt = 1.0 / (fallOff[0] + fallOff[1] * lightDistance + fallOff[2] * lightDistance * lightDistance);
			cli += fhr.node->object->diffuse * fAtt * d;

		}
		
		c += cli * lights[i].getColor();

	}

	Color cr;	// color contribution of reflections

	// check if surface is reflective
	if (fhr.node->object->shine > 0.0) {
		Vector3 L;	// look vector
		Vector3 r;	// reflection vector
		L = dir * -1.0;
		r = 2 * (L * fhr.n) * fhr.n - L;
		double rDist = r.length();
		r.normalize();

		FirstHitRecord reflectedObject = masters->get("root")->intersect(fhr.p + 0.1 * r, r * rDist);
		double fAtt = 1.0 / (reflectedObject.p[0] + reflectedObject.p[1] * rDist + reflectedObject.p[2] * rDist * rDist);
		
		cr += fhr.node->object->reflect * fAtt * traceRay(fhr.p, r, level+1);
	}

	return c;
}
