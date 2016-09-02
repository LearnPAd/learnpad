LearnPAd Content Analysis Component
==================
[![License](https://img.shields.io/badge/License-GPL-blue.svg)](https://github.com/ISTI-FMT-LearnPAd/ContentAnalysisComponent/blob/master/LICENSE) 




Status project [![Build Status](https://travis-ci.org/ISTI-FMT-LearnPAd/ContentAnalysisComponent.svg?branch=develop)](https://travis-ci.org/ISTI-FMT-LearnPAd/ContentAnalysisComponent)

[![codecov.io](https://codecov.io/github/ISTI-FMT-LearnPAd/ContentAnalysisComponent/coverage.svg?branch=develop)](https://codecov.io/github/ISTI-FMT-LearnPAd/ContentAnalysisComponent?branch=develop)


Information   | Value
------------- | --------
Component     | Content Analysis
Partner       | CNR
WP            | 4
Responsible   | Alessio Ferrari <alessio.ferrari at isti.cnr.it>
Collaborators | Giorgio O. Spagnolo <spagnolo at isti.cnr.it>
Roadmap       | [wiki](https://github.com/ISTI-FMT-LearnPAd/ContentAnalysisComponent/wiki)
LearnPAd Wiki | [http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Content+Analysis](http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Content+Analysis)

# Summary
This is the Content Analysis component of the LearnPAd platform. It implements automated procedures to verify that the textual content that describes the tasks of a
Business Process (e.g., documents created in the Collaborative Workspace) provides information
that is consistent with respect to the Business Process model itself, and to automatically identify
ambiguous sentences and vague terms in natural language requirements, and estimates quantitative
indexes concerning the linguistic quality of the contents. 

#How it works?
This component expose REST API to the LearnPAd platform 
in order to perform several kind of content analysis on a Collaborative and Static Content. 
The different types of content analysis are provided by the component es. simplicity, non ambiguity, content clarity, presentation clarity, completeness and correctness.

In this page [Collaborative](http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Collaborative+Content+Analysis) content is present the XML input and output of component.

In this page [Static](http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Static+Content+Analysis) content is present the XML input and output of component.

The component is ready to be packaged as a WAR to be deployed on an Application Server like Tomcat.

The component is also to be packaged as a JAR and it deployed in an Grizzly HTTP server.

To build/run component [click here](https://github.com/ISTI-FMT-LearnPAd/ContentAnalysisComponent/wiki/Build-Run-Component)

The service is available at `localhost:8082/lp-content-analysis/`.



#CURL Test
 * To send CollaborativeContent XML to component, the reply is a ID:  
`curl -X POST -H "Content-Type: application/XML" --data @CollaborativeContentXML.xml http://localhost:8082/lp-content-analysis/learnpad/ca/bridge/validatecollaborativecontent/`

* To send StaticContent XML to component, the reply is a ID:  
`curl -X POST -H "Content-Type: application/XML" --data @StaticContentXML.xml http://localhost:8082/lp-content-analysis/learnpad/ca/bridge/validatestaticcontent/`

* To get Status of analysis of Collaborative Content referred by ID:  
`curl -X GET http://localhost:8082/lp-content-analysis/learnpad/ca/bridge/validatecollaborativecontent/{id}/status`

* To get Status of analysis of Static Content referred by ID:  
`curl -X GET http://localhost:8082/lp-content-analysis/learnpad/ca/bridge/validatestaticcontent/{id}/status`

* To get Collaborative Content referred by ID:  
`curl -X GET http://localhost:8082/lp-content-analysis/learnpad/ca/bridge/validatecollaborativecontent/{id}`
* To get Static Content referred by ID:  
`curl -X GET http://localhost:8082/lp-content-analysis/learnpad/ca/bridge/validatestaticcontent/{id}`

# Build
In the `lp-content-analysis` folder, you can give one of the following options to
the build script:
* `--marche`

These options impacts on the [`build` script](https://github.com/LearnPAd/learnpad/blob/master/lp-content-analysis/build), and
their main configurations are defined in dedicated [configuration file](https://github.com/LearnPAd/learnpad/blob/master/lp-content-analysis/src/main/resources/).

## `marche` mode
The marche mode is only about a preconfiguration of the addresses for
both the component and its sub-components according to the deploy foreseen at MAR.
This configuration impacts:
* the [`build` script](https://github.com/LearnPAd/learnpad/blob/master/lp-content-analysis/build).
* the [`start` script](https://github.com/LearnPAd/learnpad/blob/master/lp-content-analysis/scripts/start-marche).
* the [configuration file](https://github.com/LearnPAd/learnpad/blob/master/lp-content-analysis/src/main/resources/componentMarche.properties) for the deloy at MAR

# Note
You can also refer to 
`http://localhost:8082/lp-content-analysis/application.wadl`
Wiki of repo is [here](https://github.com/ISTI-FMT-LearnPAd/ContentAnalysisComponent/wiki)


