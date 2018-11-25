#include "../cse452.h"
#include "Shape.h"
#include <fstream>

Shape::~Shape() {
	// destroy your data here
}

void Shape::draw() {
    // render the shape using OpenGL
    glBegin(GL_TRIANGLES);
    if (vertices.size() == normals.size()) { // one normal per vertex
        for (unsigned int i = 0; i < vertices.size(); i += 3) {
            glNormal3dv(&normals[i][0]);
            glVertex3dv(&vertices[i][0]);
            glNormal3dv(&normals[i+1][0]);
            glVertex3dv(&vertices[i+1][0]);
            glNormal3dv(&normals[i+2][0]);
            glVertex3dv(&vertices[i+2][0]);
        }
    } else { // one normal per face
        for (unsigned int i = 0; i < vertices.size(); i += 3) {
            glNormal3dv(&normals[i/3][0]);
            glVertex3dv(&vertices[i][0]);
            glVertex3dv(&vertices[i+1][0]);
            glVertex3dv(&vertices[i+2][0]);
        }
    }
    glEnd();
}

Shape::Shape() : vertices(), normals() {
}

HitRecord Shape::intersect(const Point3& o, const Vector3& dir) {
	return HitRecord();
}

void Shape::addTriangle(const Point3& p1, const Point3& p2, const Point3& p3) {
    vertices.push_back(p1);
    vertices.push_back(p2);
    vertices.push_back(p3);
}

void Shape::addTriangle(const Point3& p1, const Point3& p2, const Point3& p3, const Vector3& n) {
    vertices.push_back(p1);
    vertices.push_back(p2);
    vertices.push_back(p3);
    normals.push_back(n);
}

void Shape::addTriangle(const Point3& p1, const Point3& p2, const Point3& p3, const Vector3& n1, const Vector3& n2, const Vector3& n3) {
    vertices.push_back(p1);
    vertices.push_back(p2);
    vertices.push_back(p3);
    normals.push_back(n1);
    normals.push_back(n2);
    normals.push_back(n3);
}

void Shape::addSquare(const Point3& p1, const Point3& p2, const Point3& p3, const Point3& p4) {
    addTriangle(p1, p2, p4);
    addTriangle(p2, p3, p4);
}

void Shape::addSquare(const Point3& p1, const Point3& p2, const Point3& p3, const Point3& p4, const Vector3& n) {
    addTriangle(p1, p2, p4, n);
    addTriangle(p2, p3, p4, n);
}

void Shape::addSquare(const Point3& p1, const Point3& p2, const Point3& p3, const Point3& p4, const Vector3& n1, const Vector3& n2, const Vector3& n3, const Vector3& n4) {
    addTriangle(p1, p2, p4, n1, n2, n4);
    addTriangle(p2, p3, p4, n2, n3, n4);
}

Cube::Cube(int n) : Shape() {
    for (int u = 0; u < n; u++) {
        for (int v = 0; v < n; v++) {
            double s0 = -0.5 + (1.0 * u) / n;
            double t0 = 0.5 - (1.0 * v) / n;

            double s1 = -0.5 + (1.0 * (u + 1)) / n;
            double t1 = 0.5 - (1.0 * (v + 1)) / n;

            // z = 0.5
            addSquare(Point3(s0, t0, 0.5), Point3(s0, t1, 0.5), Point3(s1, t1, 0.5), Point3(s1, t0, 0.5), Vector3(0, 0, 1));
            // z = -0.5
            addSquare(Point3(s1, t0, -0.5), Point3(s1, t1, -0.5), Point3(s0, t1, -0.5), Point3(s0, t0, -0.5), Vector3(0, 0, -1));
            // x = 0.5
            addSquare(Point3(0.5, t0, s0), Point3(0.5, t0, s1), Point3(0.5, t1, s1), Point3(0.5, t1, s0), Vector3(1, 0, 0));
            // x = -0.5
            addSquare(Point3(-0.5, t0, s1), Point3(-0.5, t0, s0), Point3(-0.5, t1, s0), Point3(-0.5, t1, s1), Vector3(-1, 0, 0));
            // y = 0.5
            addSquare(Point3(s0, 0.5, t0), Point3(s1, 0.5, t0), Point3(s1, 0.5, t1), Point3(s0, 0.5, t1), Vector3(0, 1, 0));
            // y = -0.5
            addSquare(Point3(s1, -0.5, t0), Point3(s0, -0.5, t0), Point3(s0, -0.5,  t1), Point3(s1, -0.5,  t1), Vector3(0, -1, 0));
        }
    }
}

