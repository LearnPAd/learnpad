<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <!--To be set as parameter in transformation engine -->
  <xsl:param name="modelSetVersion">titolo-unico-v4</xsl:param>
<!--___________________________________________________________________________________________________
HEADER 
___________________________________________________________________________________________________-->
	<xsl:template name="header">
<xsl:text># baseURI: http://learnpad.eu/transfer
# imports: http://learnpad.eu

@prefix bmm: &lt;http://ikm-group.ch/archiMEO/BMM#&gt; .
@prefix bpmn: &lt;http://ikm-group.ch/archiMEO/BPMN#&gt; .
@prefix cmm: &lt;http://ikm-group.ch/archiMEO/CMM#&gt; .
@prefix cmmn: &lt;http://ikm-group.ch/archiMEO/CMMN#&gt; .
@prefix dkm: &lt;http://ikm-group.ch/archiMEO/dkm#&gt; .
@prefix emo: &lt;http://ikm-group.ch/archiMEO/emo#&gt; .
@prefix kpi: &lt;http://ikm-group.ch/archiMEO/kpi#&gt; .
@prefix lpd: &lt;http://learnpad.eu#&gt; .
@prefix omm: &lt;http://ikm-group.ch/archiMEO/omm#&gt; .
@prefix owl: &lt;http://www.w3.org/2002/07/owl#&gt; .
@prefix rdf: &lt;http://www.w3.org/1999/02/22-rdf-syntax-ns#&gt; .
@prefix rdfs: &lt;http://www.w3.org/2000/01/rdf-schema#&gt; .
@prefix transfer: &lt;http://learnpad.eu/transfer#&gt; .
@prefix xsd: &lt;http://www.w3.org/2001/XMLSchema#&gt; .

&lt;http://learnpad.eu/transfer&gt;
  rdf:type owl:Ontology ;
  owl:imports &lt;http://learnpad.eu&gt; ;
  owl:versionInfo "1.0"^^xsd:string ;
.	
</xsl:text>
transfer:<xsl:value-of select="$modelSetVersion"/>
  rdf:type owl:Class;
  rdf:type emo:ModelSetVersion ;
  rdfs:subClassOf emo:ModelSetVersion ;
  emo:majorModelSetVersion "<xsl:value-of select="$modelSetVersion"/>"^^xsd:string ;
.
</xsl:template>


<!--========================================================================================
 Common templates
===========================================================================================-->
<!--___________________________________________________________________________________________________
Writes basic instance properties like the URI, type, label and name
___________________________________________________________________________________________________-->
<xsl:template name="basicInstanceProperties">
	<xsl:param name="rdfType" required="yes"/>
	<xsl:param name="id" tunnel="yes"/>
	<xsl:param name="name" tunnel="yes"/>
	<xsl:param name="class" tunnel="yes"/>
transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type <xsl:value-of select="$rdfType"/> ;
  rdfs:subClassOf <xsl:value-of select="$rdfType"/> ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;
</xsl:template>

<!--___________________________________________________________________________________________________
  Add version and last . ("DOT") to finish ontology TTL format entry
___________________________________________________________________________________________________-->
<xsl:template name="elementPostProcessing">
  <xsl:param name="id"/>emo:belongsToVersion transfer:<xsl:value-of select="$modelSetVersion"/> ;
.</xsl:template>


<!--
___________________________________________________________________________________________________
 Property to connect flow elements with a Pool and Lane
___________________________________________________________________________________________________-->
	<xsl:template name="PoolLaneConnector">
  		<xsl:param name="fromId"/>
  		<xsl:param name="toId"/>
  bpmn:flowNodeBelongsToParticipant transfer:<xsl:value-of select="$toId"/>;
	</xsl:template>
<!--...............................................................................................-->
<!--======================================================================================== -->
<!--
___________________________________________________________________________________________________
 Business process diagram (BPMN 2.0)
