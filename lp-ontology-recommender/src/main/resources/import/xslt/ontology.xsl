<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <!--To be set as parameter in transformation engine -->
  <!--xsl:param name="modelSetVersion">titolo-unico-v4</xsl:param-->
  <xsl:param name="modelSetVersion">titolo-unico</xsl:param>
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
@prefix eo: &lt;http://ikm-group.ch/archiMEO/eo#&gt; .
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
	<xsl:param name="id" />
	<xsl:param name="name" />
	<xsl:param name="class" />
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
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type emo:BPMN_MetaModel ;
  rdf:type bpmn:Process ;
  rdfs:subClassOf emo:BPMN_MetaModel ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;
  rdf:subClassOf bpmn:Process ;
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Start Event
___________________________________________________________________________________________________-->
	<xsl:template name="StartEvent">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:StartEvent ;
  rdfs:subClassOf bpmn:StartEvent ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;
  lpd:bpmnStartEventHasId "<xsl:value-of select="$id"/>"^^xsd:string ;
  lpd:bpmnStartEventHasName "<xsl:value-of select="$name"/>"^^xsd:string ;
  	</xsl:template>
       
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Message Start Event
___________________________________________________________________________________________________-->
	<xsl:template name="MessageStartEvent">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:MessageStartEvent ;
  rdfs:subClassOf bpmn:MessageStartEvent ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;
  lpd:bpmnStartEventHasId "<xsl:value-of select="$id"/>"^^xsd:string ;
  lpd:bpmnStartEventHasName "<xsl:value-of select="$name"/>"^^xsd:string ;
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Message Catching Intermediate Event
___________________________________________________________________________________________________-->
	<xsl:template name="MessageCatchingSequenceIntermediateEvent">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:MessageCatchingSequenceIntermediateEvent ;
  rdfs:subClassOf bpmn:MessageCatchingSequenceIntermediateEvent ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;
	</xsl:template>
<!--
___________________________________________________________________________________________________
 Message Interrupting Boundary Intermediate Event
___________________________________________________________________________________________________-->	
<!--...............................................................................................-->
	<xsl:template name="MessageInterruptingBoundaryIntermediateEvent">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:MessageInterruptingBoundaryIntermediateEvent ;
  rdfs:subClassOf bpmn:MessageInterruptingBoundaryIntermediateEvent ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 End Event
___________________________________________________________________________________________________-->
	<xsl:template name="EndEvent">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:EndEvent ;
  rdfs:subClassOf bpmn:EndEvent ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                  
  lpd:bpmnEndEventHasId "<xsl:value-of select="$id"/>"^^xsd:string ;
  lpd:bpmnEndEventHasName "<xsl:value-of select="$name"/>"^^xsd:string ;
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Task
___________________________________________________________________________________________________-->
	<xsl:template name="Task">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:Task ;
  rdfs:subClassOf bpmn:Task ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                  
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
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:SendTask ;
  rdfs:subClassOf bpmn:SendTask ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                
  	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Receive Task
___________________________________________________________________________________________________-->
	<xsl:template name="ReceiveTask">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:ReceiveTask ;
  rdfs:subClassOf bpmn:ReceiveTask ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                                
  	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Task bpmn:SubProcess ;
___________________________________________________________________________________________________-->
	<xsl:template name="SubProcess">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:SubProcess ;
  rdfs:subClassOf bpmn:SubProcess ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                
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
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:ExclusiveGateway ;
  rdfs:subClassOf bpmn:ExclusiveGateway ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                                
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
  Parallel Gateway (Non-Exclusive Gateway)
___________________________________________________________________________________________________-->
	<xsl:template name="ParallelGateway">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:ParallelGateway ;
  rdfs:subClassOf bpmn:ParallelGateway ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Data Input
___________________________________________________________________________________________________-->
	<xsl:template name="DataInput">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:DataInput ;
  rdfs:subClassOf bpmn:DataInput ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                                
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
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:DataOutput ;
  rdfs:subClassOf bpmn:DataOutput ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                                                
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
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:Pool ;
  rdfs:subClassOf bpmn:Pool ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                  
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
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bpmn:Lane ;
  rdfs:subClassOf bpmn:Lane ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                                  
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
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type emo:BusinessMotivationMetaModel ;
  rdfs:subClassOf emo:BusinessMotivationMetaModel ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                  	
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Goal
___________________________________________________________________________________________________-->
	<xsl:template name="Goal">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type bmm:Goal ;
  rdfs:subClassOf bmm:Goal ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                  	                	
	</xsl:template>