HitRecord Cube::intersect(const Point3& o, const Vector3& d) {
    HitRecord hr;

    double tmin = 0.0;
    double tmax = 1e40;
    double t0, t1;
    int n0 = -1, n1 = -1;

    double invDirX = 1.0 / d[0];
    t0 = (-0.5 - o[0]) * invDirX;
    t1 = ( 0.5 - o[0]) * invDirX;
    if (invDirX > 0.0) {
        if (t0 > tmin) { tmin = t0; n0 = 0; }
        if (t1 < tmax) { tmax = t1; n1 = 0; }
        if (tmax < tmin) return hr;
    } else {
        if (t1 > tmin) { tmin = t1; n0 = 0; }
        if (t0 < tmax) { tmax = t0; n1 = 0; }
        if (tmax < tmin) return hr;
    }
    double invDirY = 1.0 / d[1];
    t0 = (-0.5 - o[1]) * invDirY;
    t1 = ( 0.5 - o[1]) * invDirY;
    if (invDirY > 0.0) {
        if (t0 > tmin) { tmin = t0; n0 = 1; }
        if (t1 < tmax) { tmax = t1; n1 = 1; }
        if (tmax < tmin) return hr;
    } else {
        if (t1 > tmin) { tmin = t1; n0 = 1; }
        if (t0 < tmax) { tmax = t0; n1 = 1; }
        if (tmax < tmin) return hr;
    }
    double invDirZ = 1.0 / d[2];
    t0 = (-0.5 - o[2]) * invDirZ;
    t1 = ( 0.5 - o[2]) * invDirZ;
    if (invDirZ > 0.0) {
        if (t0 > tmin) { tmin = t0; n0 = 2; }
        if (t1 < tmax) { tmax = t1; n1 = 2; }
        if (tmax < tmin) return hr;
    } else {
        if (t1 > tmin) { tmin = t1; n0 = 2; }
        if (t0 < tmax) { tmax = t0; n1 = 2; }
        if (tmax < tmin) return hr;
    }
    // intersection with the box at tmin and tmax
    if (tmin > 0.0 && n0 != -1) {
        Point3 p;
        Vector3 n;
        p = o + tmin * d;
        switch (n0) {
            case 0: n = Vector3(-d[0],  0.0,  0.0); break;
            case 1: n = Vector3( 0.0, -d[1],  0.0); break;
            case 2: n = Vector3( 0.0,  0.0, -d[2]); break;
            default: break;
        }
        n.normalize();
        hr.addHit(tmin, 0.0, 0.0, p, n);
    }
    if (tmax > 0.0 && n1 != -1) {
        Point3 p;
        Vector3 n;
        p = o + tmax * d;
        switch (n1) {
            case 0: n = Vector3(d[0], 0.0, 0.0); break;
            case 1: n = Vector3(0.0, d[1], 0.0); break;
            case 2: n = Vector3(0.0, 0.0, d[2]); break;
            default: break;
        }
        n.normalize();
        hr.addHit(tmax, 0.0, 0.0, p, n);
    }
	hr.sortHits();
    return hr;
}