___________________________________________________________________________________________________-->
	<xsl:template name="BPMN_MODEL">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">emo:BPMN_MetaModel</xsl:with-param>
		</xsl:call-template>  rdf:type bpmn:Process ;
  rdf:subClassOf bpmn:Process ;
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Start Event
___________________________________________________________________________________________________-->
	<xsl:template name="StartEvent">
		<xsl:param name="id" tunnel="yes"/>
		<xsl:param name="name" tunnel="yes"/>
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:StartEvent</xsl:with-param>
		</xsl:call-template>
  lpd:bpmnStartEventHasId "<xsl:value-of select="$id"/>"^^xsd:string ;
  lpd:bpmnStartEventHasName "<xsl:value-of select="$name"/>"^^xsd:string ;
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Message Start Event
___________________________________________________________________________________________________-->
	<xsl:template name="MessageStartEvent">
		<xsl:param name="id" tunnel="yes"/>
		<xsl:param name="name" tunnel="yes"/>
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:MessageStartEvent</xsl:with-param>
		</xsl:call-template>
  lpd:bpmnStartEventHasId "<xsl:value-of select="$id"/>"^^xsd:string ;
  lpd:bpmnStartEventHasName "<xsl:value-of select="$name"/>"^^xsd:string ;
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Message Catching Intermediate Event
___________________________________________________________________________________________________-->
	<xsl:template name="MessageCatchingSequenceIntermediateEvent">
		<xsl:param name="id" tunnel="yes"/>
		<xsl:param name="name" tunnel="yes"/>
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:MessageCatchingSequenceIntermediateEvent</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
<!--
___________________________________________________________________________________________________
 Message Interrupting Boundary Intermediate Event
___________________________________________________________________________________________________-->	
<!--...............................................................................................-->
	<xsl:template name="MessageInterruptingBoundaryIntermediateEvent">
		<xsl:param name="id" tunnel="yes"/>
		<xsl:param name="name" tunnel="yes"/>
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:MessageInterruptingBoundaryIntermediateEvent</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 End Event
___________________________________________________________________________________________________-->
	<xsl:template name="EndEvent">
		<xsl:param name="id" tunnel="yes"/>
		<xsl:param name="name" tunnel="yes"/>
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:EndEvent</xsl:with-param>
		</xsl:call-template>
  lpd:bpmnEndEventHasId "<xsl:value-of select="$id"/>"^^xsd:string ;
  lpd:bpmnEndEventHasName "<xsl:value-of select="$name"/>"^^xsd:string ;
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Task
___________________________________________________________________________________________________-->
	<xsl:template name="Task">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:Task</xsl:with-param>
		</xsl:call-template>
  	</xsl:template>
  	<xsl:template name="addInModelConnectionForTask">
  		<xsl:param name="toId"/>
	  	bpmn:activitiyHasReferenceToActivity transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>
  	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Send Task
___________________________________________________________________________________________________-->
	<xsl:template name="SendTask">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:SendTask</xsl:with-param>
		</xsl:call-template>
  	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Receive Task
___________________________________________________________________________________________________-->
	<xsl:template name="ReceiveTask">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:ReceiveTask</xsl:with-param>
		</xsl:call-template>
  	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Task bpmn:SubProcess ;
___________________________________________________________________________________________________-->
	<xsl:template name="SubProcess">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:SubProcess</xsl:with-param>
		</xsl:call-template>
  	</xsl:template>
  	<xsl:template name="addReferencedSubProcessConnection">
		<xsl:param name="targetId"/>
   lpd:bpmnSubProcessRepresentsBProcess transfer:<xsl:value-of select="$targetId"/> ;<xsl:text>&#10;</xsl:text>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Gateway
___________________________________________________________________________________________________-->
	<xsl:template name="ExclusiveGateway">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:ExclusiveGateway</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
  Parallel Gateway (Non-Exclusive Gateway)
___________________________________________________________________________________________________-->
	<xsl:template name="ParallelGateway">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:ParallelGateway</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Data Input
___________________________________________________________________________________________________-->
	<xsl:template name="DataInput">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:DataInput</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
	<xsl:template name="addDataInputConnection">
		<xsl:param name="targetId"/>
   emo:dataInputReferencesDocument transfer:<xsl:value-of select="$targetId"/> ;<xsl:text>&#10;</xsl:text>
	</xsl:template>
<!--...............................................................................................-->
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Data Output
___________________________________________________________________________________________________-->
	<xsl:template name="DataOutput">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:DataOutput</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
	<xsl:template name="addDataOutputConnection">
		<xsl:param name="targetId"/>
   emo:dataOutputReferencesDocument transfer:<xsl:value-of select="$targetId"/> ;<xsl:text>&#10;</xsl:text>
	</xsl:template>
<!--...............................................................................................-->

<!--
___________________________________________________________________________________________________
 Pool
___________________________________________________________________________________________________-->
	<xsl:template name="Pool">
		<xsl:param name="id" tunnel="yes"/>
		<xsl:param name="name" tunnel="yes"/>
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:Pool</xsl:with-param>
		</xsl:call-template>  
  lpd:bpmnPoolHasId "<xsl:value-of select="$id"/>"^^xsd:string ;
  lpd:bpmnPoolHasName "<xsl:value-of select="$name"/>"^^xsd:string ;
	</xsl:template>
    <xsl:template name="addPoolRefToOrganisationalUnit">
		<xsl:param name="targetId"/>
   emo:poolRepresentsOrganisationalUnit transfer:<xsl:value-of select="$targetId"/> ;<xsl:text>&#10;</xsl:text>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Lane
