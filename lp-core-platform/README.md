Core Platform component
=======================

Information   | Value
------------- | --------
Component     | Core Platform
Partner       | XWIKI, CNR
WP            | 2
Responsible   | Jean Simard <jean.simard@xwiki.com>
Collaborators | Guglielmo De Angelis <guglielmo.deangelis@isti.cnr.it>
Roadmap       | http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Template

# Summary
This is the core of the Learn PAd platform.  It provides controllers for every
component of the platform.
[http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Components/](here).

# How it works?
Core Platform component works as a bus.  It offers interfaces to the components
and notify other components.

Core Platform is accessible on port `8080`.

# Configuration
There is no possible configuration at the moment.

# Interfaces
All interfaces are defined as REST interfaces.  The source code being Java, it
means that you may also use the Java interface instead of the web service.

See on the [http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Components/](wiki) for
more information.

All the interfaces exposed/used as XWIKI components **MUST** be added in the file (components.txt)[https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform/lp-cp-xwiki-implementation/src/main/resources/META-INF/components.txt]
Futher configurations about the components should be set inside (learnpad.properties.vm file)[https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform/lp-cp-conf/src/main/resources/learnpad.properties.vm], or better inside (lp-platform/lp-platform-distributions/pom.xml)[https://github.com/LearnPAd/learnpad/blob/master/lp-platform/lp-platform-distributions/pom.xml]
