/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package eu.learnpad.mt.rest;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.ws.rs.Path;

import org.xwiki.xff.core.XFFZipper;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.mt.Bridge;
import eu.learnpad.transformations.launchers.Launcher;

@Path("/learnpad/mt/bridge")
public class BridgeImpl extends Bridge
{
    private String TMP_PATH = "/tmp/learnpad/mt";

    /*
     * Please consider the limitations described
     * as comments and as todo in eu.learnpad.me.rest.data.ModelSetType
     *  
     * @see eu.learnpad.me.rest.data.ModelSetType
     */
    @Override
    public InputStream transform(ModelSetType type, InputStream model) throws LpRestException
    {
        try {
            Files.createDirectories(Paths.get(TMP_PATH));
        } catch (Exception e) {
            String message = String.format("Cannot create the directory '%s'", TMP_PATH);
            System.err.println(message);
            e.printStackTrace();
        }
        Launcher launcher = new Launcher();
        java.nio.file.Path path = null;
        try {
            path = launcher.chain(model, type.toString());
        } catch (Exception e) {
            String message = String.format("Error in the transformation of type '%s'", type);
            System.err.println(message);
            e.printStackTrace();
            return null;
        }
        XFFZipper xffZipper = null;
        String packageName = String.format("%s.xff", UUID.randomUUID().toString());
        java.nio.file.Path packagePath = Paths.get("/tmp", packageName);
        try {
            xffZipper = new XFFZipper(path);
            xffZipper.xff(packagePath);
            return Files.newInputStream(packagePath);
        } catch (Exception e) {
            String message = String.format("Error in the XFF zipping of the package of type '%s'", type);
            System.err.println(message);
            e.printStackTrace();
        }
        return null;
    }
}