Cone::Cone(int n, int m) : Shape() {
    if (n < 3)
        n = 3;
    double ca = 0.5, sa = 0.0;
    for (int i = 1; i <= n; i++) {
        double a = (2 * M_PI * i) / n;
        double ca1 = 0.5 * cos(a);
        double sa1 = 0.5 * sin(a);
        addTriangle(Point3(0, -0.5, 0), Point3(ca, -0.5, sa), Point3(ca1, -0.5, sa1), Vector3(0, -1, 0), Vector3(0, -1, 0), Vector3(0, -1, 0));
        float y = 0.5f - 1.0f / m;
        double r = 1.0 / m;
        addTriangle(Point3(0, 0.5, 0), 
                    Point3(r * ca1, y, r * sa1), 
                    Point3(r * ca, y, r * sa), 
                    Vector3(0, 1, 0), 
                    Vector3(ca1 * 2, 0.5, sa1 * 2), 
                    Vector3(ca * 2, 0.5, sa * 2));

        for (int j = 1; j < m; j++) {
            float y1 = 0.5f - (float) j / m;
            float y2 = 0.5f - (float) (j + 1) / m;
            r = (double) j / m;
            double r2 = (double) (j + 1) / m;
            addSquare(Point3(r * ca, y1, r * sa), 
                      Point3(r * ca1, y1,r * sa1), 
                      Point3(r2 * ca1, y2, r2 * sa1), 
                      Point3(r2 * ca, y2, r2 * sa), 
                      Vector3(ca * 2, 0.5, sa * 2), 
                      Vector3(ca1 * 2, 0.5, sa1 * 2), 
                      Vector3(ca1 * 2, 0.5, sa1 * 2), 
                      Vector3(ca * 2, 0.5, sa * 2));
        }
        ca = ca1;
        sa = sa1;
    }
}

HitRecord Cone::intersect(const Point3& o, const Vector3& d) {
    HitRecord hr;
    double a = (d[0] * d[0]) + (d[2] * d[2]) - 0.25 * (d[1] * d[1]);
    double half_min_y = 0.5 - o[1];
    double b = 2.0 * ((d[0] * o[0]) + (d[2] * o[2])) + 0.5 * d[1] * half_min_y;
    double c = (o[0] * o[0]) + (o[2] * o[2]) - 0.25 * half_min_y * half_min_y;

    // solve a*t^2 + b*t + c = 0

    double det = (b * b) - (4 * a * c);
    if (det >= 0.0) {
        det = sqrt(det);
        double t1 = ( det - b) / (2 * a);
        double t2 = (-det - b) / (2 * a);
        Point3 p;
        Vector3 n;
        if (t1 > 0.0) {
            p = o + t1 * d;
            if ((p[1] < 0.5) && (p[1] > -0.5)) {
                const double dTheta = atan2( p[2], p[0] );
                n[0] = cos( dTheta );
                n[1] = 1.0 / 2.0;
                n[2] = sin( dTheta );
                n.normalize();
                hr.addHit(t1, 0.0, 0.0, p, n);
            }
        }
        if (t2 > 0.0) {
            p = o + t2 * d;
            if ((p[1] < 0.5) && (p[1] > -0.5)) {
                const double dTheta = atan2( p[2], p[0] );
                n[0] = cos( dTheta );
                n[1] = 1.0 / 2.0;
                n[2] = sin( dTheta );
                n.normalize();
                hr.addHit(t2, 0.0, 0.0, p, n);
            }
        }
    }
    // check bottom facet
    if (d[1] != 0.0) {
        double t = -(0.5 + o[1]) / d[1];
        if (t > 0.0) {
            Point3 p = o + d * t;
            if ((p[0] * p[0]) + (p[2] * p[2]) <= 0.25) {
                Vector3 n = Vector3(0.0, -1.0, 0.0);
                hr.addHit(t, 0.0, 0.0, p, n);
            }
        }
    }
	hr.sortHits();
    return hr;
}

