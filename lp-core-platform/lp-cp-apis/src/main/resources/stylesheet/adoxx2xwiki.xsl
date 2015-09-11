<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name="packageFolder" />
	<xsl:param name="modelSetId" />
	<xsl:output method="text" />
<!--
___________________________________________________________________________________________________
 XWiki variables
___________________________________________________________________________________________________-->
	<xsl:variable name="learnpadCodeSpace">LearnPAdCode</xsl:variable>
	<xsl:variable name="modelClassName">ModelClass</xsl:variable>
	<xsl:variable name="baseElementClassName">BaseElementClass</xsl:variable>
	<xsl:variable name="linkClassName">LinkClass</xsl:variable>
	<xsl:variable name="modelClass">
		<xsl:value-of select="$learnpadCodeSpace" />
		<xsl:text>.</xsl:text>
		<xsl:value-of select="$modelClassName" />
	</xsl:variable>
	<xsl:variable name="baseElementClass">
		<xsl:value-of select="$learnpadCodeSpace" />
		<xsl:text>.</xsl:text>
		<xsl:value-of select="$baseElementClassName" />
	</xsl:variable>
	<xsl:variable name="linkClass">
		<xsl:value-of select="$learnpadCodeSpace" />
		<xsl:text>.</xsl:text>
		<xsl:value-of select="$linkClassName" />
	</xsl:variable>
<!--
___________________________________________________________________________________________________
 Parse the models
___________________________________________________________________________________________________-->
	<xsl:template match="/">
		<xsl:apply-templates select="//MODEL[@modeltype='Business process diagram (BPMN 2.0)']" mode="BPMN"/>
		<xsl:apply-templates select="//MODEL[@modeltype='BMM']" mode="BMM"/>
		<xsl:apply-templates select="//MODEL[@modeltype='Document and Knowledge model']" mode="DKM"/>
		<xsl:apply-templates select="//MODEL[@modeltype='Organizational structure']" mode="OMM"/>
	</xsl:template>
	
<!--
___________________________________________________________________________________________________
 Common templates
___________________________________________________________________________________________________-->
	<xsl:template name="addInModelConnection">
		<xsl:param name="objectProperty" />
		<xsl:param name="targetInstanceClass" />
		<xsl:variable name="fromInstanceName" select="@name" />
		<xsl:variable name="fromInstanceClass" select="@class" />
		  <xsl:for-each select="..//CONNECTOR/FROM[@instance=$fromInstanceName and @class=$fromInstanceClass]">
		  	<xsl:variable name="toInstance" select="../TO/@instance"/>
		  	<xsl:variable name="toId" select="//INSTANCE[@name=$toInstance and @class=$targetInstanceClass]/@id"/>
		 	<xsl:if test="$toId"> <xsl:value-of select="$objectProperty"/> transfer:<xsl:value-of select="$toId"/> ;<xsl:text>&#10;</xsl:text>
		 	</xsl:if>
		  </xsl:for-each>
	</xsl:template>	

<!--
___________________________________________________________________________________________________
 Business process diagram (BPMN 2.0)
