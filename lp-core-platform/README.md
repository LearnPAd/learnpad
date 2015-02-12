Core Platform component
=======================

Information   | Value
------------- | --------
Component     | Core Platform
Partner       | XWIKI, CNR
WP            | 2
Responsible   | Jean Simard <jean.simard@xwiki.com>
Collaborators | Guglielmo de Angelis <guglielmo.deangelis@isti.cnr.it>
Roadmap       | wiki.learnpad.eu/LearnPAdWiki/bin/view/Components/Template

# Summary
This is the core of the Learn PAd platform.  It provides controllers for every
component of the platform.
[http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Components/](here).

# Configuration
There is no possible configuration at the moment.

# How it works?
Core Platform component works as a bus.  It offers interfaces to the components
and notify other components.

# Interfaces
All interfaces are defined as REST interfaces.  The source code being Java, it
means that you may also use the Java interface instead of the web service.

## API
See on the [http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Components/](wiki) for
more information.

## External calls
Core platform will communicate with all the other components.
