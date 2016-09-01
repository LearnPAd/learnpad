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
An important part of the configuration is made at build time.

## UI configuration
There is 2 places where UI is configured at build time.  First of all, there is
[skin](https://github.com/LearnPAd/learnpad/tree/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-skin)
for Learn PAd that make disappear a list of UI element like header part; you can
look inside the file
[`layoutExtraVars.vm`](https://github.com/LearnPAd/learnpad/blob/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-skin/src/main/resources/learnpad/layoutExtraVars.vm)
for more details.

Also, some of the configuration is realized at build time by Maven.  For
example, all the left and right panels are configured at build time.  You'll
find these configuration elements in the submodule
[`lp-platform-conf`](https://github.com/LearnPAd/learnpad/tree/master/lp-platform/lp-platform-conf).

## Network configuration
The Learn PAd platform is a collection of component that all communicate through
REST API.  Therefore, it should be possible to configure the REST addresses of
each of these components.  The Maven submodule
[`lp-cp-conf`](https://github.com/LearnPAd/learnpad/tree/master/lp-core-platform/lp-cp-conf)
make that possible by creating a file `learnpad.properties` in the final build.
You should be able to find this file once the build is completed, next to
`xwiki.properties` or `xwiki.cfg`, inside the folder
`lp-platform/out/xwiki/webapps/xwiki/WEB-INF/`.

This file provide a default configuration which make possible to run all the
components in local.  See the default values provided
[here](https://github.com/LearnPAd/learnpad/blob/master/lp-platform/lp-platform-distributions/pom.xml).

# Build
To build the platform, you need of course to build all the other component of
the platform first.  Once done, you can build 3 different variants of the
platform with the help of profiles in Maven.

In the `lp-platform` folder, you can give one of the 3 following options to
the build script:
* `--dev`
* `--review`
* `--marche`

These profiles have influence in different part of the build and therefore may
be dispatched in all of these projects:
[`lp-platform-conf`](https://github.com/LearnPAd/learnpad/tree/master/lp-platform/lp-platform-conf),
[`lp-platform-distributions`](https://github.com/LearnPAd/learnpad/tree/master/lp-platform/lp-platform-distributions)
and
[`lp-platform-jetty-hsqldb`](https://github.com/LearnPAd/learnpad/tree/master/lp-platform/lp-platform-distributions/lp-platform-jetty-hsqldb). By enabling these profiles it may require to configure also others components runnig externally from the `lp-platform` (e.g. [`lp-dashboard-kpi`](https://github.com/LearnPAd/learnpad/tree/master/lp-dashboard-kpi), [`lp-content-analysis`](https://github.com/LearnPAd/learnpad/tree/master/lp-content-analysis), [`lp-simulation-environment`](https://github.com/LearnPAd/learnpad/tree/master/lp-simulation-environment)) 
The two following sections will give details about these modes.

## `dev` mode
This mode has influence on the configuration of the UI of the platform and on
the applications that will be installed on the platform.

About the UI configuration (see
[`lp-platform-conf`](https://github.com/LearnPAd/learnpad/tree/master/lp-platform/lp-platform-conf)),
some panels that should be hidden in production are now shown like the left
panel containing quick links towards some of the application (like
[WebIDE](http://extensions.xwiki.org/xwiki/bin/view/Extension/XWebIDE+Application)).
Also, bottom tabs, like _History_ and _Information_ are shown in `dev` mode.

Also, in order to facilitate the development of Learn PAd application in the
wiki, some applications has been installed specifically for development purpose:
* [WebIDE](http://extensions.xwiki.org/xwiki/bin/view/Extension/XWebIDE+Application)
* [Scripting Documentation
  Application](http://extensions.xwiki.org/xwiki/bin/view/Extension/Scripting+Documentation+Application)

## `review` mode
The review mode is only about a preconfiguration of every REST addresses for
components.  You'll find this configuration in
[`lp-platform-distributions`](https://github.com/LearnPAd/learnpad/blob/master/lp-platform/lp-platform-distributions/pom.xml).

## `marche` mode
The marche mode is only about a preconfiguration of every REST addresses for
components according to the deploy foreseen at MAR.  You'll find this configuration in
[`lp-platform-distributions`](https://github.com/LearnPAd/learnpad/blob/master/lp-platform/lp-platform-distributions/pom.xml).

# Details
## Maven dependencies
Something to be careful about is the XAR dependencies from the build.  Usually,
XAR Maven modules are declaring their own dependencies, possibly some JAR
dependencies.  And for some of them, their relative to XWiki modules that are
already part of the build.  However, Learn PAd is built on an XWiki 7.4.2 and
these XAR package may ask for an older or newer versions.  The dependency
resolution usually resolve towards the versions asked by the XAR package which
usually cause problems.  So every new XAR package added as a dependency should
be added with care.  Solution is to force the version of each dependency that
cause problem (see
[`lp-platform-distributions`](https://github.com/LearnPAd/learnpad/blob/master/lp-platform/lp-platform-distributions/pom.xml)).