___________________________________________________________________________________________________-->
	<xsl:template match="MODEL" mode="BPMN">
		<xsl:result-document method="xml" href="{$packageFolder}/xwiki/{$modelSetId}/WebHome/index.xml">
			<page xmlns="http://www.xwiki.org">
				<title><xsl:value-of select="@name"/></title>
				<content>
					<xsl:text>{{include reference="${services.model.createDocumentReference('xwiki','</xsl:text>
					<xsl:value-of select="@id"/>
					<xsl:text>','</xsl:text>
					<xsl:value-of select="@id"/>
					<xsl:text>')}" /}}</xsl:text>
				</content>
			</page>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/WebHome/objects/XWiki/DocumentSheetBinding/bind.xml">
			<object xmlns="http://www.xwiki.org">
				<className>XWiki.DocumentSheetBinding</className>
				<property name="sheet" type="String">
					<value>LearnPAdCode.ModelSheet</value>
				</property>
			</object>
		</xsl:result-document>
		<xsl:result-document method="xml" href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/index.xml">
			<page xmlns="http://www.xwiki.org">
				<title><xsl:value-of select="@name"/></title>
				<content />
			</page>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/objects/{$learnpadCodeSpace}/{$modelClassName}/0.xml">
			<object xmlns="http://www.xwiki.org">
				<className><xsl:copy-of select="$modelClass" /></className>
				<property name="id" type="String">
					<value><xsl:value-of select="@id" /></value>
				</property>
				<property name="name" type="String">
					<value><xsl:value-of select="@name" /></value>
				</property>
				<property name="type" type="StaticList">
					<value>bpmn</value>
				</property>
			</object>
		</xsl:result-document>
		<xsl:apply-templates select=".//INSTANCE[@class='Start Event']" mode="StartEvent"/>
		<xsl:apply-templates select=".//INSTANCE[@class='End Event']" mode="EndEvent"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Task']" mode="Task"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Exclusive Gateway']" mode="ExclusiveGateway"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Pool']" mode="Pool"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Lane']" mode="Lane"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Data Object'][./ATTRIBUTE[@name='Data type']='Data Input']" mode="DataInput"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Data Object'][./ATTRIBUTE[@name='Data type']='Data Output']" mode="DataOutput"/>
		<xsl:apply-templates select=".//CONNECTOR" />
	</xsl:template>
<!--...............................................................................................-->

	<xsl:template match="ATTRIBUTE" mode="Documentation">
		<property name="documentation" type="TextArea">
			<value><xsl:value-of select="current()" /></value>
		</property>
	</xsl:template>
	<xsl:template match="CONNECTOR">
		<xsl:variable name="modelId" select="ancestor::MODEL[1]/@id" />
		<xsl:variable name="fromName" select="./FROM/@instance" />
		<xsl:variable name="toName" select="./TO/@instance" />
		<xsl:variable name="fromId" select="ancestor::MODEL[1]/INSTANCE[@name=$fromName]/@id" />
		<xsl:variable name="toId" select="ancestor::MODEL[1]/INSTANCE[@name=$toName]/@id" />
		
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{$fromId}/objects/{$learnpadCodeSpace}/{$linkClassName}/{@id}.xml">
			<object xmlns="http://www.xwiki.org">
				<className><xsl:copy-of select="$linkClass" /></className>
				<property name="id" type="String">
					<value><xsl:value-of select="@id" /></value>
				</property>
				<property name="type" type="StaticList">
					<value>outgoing</value>
				</property>
				<property name="uri" type="String">
					<value><xsl:value-of select="$toId" /></value>
				</property>
			</object>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{$toId}/objects/{$learnpadCodeSpace}/{$linkClassName}/{@id}.xml">
			<object xmlns="http://www.xwiki.org">
				<className><xsl:copy-of select="$linkClass" /></className>
				<property name="id" type="String">
					<value><xsl:value-of select="@id" /></value>
				</property>
				<property name="type" type="StaticList">
					<value>incoming</value>
				</property>
				<property name="uri" type="String">
					<value><xsl:value-of select="$fromId" /></value>
				</property>
			</object>
		</xsl:result-document>
	</xsl:template>