<!--...............................................................................................-->	

<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
___________________________________________________________________________________________________
 Document and Knowledge model (DKM)
___________________________________________________________________________________________________-->
	<xsl:template name="DKM_MODEL">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type emo:DocumentAndKnowledgeMetaModel ;
  rdfs:subClassOf emo:DocumentAndKnowledgeMetaModel ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                				
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Document
___________________________________________________________________________________________________-->
	<xsl:template name="Document">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type dkm:Document ;
  rdfs:subClassOf dkm:Document ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                			
	</xsl:template>
	
	<xsl:template name="addInModelConnectionForDocument">
            <xsl:param name="toId"/>  dkm:d_ConstructIsInsideD_Container transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>	
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Learning Document
___________________________________________________________________________________________________-->
	<xsl:template name="LearningDocument">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
            <xsl:param name="materialURL"/>
            <xsl:param name="contributorEmails"/>
            <xsl:param name="description" />
            <xsl:param name="comment" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type dkm:LearningDocument ;
  rdfs:subClassOf dkm:LearningDocument ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;  
  dkm:documentHasURL "<xsl:value-of select="$materialURL"/>"^^xsd:string ;  
  <xsl:choose>
    <xsl:when test="contains($materialURL, '.doc')">dkm:documentHasMIMEType "application/msword"^^xsd:string ;</xsl:when>
    <xsl:when test="contains($materialURL, '.pdf')">dkm:documentHasMIMEType "application/pdf"^^xsd:string ;</xsl:when>
    <xsl:otherwise>dkm:documentHasMIMEType "text/html"^^xsd:string ;</xsl:otherwise>
  </xsl:choose>
  <xsl:for-each select="$contributorEmails">
    dkm:documentHasContributorEmail "<xsl:value-of select="."/>"^^xsd:string ;  
  </xsl:for-each>
  dkm:documentHasDescription """<xsl:value-of select="$description"/>"""^^xsd:string ;
  rdfs:comment """<xsl:value-of select="$comment"/>"""^^xsd:string ;
	</xsl:template>
	
    <xsl:template name="addInModelConnectionForLearningDocumentToCompetency">
            <xsl:param name="toId"/>  dkm:learningDocumentIncreasesCompetenciesToLevel transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>	
	
	<xsl:template name="addInModelConnectionForLearningDocument">
            <xsl:param name="toId"/>  dkm:d_ConstructIsInsideD_Container transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>	
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Document Group
___________________________________________________________________________________________________-->
	<xsl:template name="DocumentGroup">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type dkm:D_Container ;
  rdfs:subClassOf dkm:D_Container ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                
	</xsl:template>
<!--...............................................................................................-->	

<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
___________________________________________________________________________________________________
 Organizational structure (OMM)
___________________________________________________________________________________________________-->
	<xsl:template name="OMM_MODEL">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type emo:OrganisationMetaModel ;
  rdfs:subClassOf emo:OrganisationMetaModel ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                    			
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Organizational unit
___________________________________________________________________________________________________-->
	<xsl:template name="OrganizationalUnit">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type omm:OrganisationalUnit ;
  rdfs:subClassOf omm:OrganisationalUnit ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                				
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
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type omm:Role ;
  rdfs:subClassOf omm:Role ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                				
	</xsl:template>
	
	<xsl:template name="addInModelConnectionForRole">
        		<xsl:param name="toId"/>  lpd:roleIsCastedByOrgUnit transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>	
    	
     <xsl:template name="addRoleRefToCompetencyProfile">
        		<xsl:param name="targetId"/> omm:roleRequiresCompetencyProfile transfer:<xsl:value-of select="$targetId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>    	
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Performer
___________________________________________________________________________________________________-->
	<xsl:template name="Performer">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
            <xsl:param name="firstName" />
            <xsl:param name="lastName" />
            <xsl:param name="email" />
            <xsl:param name="phoneNo" />
            <xsl:param name="skypeId" />
            <xsl:param name="officeAddress" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type omm:Performer ;
  rdfs:subClassOf omm:Performer ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                
		  <xsl:if test="$firstName != ''">omm:performerHasFirstName "<xsl:value-of select="$firstName"/>"^^xsd:string ;
		  </xsl:if>
		  <xsl:if test="$lastName != ''">omm:performerHasLastName "<xsl:value-of select="$lastName"/>"^^xsd:string ;
		  </xsl:if>
		  <xsl:if test="$email != ''">emo:performerHasEmailAddress "<xsl:value-of select="$email"/>"^^xsd:string ;
		  </xsl:if>
		  <xsl:if test="$phoneNo != ''">omm:performerHasPhoneNumber "<xsl:value-of select="$phoneNo"/>"^^xsd:string ;
		  </xsl:if>
		  <xsl:if test="$skypeId != ''">omm:performerHasSkypeId "<xsl:value-of select="$skypeId"/>"^^xsd:string ;
		  </xsl:if>
		  <xsl:if test="$officeAddress != ''">omm:performerHasOfficeAddress "<xsl:value-of select="$officeAddress"/>"^^xsd:string ;
		  </xsl:if>
	</xsl:template>
	
	<xsl:template name="addInModelConnectionForPerformerHasRole">
        		<xsl:param name="toId"/>  omm:performerHasRole transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>	
    	<xsl:template name="addInModelConnectionForPerformerIsManagerOf">
        		<xsl:param name="toId"/>  omm:performerIsManagerOfOrganisationalUnit transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>
    	
     <xsl:template name="addPerformerRefToCompetencyProfile">
        		<xsl:param name="targetId"/> omm:performerAcquiredCompetencyProfile transfer:<xsl:value-of select="$targetId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>    	    	                         
