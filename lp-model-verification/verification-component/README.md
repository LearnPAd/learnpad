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
This component expose Java API in order to perform several kind of verifications on a LearnPAd model. The different types of verifications are provided by plugins.

The component automatically recognize all the plugins present inside a specific folder (the default one is ./VerificationComponentPlugins where . is the directory containing the verification component jar) and execute the right one on each request, in an asynchronous way. 

Results are stored in a folder (the default one is ./VerificationComponentResults where . is the directory containing the verification component jar) and available for future analysis.

The currently recognized plugins are:

* [Deadlock plugin](../verification-component-deadlock-plugin/)
* [Understandability plugin](../verification-component-understandability-plugin/)

All the plugins are automatically compiled and copied in the default plugin directory by maven.

# Configuration
No configuration needed but inside the jar (in eu/learnpad/verification/utils/) is present a file called config.txt where you can change the default folder path of plugins and results.

# Interfaces
The main class that expose verification API is [eu.learnpad.verification.VerificationComponent](./src/main/java/eu/learnpad/verification/VerificationComponent.java)