<!--
___________________________________________________________________________________________________
 Start Event
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="StartEvent">
		<xsl:variable name="bpId" select="ancestor::MODEL[1]/@id" />
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/index.xml">
			<page xmlns="http://www.xwiki.org">
				<title><xsl:value-of select="@name"/></title>
				<content />
			</page>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/objects/{$learnpadCodeSpace}/{$baseElementClassName}/{@id}.xml">
			<object xmlns="http://www.xwiki.org">
				<className><xsl:copy-of select="$baseElementClass" /></className>
				<property name="id" type="String">
					<value><xsl:value-of select="@id" /></value>
				</property>
				<property name="name" type="String">
					<value><xsl:value-of select="@name" /></value>
				</property>
				<property name="type" type="StaticList">
					<value>event-start</value>
				</property>
				<xsl:apply-templates select=".//ATTRIBUTE[@name='Documentation']" mode="Documentation"/>
			</object>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/objects/XWiki/DocumentSheetBinding/bind.xml">
			<object xmlns="http://www.xwiki.org">
				<className>XWiki.DocumentSheetBinding</className>
				<property name="sheet" type="String">
					<value>LearnPAdCode.BaseElementSheet</value>
				</property>
			</object>
		</xsl:result-document>
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 End Event
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="EndEvent">
		<xsl:variable name="bpId" select="ancestor::MODEL[1]/@id" />
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/index.xml">
			<page xmlns="http://www.xwiki.org">
				<title><xsl:value-of select="@name"/></title>
				<content />
			</page>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/objects/{$learnpadCodeSpace}/{$baseElementClassName}/{@id}.xml">
			<object xmlns="http://www.xwiki.org">
				<className><xsl:copy-of select="$baseElementClass" /></className>
				<property name="id" type="String">
					<value><xsl:value-of select="@id" /></value>
				</property>
				<property name="name" type="String">
					<value><xsl:value-of select="@name" /></value>
				</property>
				<property name="type" type="StaticList">
					<value>event-end</value>
				</property>
				<xsl:apply-templates select=".//ATTRIBUTE[@name='Documentation']" mode="Documentation"/>
			</object>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/objects/XWiki/DocumentSheetBinding/bind.xml">
			<object xmlns="http://www.xwiki.org">
				<className>XWiki.DocumentSheetBinding</className>
				<property name="sheet" type="String">
					<value>LearnPAdCode.BaseElementSheet</value>
				</property>
			</object>
		</xsl:result-document>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Task
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Task">
		<xsl:variable name="bpId" select="ancestor::MODEL[1]/@id" />
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/index.xml">
			<page xmlns="http://www.xwiki.org">
				<title><xsl:value-of select="@name"/></title>
				<content />
			</page>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/objects/{$learnpadCodeSpace}/{$baseElementClassName}/{@id}.xml">
			<object xmlns="http://www.xwiki.org">
				<className><xsl:copy-of select="$baseElementClass" /></className>
				<property name="id" type="String">
					<value><xsl:value-of select="@id" /></value>
				</property>
				<property name="name" type="String">
					<value><xsl:value-of select="@name" /></value>
				</property>
				<property name="type" type="StaticList">
					<value>task</value>
				</property>
				<xsl:apply-templates select=".//ATTRIBUTE[@name='Documentation']" mode="Documentation"/>
			</object>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/objects/XWiki/DocumentSheetBinding/bind.xml">
			<object xmlns="http://www.xwiki.org">
				<className>XWiki.DocumentSheetBinding</className>
				<property name="sheet" type="String">
					<value>LearnPAdCode.BaseElementSheet</value>
				</property>
			</object>
		</xsl:result-document>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Gateway
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="ExclusiveGateway">
		<xsl:variable name="bpId" select="ancestor::MODEL[1]/@id" />
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/index.xml">
			<page xmlns="http://www.xwiki.org">
				<title><xsl:value-of select="@name"/></title>
				<content />
			</page>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/objects/{$learnpadCodeSpace}/{$baseElementClassName}/{@id}.xml">
			<object xmlns="http://www.xwiki.org">
				<className><xsl:copy-of select="$baseElementClass" /></className>
				<property name="id" type="String">
					<value><xsl:value-of select="@id" /></value>
				</property>
				<property name="name" type="String">
					<value><xsl:value-of select="@name" /></value>
				</property>
				<property name="type" type="StaticList">
					<value>gateway-exclusive</value>
				</property>
				<xsl:apply-templates select=".//ATTRIBUTE[@name='Documentation']" mode="Documentation"/>
			</object>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/objects/XWiki/DocumentSheetBinding/bind.xml">
			<object xmlns="http://www.xwiki.org">
				<className>XWiki.DocumentSheetBinding</className>
				<property name="sheet" type="String">
					<value>LearnPAdCode.BaseElementSheet</value>
				</property>
			</object>
		</xsl:result-document>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Data Input
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="DataInput">
transfer:<xsl:value-of select="@id"/>
  rdf:type bpmn:DataInput ;
  rdfs:label "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="@class"/>"^^xsd:string ; <xsl:apply-templates select="./INTERREF/IREF[@type='objectreference'][@tmodeltype='Document and Knowledge model']" mode="dataInputReferencesDocument"/> 