Cylinder::Cylinder(int n, int m) : Shape() {
    if (n < 3)
        n = 3;
    double ca = 0.5, sa = 0.0;
    for (int i = 1; i <= n; i++) {
        double a = (2 * M_PI * i) / n;
        double ca1 = 0.5 * cos(a);
        double sa1 = 0.5 * sin(a);
        addTriangle(Point3(0, 0.5, 0), Point3(ca1, 0.5, sa1), Point3(ca, 0.5, sa), Vector3(0, 1, 0), Vector3(0, 1, 0), Vector3(0, 1, 0));
        addTriangle(Point3(0, -0.5, 0), Point3(ca, -0.5, sa), Point3(ca1, -0.5, sa1), Vector3(0, -1, 0), Vector3(0, -1, 0), Vector3(0, -1, 0));
        for (int j = 0; j < m; j++) {
            float y1 = 0.5f - (float) j / m;
            float y2 = 0.5f - (float) (j + 1) / m;
            addSquare(Point3(ca, y1, sa), Point3(ca1, y1, sa1), Point3(ca1, y2, sa1), Point3(ca, y2, sa), Vector3(ca * 2, 0, sa * 2), Vector3(ca1 * 2, 0, sa1 * 2), Vector3(ca1 * 2, 0, sa1 * 2), Vector3(ca * 2, 0, sa * 2));
        }
        ca = ca1;
        sa = sa1;
    }
}

HitRecord Cylinder::intersect(const Point3& o, const Vector3& d) {
    HitRecord hr;
    double a = (d[0] * d[0]) + (d[2] * d[2]);
    double b = 2.0 * ((d[0] * o[0]) + (d[2] * o[2]));
    double c = (o[0] * o[0]) + (o[2] * o[2]) - 0.25;

    // solve a*t^2 + b*t + c = 0

    double det = (b * b) - (4.0 * a * c);
    if (det >= 0.0) {
        det = sqrt(det);
        double t1 = ( det - b) / (2.0 * a);
        double t2 = (-det - b) / (2.0 * a);
        Point3 p;
        Vector3 n;
        if (t1 > 0.0) {
            p = o + t1 * d;
            if ((p[1] < 0.5) && (p[1] > -0.5)) {
                n = Vector3(p[0] * 2.0, 0, p[2] * 2.0);
                n.normalize();
                hr.addHit(t1, 0.0, 0.0, p, n);
            }
        }
        if (t2 > 0.0) {
            p = o + t2 * d;
            if ((p[1] < 0.5) && (p[1] > -0.5)) {
                n = Vector3(p[0] * 2.0, 0, p[2] * 2.0);
                n.normalize();
                hr.addHit(t2, 0.0, 0.0, p, n);
            }
        }
    }

    // check top and bottom facets

    if (d[1] != 0.0) {
        double t1 = (-0.5 - o[1]) / d[1];
        double t2 = ( 0.5 - o[1]) / d[1];
        Point3 p;
        Vector3 n;
        if (t1 > 0.0) {
            p = o + t1 * d;
            if ((p[0] * p[0]) + (p[2] * p[2]) <= 0.25) {
                n = (p[1] > 0.0) ? Vector3(0.0, 1.0, 0.0) : Vector3(0.0, -1.0, 0.0);
                hr.addHit(t1, 0.0, 0.0, p, n);
            }
        }
        if (t2 > 0.0) {
            p = o + t2 * d;
            if ((p[0] * p[0]) + (p[2] * p[2]) <= 0.25) {
                n = (p[1] > 0.0) ? Vector3(0.0, 1.0, 0.0) : Vector3(0.0, -1.0, 0.0);
                hr.addHit(t2, 0.0, 0.0, p, n);
            }
        }
    }
	hr.sortHits();
    return hr;
}

