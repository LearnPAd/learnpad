Model Environment's component
===================================

Information   | Value
------------- | --------
Component     | Model Environment (ME)
Partner       | BOC, NME
WP            | 3
Responsible   | Nesat Efendioglu  <Nesat.Efendioglu@boc-eu.com>, Jovaldas Januškevičius <jovaldas.januskevicius@nomagic.com>
Collaborators | Darius Šilingas <darius.silingas@nomagic.com>, Robert Woitsch <Robert.Woitsch@boc-eu.com>
Roadmap       | http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Modelling+Environment

# Summary
The Modelling Environment (ME) is the main entry-point for modelers.  It's where the
models will be designed and then pushed towards the Learn PAd platform.  For
more information, see the
[http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Modelling+Environment](wiki).

# How it works?
There is 2 different technologies here: Adoxx software and MagicDraw software.
They will both produces Learn PAd modelsets.  They will push towards the Learn
PAd platform an archive that contains a few things:
* an XML file representing the complete Learn PAd modelset instance
* an image for every model from the modelset
* a map defining clickable area of the image for each model of the modelset
* a BPMN 2.0 XML file for every business process in the modelset

Modelling Environment (ME) component has a few specificities.  First of all,
it's not at all developed inside this source repository, it's an external
software; so you'll only find documentation about this component, not any
source-code.  Also, these software are desktop applications: they can be
shutdown; so the whole process of communication between Modelling Environment
(ME) and Learn PAd Core Platform (CP) is always one-way: from Modelling
Environment (ME) towards Learn PAd Core Platform (CP).

# Configuration
The Modelling Environment (ME) has to configure the address and port at which it
can access the Learn PAd platform.

# Interfaces
## Consumes
### Push a modelset
**Description**
Push a modelset archive towards the Learn PAd platform.  The Learn PAd Core
Platform (CP) will immediately store this file inside a page named after the
modelset ID into the `CoreRepository` space of the wiki.  However, before
telling every component that a new modelset is available, it will launch a model
verification.  The verification ID will be returned to the Modelling Environment
(ME).

The models used during :
 * the demo for both the Y2 Review, 
 * the validation run at MAR 
