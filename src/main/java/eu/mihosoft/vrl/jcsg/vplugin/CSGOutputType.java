/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.jcsg.vplugin;

import eu.mihosoft.jcsg.Vertex;
import eu.mihosoft.vrl.annotation.TypeInfo;
import eu.mihosoft.vrl.v3d.VGeometry3D;
import eu.mihosoft.jcsg.CSG;
import eu.mihosoft.vrl.vrljoglplugin.JoglType;
import eu.mihosoft.vrl.vrljoglplugin.glview.GLMeshCanvas;
import eu.mihosoft.vrl.vrljoglplugin.glview.Mesh;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
@TypeInfo(input = false, output = true, style = "default", type = CSG.class)
public class CSGOutputType extends JoglType {
    private CSG viewValue;
    
    @Override
    public void setViewValue(Object o) {
        if (o instanceof CSG) {
            CSG csg = (CSG) o;
            viewValue = csg;

            List<Vertex> vertexList = csg.getPolygons().stream().
                    flatMap(p->p.toTriangles().stream()).
                    flatMap(t->t.vertices.stream()).
                    collect(Collectors.toList());

            float[] vertices = new float[vertexList.size()*3];

            for (int i = 0; i < vertexList.size(); i++) {
                vertices[i * 3 + 0] = (float)vertexList.get(i).pos.x();
                vertices[i * 3 + 1] = (float)vertexList.get(i).pos.y();
                vertices[i * 3 + 2] = (float)vertexList.get(i).pos.z();
            }

            int[] indices = new int[vertexList.size()];

            for(int i = 0; i < vertexList.size(); i++) {
                indices[i] = i;
            }
            GLMeshCanvas meshCanvas = new GLMeshCanvas(Mesh.newInstance(vertices,indices));
            meshCanvas.setSkipInitAnimation(true);
            super.setViewValue(meshCanvas);

        }
    }

    @Override
    public void emptyView() {
        viewValue=null;
        super.emptyView();
    }
    
    
    
    @Override
    public Object getViewValue() {
        return viewValue;
    }

    @Override
    public boolean preferBinarySerialization() {
        return true;
    }
    
    
}
