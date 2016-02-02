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
This component exposes the verification capabilities of the [verification-component](../verification-component/) as REST API, implementing the [LearnPAd Core Platform API](../../lp-core-platform/lp-cp-apis/src/main/java/eu/learnpad/mv).

# How it works?
More details on the verification capabilities are provided in the [verification-component](../verification-component/).

# Configuration
Inside the jar (in eu/learnpad/verificationComponent/utils/) is present a file called config.txt where you can change the default address of the LearnPAd Platform and the local port used by the REST Web Server.

# Interfaces
The class that implement REST interface is described [here](./src/main/java/eu/learnpad/verificationComponent/BridgeImpl.java)

The class that call the LearnPAd platform API is described [here](./src/main/java/eu/learnpad/verificationComponent/PlatformFacadeImpl.java)

The service is available at 'localhost:9998/rest'.

