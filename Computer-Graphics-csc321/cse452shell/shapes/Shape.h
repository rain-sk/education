#ifndef _SHAPE_H_
#define _SHAPE_H_

#include "../cse452.h"
#include "../intersection/HitRecord.h"
#include "Point3.h"
#include "Vector3.h"
#include <vector>

class Shape {
public:
	virtual ~Shape();
	virtual void draw();
	virtual HitRecord intersect(const Point3&, const Vector3&);
protected:
	Shape();
	void addTriangle(const Point3& p1, const Point3& p2, const Point3& p3);
	void addTriangle(const Point3& p1, const Point3& p2, const Point3& p3, const Vector3& n);
	void addTriangle(const Point3& p1, const Point3& p2, const Point3& p3, 
					 const Vector3& n1, const Vector3& n2, const Vector3& n3);
	void addSquare(const Point3& p1, const Point3& p2, const Point3& p3, const Point3& p4);
	void addSquare(const Point3& p1, const Point3& p2, const Point3& p3, const Point3& p4, const Vector3& n);
	void addSquare(const Point3& p1, const Point3& p2, const Point3& p3, const Point3& p4, const Vector3& n1, const Vector3& n2, const Vector3& n3, const Vector3& n4);

	std::vector<Point3>  vertices;
	std::vector<Vector3> normals;
};

class Cube : public Shape {
public:
	Cube(int);
	HitRecord intersect(const Point3&, const Vector3&);
};

class Cylinder : public Shape {
public:
	Cylinder(int, int);
	HitRecord intersect(const Point3&, const Vector3&);
};

class Cone : public Shape {
public:
	Cone(int, int);
	HitRecord intersect(const Point3&, const Vector3&);
};

class Sphere : public Shape {
public:
	Sphere(int);
	HitRecord intersect(const Point3&, const Vector3&);
};

class MoebiusStrip : public Shape {
public:
	MoebiusStrip(int, int);
};

class Cow : public Shape {
public:
	Cow();
};

#endif /* _SHAPE_H_ */