<!--...............................................................................................-->	
<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
___________________________________________________________________________________________________
 Case Management Model and Notation (CMMN)
___________________________________________________________________________________________________-->
	<xsl:template name="CMMN_MODEL">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type emo:CaseManagementMetaModel ;
  rdfs:subClassOf emo:CaseManagementMetaModel ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                			
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Case
___________________________________________________________________________________________________-->
	<xsl:template name="Case">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type cmmn:Case ;
  rdfs:subClassOf cmmn:Case ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 CaseTask
___________________________________________________________________________________________________-->
	<xsl:template name="CaseTask">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type cmmn:CaseTask ;
  rdfs:subClassOf cmmn:CaseTask ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                 
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 DiscretionaryTask (mapped to Task since nothing else available) ??? Correct ???
___________________________________________________________________________________________________-->
	<xsl:template name="DiscretionaryTask">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type cmmn:Task ;
  rdfs:subClassOf cmmn:Task ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 CaseFile
___________________________________________________________________________________________________-->
	<xsl:template name="CaseFile">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type cmmn:CaseFile ;
  rdfs:subClassOf cmmn:CaseFile ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;   
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;               
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 PlanningTable
___________________________________________________________________________________________________-->
	<xsl:template name="PlanningTable">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type cmmn:PlanningTable ;
  rdfs:subClassOf cmmn:PlanningTable ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                
	</xsl:template>
<!--...............................................................................................-->	

<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
 __________________________________________________________________________________________________   
 KPI-Overview
___________________________________________________________________________________________________-->
	<xsl:template name="KPI_MODEL">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type emo:KPI_MetaModel ;
  rdfs:subClassOf emo:KPI_MetaModel ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                				
	</xsl:template>
<!--...............................................................................................-->  
<!--
___________________________________________________________________________________________________
 Perspective
___________________________________________________________________________________________________-->
  	<xsl:template name="Perspective">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type kpi:Perspective ;
  rdfs:subClassOf kpi:Perspective ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                
  kpi:kpiPerspectiveHasId "<xsl:value-of select="$id"/>"^^xsd:string ;
  kpi:kpiPerspectiveHasName "<xsl:value-of select="$name"/>"^^xsd:string ;
	</xsl:template>
  <!--...............................................................................................-->  
<!--
___________________________________________________________________________________________________
 Operational (Business) Goal
___________________________________________________________________________________________________-->
  	<xsl:template name="OperationalGoal">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type kpi:OperationalGoal ;
  rdfs:subClassOf kpi:OperationalGoal ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                
  
  	</xsl:template>
	<xsl:template name="addLink_OperationalGoalToPerspective"><xsl:param name="toId"/>kpi:operationalGoalHasPerspective transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text></xsl:template>
	<!-- TODO
      kpi:operationalGoalSupportsOperationalGoal
  -->

<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Learning Goal
___________________________________________________________________________________________________-->
  	<xsl:template name="LearningGoal">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type kpi:LearningGoal ;
  rdfs:subClassOf kpi:LearningGoal ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                
  
  <!-- TODO
  kpi:learningGoalContributesToLearningGoal
  kpi:learningGoalEnablesOperationalGoal
  kpi:learningGoalHasPerspective
  -->
	</xsl:template>
	<xsl:template name="addLink_LearningGoalToPerspective"><xsl:param name="toId"/>kpi:learningGoalHasPerspective transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text></xsl:template>
	<xsl:template name="addLink_LearningGoalToOperationalGoal"><xsl:param name="toId"/>kpi:learningGoalEnablesOperationalGoal transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text></xsl:template>
