<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" xmlns:kpi="http://ikm-group.ch/archiMEO/kpi#">
	<xsl:param name="businessActorId">barnaby.barnes@learnpad.com</xsl:param>
	<xsl:output method="xml" indent="yes"/>

	<xsl:template match="/">
<cockpit xml:space="preserve">
   <MODEL ID="352402" NAME="LearnPAd Scorecard!" TYPE="Cause and Effect Model">		
		<xsl:apply-templates select="//rdf:Description[./rdf:type/@rdf:resource='http://ikm-group.ch/archiMEO/kpi#Perspective']" mode="Perspective"/>
	</MODEL>
</cockpit>
	</xsl:template>
	
	<xsl:template match="rdf:Description" mode="Perspective">
		<xsl:element name="PERSPECTIVE">
			<xsl:attribute name="NAME"><xsl:value-of select="rdfs:label/text()"/></xsl:attribute>
			<xsl:attribute name="ID"><xsl:value-of select="@rdf:about"/></xsl:attribute>
			<xsl:attribute name="TYPE">Perspective</xsl:attribute>
			<xsl:variable name="id"><xsl:value-of select="@rdf:about"/></xsl:variable>
			<xsl:apply-templates select="//rdf:Description[./rdf:type/@rdf:resource='http://ikm-group.ch/archiMEO/kpi#OperationalGoal'][./kpi:operationalGoalHasPerspective/@rdf:resource=$id]" mode="OperationGoal"></xsl:apply-templates>
			<!-- xsl:apply-templates select="//rdf:Description[./rdf:type/@rdf:resource='http://ikm-group.ch/archiMEO/kpi#LearningGoal']" mode="LearningGoal"></xsl:apply-templates -->
			<!--xsl:apply-templates select="//rdf:Description[./rdf:type/@rdf:resource='http://ikm-group.ch/archiMEO/kpi#KPI']" mode="KPIValue"></xsl:apply-templates-->
		</xsl:element>
		    <xsl:text>&#10;</xsl:text>
	</xsl:template>
	
	<xsl:template match="rdf:Description" mode="OperationGoal">
		<xsl:element name="OPERATIONALGOAL">
			<xsl:attribute name="NAME"><xsl:value-of select="rdfs:label/text()"/></xsl:attribute>
			<xsl:attribute name="ID"><xsl:value-of select="@rdf:about"/></xsl:attribute>
			<xsl:attribute name="TYPE">Operational goal</xsl:attribute>
			<xsl:element name="PARENT">
				<xsl:attribute name="ID"><xsl:value-of select="./kpi:operationalGoalHasPerspective/@rdf:resource"/></xsl:attribute>
			</xsl:element>
		</xsl:element>
		    <xsl:text>&#10;</xsl:text>
	</xsl:template>
	
	<xsl:template match="rdf:Description" mode="LearningGoal">
		<xsl:element name="LEARNINGGOAL">
			<xsl:attribute name="NAME"><xsl:value-of select="rdfs:label/text()"/></xsl:attribute>
			<xsl:attribute name="ID"><xsl:value-of select="@rdf:about"/></xsl:attribute>
			<xsl:attribute name="TYPE">Learning goal</xsl:attribute>
		</xsl:element>
		<xsl:text>&#10;</xsl:text>
	</xsl:template>
   
</xsl:stylesheet>