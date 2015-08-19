Verification component plugin
====================

Information   | Value
------------- | --------
Component     | Verification Plugin
Partner       | Unicam
WP            | 4
Responsible   | Andrea Polini <andrea.polini@unicam.it>
Collaborators | Damiano Falcioni <damiano.falcioni@unicam.it>
Roadmap       | http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Model+Verification

# Summary
This component is a plugin for the verification component that provide deadlock check capabilities over a LearnPAd model.

# How it works?
The plugin is specific for the Verification Component and provide deadlock verification of a LearnPAd model. In order to work it must be placed in the plugin folder defined on the Verification Component and it will be automatically recognized.
This operation is currently automated by maven during the installation phase of the Verification Component.

# Configuration
No configuration needed

# Interfaces
In order to be recognized by the Verification Component, this plugin expose the following interface https://github.com/damianofalcioni/learnpad/blob/master/lp-verification-component-deadlock-plugin/src/main/java/eu/learnpad/verification/plugin/interfaces/Plugin.java 
and define this MANIFEST.MF https://github.com/damianofalcioni/learnpad/blob/master/lp-verification-component-deadlock-plugin/src/main/resources/custom/MANIFEST.MF

