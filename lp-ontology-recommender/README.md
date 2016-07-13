Collaboration workspace's component
===================================

Information   | Value
------------- | --------
Component     | Ontology Recommender
Partner       | FHNW
WP            | 5
Responsible   | Sandro Emmenegger <sandro.emmenegger@fhnw.ch>
Collaborators | CongYu Zhang <congyu.zhang@fhnw.ch>, Andreas Martin <andreas.martin@fhwn.ch>
Roadmap       | http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Ontology+Recommender

# Summary
The Collaborative Workspace is the main entry-point for end-user.  It's the
place where learners will browse the documented processes.  For more
information, see the
[http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Collaborative+workspace](wiki).

# How it works?
Based on XWiki, the processes are transformed into a XWiki structure of data.
On top of these data, there is a application that will allow to display and
browse these data in a user-friendly interface.

This application is developed with XWiki scripts.  To help in this development,
a XWiki service has been provided to facilitate access to data.

# Configuration
Since this component is based on XWiki, the configuration is mainly specific to
XWiki.  You should find any information on
[http://www.xwiki.org/xwiki/bin/view/Main/Documentation](xwiki.org).

# Interfaces
You can see the description of interfaces on the
[http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Components/Collaborative+Workspace](wiki).

# KPI data sources and assessment
The model driven KPI's for individuals (employees) as well as for organisational units are assessed and calculated with SPARQL inferencing rules (SPIN). The data for the KPI assessment is collected from 3 sources:

 - Platform activity (log) 
 - Process simulation scores
 - External data (Excel)

The Excel files based on a template filled with employees data from regular self and supervised assessments. 
Up to now there is no userinterface for the file upload. Therefore the the Excel files have to be placed into a working folder resp. subfolder defined in the components properties file (application.properties): 

*working.directory=/usr/local/xwiki-workdir*
...
*kpi.dashboard.data.folder.relative=dashboard*

This results in a working folder for KPI dashboard data like  */usr/local/xwiki-workdir/dashboard* for the Excel data files.