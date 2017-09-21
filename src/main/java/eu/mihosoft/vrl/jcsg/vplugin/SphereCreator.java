/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.jcsg.vplugin;

import eu.mihosoft.jcsg.CSG;
import eu.mihosoft.jcsg.Sphere;
import eu.mihosoft.vrl.annotation.ComponentInfo;
import eu.mihosoft.vrl.annotation.ParamInfo;
import java.io.Serializable;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
@ComponentInfo(name="SphereCreator", category="JCSG")
public class SphereCreator implements Serializable {

    private static final long serialVersionUID = 1L;

    public CSG create(
            @ParamInfo(name="Radius", options="value=1") double radius,
            @ParamInfo(name="Resolution", style="slider", options="min=3;max=128;value=8") int resolution) {
        return new Sphere(radius, resolution*2, resolution).toCSG();
    }
}