<!--...............................................................................................--> 

<!--
___________________________________________________________________________________________________
 Performance Indicator
___________________________________________________________________________________________________-->
  	<xsl:template name="PerformanceIndicator">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
            <xsl:param name="description" />
            <xsl:param name="periodicity" />
            <xsl:param name="unit" />
            <xsl:param name="moreIsBetter" />
            <xsl:param name="thresholdGreenYellow" />
            <xsl:param name="thresholdYellowRed" />
            <xsl:param name="dataSource" />
            <xsl:param name="recommendation" />
            
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type kpi:KPI ;
  rdfs:subClassOf kpi:KPI ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                
  
  kpi:kpiHasDescription """<xsl:value-of select="$description"/>"""^^xsd:string ;
  kpi:kpiEvaluatedWithPeriodicity kpi:<xsl:value-of select="$periodicity"/>_periodicity ;
  kpi:kpiValueUnit "<xsl:value-of select="$unit"/>"^^xsd:string ;
  <xsl:choose>
    <xsl:when test="$moreIsBetter = 'more is better'">
      kpi:kpiHasThresholdTypeGreaterIsBetter "true"^^xsd:boolean ;
    </xsl:when>
    <xsl:otherwise>
      kpi:kpiHasThresholdTypeGreaterIsBetter "false"^^xsd:boolean ;
    </xsl:otherwise>
  </xsl:choose>
  <xsl:if test="$thresholdGreenYellow != ''">kpi:kpiHasGreenYellowThreshold "<xsl:value-of select="$thresholdGreenYellow"/>"^^xsd:float ;
  </xsl:if>
  <xsl:if test="$thresholdYellowRed != ''">kpi:kpiHasYellowRedThreshold "<xsl:value-of select="$thresholdYellowRed"/>"^^xsd:float ;
  </xsl:if>
  kpi:kpiHasAssignedDataSource "<xsl:value-of select="$dataSource"/>"^^xsd:string ;
  kpi:kpiProvidesRecommendation """<xsl:value-of select="$recommendation"/>"""^^xsd:string ;
    	</xsl:template>

  	<xsl:template name="addLink_KpiToPerspective"><xsl:param name="toId"/>kpi:kpiHasPerspective transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text></xsl:template>

  	<xsl:template name="addLink_KpiToOperationalGoal"><xsl:param name="toId"/>kpi:kpiMeasuresOperationalGoal transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>	</xsl:template>

  	<xsl:template name="addLink_KpiToLearningGoal"><xsl:param name="toId"/>kpi:kpiMeasuresLearningGoal transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text></xsl:template>
    	
<!--...............................................................................................--> 
<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
 __________________________________________________________________________________________________   
 Competency Model
___________________________________________________________________________________________________-->
        <xsl:template name="COMPETENCY_MODEL">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type emo:CompetencyMetaModel ;
  rdfs:subClassOf emo:CompetencyMetaModel ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                        
        </xsl:template>
<!--...............................................................................................-->  
<!--
___________________________________________________________________________________________________
 Competency
___________________________________________________________________________________________________-->
 	<xsl:template name="EQFCompetency">
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
            <xsl:param name="level"/>
            <xsl:param name="competence"/>
            <xsl:param name="skill"/>
            <xsl:param name="knowledge"/>
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type cmm:EQFCompetency ;
  rdfs:subClassOf cmm:EQFCompetency ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                	
  cmm:eqfCompetencyHasLevel "<xsl:value-of select="$level"/>"^^xsd:integer ;
  cmm:eqfCompetencyHasCompetence """<xsl:value-of select="$competence"/>"""^^xsd:string ;
  cmm:eqfCompetencyHasSkill """<xsl:value-of select="$skill"/>"""^^xsd:string ;
  cmm:eqfCompetencyHasKnowledge """<xsl:value-of select="$knowledge"/>"""^^xsd:string ;
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
            <xsl:param name="id" />
            <xsl:param name="name" />
            <xsl:param name="class" />
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type cmm:CompetencyGroup ;
  rdfs:subClassOf cmm:CompetencyGroup ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;                
	</xsl:template>  
<!--...............................................................................................--> 
<!--
___________________________________________________________________________________________________
 Acquired Competency Profile
