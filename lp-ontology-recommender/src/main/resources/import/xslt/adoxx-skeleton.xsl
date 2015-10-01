<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text"/>
	<xsl:template match="/">
		<xsl:call-template name="header"/>
		<xsl:apply-templates select="//MODEL[@modeltype='Business process diagram (BPMN 2.0)']" mode="BPMN"/>
		<xsl:apply-templates select="//MODEL[@modeltype='BMM']" mode="BMM"/>
		<xsl:apply-templates select="//MODEL[@modeltype='Document and Knowledge model']" mode="DKM"/>
		<xsl:apply-templates select="//MODEL[@modeltype='Organizational structure']" mode="OMM"/>
          <xsl:apply-templates select="//MODEL[@modeltype='KPI-Overview']" mode="KPI"/>
          <xsl:apply-templates select="//MODEL[@modeltype='CMMN model']" mode="CMMN"/>
          <xsl:apply-templates select="//MODEL[@modeltype='Competency model']" mode="CM"/>
	</xsl:template>
<!--========================================================================================
 Common templates
===========================================================================================-->
<!--========================================================================================-->
<!--___________________________________________________________________________________________________
 Business process diagram (BPMN 2.0)
___________________________________________________________________________________________________-->
	<xsl:template match="MODEL" mode="BPMN">
		<xsl:call-template name="BPMN_MODEL">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@modeltype" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Start Event']" mode="StartEvent"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Start Event'][./ATTRIBUTE[@name='Message']='Yes']" mode="MessageStartEvent"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Intermediate Event (sequence flow)'][./ATTRIBUTE[@name='Type']='catching'][./ATTRIBUTE[@name='Message']='Yes']" mode="MessageCatchingSequenceIntermediateEvent"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Intermediate Event (boundary)'][./ATTRIBUTE[@name='Type']='interrupting'][./ATTRIBUTE[@name='Message']='Yes']" mode="MessageInterruptingBoundaryIntermediateEvent"/>
		<xsl:apply-templates select=".//INSTANCE[@class='End Event']" mode="EndEvent"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Task']" mode="Task"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Task'][./ATTRIBUTE[@name='Task type']='Send']" mode="SendTask"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Task'][./ATTRIBUTE[@name='Task type']='Receive']" mode="ReceiveTask"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Sub-Process']" mode="SubProcess"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Exclusive Gateway']" mode="ExclusiveGateway"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Non-exclusive Gateway']" mode="ParallelGateway"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Pool']" mode="Pool"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Lane']" mode="Lane"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Data Object'][./ATTRIBUTE[@name='Data type']='Data Input']" mode="DataInput"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Data Object'][./ATTRIBUTE[@name='Data type']='Data Output']" mode="DataOutput"/>
		<xsl:apply-templates select=".//CONNECOR[./FROM/@class='Start Event' or ./FROM/@class='End Event' or ./FROM/@class='Task' or ./FROM/@class='Sub-Process' or ./FROM/@class='Exclusive Gateway' or ./FROM/@class='Non-exclusive Gateway'][./TO/@class='Start Event' or ./TO/@class='End Event' or ./TO/@class='Task' or ./FROM/@class='Sub-Process' or ./TO/@class='Exclusive Gateway' or ./TO/@class='Non-exclusive Gateway']" mode="FlowElementConnector"/>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Start Event
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="StartEvent">
		<xsl:call-template name="StartEvent">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:apply-templates select="../CONNECTOR[./FROM/@instance=current()/@name and ./FROM/@class=current()/@class][./TO/@class='Pool' or ./TO/@class='Lane']" mode="PoolLaneConnector"/>
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Message Start Event
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="MessageStartEvent">
		<xsl:call-template name="MessageStartEvent">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:apply-templates select="../CONNECTOR[./FROM/@instance=current()/@name and ./FROM/@class=current()/@class][./TO/@class='Pool' or ./TO/@class='Lane']" mode="PoolLaneConnector"/>
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Message Catching Sequence Intermediate Event
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="MessageCatchingSequenceIntermediateEvent">
		<xsl:call-template name="MessageCatchingSequenceIntermediateEvent">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:apply-templates select="../CONNECTOR[./FROM/@instance=current()/@name and ./FROM/@class=current()/@class][./TO/@class='Pool' or ./TO/@class='Lane']" mode="PoolLaneConnector"/>
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Message Interrupting Boundary Intermediate Event
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="MessageInterruptingBoundaryIntermediateEvent">
		<xsl:call-template name="MessageInterruptingBoundaryIntermediateEvent">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:apply-templates select="../CONNECTOR[./FROM/@instance=current()/@name and ./FROM/@class=current()/@class][./TO/@class='Pool' or ./TO/@class='Lane']" mode="PoolLaneConnector"/>