___________________________________________________________________________________________________-->
	<xsl:template name="Lane">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bpmn:Lane</xsl:with-param>
		</xsl:call-template>  
	</xsl:template>
    <xsl:template name="addSwimlaneRefToOrganisationalUnit">
		<xsl:param name="targetId"/>
   emo:swimlaneRepresentsOrganisationalUnit transfer:<xsl:value-of select="$targetId"/> ;<xsl:text>&#10;</xsl:text>
	</xsl:template>	
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 SequenceFlow (Connector)
___________________________________________________________________________________________________-->
	<xsl:template name="FlowElementConnector">
		<xsl:param name="id"/>
		<xsl:param name="class"/>
		<xsl:param name="fromInstance"/>
  		<xsl:param name="fromId"/>
  		<xsl:param name="toInstance"/>
  		<xsl:param name="toId"/>
transfer:<xsl:value-of select="$id"/>
  rdf:type bpmn:SequenceFlow ;
  rdfs:label "Connector from <xsl:value-of select="$fromInstance"/> to <xsl:value-of select="$toInstance"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;
  bpmn:fromFlowElement transfer:<xsl:value-of select="$fromId"/>;
  bpmn:toFlowElement transfer:<xsl:value-of select="$toId"/>;
.	
	</xsl:template>
<!--...............................................................................................-->	
<!--...............................................................................................-->	

<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
___________________________________________________________________________________________________
 Business Motivation Model (BMM)
___________________________________________________________________________________________________-->
	<xsl:template name="BMM_MODEL">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">emo:BusinessMotivationMetaModel</xsl:with-param>
		</xsl:call-template>		
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Goal
___________________________________________________________________________________________________-->
	<xsl:template name="Goal">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">bmm:Goal</xsl:with-param>
		</xsl:call-template> 		
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Learning Goal
___________________________________________________________________________________________________-->
	<xsl:template name="LearningGoal">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">emo:LearningGoal</xsl:with-param>
		</xsl:call-template> 				
	</xsl:template>
<!--...............................................................................................-->	

<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
___________________________________________________________________________________________________
 Document and Knowledge model (DKM)
___________________________________________________________________________________________________-->
	<xsl:template name="DKM_MODEL">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">emo:DocumentAndKnowledgeMetaModel</xsl:with-param>
		</xsl:call-template>				
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Document
___________________________________________________________________________________________________-->
	<xsl:template name="Document">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">dkm:Document</xsl:with-param>
		</xsl:call-template>				
	</xsl:template>
	
	<xsl:template name="addInModelConnectionForDocument">
        		<xsl:param name="toId"/>  dkm:d_ConstructIsInsideD_Container transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>	
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Document Group
___________________________________________________________________________________________________-->
	<xsl:template name="DocumentGroup">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">dkm:D_Container</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
<!--...............................................................................................-->	

<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
___________________________________________________________________________________________________
 Organizational structure (OMM)
___________________________________________________________________________________________________-->
	<xsl:template name="OMM_MODEL">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">emo:OrganisationMetaModel</xsl:with-param>
		</xsl:call-template>				
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Organizational unit
___________________________________________________________________________________________________-->
	<xsl:template name="OrganizationalUnit">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">omm:OrganisationalUnit</xsl:with-param>
		</xsl:call-template>				
	</xsl:template>
	
	<xsl:template name="addInModelConnectionForOrganizationalUnit">
        		<xsl:param name="toId"/>  omm:organisationalUnitIsSubordinatedToOrganisationalUnit transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>	
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Role
___________________________________________________________________________________________________-->
	<xsl:template name="Role">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">omm:Role</xsl:with-param>
		</xsl:call-template>				
	</xsl:template>
	
	<xsl:template name="addInModelConnectionForRole">
        		<xsl:param name="toId"/>  lpd:roleIsCastedByOrgUnit transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>	
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Performer
___________________________________________________________________________________________________-->
	<xsl:template name="Performer">
	     <xsl:param name="email" tunnel="yes"/>
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">omm:Performer</xsl:with-param>
		</xsl:call-template>
		  <xsl:if test="$email != ''">
		emo:performerHasEmailAddress "<xsl:value-of select="$email"/>"^^xsd:string ;
		  </xsl:if>
	</xsl:template>
	
	<xsl:template name="addInModelConnectionForPerformer">
        		<xsl:param name="toId"/>  omm:performerHasRole transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>	