___________________________________________________________________________________________________-->
  	<xsl:template name="AcquiredCompetencyProfile">
            <xsl:param name="id"/>
            <xsl:param name="name"/>
            <xsl:param name="class"/>
            <xsl:param name="learningPreferences"/>
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type cmm:CompetencyProfile ;
  rdfs:subClassOf cmm:CompetencyProfile ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;
  emo:competencyProfileIsDocumentedInDocument transfer:<xsl:value-of select="$id"/> ;
              <xsl:for-each select="tokenize($learningPreferences, ';')">
  cmm:learningPreferencesAssignedToCompetencyProfile cmm:<xsl:value-of select="replace(replace(replace(current(),' ',''), '\(', ''), '\)', '')"/>_learningPreference ;
            </xsl:for-each>
	</xsl:template>  
<!--...............................................................................................--> 
<!--
___________________________________________________________________________________________________
 Required Competency Profile
___________________________________________________________________________________________________-->
  	<xsl:template name="RequiredCompetencyProfile">
            <xsl:param name="id"/>
            <xsl:param name="name"/>
            <xsl:param name="class"/>
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type cmm:CompetencyProfile ;
  rdfs:subClassOf cmm:CompetencyProfile ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;
  emo:competencyProfileIsDocumentedInDocument transfer:<xsl:value-of select="$id"/> ;
	</xsl:template>  
<!--...............................................................................................--> 
	<xsl:template name="addInModelConnectionForCompetencyProfileToCompetency">
            <xsl:param name="toId"/>  cmm:competenciesAndLevels transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>	
<!--
___________________________________________________________________________________________________
 Competency and Level ( which was acquired)
___________________________________________________________________________________________________-->
  	<xsl:template name="AcquiredCompetencyAndLevel">
            <xsl:param name="id"/>
            <xsl:param name="name"/>
            <xsl:param name="class"/>
  	       <xsl:param name="competencyAndLevelLevel"/>
  		  <!--xsl:param name="competencyAndLevelLearningGoal"/-->
  		  <xsl:param name="competencyAndLevelStatus"/>
  		  <xsl:param name="competencyAndLevelScore"/>
  		  <xsl:param name="competencyAndLevelLastUpdate"/>
  		  <xsl:param name="competencyAndLevelComment"/>
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type cmm:CompetencyAndLevel ;
  rdfs:subClassOf cmm:CompetencyAndLevel ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;
  cmm:competencyAndLevelLevel "<xsl:value-of select="$competencyAndLevelLevel"/>"^^xsd:integer ; 
  cmm:competencyAndLevelStatus "<xsl:value-of select="$competencyAndLevelStatus"/>"^^xsd:string ;
  cmm:competencyAndLevelScore "<xsl:value-of select="$competencyAndLevelScore"/>"^^xsd:string ;
  <!-- cmm:competencyAndLevelLastUpdate "<xsl:value-of select="$competencyAndLevelLastUpdate"/>"^^xsd:date ; -->
  cmm:competencyAndLevelComment """<xsl:value-of select="$competencyAndLevelComment"/>"""^^xsd:string ;
  	</xsl:template> 
	
     <xsl:template name="addCompetencyLevelRefToCompetency">
        		<xsl:param name="targetId"/>  cmm:competencyAndLevelRefersToCompetency transfer:<xsl:value-of select="$targetId"/> ;<xsl:text>&#10;</xsl:text>
    	</xsl:template>
<!--...............................................................................................--> 
<!--
___________________________________________________________________________________________________
 Target Competency and Level (Association instance to target competencies with a specific level).
___________________________________________________________________________________________________-->
  	<xsl:template name="TargetCompetencyAndLevel">
            <xsl:param name="id"/>
            <xsl:param name="name"/>
            <xsl:param name="class"/>
  	       <xsl:param name="competencyAndLevelLevel"/>
  		  <xsl:param name="competencyAndLevelComment"/>
  transfer:<xsl:value-of select="$id"/>
  rdf:type owl:Class;
  rdf:type cmm:CompetencyAndLevel ;
  rdfs:subClassOf cmm:CompetencyAndLevel ;
  rdfs:label "<xsl:value-of select="$name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="$class"/>"^^xsd:string ;
  cmm:competencyAndLevelLevel "<xsl:value-of select="$competencyAndLevelLevel"/>"^^xsd:integer ; 
  cmm:competencyAndLevelComment """<xsl:value-of select="$competencyAndLevelComment"/>"""^^xsd:string ;
  	</xsl:template> 
<!--...............................................................................................--> 
<!-- ============================================================================================================================================== -->
</xsl:stylesheet>