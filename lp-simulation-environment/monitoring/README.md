Monitoring sub-component
====================

Information   | Value
------------- | --------
Component     | Monitoring
Partner       | CNR
WP            | 6
Responsible   | Antonello Calabro' <antonello.calabro@isti.cnr.it>
Collaborators | 
Roadmap       | wiki.learnpad.eu/LearnPAdWiki/bin/view/Components/Monitoring

# Summary
This sub-component is in charge to monitor simulation and kpi related.
It is based on a prototype available at this link:
http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Components/Template (here).

# How it works?
Based on Publish Subscribe, JMS messages through ActiveMQ, Event correlation
through Complex Event Processor by means of Drools Engine.

# Configuration
The component is plugged into the Simulation Environment and
will be called through Rest API or JMS Messages.

# Interfaces
TODO
