package eu.learnpad.transformations.launchers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Launcher
{
    /**
     * Alignment Launcher: starting from an XML file as InputStream it create a valid XMI model file directly into the
     * OutputStream provided as input.
     * 
     * @param model
     * @param type
     * @param out
     * @return boolean
     * @throws Exception
     */
    public boolean align(InputStream model, String type, OutputStream out) throws Exception
    {
        AlignmentLauncher aL = new AlignmentLauncher();
        return aL.align(model, type, out);
    }

    /**
     * Execution of the ATL Transformation with a pre-processing with alignment. This method take an XML file as
     * InputStream and after a pre-precessing phase execute the transformation with the resulting XMI model file.
     * 
     * @param model
     * @param out
     * @param type
     * @return boolean
     * @throws Exception
     */
    public boolean transform(InputStream model, String type, OutputStream out) throws Exception
    {
        ATLTransformationLauncher atlTL = new ATLTransformationLauncher();
        return atlTL.transform(model, type, out);
    }

    /**
     * Acceleo Transformation Launcher (MODEL2CODE Transformation).
     * 
     * @param model
     * @return Path
     */
    public Path write(InputStream model)
    {
        AcceleoTransformationLauncher acceleoTL = new AcceleoTransformationLauncher();
        return acceleoTL.write(model);
    }

    /**
     * Execute the chain of transformation composed by: ATL Transformation (MODEL2MODEL Transformation) and Acceleo
     * Transformation (MODEL2TEXT Transformation).
     * 
     * @param model
     * @param type
     * @param out
     * @return Path
     * @throws Exception
     */
    public Path chain(InputStream model, String type) throws Exception
    {
        Path lpModelPath = Paths.get("/tmp/learnpad/mt/model.xmi");
        Path xwikiModelPath = Paths.get("/tmp/learnpad/mt/model.xwiki.xmi");

        // ALIGN
        AlignmentLauncher alignLauncher = new AlignmentLauncher();
        OutputStream alignOutputStream = Files.newOutputStream(lpModelPath);
        boolean isAlign = alignLauncher.align(model, type, alignOutputStream);

        // TRANSFORM
        ATLTransformationLauncher transformLauncher = new ATLTransformationLauncher();
        InputStream transformInputStream = Files.newInputStream(lpModelPath);
        OutputStream transformOutputStream = Files.newOutputStream(xwikiModelPath);
        boolean isTransformed = transformLauncher.transform(transformInputStream, type, transformOutputStream);

        // WRITE
        AcceleoTransformationLauncher writeLauncher = new AcceleoTransformationLauncher();
        InputStream writeInputStream = Files.newInputStream(xwikiModelPath);
        Path path = writeLauncher.write(writeInputStream);

        // CLEAN
        alignOutputStream.close();
        transformInputStream.close();
        transformOutputStream.close();
        writeInputStream.close();
//        Files.delete(lpModelPath);
//        Files.delete(xwikiModelPath);

        if (isAlign && isTransformed && path != null) {
            return path;
        } else {
            return null;
        }
    }

    public static void main(String[] args) throws Exception
    {

        // String model_in = "resources/model/ado4f16a6bb-9318-4908-84a7-c2d135253dc9.xml";
        String model_in = "resources/model/adoxx_modelset.xml";
        String file_out = "/tmp/testAlignmentOutputStream.xmi";
        String type = "ADOXX";
        // String type = "MD";

        // create a new output stream
        OutputStream out = new FileOutputStream(file_out);

        Launcher launcher = new Launcher();
        FileInputStream fis = new FileInputStream(model_in);

        /*
         * ****************************************** ****ALIGNMENT EXAMPLE**********************
         * ******************************************
         */
        launcher.align(fis, type, out);

        /*
         * ****************************************** ****ATL TRANSFORMATION EXAMPLE*************
         * ******************************************
         */
        // launcher.transform(fis, type, out);

        /*
         * ****************************************** ****ACCELEO TRANSFORMATION EXAMPLE*********
         * ******************************************
         */
        // launcher.write(fis);

        /*
         * ****************************************** ****CHAIN EXAMPLE*************************
         * ******************************************
         */
        // launcher.chain(fis, type);

    }
}