<!-- TODO Ref to Activity resp. Sub-Process -->		
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 End Event
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="EndEvent">
		<xsl:call-template name="EndEvent">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>			
		</xsl:call-template>
		<xsl:apply-templates select="../CONNECTOR[./FROM/@instance=current()/@name and ./FROM/@class=current()/@class][./TO/@class='Pool' or ./TO/@class='Lane']" mode="PoolLaneConnector"/>
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Task
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Task">
		<xsl:call-template name="Task">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:apply-templates select="../CONNECTOR[./FROM/@instance=current()/@name and ./FROM/@class=current()/@class][./TO/@class='Pool' or ./TO/@class='Lane']" mode="PoolLaneConnector"/>
    		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Send Task
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="SendTask">
		<xsl:call-template name="SendTask">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:apply-templates select="../CONNECTOR[./FROM/@instance=current()/@name and ./FROM/@class=current()/@class][./TO/@class='Pool' or ./TO/@class='Lane']" mode="PoolLaneConnector"/>
    		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Receive Task
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="ReceiveTask">
		<xsl:call-template name="ReceiveTask">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:apply-templates select="../CONNECTOR[./FROM/@instance=current()/@name and ./FROM/@class=current()/@class][./TO/@class='Pool' or ./TO/@class='Lane']" mode="PoolLaneConnector"/>
    		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Sub-Process
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="SubProcess">
		<xsl:call-template name="SubProcess">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:apply-templates select="../CONNECTOR[./FROM/@instance=current()/@name and ./FROM/@class=current()/@class][./TO/@class='Pool' or ./TO/@class='Lane']" mode="PoolLaneConnector"/>
		<xsl:apply-templates select="./INTERREF/IREF[@type='modelreference'][@tmodeltype='Business process diagram (BPMN 2.0)']" mode="referencedSubProcess"/>
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
	 
	<xsl:template match="IREF" mode="referencedSubProcess">
		<xsl:call-template name="addReferencedSubProcessConnection">
			<xsl:with-param name="targetId" select="//MODEL[@modeltype=current()/@tmodeltype][@name=current()/@tmodelname][@name=current()/@tmodelname][@name=current()/@tmodelname]/@id"/>
		</xsl:call-template>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Gateway
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="ExclusiveGateway">
		<xsl:call-template name="ExclusiveGateway">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>			
		</xsl:call-template>
		<xsl:apply-templates select="../CONNECTOR[./FROM/@instance=current()/@name and ./FROM/@class=current()/@class][./TO/@class='Pool' or ./TO/@class='Lane']" mode="PoolLaneConnector"/>
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Parallel Gateway (Non-Exclusive Gateway)
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="ParallelGateway">
		<xsl:call-template name="ParallelGateway">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>			
		</xsl:call-template>
		<xsl:apply-templates select="../CONNECTOR[./FROM/@instance=current()/@name and ./FROM/@class=current()/@class][./TO/@class='Pool' or ./TO/@class='Lane']" mode="PoolLaneConnector"/>
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Data Input
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="DataInput">
		<xsl:call-template name="DataInput">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>		
		<xsl:apply-templates select="./INTERREF/IREF[@type='objectreference'][@tmodeltype='Document and Knowledge model']" mode="dataInputReferencesDocument"/>
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
	 
	<xsl:template match="IREF" mode="dataInputReferencesDocument">
		<xsl:call-template name="addDataInputConnection">
			<xsl:with-param name="targetId" select="//MODEL[@modeltype=current()/@tmodeltype][@name=current()/@tmodelname][@name=current()/@tmodelname]/INSTANCE[@class=current()/@tclassname][@name=current()/@tobjname]/@id"/>
		</xsl:call-template>
	</xsl:template>