are all available in ``src/test/resources/``. However, note that currenly the ME
include in the ZIP file (i.e. the ones used in the experiments) a BPMN version
of the processes that is not really executable (by Activity). So actually the SIM is not relying on the
BPMN files the ME includes in the ZIP but on some `test file` embedded in the 
SIM component. Thus it was necessary to hard-code some IDs in the CW
(see [here](https://github.com/LearnPAd/learnpad/tree/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPUI/SimulationEnvironment.xml#L60-L91) ).
Specifically this hard-coded IDs are for the moment
necessary,  since the ID may change with any ME export proceduere.
In conclusion, the simulaton should only work for the models
imported in `src/test/resources/LearnPAd-ValidationMAR-Models/SUAP-TitoloUnico/CorrectModels/LP_ME_ADOXX_MODELSET_28600.zip`

A default set of users which can be used testing the platform with [the models used during the Validation at MAR](https://github.com/LearnPAd/learnpad/tree/master/lp-model-environment/src/test/resources/LearnPAd-ValidationMAR-Models/SUAP-TitoloUnico/CorrectModels/LP_ME_ADOXX_MODELSET_28600.zip) can be found 
[`here`](https://github.com/LearnPAd/learnpad/tree/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPUsers/ 	UsersListY3Demo.xml). Such users list can be enabled by logging in as `Admin` and launching the [`AddUsersScript`](https://github.com/LearnPAd/learnpad/tree/master/lp-collaborative-workspace/lp-cw-component/lp-cw-component-ui/src/main/resources/LPUsers/AddUsersScript.xml) which is accessible at `<platform_uri>/xwiki/bin/view/LPUsers/AddUsersScript`.


**cURL**
```
curl \
	--verbose \
	--user "superadmin:LearnPAss" \
	--request PUT \
	--header "Content-Type: application/octet-stream" \
	--data-binary "@modelset.zip" \
	"http://localhost:8080/xwiki/rest/learnpad/me/corefacade/importmodelset/<modelsetid>?type={ADOXX|MD|LPZIP}"
```

**Input**
The ZIP archive contains:
* an XML file representing the complete Learn PAd modelset instance (either
  ADOXX or MagicDraw XML)
* an image for every model from the modelset (named after the model ID + any
  picture extension)
* a map defining clickable area of the image for each model of the modelset
  (named after the model ID + `.map` extension)
* a BPMN 2.0 XML file for every business process in the modelset (named after
  the model ID + `.bpmn` extension)

**Output**
The Learn PAd Core Platform (CP) return an object
[`VerificationId`](https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform/lp-cp-apis/src/main/java/eu/learnpad/mv/rest/data/VerificationId.java)
of the launched moodel verification.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<VerificationId>
	<Id>96e5736d-2d4c-41a1-a22d-e94e97ffe0b2</Id>
</VerificationId>
```

**Details**
Be careful about the use of `type` in the request.  At first, there was `adoxx`
or `md` as accepted value and they were corresponding to a push of the modelset
XML file only (not the whole archive).  Then we introduced the archive concept
and used the `lpzip` value.  But since nothing in the archive gives a clue about
the nature of the modelset XML (is it Adoxx or MagicDraw?) and that sending only
the XML file didn't make sense anymore, we used back the `ADOXX` and `MD` values
for the archives.  Note that now they're uppercase (mainly because Core Platform
(CP) is using a `enum` object now).  `LPZIP` may be used in the future if a
standardization of the modelset instance of a Learn PAd meta-model can be
reached.

### Start a verification
**Description**
Even if the Core Platform (CP) is launching a model verification every time a
new model is pushed, the Modelling Environment (ME) has also a direct access to
launch one itself (on a user decision mainly).  This API give the ability to
launch such a verification.

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request PUT \
	"http://localhost:8080/xwiki/rest/learnpad/me/corefacade/checkmodelset/start/<modelsetid>?type={ADOXX|MD|LPZIP}&verification={ALL|ONE DEADLOCK|ALL DEADLOCKS|END PLACES UNBOUNDEDNESS|ALL PLACES UNBOUNDEDNESS}"
```

**Output**
The Learn PAd Core Platform (CP) return an object
[`VerificationId`](https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform/lp-cp-apis/src/main/java/eu/learnpad/mv/rest/data/VerificationId.java)
of the launched moodel verification.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<VerificationId>
	<Id>96e5736d-2d4c-41a1-a22d-e94e97ffe0b2</Id>
</VerificationId>
```

**Details**
Note that in this case, the Modelling Environment can ask only about a certain
type of verification, not necessary all of them.

### Get the status of a model verification
**Description**
The model verification is performed asynchroneously.  Therefore, this API allow
to ask about the current status of the model verification (in progress,
finished, etc.).

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request GET \
	--header "Accept: application/xml" \
	  "http://localhost:8080/xwiki/rest/learnpad/me/corefacade/checkmodelset/check/<verificationprocessid>"
```

**Output**
The returned value is from type
[`VerificationStatus`](https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform/lp-cp-apis/src/main/java/eu/learnpad/mv/rest/data/VerificationStatus.java).

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<VerificationStatus>
	<Status>COMPLETED</Status>
</VerificationStatus>
```

**Details**
The status can take the following values:
* `IN PROGRESS`
* `NEVER STARTED`
* `COMPLETED`

### Get the results of a model verification
**Description**
Once the model verification has been completed, this API allows to ask for the
complete results.

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request GET \
	--header "Accept: application/xml" \
	  "http://localhost:8080/xwiki/rest/learnpad/me/bridge/checkmodelset/results/<verificationprocessid>"
```

**Output**
The returned value is from type
[`VerificationResults`](https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform/lp-cp-apis/src/main/java/eu/learnpad/mv/rest/data/VerificationResults.java).

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<VerificationResults>
	<VerificationType>ALL</VerificationType>
	<VerificationID>a11b684a-0a4c-486b-a262-4dc6899d82f9</VerificationID>
	<ModelID>LP_ME_ADOXX_MODELSET_28600</ModelID>
	<FinalResult>OK</FinalResult>
	<Time>2016-04-14T10:12:33.236Z</Time>
	<Results>
		<FormalVerificationResult>
			<VerificationType>ALL PLACES UNBOUNDEDNESS</VerificationType>
			<DefinitionID>def_30278</DefinitionID>
			<Status>OK</Status>
			<Description>The property "Unboundedness absence in all the model" is TRUE!</Description>
		</FormalVerificationResult>
		...
		<UnderstandabilityResult>
			<VerificationType>UNDERSTANDABILITY</VerificationType>
			<DefinitionID>def_30278</DefinitionID>
			<Status>OK</Status>
			<Description>Well done, no errors found!</Description>
			<Guidelines>
				<Guideline id="2" Name="Minimize Model Size">
					<Description>The modeler should try to keep models as small as possible. Large process models are difficult to read and comprehend.  Additionally, they tend to contain more errors.  Defining the correct scope of tasks and level of detail of processes is key to reduce the overage of information.</Description>
					<Suggestion>Well done!</Suggestion>
				</Guideline>
				...
			</Guidelines>
		</UnderstandabilityResult>
		...
	</Results>
</VerificationResults>
```


**Details**
In this results, there is two different kind of results:
* the formal verification (for example, identify deadlocks in the model)
* the understandability verification (for example, verify that every Task has a
  label)

Note that if the whole verification is completed without any error, then the
Core Platform (CP) will notify components (OR, CW, SIM) that a new modelset is
available.

### Get the feedbacks
**Description**
In the Collaborative Workspace (CW), end-user can generate feedbacks in order to
communicate to modelers some concerns about the models.  Modelling Environment
(ME) needs to ask for the feedbacks.

**cURL**
```
curl \
	--verbose \
	--user username:password \
	--request GET \
	--header "Accept: application/xml" \
	"http://localhost:8080/xwiki/rest/learnpad/me/corefacade/retrievefeedbacks/<modelsetid>"
```

**Output**
Output of this interface is a `PFResults` object.  This class is automatically
generated at build time from a
[XML-Schema](https://github.com/LearnPAd/learnpad/blob/master/lp-core-platform/lp-cp-apis/src/main/resources/PFResults.xsd);
you should be able to look at the generated classes in the folder
`lp-cp-apis/target/generated-sources/xjc`.

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
Patches are not generated in the Learn PAd platform at the moment.  Adoxx
software is able to understand this patches and to deal with them.

You can see the description of interfaces on the
[http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Components/Modelling+Environment](wiki).
