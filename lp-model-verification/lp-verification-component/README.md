Verification component
====================

Information   | Value
------------- | --------
Component     | Verification
Partner       | Unicam
WP            | 4
Responsible   | Andrea Polini <andrea.polini@unicam.it>
Collaborators | Damiano Falcioni <damiano.falcioni@unicam.it>
Roadmap       | http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Model+Verification

# Summary
This component provide verification capabilities over a LearnPAd model relying on plugins.

# How it works?
This component expose API Java and REST to the LearnPAd platform in order to perform several kind of verifications on a LearnPAd model. The different types of verifications are provided by plugins.
The component automatically recognize all the plugins present inside a specific folder (the default one is ./VerificationComponentPlugins where . is the directory containing the verification component jar) and execute the right one on each request, in an asynchronous way. Results are stored in a folder (the default one is ./VerificationComponentResults where . is the directory containing the verification component jar) and available for future analysis.
The component expose the Java API described in the next section but can also be executed stand alone. In this way it will initiate an internal Grizzly Web Server providing REST API.
The component is also ready to be packaged as a WAR to be deployed on an Application Server like Tomcat.

# Configuration
No configuration needed but inside the jar (in eu/learnpad/verification/utils/) is present a file called config.txt where you can change the default folder path of plugins and results.

# Interfaces
The main class that expose verification API is eu.learnpad.verification.VerificationComponent described here: https://github.com/damianofalcioni/learnpad/blob/master/lp-verification-component/src/main/java/eu/learnpad/verification/VerificationComponent.java
The class the implement REST interface and execute the Grizzly internal web server is eu.learnpad.verification.JAXRSService described here: https://github.com/damianofalcioni/learnpad/blob/master/lp-verification-component/src/main/java/eu/learnpad/verification/JAXRSService.java

The service is available at 'localhost:9998/rest'.