<!--...............................................................................................-->
<!--
___________________________________________________________________________________________________
 Data Output
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="DataOutput">
		<xsl:call-template name="DataOutput">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>		
		<xsl:apply-templates select="./INTERREF/IREF[@type='objectreference'][@tmodeltype='Document and Knowledge model']" mode="dataOutputReferencesDocument"/>
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>

	<xsl:template match="IREF" mode="dataOutputReferencesDocument">
		<xsl:call-template name="addDataOutputConnection">
			<xsl:with-param name="targetId" select="//MODEL[@modeltype=current()/@tmodeltype][@name=current()/@tmodelname]/INSTANCE[@class=current()/@tclassname][@name=current()/@tobjname]/@id"/>
		</xsl:call-template>
	</xsl:template>		
<!--...............................................................................................-->

<!--
___________________________________________________________________________________________________
 Pool
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Pool">
		<xsl:call-template name="Pool">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>	
          <xsl:apply-templates select="./INTERREF/IREF[@type='objectreference'][@tmodeltype='Organizational structure'][@tclassname='Organizational unit']" mode="poolRefToOrganisationalUnit"/>
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
	 
	<xsl:template match="IREF" mode="poolRefToOrganisationalUnit">
		<xsl:call-template name="addPoolRefToOrganisationalUnit">
			<xsl:with-param name="targetId" select="//MODEL[@modeltype=current()/@tmodeltype][@name=current()/@tmodelname]/INSTANCE[@class=current()/@tclassname][@name=current()/@tobjname]/@id"/>
		</xsl:call-template>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Lane
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Lane">
		<xsl:call-template name="Lane">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:apply-templates select="./INTERREF/IREF[@type='objectreference'][@tmodeltype='Organizational structure'][@tclassname='Organizational unit']" mode="swimlaneRefToOrganisationalUnit"/>
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
	
	<xsl:template match="IREF" mode="swimlaneRefToOrganisationalUnit">
		<xsl:call-template name="addSwimlaneRefToOrganisationalUnit">
			<xsl:with-param name="targetId" select="//MODEL[@modeltype=current()/@tmodeltype][@name=current()/@tmodelname]/INSTANCE[@class=current()/@tclassname][@name=current()/@tobjname]/@id"/>
		</xsl:call-template>
	</xsl:template>	
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 SequenceFlow (Connector)
___________________________________________________________________________________________________-->
	<xsl:template match="CONNECTOR" mode="FlowElementConnector">
		<xsl:call-template name="FlowElementConnector">
			<xsl:with-param name="id" select="@id"/>
			<xsl:with-param name="class" select="@class"/>
			<xsl:with-param name="fromInstance" select="./FROM/@instance"/>
	  		<xsl:with-param name="fromId" select="..//INSTANCE[@name=current()/FROM/@instance and @class=current()/FROM/@class]/@id"/>
	  		<xsl:with-param name="toInstance" select="./TO/@instance"/>
	  		<xsl:with-param name="toId" select="..//INSTANCE[@name=current()/TO/@instance and @class=current()/TO/@class]/@id"/>
		</xsl:call-template>	
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Connections to Pool and Lanes (in class)
___________________________________________________________________________________________________-->
	<xsl:template match="CONNECTOR" mode="PoolLaneConnector">
		<xsl:call-template name="PoolLaneConnector">
	  		<xsl:with-param name="fromId" select="..//INSTANCE[@name=current()/FROM/@instance and @class=current()/FROM/@class]/@id"/>
	  		<xsl:with-param name="toId" select="..//INSTANCE[@name=current()/TO/@instance and @class=current()/TO/@class]/@id"/>
		</xsl:call-template>	
	</xsl:template>
<!--...............................................................................................-->	
<!--...............................................................................................-->	

<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
___________________________________________________________________________________________________
 Business Motivation Model (BMM)
___________________________________________________________________________________________________-->
	<xsl:template match="MODEL" mode="BMM">
		<xsl:call-template name="BMM_MODEL">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@modeltype" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>	  
		<xsl:apply-templates select=".//INSTANCE[@class='Goal']" mode="Goal"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Learning Goal']" mode="LearningGoal"/>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Goal
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Goal">
		<xsl:call-template name="Goal">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Learning Goal
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="LearningGoal">
		<xsl:call-template name="LearningGoal">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>	</xsl:template>