<!--...............................................................................................-->	
<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
___________________________________________________________________________________________________
 Case Management Model and Notation (CMMN)
___________________________________________________________________________________________________-->
	<xsl:template name="CMMN_MODEL">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">emo:CaseManagementMetaModel</xsl:with-param>
		</xsl:call-template>				
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Case
___________________________________________________________________________________________________-->
	<xsl:template name="Case">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">cmmn:Case</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 CaseTask
___________________________________________________________________________________________________-->
	<xsl:template name="CaseTask">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">cmmn:CaseTask</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 DiscretionaryTask (mapped to Task since nothing else available) ??? Correct ???
___________________________________________________________________________________________________-->
	<xsl:template name="DiscretionaryTask">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">cmmn:Task</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 CaseFile
___________________________________________________________________________________________________-->
	<xsl:template name="CaseFile">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">cmmn:CaseFile</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 PlanningTable
___________________________________________________________________________________________________-->
	<xsl:template name="PlanningTable">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">cmmn:PlanningTable</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
<!--...............................................................................................-->	

<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
 __________________________________________________________________________________________________   
 KPI-Overview
___________________________________________________________________________________________________-->
	<xsl:template name="KPI_MODEL">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">emo:KPI_MetaModel</xsl:with-param>
		</xsl:call-template>				
	</xsl:template>
<!--...............................................................................................-->  
<!--
___________________________________________________________________________________________________
 Perspective
___________________________________________________________________________________________________-->
  	<xsl:template name="Perspective">
		<xsl:param name="id" tunnel="yes"/>
		<xsl:param name="name" tunnel="yes"/>  	        
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">kpi:Perspective</xsl:with-param>
		</xsl:call-template>
  lpd:kpiPerspectiveHasId "<xsl:value-of select="$id"/>"^^xsd:string ;
  lpd:kpiPerspectiveHasName "<xsl:value-of select="$name"/>"^^xsd:string ;
	</xsl:template>
  <!--...............................................................................................-->  
<!--
___________________________________________________________________________________________________
 Strategic Goal
___________________________________________________________________________________________________-->
  	<xsl:template name="StrategicGoal">
		<xsl:param name="id" tunnel="yes"/>
		<xsl:param name="name" tunnel="yes"/>
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">kpi:StrategicGoal</xsl:with-param>
		</xsl:call-template>
  lpd:kpiStrategicGoalHasId "<xsl:value-of select="$id"/>"^^xsd:string ;
  lpd:kpiStrategicGoalHasName "<xsl:value-of select="$name"/>"^^xsd:string ;
	</xsl:template>
<!--...............................................................................................--> 
<!--
___________________________________________________________________________________________________
 Performance Indicator
___________________________________________________________________________________________________-->
  	<xsl:template name="PerformanceIndicator">
        	<xsl:param name="id" tunnel="yes"/>
        	<xsl:param name="name" tunnel="yes"/>
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">kpi:PerformanceIndicator</xsl:with-param>
		</xsl:call-template>
  lpd:kpiPerformanceIndicatorHasId "<xsl:value-of select="$id"/>"^^xsd:string ;
  lpd:kpiPerformanceIndicatorHasName "<xsl:value-of select="$name"/>"^^xsd:string ;
  	</xsl:template>
<!--...............................................................................................--> 
<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
 __________________________________________________________________________________________________   
 Competency Model
___________________________________________________________________________________________________-->
        <xsl:template name="COMPETENCY_MODEL">
                <xsl:call-template name="basicInstanceProperties">
			        <xsl:with-param name="rdfType">emo:CompetencyMetaModel</xsl:with-param>
		        </xsl:call-template>
        </xsl:template>
<!--...............................................................................................-->  
<!--
___________________________________________________________________________________________________
 Competency
___________________________________________________________________________________________________-->
 	<xsl:template name="Competency">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">cmm:Competency</xsl:with-param>
		</xsl:call-template>				
	</xsl:template>
	
	<xsl:template name="addInModelConnectionForCompetency">
        		<xsl:param name="toId"/>  cmm:competencyBelongsToCompetencyGroup transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>
<!--...............................................................................................-->  
<!--
___________________________________________________________________________________________________
 Competency Group
___________________________________________________________________________________________________-->
  	<xsl:template name="CompetencyGroup">
		<xsl:call-template name="basicInstanceProperties">
			<xsl:with-param name="rdfType">cmm:CompetencyGroup</xsl:with-param>
		</xsl:call-template>
	</xsl:template>  
<!--...............................................................................................--> 
<!-- ============================================================================================================================================== -->
</xsl:stylesheet>