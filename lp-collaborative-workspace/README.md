Collaboration workspace's component
===================================

Information   | Value
------------- | --------
Component     | Collaboration Workspace (CW)
Partner       | XWIKI
WP            | 2, 3, 5
Responsible   | Jean Simard <jean.simard@xwiki.com>
Collaborators | Caleb J. DeLisle <cjd@cjdns.fr>, Daniele Gagliardi <daniele.gagliardi@students.fhnw.ch>
Roadmap       | http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Collaborative+Workspace

# Summary
The Collaborative Workspace is the main entry-point for end-user.  It's the
place where learners will browse the documented processes.  For more
information, see the
[http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Collaborative+workspace](wiki).

# Fixes
The clickable image based navigation feature is dependent on ADOXX model type pending release of a
standardized image map file. See [`LPUI.BaseElementSheet`](https://github.com/LearnPAd/learnpad/blob/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPUI/BaseElementSheet.xml) for the code.
ADOXX is exporting a map-like file which might be the basis for a LearnPAd standard but:
* Needs to be standardized (currently it is only an ADOXX specific file)
* Should (to ensure the current level of stability) contain the size of the entire image in units which are equivalent to the units specified for the coordinents.
* To provide tooltips with the name of the model element, should contain the element names in the `<AREA>` tags.

# How it works?
Based on XWiki, the processes are transformed into a XWiki structure of data.
On top of these data, there is a application that will allow to display and
browse these data in a user-friendly interface.

This application is developed with XWiki scripts.  To help in this development,
a XWiki service has been provided to facilitate access to data.

Collaborative Workspace is accessible on port `8080` (as part of the core
platform).

# Configuration
Since this component is based on XWiki, the configuration is mainly specific to
XWiki tools.  You should find any information on
[http://www.xwiki.org/xwiki/bin/view/Main/Documentation](xwiki.org).

# Interfaces
## Provides
All interfaces that Collaborative Workspace should provide are declared in
[`lp-cp-apis`](https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform/lp-cp-apis/src/main/java/eu/learnpad/cw/BridgeInterface.java).
The corresponding implementation is the class
[`CWXWikiBridge`](https://github.com/LearnPAd/learnpad/blob/master/lp-collaborative-workspace/lp-cw-bridge/lp-cw-bridge-implementation/src/main/java/eu/learnpad/cw/CWXwikiBridge.java).

### Notification about a new imported modelset
**Description**
The Core Platform (CP) is notifying the Collaborative Workspace (CW) that a new
modelset has been successfully imported.

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request PUT \
	"http://localhost:8080/xwiki/rest/learnpad/cw/bridge/modelsetimported/<modelsetid>?type={ADOXX,MD}"
```

**Details**
When a modelset is pushed to the platform, a model verification is trigger by
the [Modelling Environment
Controller](https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform/lp-cp-xwiki-implementation/src/main/java/eu/learnpad/core/impl/me/XwikiController.java).
Once the Model Verification (MV) is finished, he sends back a notification to
[Core Platform
(CP)](https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform/lp-cp-xwiki-implementation/src/main/java/eu/learnpad/core/impl/mv/XwikiController.java),
which, if verification is OK, will notify other components (CW, OR, SIM) that a
new modelset is available.

### Notification about a verified model
**Description**
The Core Platform (CP) is notifying the Collaborative Workspace (CW) that a new
model has been successfully verified.

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request PUT \
	"http://localhost:8080/xwiki/rest/learnpad/cw/bridge/modelverified/<modelsetid>?result={OK,KO}"
```

**Details**
NOT IMPLEMENTED.
The Collaborative Workspace (CW) uses instead the `modelsetimported`
notification to decide to import a new model.  `modelverified` interface has
been designed very early in the project but should probably be removed now.

### Get the feedbacks
**Description**
User of the platform (civil-servants) may want to apply modifications on the
models.  Since they are not modelers, they will communicate with the modelers
through textual content: the feedbacks.  They create these feedbacks in the
wiki; the modelers can gather these feedbacks in the Modeling Environment (ME).

There is also the possibility to return machine structure feedbacks that can be
applied directly to the models (user will only validate or not): these are
_patches_.

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request GET \
	--header "Accept: application/xml" \
	"http://localhost:8080/xwiki/rest/learnpad/cw/bridge/<modelsetid>/feedbacks"
```

**Output**
Output of this interface is an `PFResults` object.  This class is automatically
generated at build time from a
[XML-Schema](https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform/lp-cp-apis/src/main/resources/PFResults.xsd); you should be able to look at the generated classes in the folder `lp-cp-apis/target/generated-sources/xjc`.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<PFResults>
	<patches>
		<patch id="123" type="add" modelid="123">
			<artefact id="" name="sdfds" className="Task">
				<attribute id="123" name="name">adsfdsafY</attribute>
				<attribute id="234" name="xs">adsfdsafY</attribute>
				<attribute id="345" name="sd">adsfdsafY</attribute>
			</artefact>
		</patch>
		<patch id="123" type="edit" modelid="123">
			<artefact id="123" name="sdfasdf" className="Task">
				<attribute id="123" name="name">adsfdsafY</attribute>
				<attribute id="234" name="xs">adsfdsafY</attribute>
				<attribute id="345" name="sd">adsfdsafY</attribute>
			</artefact>
		</patch>
		<patch id="123" type="delete" modelid="123">
			<artefact id="123" name="sdfasdf" className="End Event"></artefact>
		</patch>
	</patches>
	<feedbacks>
		<feedback id="123" modelid="123" artefactid="212">sadfdsfdsf</feedback>
		<feedback id="123" modelid="123" artefactid="212">sadfdsfdsf</feedback>
		<feedback id="123" modelid="123" artefactid="212">sadfdsfdsf</feedback>
		<feedback id="123" modelid="123" artefactid="212">sadfdsfdsf</feedback>
	</feedbacks>
</PFResults>
```

**Details**
Feedbacks is an App Within Minute from the Collaborative Workspace (CW) (see
[`FeedbackClass`](ttps://github.com/LearnPAd/learnpad/blob/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPCode/FeedbackClass.xml)
and
[`FeedbackSheet`](https://github.com/LearnPAd/learnpad/blob/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPUI/FeedbackSheet.xml)).  At the moment, there is nothing implemented able to generate patches, only feedbacks can be created.

### Notify a new recommendation
**Description**
Collaborative Workspace (CW) is able to look for recommendations by himself (in
browsing mode).  However, during simulation, recommendations may change without
page refreshment.  In this case, the Simulation Environment (SIM) send events
about the simulation to the Core Platform (CP).  The Core Platform (CP) send
these information to the Ontology Recommender (OR) to obtain a up-to-date
version of the recommendations.  This new version is then sent to the
Collaborative Workspace (CW).

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request PUT \
	--header "Content-Type: application/xml" \
	--data-binary "@recommendations.xml" \
	"http://localhost:8080/xwiki/rest/learnpad/cw/bridge/notify/<modelsetid>?simulationid=<simid>&userid=<userid>"
```

`userid` is the email of the user.

**Input**
Input of this interface is defined by the [`Recommendations`
object](https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform/lp-cp-apis/src/main/java/eu/learnpad/or/rest/data/Recommendations.java).

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<recommendations>
	<experts>
		<businessActor>
			<description>The line manager of the organisational unit recommended as expert</description>
			<email>sally.shugar@learnpad.com</email>
			<name>Sally Shugar</name>
			<phoneNumber>+39 0733 656 355</phoneNumber>
			<uri>http://learnpad.eu/transfer#obj.27426</uri>
		</businessActor>
		...
	</experts>
	<learningMaterials>
		<learningMaterial>
			<comment>This material should be updated ...</comment>
			<description>This web page contains the required learning material to improve ...</description>
			<id>http://learnpad.eu/transfer#obj.27311</id>
			<mimeType>text/html</mimeType>
			<name>MR: Learning Platform</name>
			<queryDescription>Recommend learning material related to the competency level above the users comptency level considering the learning style</queryDescription>
			<url>http://marlenedidateca.regione.marche.it/moodle/mod/scorm/view.php?id=30699</url>
		</learningMaterial>
		...
	</learningMaterials>
	<similarCases>
		<similarCase>
			...
		</similarCase>
		...
	</similarCases>
</recommendations>
```

**Details**
The recommendations panel is displayed asynchroneously on the
[`SimulationEnvironment`
page](https://github.com/LearnPAd/learnpad/blob/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPUI/SimulationEnvironment.xml).
The best way would be that the server is notifying directly the client
(webbrowser) about a new recommendation (websocket?).  However, this would imply
websocket architecture on the server side (client side should be pretty
straightforward to implement).  The actual solution is: client-side is
refreshing every second the panel.  On server side, every new recommendation is
stored in a static map in the
[bridge](https://github.com/LearnPAd/learnpad/blob/master/lp-collaborative-workspace/lp-cw-bridge/lp-cw-bridge-implementation/src/main/java/eu/learnpad/cw/CWXwikiBridge.java).

Also, the currenly release of the ME
include in the ZIP file (i.e. the ones used in the experiments) a BPMN version
of the processes that is not executable (by Activity). So actually the SIM is not relying on the
BPMN files the ME includes in the ZIP but on some `test file` embedded in the 
SIM component. Thus it was necessary to hard-code some IDs in the CW
(see [here](https://github.com/LearnPAd/learnpad/tree/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPUI/SimulationEnvironment.xml#L60-L91) ).
Specifically this hard-coded IDs are for the moment
necessary,  since the ID may change with any ME export proceduere.
In conclusion, the simulaton should only work for the models
imported in `src/test/resources/LearnPAd-ValidationMAR-Models/SUAP-TitoloUnico/CorrectModels/LP_ME_ADOXX_MODELSET_28600.zip`

**Users**
A default set of users which can be used testing the platform with [the models used during the Validation at MAR](https://github.com/LearnPAd/learnpad/tree/master/lp-model-environment/src/test/resources/LearnPAd-ValidationMAR-Models/SUAP-TitoloUnico/CorrectModels/LP_ME_ADOXX_MODELSET_28600.zip) can be found 
[`here`](https://github.com/LearnPAd/learnpad/tree/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPUsers/ 	UsersListY3Demo.xml). Such users list can be enabled by logging in as `Admin` and launching the [`AddUsersScript`](https://github.com/LearnPAd/learnpad/tree/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPUsers/AddUsersScript.xml) which is accessible at `<platform_uri>/xwiki/bin/view/LPUsers/AddUsersScript`.


## Consumes
### Get a model
**Description**
Download a model from the Core Platform (CP) based on the type and modelset ID.

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request GET \
	--header "Accept: application/xml" \
	"http://localhost:8080/xwiki/rest/learnpad/cw/corefacade/getmodel/<modelsetid>?type={ADOXX,MD}"
```

**Details**
The modelset is stored on the Core Platform (CP) as attachment of a page named
after the modelset ID inside the space `CoreRepository`.

### Transform a model
**Description**
Send a raw model (ADOXX or MD) to the transformer which will send back a [XFF
package](http://extensions.xwiki.org/xwiki/bin/view/Extension/XFF+filter) which represent an XWiki structure.

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request POST \
	--header "Accept: application/octet-stream" \
	--header "Content-Type: application/octet-stream" \
	--data-binary "@modelset.xml" \
	"http://localhost:8080/xwiki/rest/learnpad/cw/corefacade/transform?type={ADOXX,MD}"
```

**Details**
This method is only a proxy to access Model Transformer (MT) through the Core
Platform (CP).

### Get a recommendation
**Description**
When in browsing mode, the right-side panel is displaying recommendations based
on contextual information like the current user and the page being displayed.

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request GET \
	--header "Accept: application/xml" \
	"http://localhost:8080/xwiki/rest/learnpad/cw/corefacade/recommendation?modelsetid=<modelsetid>&artifactid=<artifactid>&userid=<email>"
```
It is possible to send an empty `artifactid`.

**Output**
The returned value is from type [`Recommendations`](https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform/lp-cp-apis/src/main/java/eu/learnpad/or/rest/data/Recommendations.java).

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<recommendations>
	<experts>
		<businessActor>
			<description>The line manager of the organisational unit recommended as expert</description>
			<email>sally.shugar@learnpad.com</email>
			<name>Sally Shugar</name>
			<phoneNumber>+39 0733 656 355</phoneNumber>
			<uri>http://learnpad.eu/transfer#obj.27426</uri>
		</businessActor>
		...
	</experts>
	<learningMaterials>
		<learningMaterial>
			<comment>This material should be updated ...</comment>
			<description>This web page contains the required learning material to improve ...</description>
			<id>http://learnpad.eu/transfer#obj.27311</id>
			<mimeType>text/html</mimeType>
			<name>MR: Learning Platform</name>
			<queryDescription>Recommend learning material related to the competency level above the users comptency level considering the learning style</queryDescription>
			<url>http://marlenedidateca.regione.marche.it/moodle/mod/scorm/view.php?id=30699</url>
		</learningMaterial>
		...
	</learningMaterials>
	<similarCases>
		<similarCase>
			...
		</similarCase>
		...
	</similarCases>
</recommendations>
```

**Details**
Recommendations obtained in this context (browsing mode) usually doesn't contain
any Similar Case in the results since they are mainly related to contextual data
of a particular case (mostly obtained in the context of simulation).

### Start a semantic analysis
**Description**
On the wiki, you'll have collaborative documents that everybody can contribute
too.  These documents will be parsed in order to track inconsistencies or syntax
errors.

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request POST \
	--header "Content-Type: text/plain" \
	--data-binary "@document.txt" \
	"http://localhost:8080/xwiki/rest/learnpad/cw/corefacade/analyze?id=<documentid>&language={english|italian}&option={simplicity|non_ambiguity|content_clarity|presentation_clarity|completeness|correctness}"
```

* `documentid` is a unique ID to identify the document that is sent; from XWiki,
  we use the serialization of the
  [`DocumentReference`](http://extensions.xwiki.org/xwiki/bin/view/Extension/Model+Module)
  to the document.
* `options` can be used multiple time, for example
  `?option=simplicity&option=completeness&option=correctness`

**Input**
The input is splitted into 3 parts, all separated by a line `-*-*-`.  The parts
are the following:
1. Title of the document
2. Plain content of the document, without XWiki syntax
3. Structured content of the document, as HTML

```
Some title
-*-*-
This is the plain content.
-*-*-
<em>This</em> is the <b><code>HTML</code> content</b>.
```

**Output**
The return value is only a string representing the ID of the launched analysis
so we could use it later to ask the progress of the analysis.

**Details**
The code that call this API is inside the javascript of the
[`CollaborativeDocumentSheet`](https://github.com/LearnPAd/learnpad/blob/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPUI/CollaborativeDocumentSheet.xml).
To forge this request, it needs to get a plain content which is generated with
the help of
[`Render`](https://github.com/LearnPAd/learnpad/blob/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPCode/Render.xml).

### Get the status of a semantic analysis
**Description**
Once you launched a semantic analysis, you can ask the status of this analysis
to know if it's finished or in progress.  You'll use the analysis ID (see _Start
a semantic analysis_).

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request GET \
	--header "Accept: text/plain" \
	"http://localhost:8080/xwiki/rest/learnpad/cw/corefacade/analyze/<analysisid>/status"
```

**Output**
The return value is float representing the percentage of completion of the
analysis (value between 0 and 1).  If any error happen, -1.0 will be returned.

**Details**
This request is called every second from
[`CollaborativeDocumentSheet`](https://github.com/LearnPAd/learnpad/blob/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPUI/CollaborativeDocumentSheet.xml)
in order to produce a progress bar about the analysis.

### Get the results of a semantic analysis
**Description**
Once you launched a semantic analysis and verified that the analysis is
finished, you can ask for the final results.

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request GET \
	--header "Accept: text/plain" \
	"http://localhost:8080/xwiki/rest/learnpad/cw/corefacade/analyze/<analysisid>"
```

**Output**
The result is encoded in a object
[`AnnotatedCollaborativeContentAnalyses`](https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform/lp-cp-apis/src/main/java/eu/learnpad/ca/rest/data/collaborative/AnnotatedCollaborativeContentAnalyses.java).

```xml
<?xml version="1.0" encoding="UTF-8"?>
<annotatedCollaborativeContentAnalysis id="9324" type="Non Ambiguity">
	<CollaborativeContent id="1213">
		<Title>Title of Documents</Title>
		<Content>
			The document shall be sent to the <Node id="30"/>proper<Node id="36"/> authorities <Node id="48"/>as soon as possible<Node id="66"/>
		</Content>
	</CollaborativeContent>
	<Annotations>
		<Annotation
			id="0"
			type="Non ambiguity"
			StartNode="30"
			EndNode="36"
			recommendation="The term proper is vague"
			StartNode_Offset="35"
			EndNode_Offset="41" />
		<Annotation
			id="2"
			type="Non ambiguity"
			StartNode="48"
			EndNode="66"
			recommendation="The as soon as possible is vague"
			StartNode_Offset="53"
			EndNode_Offset="73" />
	</Annotations>
	<OverallQuality>BAD</OverallQuality>
	<OverallQualityMeasure>50%</OverallQualityMeasure>
	<OverallRecommendations>Use more precise terms!</OverallRecommendations>
</annotatedCollaborativeContentAnalysis>
```

**Details**
For the moment, these results are gathered in the javascript code of
[`CollaborativeDocumentSheet`](https://github.com/LearnPAd/learnpad/blob/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPUI/CollaborativeDocumentSheet.xml)
but are not used or displayed.

### Start a simulation
**Description**
For each Business Process, you should be able to launch a simulation.  The
Collaborative Workspace (CW) ask for a new simulation to the Simulation
Environment (SIM) which will return a URL of the simulation inserted in a
`iframe`.

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request POST \
	--header "Content-Type: application/json" \
	--data-binary "@users.json" \
	"http://localhost:8080/xwiki/rest/learnpad/cw/corefacade/simulation/start/<modelid>?currentuser=<userid>"
```

**Input**
```json
[
	{
		"id": "XWiki.bbarnes",
		"firstName": "Barnaby",
		"lastName": "Barnes",
		"bio": "Got a Master in Psychology",
		"pictureURL": "https://www.xwikisas.com/xwiki/bin/download/XWiki/bbarnes/me.png",
		"profileURL": "https://www.xwikisas.com/xwiki/bin/view/XWiki/bbarnes"
	},
	{
		"id": "XWiki.sshugar",
		"firstName": "Sally",
		"lastName": "Shugar",
		"bio": "Got a Master in Psychology",
		"pictureURL": "https://www.xwikisas.com/xwiki/bin/download/XWiki/sshugar/me.png",
		"profileURL": "https://www.xwikisas.com/xwiki/bin/view/XWiki/sshugar"
	}
]
```

**Output**
In response, the Simulation Environment (SIM) gives a URL that can be used for
an iframe of the simulation.

**Details**
The simulation is having a bounding box that change (especially, the height that
grow).  It's possible to get the height of the inner iframe only if main window
and inner frame are on the same server which is not the case here.  The
Simulation Environment is then sending the height each time the height change,
towards the Collaborative Workspace (CW) using the `postMessage` WebAPI (see in
javascript code of
[`SimulationEnvironment`](https://github.com/LearnPAd/learnpad/blob/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPUI/SimulationEnvironment.xml)).

You can see the description of interfaces on the
[http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Components/Collaborative+Workspace](wiki).