.	
	</xsl:template>
	<xsl:template match="IREF" mode="dataInputReferencesDocument">
   emo:dataInputReferencesDocument transfer:<xsl:value-of select="//MODEL[@modeltype=current()/@tmodeltype]/INSTANCE[@class=current()/@tclassname][@name=current()/@tobjname]/@id"/>		
	</xsl:template>
<!--...............................................................................................-->
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Data Output
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="DataOutput">
transfer:<xsl:value-of select="@id"/>
  rdf:type bpmn:DataOutput ;
  rdfs:label "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="@class"/>"^^xsd:string ; <xsl:apply-templates select="./INTERREF/IREF[@type='objectreference'][@tmodeltype='Document and Knowledge model']" mode="dataOutputReferencesDocument"/> 
.	
	</xsl:template>
	<xsl:template match="IREF" mode="dataOutputReferencesDocument">
   emo:dataOutputReferencesDocument transfer:<xsl:value-of select="//MODEL[@modeltype=current()/@tmodeltype]/INSTANCE[@class=current()/@tclassname][@name=current()/@tobjname]/@id"/>		
	</xsl:template>
<!--...............................................................................................-->

<!--
___________________________________________________________________________________________________
 Pool
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Pool">
		<xsl:variable name="bpId" select="ancestor::MODEL[1]/@id" />
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/index.xml">
			<page xmlns="http://www.xwiki.org">
				<title><xsl:value-of select="@name"/></title>
				<content />
			</page>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/objects/{$learnpadCodeSpace}/{$baseElementClassName}/{@id}.xml">
			<object xmlns="http://www.xwiki.org">
				<className><xsl:copy-of select="$baseElementClass" /></className>
				<property name="id" type="String">
					<value><xsl:value-of select="@id" /></value>
				</property>
				<property name="name" type="String">
					<value><xsl:value-of select="@name" /></value>
				</property>
				<property name="type" type="StaticList">
					<value>pool</value>
				</property>
				<xsl:apply-templates select=".//ATTRIBUTE[@name='Documentation']" mode="Documentation"/>
			</object>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/objects/XWiki/DocumentSheetBinding/bind.xml">
			<object xmlns="http://www.xwiki.org">
				<className>XWiki.DocumentSheetBinding</className>
				<property name="sheet" type="String">
					<value>LearnPAdCode.BaseElementSheet</value>
				</property>
			</object>
		</xsl:result-document>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Lane
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Lane">
		<xsl:variable name="bpId" select="ancestor::MODEL[1]/@id" />
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/index.xml">
			<page xmlns="http://www.xwiki.org">
				<title><xsl:value-of select="@name"/></title>
				<content />
			</page>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/objects/{$learnpadCodeSpace}/{$baseElementClassName}/{@id}.xml">
			<object xmlns="http://www.xwiki.org">
				<className><xsl:copy-of select="$baseElementClass" /></className>
				<property name="id" type="String">
					<value><xsl:value-of select="@id" /></value>
				</property>
				<property name="name" type="String">
					<value><xsl:value-of select="@name" /></value>
				</property>
				<property name="type" type="StaticList">
					<value>lane</value>
				</property>
				<xsl:apply-templates select=".//ATTRIBUTE[@name='Documentation']" mode="Documentation"/>
			</object>
		</xsl:result-document>
		<xsl:result-document method="xml"
			href="{$packageFolder}/xwiki/{$modelSetId}/{@id}/objects/XWiki/DocumentSheetBinding/bind.xml">
			<object xmlns="http://www.xwiki.org">
				<className>XWiki.DocumentSheetBinding</className>
				<property name="sheet" type="String">
					<value>LearnPAdCode.BaseElementSheet</value>
				</property>
			</object>
		</xsl:result-document>
	</xsl:template>
<!--...............................................................................................-->	

<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
___________________________________________________________________________________________________
 Business Motivation Model (BMM)
___________________________________________________________________________________________________-->
	<xsl:template match="MODEL" mode="BMM">
transfer:<xsl:value-of select="@id"/>
  rdf:type emo:BusinessMotivationMetaModel ;
  rdfs:label "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasID "<xsl:value-of select="@id"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasDescription "<xsl:value-of select="@modeltype"/>"^^xsd:string ;