Sphere::Sphere(int n) : Shape() {
    unsigned int i;
    int j;
    if (n > 5)
        n = 5;
    double a = 2.0 / (1.0 + sqrt(5.0));
      
    addTriangle(Point3( 0,  a, -1), Point3(-a,  1,  0), Point3( a,  1,  0));
    addTriangle(Point3( 0,  a,  1), Point3( a,  1,  0), Point3(-a,  1,  0));
    addTriangle(Point3( 0,  a,  1), Point3(-1,  0,  a), Point3( 0, -a,  1));
    addTriangle(Point3( 0,  a,  1), Point3( 0, -a,  1), Point3( 1,  0,  a));
    addTriangle(Point3( 0,  a, -1), Point3( 1,  0, -a), Point3( 0, -a, -1));
    addTriangle(Point3( 0,  a, -1), Point3( 0, -a, -1), Point3(-1,  0, -a));
    addTriangle(Point3( 0, -a,  1), Point3(-a, -1,  0), Point3( a, -1,  0));
    addTriangle(Point3( 0, -a, -1), Point3( a, -1,  0), Point3(-a, -1,  0));
    addTriangle(Point3(-a,  1,  0), Point3(-1,  0, -a), Point3(-1,  0,  a));
    addTriangle(Point3(-a, -1,  0), Point3(-1,  0,  a), Point3(-1,  0, -a));
    addTriangle(Point3( a,  1,  0), Point3( 1,  0,  a), Point3( 1,  0, -a));
    addTriangle(Point3( a, -1,  0), Point3( 1,  0, -a), Point3( 1,  0,  a));
    addTriangle(Point3( 0,  a,  1), Point3(-a,  1,  0), Point3(-1,  0,  a));
    addTriangle(Point3( 0,  a,  1), Point3( 1,  0,  a), Point3( a,  1,  0));
    addTriangle(Point3( 0,  a, -1), Point3(-1,  0, -a), Point3(-a,  1,  0));
    addTriangle(Point3( 0,  a, -1), Point3( a,  1,  0), Point3( 1,  0, -a));
    addTriangle(Point3( 0, -a, -1), Point3(-a, -1,  0), Point3(-1,  0, -a));
    addTriangle(Point3( 0, -a, -1), Point3( 1,  0, -a), Point3( a, -1,  0));
    addTriangle(Point3( 0, -a,  1), Point3(-1,  0,  a), Point3(-a, -1,  0));
    addTriangle(Point3( 0, -a,  1), Point3( a, -1,  0), Point3( 1,  0,  a));
    
    std::vector<Point3> vOld;
    for (j = 1; j < n; j++) {
        // normalize to sphere
        Point3 origin;
        for (i = 0; i < vertices.size(); i++) {
            Vector3 v = unit( vertices[i] - origin );
            vertices[i] = origin + v;
        }
        vOld = vertices;
        vertices.clear();
        for (i = 0; i < vOld.size(); i += 3) {
            Point3 v1 = vOld[i];
            double x1 = v1[0], y1 = v1[1], z1 = v1[2];
            Point3 v2 = vOld[i + 1];
            double x2 = v2[0], y2 = v2[1], z2 = v2[2];
            Point3 v3 = vOld[i + 2];
            double x3 = v3[0], y3 = v3[1], z3 = v3[2];

            addTriangle(v1, Point3((x1 + x2) * 0.5, (y1 + y2) * 0.5, (z1 + z2) * 0.5), Point3((x1 + x3) * 0.5, (y1 + y3) * 0.5, (z1 + z3) * 0.5));
            addTriangle(Point3((x1 + x2) * 0.5, (y1 + y2) * 0.5, (z1 + z2) * 0.5), v2, Point3((x3 + x2) * 0.5, (y3 + y2) * 0.5, (z3 + z2) * 0.5));
            addTriangle(Point3((x1 + x3) * 0.5, (y1 + y3) * 0.5, (z1 + z3) * 0.5), Point3((x3 + x2) * 0.5, (y3 + y2) * 0.5, (z3 + z2) * 0.5), v3);
            addTriangle(Point3((x1 + x3) * 0.5, (y1 + y3) * 0.5, (z1 + z3) * 0.5), Point3((x1 + x2) * 0.5, (y1 + y2) * 0.5, (z1 + z2) * 0.5), Point3((x3 + x2) * 0.5, (y3 + y2) * 0.5, (z3 + z2) * 0.5));
        }
    }
    // compute normals and scale to 0.5 radius
    Point3 origin;
    for (i = 0; i < vertices.size(); i++) {
        Vector3 n = unit( vertices[i] - origin );
        normals.push_back(n);
        vertices[i] = origin + n * 0.5;
    }
}

