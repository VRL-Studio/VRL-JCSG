/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.jcsg.vplugin;

import eu.mihosoft.jcsg.CSG;
import eu.mihosoft.jcsg.FileUtil;
import eu.mihosoft.vrl.annotation.ComponentInfo;
import eu.mihosoft.vrl.annotation.ParamInfo;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
@ComponentInfo(name="STL-Saver", category="JCSG")
public class STLSaver implements Serializable{

    private static final long serialVersionUID = 1L;


    public File save(@ParamInfo(name="File", style="save-dialog", options="endings=[\".stl\"]; description=\"STL-Files (*.stl)\"") File f, CSG csg) throws IOException {
        
        FileUtil.write(f.toPath(), csg.toStlString());
        
        return f;
    }
}
