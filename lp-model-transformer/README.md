Model Transformer's component
=============================

Information   | Value
------------- | --------
Component     | Model Transformer
Partner       | UNIVAQ
WP            | 3
Responsible   | Francesco Basciani <francesco.basciani@gmail.com>
Collaborators | Gianni Rosa <gianni.rosa@gmail.com>
Roadmap       | http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Model+Transformer

# Summary
Model Transformer will provide a service to transform model from Modeling
Environment to Collaborative Workspace (and possibly others).

# How it works?
Based on EMF, the Model Transformer component is using ATL for model to model
transformation and Acceleo for serialization of the models.  It's ran as a
webservice on port `8083`.

# Configuration
No specific configuration

# Interfaces
No specific documentation at the moment

# Further details

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

## Eclipse
To make it work on Eclipse, there is a few things to do.

### Install plugins
First of all, you'll have to install the ATL and Acceleo plugins.  You'll find
them in the Eclipse market, take the version 3.5 (for each).

### Configure as an ATL Project
n the ATL perspective, right-click on the java project you want to convert (in
the Package Explorer) and select _Convert Project to ATL Project_.

### Configure as an Acceleo Project
Acceleo needs the current module be recognized as an Acceleo project.  To do
that, you can convert the `lp-cw-bridge-transformer` into an Acceleo project
with _File->New->Convert to an Acceleo Project_.  You have to be in the Acceleo
perspective.

### Configure the ATL transformation
The configuration of the ATL transformation needs a few things.  First, you have
to register the metamodels into Eclipse (**each time you start Eclipse, you'll
have to do that**).  Open the ATL perspective, then find the `*.ecore` file in
package explorer.  Click-right on each and click _Register Metamodel_.

Then find _Run->Run Configurations..._ and find the _ATL Transformation_ on the
left.  Create a new configuration called **ado2xwiki** for example and fill the
fields.  The first one should contains a link to the `*.atl` file.  The metamodel
sections will contains the links to the `*.ecore` files. The sources and target
models will contains links to the `*.xmi` files.

### Configure the Acceleo transformation
Find _Run->Run Configurations..._ and find the _Acceleo Application_ on the
left.  Then fill the fields.  The Main class should contain an complete class
name of the generated class
(for example, `eu.learnpad.transformations.model2text.main.Generate`).  The
Model will be the `*.xmi` file that is the output of the ATL transformation.
The target result is the folder in which you want the result to be created.