.	
		<xsl:variable name="modelId" select="@id">
  </xsl:variable>
		<xsl:apply-templates select=".//INSTANCE[@class='Goal']" mode="Goal"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Learning Goal']" mode="LearningGoal"/>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Goal
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Goal">
transfer:<xsl:value-of select="@id"/>
  rdf:type bmm:Goal ;
  rdfs:label "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="@class"/>"^^xsd:string ;
.	
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Learning Goal
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="LearningGoal">
transfer:<xsl:value-of select="@id"/>
  rdf:type emo:LearningGoal ;
  rdfs:label "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="@class"/>"^^xsd:string ;
.	
	</xsl:template>
<!--...............................................................................................-->	

<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
___________________________________________________________________________________________________
 Document and Knowledge model (DKM)
___________________________________________________________________________________________________-->
	<xsl:template match="MODEL" mode="DKM">
transfer:<xsl:value-of select="@id"/>
  rdf:type emo:DocumentAndKnowledgeMetaModel ;
  rdfs:label "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasID "<xsl:value-of select="@id"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasDescription "<xsl:value-of select="@modeltype"/>"^^xsd:string ;
.	
		<xsl:variable name="modelId" select="@id">
  </xsl:variable>
		<xsl:apply-templates select=".//INSTANCE[@class='Document']" mode="Document"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Group']" mode="DocumentGroup"/>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Document
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Document">
transfer:<xsl:value-of select="@id"/>
  rdf:type dkm:Document ;
  rdfs:label "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="@class"/>"^^xsd:string ;
  <xsl:call-template name="addInModelConnection">
        <xsl:with-param name="objectProperty" select="'dkm:d_ConstructIsInsideD_Container'"/>
        <xsl:with-param name="targetInstanceClass" select="'Group'"/>
    </xsl:call-template>.	
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Document Group
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="DocumentGroup">
transfer:<xsl:value-of select="@id"/>
  rdf:type dkm:D_Container ;
  rdfs:label "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="@class"/>"^^xsd:string ;
.
	</xsl:template>
<!--...............................................................................................-->	

<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
___________________________________________________________________________________________________
 Organizational structure (OMM)
___________________________________________________________________________________________________-->
	<xsl:template match="MODEL" mode="OMM">
transfer:<xsl:value-of select="@id"/>
  rdf:type emo:OrganisationMetaModel ;
  rdfs:label "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasID "<xsl:value-of select="@id"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasDescription "<xsl:value-of select="@modeltype"/>"^^xsd:string ;
.	
		<xsl:variable name="modelId" select="@id">
  </xsl:variable>
		<xsl:apply-templates select=".//INSTANCE[@class='Organizational unit']" mode="OrganizationalUnit"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Role']" mode="Role"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Performer']" mode="Performer"/>
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Organizational unit
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="OrganizationalUnit">
transfer:<xsl:value-of select="@id"/>
  rdf:type omm:OrganisationalUnit ;
  rdfs:label "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="@class"/>"^^xsd:string ;
  <xsl:call-template name="addInModelConnection">
        <xsl:with-param name="objectProperty" select="'omm:organisationalUnitIsSubordinatedToOrganisationalUnit'"/>
        <xsl:with-param name="targetInstanceClass" select="'Organizational unit'"/>
    </xsl:call-template>.
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Role
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Role">
transfer:<xsl:value-of select="@id"/>
  rdf:type omm:Role ;
  rdfs:label "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="@class"/>"^^xsd:string ;
  <xsl:call-template name="addInModelConnection">
        <xsl:with-param name="objectProperty" select="'lpd:roleIsCastedByOrgUnit'"/>
        <xsl:with-param name="targetInstanceClass" select="'Organizational unit'"/>
    </xsl:call-template>.
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Performer
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Performer">
transfer:<xsl:value-of select="@id"/>
  rdf:type omm:Performer ;
  rdfs:label "<xsl:value-of select="@name"/>"^^xsd:string ;
  emo:objectTypeHasName "<xsl:value-of select="@class"/>"^^xsd:string ;
  <xsl:call-template name="addInModelConnection">
        <xsl:with-param name="objectProperty" select="'omm:performerHasRole'"/>
        <xsl:with-param name="targetInstanceClass" select="'Role'"/>
    </xsl:call-template>.
	</xsl:template>
<!--...............................................................................................-->	
</xsl:stylesheet>
