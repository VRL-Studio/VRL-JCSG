package eu.mihosoft.vrl.jcsg.vplugin;

import eu.mihosoft.jcsg.CSG;
import eu.mihosoft.jcsg.Polygon;
import eu.mihosoft.vrl.annotation.ComponentInfo;
import eu.mihosoft.vrl.v3d.Node;
import eu.mihosoft.vrl.v3d.Triangle;
import eu.mihosoft.vrl.v3d.VTriangleArray;

import javax.vecmath.Point3f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ComponentInfo(name="VTriangleConverter", category="JCSG/VRL")
public final class VTriangleConverter implements Serializable{

    private static final long serialVersionUID = 1L;

    public static VTriangleArray toVTriangleArray(CSG csg) {
        VTriangleArray result = new VTriangleArray();

        for (Polygon p : csg.getPolygons()) {
            result.addAll(toVTriangles(p));
        }

        return result;
    }

    public static List<Triangle> toVTriangles(Polygon polygon) {
        List<Triangle> result = new ArrayList<Triangle>();
        if (polygon.vertices.size() >= 3) {

            // TODO: improve the triangulation?
            //
            // VRL requires triangular polygons.
            // If our polygon has more vertices, create
            // multiple triangles:
            Point3f firstVertex = new Point3f(
                    (float)polygon.vertices.get(0).pos.getX(),
                    (float)polygon.vertices.get(0).pos.getY(),
                    (float)polygon.vertices.get(0).pos.getZ());
            List<Point3f> triangleVertices = new ArrayList<Point3f>(3);
            for (int i = 0; i < polygon.vertices.size() - 2; i++) {

                triangleVertices.add(firstVertex);

                triangleVertices.add(new Point3f(
                        (float)polygon.vertices.get(i + 1).pos.getX(),
                        (float)polygon.vertices.get(i + 1).pos.getY(),
                        (float)polygon.vertices.get(i + 1).pos.getZ()));

                triangleVertices.add(new Point3f(
                        (float)polygon.vertices.get(i + 2).pos.getX(),
                        (float)polygon.vertices.get(i + 2).pos.getY(),
                        (float)polygon.vertices.get(i + 2).pos.getZ()));

                if (triangleVertices.size()==3) {
                    result.add(new Triangle(
                            new Node(triangleVertices.get(0)),
                            new Node(triangleVertices.get(1)),
                            new Node(triangleVertices.get(2)))
                    );
                    triangleVertices.clear();
                }
            }
        }

        return result;
    }
}