<!--...............................................................................................-->	

<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
___________________________________________________________________________________________________
 Document and Knowledge model (DKM)
___________________________________________________________________________________________________-->
	<xsl:template match="MODEL" mode="DKM">
		<xsl:call-template name="DKM_MODEL">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@modeltype" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>	 
		<xsl:apply-templates select=".//INSTANCE[@class='Document']" mode="Document"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Group']" mode="DocumentGroup"/>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Document
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Document">
		<xsl:call-template name="Document">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
		</xsl:call-template>
		<xsl:for-each select="../CONNECTOR/FROM[@instance=current()/@name and @class=current()/@class]">
		  <xsl:for-each select="../../INSTANCE[@name=current()/../TO/@instance and @class='Group']/@id">
  		     <xsl:call-template name="addInModelConnectionForDocument">
			  <xsl:with-param name="toId" select="."/>
		     </xsl:call-template>
		  </xsl:for-each>
		</xsl:for-each>		
          <xsl:call-template name="elementPostProcessing"/>		
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Document Group
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="DocumentGroup">
		<xsl:call-template name="DocumentGroup">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
<!--...............................................................................................-->	

<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
___________________________________________________________________________________________________
 Organizational structure (OMM)
___________________________________________________________________________________________________-->
	<xsl:template match="MODEL" mode="OMM">
		<xsl:call-template name="OMM_MODEL">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@modeltype" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>
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
		<xsl:call-template name="OrganizationalUnit">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
		</xsl:call-template>
		<xsl:for-each select="../CONNECTOR/FROM[@instance=current()/@name and @class=current()/@class]">
		  <xsl:for-each select="../../INSTANCE[@name=current()/../TO/@instance and @class='Organizational unit']/@id">
  		     <xsl:call-template name="addInModelConnectionForOrganizationalUnit">
			  <xsl:with-param name="toId" select="."/>
		     </xsl:call-template>
		  </xsl:for-each>
		</xsl:for-each>		
          <xsl:call-template name="elementPostProcessing"/>		
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Role
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Role">
		<xsl:call-template name="Role">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
		</xsl:call-template>
		<xsl:for-each select="../CONNECTOR/FROM[@instance=current()/@name and @class=current()/@class]">
		  <xsl:for-each select="../../INSTANCE[@name=current()/../TO/@instance and @class='Organizational unit']/@id">
  		     <xsl:call-template name="addInModelConnectionForRole">
			  <xsl:with-param name="toId" select="."/>
		     </xsl:call-template>
		  </xsl:for-each>
		</xsl:for-each>		
          <xsl:call-template name="elementPostProcessing"/>		
	</xsl:template>
<!--...............................................................................................-->	
<!--
___________________________________________________________________________________________________
 Performer
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Performer">
		<xsl:call-template name="Performer">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="email" select="./ATTRIBUTE[@name='E-Mail']" tunnel="yes"/>
		</xsl:call-template>
		<xsl:for-each select="../CONNECTOR/FROM[@instance=current()/@name and @class=current()/@class]">
		  <xsl:for-each select="../../INSTANCE[@name=current()/../TO/@instance and @class='Role']/@id">
  		     <xsl:call-template name="addInModelConnectionForPerformer">
			  <xsl:with-param name="toId" select="."/>
		     </xsl:call-template>
		  </xsl:for-each>
		</xsl:for-each>		
          <xsl:call-template name="elementPostProcessing"/>		
	</xsl:template>
<!--...............................................................................................-->	
<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
___________________________________________________________________________________________________
 Case Management Model and Notation (CMMN)
___________________________________________________________________________________________________-->

	<xsl:template match="MODEL" mode="CMMN">
		<xsl:call-template name="CMMN_MODEL">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@modeltype" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>	
		<xsl:apply-templates select=".//INSTANCE[@class='Case Plan Model']" mode="Case"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Task (Normal)']" mode="CaseTask"/>
		<xsl:apply-templates select=".//INSTANCE[@class='Discretionary Task']" mode="DiscretionaryTask"/>
		<xsl:apply-templates select=".//INSTANCE[@class='CaseFile']" mode="CaseFile"/>
		<xsl:apply-templates select=".//INSTANCE[@class='PlanningTable']" mode="PlanningTable"/>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 Case
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="Case">
		<xsl:call-template name="Case">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 CaseTask
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="CaseTask">
		<xsl:call-template name="CaseTask">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>	
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 DiscretionaryTask
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="DiscretionaryTask">
		<xsl:call-template name="DiscretionaryTask">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>	
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 CaseFile
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="CaseFile">
		<xsl:call-template name="CaseFile">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>	
	</xsl:template>
