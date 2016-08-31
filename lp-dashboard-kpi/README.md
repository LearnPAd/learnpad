Collaboration workspace's component
===================================

Information   | Value
------------- | --------
Component     | KPI Dashboard
Partner       | FHNW
WP            | 5
Responsible   | Sandro Emmenegger <sandro.emmenegger@fhnw.ch>
Collaborators | 
Roadmap       | http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Ontology+Recommender

# Summary
This component provides the Dasbhoard KPI component for the Learn PAd platform. 
It is currently structured in 2 sub-components:
 * lp-dash-bridge : which implements the interfaces mediating the interactions with the [`Learn PAd Core Platform`](https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform)
 * lp-dash-adoxx-cockpit : which provides a KPI dasbhoard UI based on ADOXX

By default: 
 * the **lp-dash-bridge** module offers a servlet responding on the port ``7078``
 * the **lp-dash-adoxx-cockpit** is deployed on the port ``9098``

In order to change this configurations, please refer to the specific documentation of each sub-component.
 
 
# Build
In the `lp-dashboard-kpi` folder, you can give one of the following options to
the build script:
* `--marche`

These options impacts the [`build` script](https://github.com/LearnPAd/learnpad/blob/master/lp-dashboard-kpi/build),
the main configurations are defined in [`configuration.properties`](https://github.com/LearnPAd/learnpad/blob/master/lp-dashboard-kpi/lp-dash-bridge/src/main/resources/config/component.properties).

## `marche` mode
The marche mode is only about a preconfiguration of the addresses for
both the component and its sub-components according to the deploy foreseen at MAR.  
This configuration impacts:
* the [`build` script](https://github.com/LearnPAd/learnpad/blob/master/lp-dashboard-kpi/build).
* the [`start` script](https://github.com/LearnPAd/learnpad/blob/master/lp-dashboard-kpi/scripts/start-marche).
* the [configuration file](https://github.com/LearnPAd/learnpad/blob/master/lp-dashboard-kpi/lp-dash-bridge/src/main/resources/config/componentMarche.properties) for the deloy at MAR