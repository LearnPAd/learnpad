# README #

In order to run the workflow of the transformation look at 
learnpad.uda.LearnpadModelTransformation.run.MainTest.java class and execute the main method.
All that needs to be provided as input to the system is the path of the model in input as you can see (that here for example is located to /resource/model folder: PATestCityApplication.xmi).

The project is divided into two sub packages: 

* one for model2model (ATL Transformation);
* the other in model2text transformations (Acceleo Transformation).

All the artifacts needed for the ATL Transformation part are in /resources folder.

The Acceleo template file is in the package: learnpad.uda.LearnpadModelTransformation.model2text.main

The output of the model2model part is a XWIKI model stored in the /tmp folder.

You can find the overall result, the xml wiki pages, in the /result folder.