package eu.learnpad.transformations.launchers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import eu.learnpad.transformations.model2text.generator.AcceleoStandaloneStarter;

public class AcceleoTransformationLauncher
{

    private String tmpModelFolder = "/tmp/learnpad/mt";

    /**
     * Acceleo Transformation Launcher (MODEL2CODE Transformation).
     * 
     * @param model The InputStream of the model file to be transformed.
     */
    public Path write(InputStream model)
    {
        Path resultFolderPath = Paths.get(String.format("%s/%s", tmpModelFolder, UUID.randomUUID().toString()));

        System.out.println("Starting Acceleo Model2Text transformation...");
        AcceleoStandaloneStarter ast = new AcceleoStandaloneStarter();
        ast.execute(model, resultFolderPath);
        System.out.println(
            String.format("Acceleo Model2Text done. You can find the result in the '%s' folder.", resultFolderPath));

        return resultFolderPath;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        /*
         * Acceleo take as input a model (XMI file)
         */
        String model_in = "resources/model/titolo-unico.xwiki.xmi";
        FileInputStream fis = new FileInputStream(model_in);

        System.out.println("*******STARTING THE ACCELEO TRANSFORMATION*******");
        AcceleoTransformationLauncher acceleoTL = new AcceleoTransformationLauncher();
        acceleoTL.write(fis);
        System.out.println("*******FINISHED THE ACCELEO TRANSFORMATION*******");
    }
}
