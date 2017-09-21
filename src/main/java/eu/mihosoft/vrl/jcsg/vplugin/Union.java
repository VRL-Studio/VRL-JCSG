/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.jcsg.vplugin;

import eu.mihosoft.jcsg.CSG;
import eu.mihosoft.vrl.annotation.ComponentInfo;

import java.io.Serializable;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
@ComponentInfo(name="Union", category="JCSG")
public class Union implements Serializable{
    private static final long serialVersionUID = 1L;
    public CSG union(CSG csg1, CSG csg2) {
        return csg1.union(csg2);
    }
}
