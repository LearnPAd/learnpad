package eu.learnpad.transformations.launchers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import eu.learnpad.transformations.model2model.ATLTransformation;

public class ATLTransformationLauncher
{
    private final String ADOXX_TYPE = "ADOXX";

    private final String MAGIC_DRAW_TYPE = "MD";

    private String ADOXX2XWIKI_ATL_TRANSFORMATION = "transformation/ado2xwiki.atl";

    private String MAGICDRAW2XWIKI_ATL_TRANSFORMATION = "";

    /**
     * Execution of the ATL Transformation with a pre-processing with alignment. This method take an XML file as
     * InputStream and after a pre-precessing phase execute the transformation with the resulting XMI model file.
     * 
     * @param model The InputStream of the model file to be transformed.
     * @throws Exception
     */
    public boolean transform(InputStream modelInputStream, String type, OutputStream out) throws Exception
    {
        String metamodel_in = "";
        String metamodel_out = "";
        InputStream atlStream;
        List<InputStream> atlStreams = new ArrayList<InputStream>();
        String inTag = "";
        String outTag = "";

        switch (type) {
            case ADOXX_TYPE:
                metamodel_in = "metamodels/adoxx/ado.ecore";
                metamodel_out = "metamodels/xwiki/XWIKI.ecore";
                atlStream = this.getClass().getClassLoader().getResourceAsStream(ADOXX2XWIKI_ATL_TRANSFORMATION);
                atlStreams.add(atlStream);
                inTag = "ADOXX";
                outTag = "XWIKI";
                break;
            case MAGIC_DRAW_TYPE:
                metamodel_in = "";
                metamodel_out = "";
                atlStream = this.getClass().getClassLoader().getResourceAsStream(MAGICDRAW2XWIKI_ATL_TRANSFORMATION);
                atlStreams.add(atlStream);
                inTag = "";
                outTag = "";
                break;
            default:
                System.out.println("Type not allowed!");
                break;
        }

        ATLTransformation myT = new ATLTransformation();
        System.out.println("Starting ATL Model2Model transformation...");
        InputStream learnpadMetamodelStream = this.getClass().getClassLoader().getResourceAsStream(metamodel_in);
        InputStream xwikiMetamodelStream = this.getClass().getClassLoader().getResourceAsStream(metamodel_out);
        myT.run(modelInputStream, learnpadMetamodelStream, xwikiMetamodelStream, atlStreams, inTag, outTag, out);
        learnpadMetamodelStream.close();
        xwikiMetamodelStream.close();
        for (InputStream module : atlStreams) {
            module.close();
        }

        return true;
    }

    public static void main(String[] args) throws Exception
    {

        // String model_in = "resources/model/ado4f16a6bb-9318-4908-84a7-c2d135253dc9.xml";
        String model_in = "resources/model/titolo-unico.xml";
        String file_out = "/tmp/testTransformationOutputStream.xmi";
        String type = "ADOXX";
        // String type = "MD";

        FileInputStream fis = new FileInputStream(model_in);
        OutputStream out = new FileOutputStream(file_out);

        ATLTransformationLauncher atlTL = new ATLTransformationLauncher();
        System.out.println("*******STARTING THE ATL TRANSFORMATION*******");
        atlTL.transform(fis, type, out);
        System.out.println("*******FINISHED THE ATL TRANSFORMATION*******");
    }
}
