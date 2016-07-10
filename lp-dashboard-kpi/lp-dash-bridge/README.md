Collaboration workspace's component
===================================

Information   | Value
------------- | --------
Component     | KPI Dashboard -- Bridge
Partner       | CNR, FHNW
WP            | 5
Responsible   | Sandro Emmenegger <sandro.emmenegger@fhnw.ch>
Collaborators | Guglielmo De Angelis <guglielmo.deangelis@isti.cnr.it>
Roadmap       | http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Dashboard+KPI

# Summary
Bridge of the Dashboard KPI component. It implements the interfaces mediating the interactions with the [`Learn PAd Core Platform`](https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform)

 
By default this module offers a servlet responding on the port ``7078``
The configurations are specified in : [`config/component.properties`](https://github.com/LearnPAd/learnpad/blob/master/lp-dashboard-kpi/lp-dash-bridge/src/main/resources/config/component.properties)

The current implementation of the LP-DASH **DOES NOT** support authentication.

# Interfaces
## Consumes
### Model Set Imported
**Description**
Notifies the LP-DASH component that a new model set has been imported in the Learn PAd platform.
The Learn PAd Core Platform (LP-CP) usually invoke this operation.

**cURL**
```
curl \
	--verbose \
	--request PUT \
	--header "Accept: application/xml" \
	"http://localhost:7078/learnpad/dash/bridge/modelsetimported/<modelsetid>?type={ADOXX|MD|LPZIP}"
```

**Input**
 * modelsetid : the id of the modelset that has been imported
 * type : the type of the imported modelset 

**Output**

**Details**

### Notify KPI Values
**Description**
Loads in the LP-DASH component a set of computed KPI values.
Such computation is mediated by means of the LP-CP, and currently it is mainly a responsibility of the LP-OR component.
Nevertheless, also other component may contribute to such computation.

**cURL**
```
curl \
	--verbose \
	--request PUT \
	--header "Content-Type: application/xml" \
	--data-binary "@<kpiValuesFile.xml>" \
	"http://localhost:7078/learnpad/dash/bridge/loadkpivalues/<modelsetid>?businessactor={businessActorID}&format={ADOXXCockpit}"
```

**Input**
 * modelsetid : the id of the modelset that has been imported
 * businessactor : the id of the Business Actor which the KPI Values refers to.
 * format : the format of the XML contents (i.e. the KPI values) in the body of the request
 * request body (i.e. kpiValuesFile.xml) : the KPI values, maybe stored in a file.

**Output**

**Details**


### View KPI Values
**Description**
Return the URL where it is possible to view the KPI Values stored in the LP-DASH component.

**cURL**
```
curl \
	--verbose \
	--request GET \
	"http://localhost:7078/learnpad/dash/bridge/view/<modelsetid>?businessactor={businessActorID}"
```

**Input**
 * modelsetid : the id of the model set that has been imported
 * businessactor : the id of the Business Actor which the KPI Values refers to.

**Output**
 * a URL where it is possible to access the KPI values for the given Business Actor on the specified model set.
**Details**
This operation depends on the **lp-dash-adoxx-cockpit** sub-component
