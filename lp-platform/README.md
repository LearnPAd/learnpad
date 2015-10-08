Core Platform component
=======================

Information   | Value
------------- | --------
Component     | Platform
Partner       | XWIKI, CNR
WP            | 2
Responsible   | Jean Simard <jean.simard@xwiki.com>
Collaborators | Guglielmo de Angelis <guglielmo.deangelis@isti.cnr.it>
Roadmap       | http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Template

# Summary
This is the core of the Learn PAd platform.  It provides the global platform as
an XWiki instance that you can deploy.

# How it works?
Platform is a XWiki instance that integrates all the XWiki components and
services built from other components.

If you want to register your work (JAR or XAR at the moment) to be build into
this platform, you just need to add you work as a dependency into the `pom.xml`
from the Maven module `lp-platform-jetty-hsqldb`; it will be automatically put
into the WAR of the XWiki instance.

# Configuration
There is no possible configuration at the moment.