HitRecord Sphere::intersect(const Point3& o, const Vector3& d) {
    HitRecord hr;
    double a = d.lengthSquared();
    double b = 2.0 * d * (o - Point3());
    double c = (o - Point3()) * (o - Point3()) - 0.25;

    // solve a*t^2 + b*t + c = 0
    
    double det = (b * b) - (4.0 * a * c);
    if (det < 0.0)
        return hr;
    det = sqrt(det);
    double t1 = ( det - b) / (2.0 * a);
    double t2 = (-det - b) / (2.0 * a);
    Point3 p;
    Vector3 n;
    if (t1 > 0.0) {
        p = o + t1 * d;
        n = p - Point3();
        n.normalize();
        hr.addHit(t1, 0.0, 0.0, p, n);
    }
    if (t2 > 0.0) {
        p = o + t2 * d;
        n = p - Point3();
        n.normalize();
        hr.addHit(t2, 0.0, 0.0, p, n);
    }
	hr.sortHits();
    return hr;
}

MoebiusStrip::MoebiusStrip(int n, int m) : Shape() {
    unsigned int k;
    int i, j;
    if (n < 3)
        n = 3;
    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            double s0 = 2 * M_PI * i / n;
            double t0 = -0.4 + 0.8 * j / m;

            double s1 = 2 * M_PI * (i + 1) / n;
            double t1 = -0.4 + 0.8 * (j + 1)  / m;

            double x1 = cos(s0) + t0 * cos(s0 * 0.5) * cos(s0);
            double y1 = sin(s0) + t0 * cos(s0 * 0.5) * sin(s0);
            double z1 = t0 * sin(s0 * 0.5);

            double x2 = cos(s1) + t0 * cos(s1 * 0.5) * cos(s1);
            double y2 = sin(s1) + t0 * cos(s1 * 0.5) * sin(s1);
            double z2 = t0 * sin(s1 * 0.5);

            double x3 = cos(s1) + t1 * cos(s1 * 0.5) * cos(s1);
            double y3 = sin(s1) + t1 * cos(s1 * 0.5) * sin(s1);
            double z3 = t1 * sin(s1 * 0.5);

            double x4 = cos(s0) + t1 * cos(s0 * 0.5) * cos(s0);
            double y4 = sin(s0) + t1 * cos(s0 * 0.5) * sin(s0);
            double z4 = t1 * sin(s0 * 0.5);

            // add the square both ways (because surface is two sided)
            addSquare(Point3(x1, y1, z1), Point3(x2, y2, z2), Point3(x3, y3, z3), Point3(x4, y4, z4));
            addSquare(Point3(x2, y2, z2), Point3(x1, y1, z1), Point3(x4, y4, z4), Point3(x3, y3, z3));
        }
    }
  
    // calculate only one normal per face, the explicit expression for the surface normal is
    // a little too messy
    for (k = 0; k < vertices.size(); k += 3) {
        vertices[k    ] *= 0.70;
        vertices[k + 1] *= 0.70;
        vertices[k + 2] *= 0.70;
        Vector3 a = vertices[k + 1] - vertices[k];
        Vector3 b = vertices[k + 2] - vertices[k];
        Vector3 n = a ^ b;
        n.normalize();
        normals.push_back(n);
    }
}

Cow::Cow() : Shape() {
    std::ifstream cowFile("../data/cow.raw");
    if ( !cowFile.good() )
        return;

    bool done = !cowFile;
    while (!done) {
        Point3 p1, p2, p3;
        cowFile >> p1[0];
        cowFile >> p1[1];
        cowFile >> p1[2];
        cowFile >> p2[0];
        cowFile >> p2[1];
        cowFile >> p2[2];
        cowFile >> p3[0];
        cowFile >> p3[1];
        done = !(cowFile >> p3[2]);
        addTriangle(p1, p2, p3);
    }
    for (unsigned int i = 0; i < vertices.size(); i += 3) {
        vertices[i    ] *= 0.20;
        vertices[i + 1] *= 0.20;
        vertices[i + 2] *= 0.20;
        Vector3 a = vertices[i + 1] - vertices[i];
        Vector3 b = vertices[i + 2] - vertices[i];
        Vector3 n = a ^ b;
        n.normalize();
        normals.push_back(n);
    }
}