<!--...............................................................................................-->	

<!--
___________________________________________________________________________________________________
 PlanningTable
___________________________________________________________________________________________________-->
	<xsl:template match="INSTANCE" mode="PlanningTable">
		<xsl:call-template name="PlanningTable">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>		
	</xsl:template>
<!--...............................................................................................-->	

<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
 __________________________________________________________________________________________________   
 KPI-Overview
___________________________________________________________________________________________________-->
<xsl:template match="MODEL" mode="KPI">
		<xsl:call-template name="KPI_MODEL">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@modeltype" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>	
    <xsl:apply-templates select=".//INSTANCE[@class='Perspective']" mode="Perspective"/>
    <xsl:apply-templates select=".//INSTANCE[@class='Strategic goal']" mode="StrategicGoal"/>
    <xsl:apply-templates select=".//INSTANCE[@class='Performance indicator']" mode="PerformanceIndicator"/>
</xsl:template>
<!--...............................................................................................-->  
<!--
___________________________________________________________________________________________________
 Perspective
___________________________________________________________________________________________________-->
  <xsl:template match="INSTANCE" mode="Perspective">
		<xsl:call-template name="Perspective">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>
  </xsl:template>
<!--...............................................................................................-->  
<!--
___________________________________________________________________________________________________
 Strategic Goal
___________________________________________________________________________________________________-->
  <xsl:template match="INSTANCE" mode="StrategicGoal">
		<xsl:call-template name="StrategicGoal">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>
  </xsl:template>
<!--...............................................................................................--> 
<!--
___________________________________________________________________________________________________
 Performance Indicator
___________________________________________________________________________________________________-->
  <xsl:template match="INSTANCE" mode="PerformanceIndicator">
		<xsl:call-template name="PerformanceIndicator">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/> 
  </xsl:template>
<!--...............................................................................................--> 
<!-- ============================================================================================================================================== -->
<!-- ============================================================================================================================================== -->
<!--
 __________________________________________________________________________________________________   
 Competency Model
___________________________________________________________________________________________________-->
<xsl:template match="MODEL" mode="CM">
		<xsl:call-template name="COMPETENCY_MODEL">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@modeltype" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/>	
    <xsl:apply-templates select=".//INSTANCE[@class='Competency']" mode="Competency"/>
    <xsl:apply-templates select=".//INSTANCE[@class='Group']" mode="CompetencyGroup"/>
</xsl:template>
<!--...............................................................................................-->  
<!--
___________________________________________________________________________________________________
 Competency
___________________________________________________________________________________________________-->
  <xsl:template match="INSTANCE" mode="Competency">
		<xsl:call-template name="Competency">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:for-each select="../CONNECTOR/FROM[@instance=current()/@name and @class=current()/@class]">
		  <xsl:for-each select="../../INSTANCE[@name=current()/../TO/@instance and @class='Role']/@id">
  		     <xsl:call-template name="addInModelConnectionForCompetency">
			  <xsl:with-param name="toId" select="."/>
		     </xsl:call-template>
		  </xsl:for-each>
		</xsl:for-each>				
		<xsl:call-template name="elementPostProcessing"/> 
  </xsl:template>
<!--...............................................................................................-->  
<!--
___________________________________________________________________________________________________
 Competency Group
___________________________________________________________________________________________________-->
  <xsl:template match="INSTANCE" mode="CompetencyGroup">
		<xsl:call-template name="CompetencyGroup">
			<xsl:with-param name="id" select="@id" tunnel="yes"/>
			<xsl:with-param name="name" select="@name" tunnel="yes"/>
			<xsl:with-param name="class" select="@class" tunnel="yes"/>
		</xsl:call-template>
		<xsl:call-template name="elementPostProcessing"/> 
  </xsl:template>
<!--...............................................................................................--> 
<!-- ============================================================================================================================================== -->
</xsl:stylesheet>