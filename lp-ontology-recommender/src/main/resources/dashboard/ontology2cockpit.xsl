<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" xmlns:kpi="http://ikm-group.ch/archiMEO/kpi#" xmlns:lpd="http://learnpad.eu#" xmlns:emo="http://ikm-group.ch/archiMEO/emo#">
	<xsl:param name="businessActorUri">http://learnpad.eu/transfer#obj.39896</xsl:param>
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
			<xsl:apply-templates select="//rdf:Description[./rdf:type/@rdf:resource='http://ikm-group.ch/archiMEO/kpi#OperationalGoal' and ./kpi:operationalGoalHasPerspective/@rdf:resource=$id]" mode="OperationGoal"></xsl:apply-templates>
			<xsl:apply-templates select="//rdf:Description[./rdf:type/@rdf:resource='http://ikm-group.ch/archiMEO/kpi#LearningGoal' and ./kpi:learningGoalHasPerspective/@rdf:resource=$id]" mode="LearningGoal"></xsl:apply-templates>
			<xsl:apply-templates select="//rdf:Description[./rdfs:subClassOf/@rdf:resource='http://ikm-group.ch/archiMEO/kpi#KPI' and ./kpi:kpiHasPerspective/@rdf:resource=$id]" mode="KPIValue"></xsl:apply-templates>
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
		     <xsl:element name="PARENT">
				<xsl:attribute name="ID"><xsl:value-of select="./kpi:learningGoalEnablesOperationalGoal/@rdf:resource"/></xsl:attribute>
			</xsl:element>
			<xsl:call-template name="createChapterDetails">
				<xsl:with-param name="status"></xsl:with-param>
			</xsl:call-template>
		</xsl:element>
		<xsl:text>&#10;</xsl:text>
	</xsl:template>
	
	<xsl:template match="rdf:Description" mode="KPIValue">
		<xsl:variable name="apos">'</xsl:variable>
		<xsl:variable name="performanceValue" select="//rdf:Description[./rdf:type/@rdf:resource='http://learnpad.eu#PerformanceValue' and ./lpd:performanceValueBelongsToBusinessActor/@rdf:resource=$businessActorUri and ./rdf:type/@rdf:resource=current()/@rdf:about]"/>
		<xsl:variable name="isValue" select="$performanceValue/lpd:performanceValueHasActualValue/text()"/>
		<xsl:variable name="score" select="$performanceValue/lpd:performanceValueHasScore/text()"/>
		<xsl:element name="CRITERION">
			<xsl:attribute name="NAME"><xsl:value-of select='translate(rdfs:label/text(), $apos, "")'/></xsl:attribute>
			<xsl:attribute name="ID"><xsl:value-of select="@rdf:about"/></xsl:attribute>
			<xsl:attribute name="TYPE">Performance indicator</xsl:attribute>
			<xsl:element name="PARENT">
				<xsl:attribute name="ID"><xsl:value-of select="./kpi:kpiMeasuresLearningGoal/@rdf:resource"/><xsl:value-of select="./kpi:kpiMeasuresOperationalGoal/@rdf:resource"/></xsl:attribute>
			</xsl:element>			
			<xsl:call-template name="createChapterDetails">
				<xsl:with-param name="status" select="replace(replace(replace($score, '3', 'green'), '2', 'yellow'), '1', 'red')"/>
				<xsl:with-param name="isValue" select="$isValue"/>
				<xsl:with-param name="description" select='translate(kpi:kpiHasDescription/text(), $apos, "")'/>
				<xsl:with-param name="recommendation" select='translate(kpi:kpiProvidesRecommendation/text(), $apos, "")'/>
				<xsl:with-param name="trend" select="0"/>
				<xsl:with-param name="unit" select="kpi:kpiValueUnit/text()"/>
				<xsl:with-param name="target" select="kpi:kpiHasGreenYellowThreshold/text()"/>
			</xsl:call-template>
		</xsl:element>
		<xsl:text>&#10;</xsl:text>
	</xsl:template>	
	
	<xsl:template name="createChapterDetails">
		<xsl:param name="status"></xsl:param>
		<xsl:param name="description"></xsl:param>
		<xsl:param name="recommendation"></xsl:param>
		<xsl:param name="trend"></xsl:param>
		<xsl:param name="unit"></xsl:param>
		<xsl:param name="target"></xsl:param>
		<xsl:param name="isValue"></xsl:param>

		<xsl:element name="CHAPTER">
				<xsl:attribute name="NAME">Description</xsl:attribute>
				<xsl:element name="ATTRIBUTES">
					<xsl:attribute name="NAME">STATUS</xsl:attribute>
					<xsl:attribute name="TYPE">ENUMERATION</xsl:attribute>
					<xsl:element name="VALUE"><xsl:value-of select="$status"/></xsl:element>
				</xsl:element>
				<xsl:element name="ATTRIBUTES">
					<xsl:attribute name="NAME">Description</xsl:attribute>
					<xsl:attribute name="TYPE">LONGSTRING</xsl:attribute>
					<xsl:element name="VALUE"><xsl:value-of select="normalize-space($description)"/></xsl:element>
				</xsl:element>
				<xsl:element name="ATTRIBUTES">
					<xsl:attribute name="NAME">Recommendation</xsl:attribute>
					<xsl:attribute name="TYPE">LONGSTRING</xsl:attribute>
					<xsl:element name="VALUE"><xsl:value-of select="normalize-space($recommendation)"/></xsl:element>
				</xsl:element>				
				<xsl:element name="ATTRIBUTES">
					<xsl:attribute name="NAME">Trend</xsl:attribute>
					<xsl:attribute name="TYPE">String</xsl:attribute>
					<xsl:element name="VALUE"><xsl:value-of select="$trend"/></xsl:element>
				</xsl:element>
				<xsl:element name="ATTRIBUTES">
					<xsl:attribute name="NAME">Unit</xsl:attribute>
					<xsl:attribute name="TYPE">STRING</xsl:attribute>
					<xsl:element name="VALUE"><xsl:value-of select="$unit"/></xsl:element>
				</xsl:element>			
				<xsl:element name="ATTRIBUTES">
					<xsl:attribute name="NAME">Target</xsl:attribute>
					<xsl:attribute name="TYPE">STRING</xsl:attribute>
					<xsl:element name="VALUE"><xsl:value-of select="$target"/></xsl:element>
				</xsl:element>		
				<xsl:element name="ATTRIBUTES">
					<xsl:attribute name="NAME">IsValue</xsl:attribute>
					<xsl:attribute name="TYPE">STRING</xsl:attribute>
					<xsl:element name="VALUE"><xsl:value-of select="$isValue"/></xsl:element>
				</xsl:element>		
		</xsl:element>
			
			
			<CHAPTER NAME="Description">
               <ATTRIBUTES NAME="Description" TYPE="LONGSTRING">
                  <VALUE/>
               </ATTRIBUTES>
               <ATTRIBUTES NAME="STATUS" TYPE="ENUMERATION">
                  <VALUE>red</VALUE>
               </ATTRIBUTES>
               <ATTRIBUTES NAME="Comment" TYPE="LONGSTRING">
                  <VALUE/>
               </ATTRIBUTES>
               <ATTRIBUTES NAME="Responsibility" TYPE="INTERREF">
                  <VALUE> / </VALUE>
               </ATTRIBUTES>
               <ATTRIBUTES NAME="Periodicity" TYPE="ENUMERATION">
                  <VALUE>Year</VALUE>
               </ATTRIBUTES>
               <ATTRIBUTES NAME="Status and score details" TYPE="">
                  <VALUE/>
               </ATTRIBUTES>
               <ATTRIBUTES NAME="Score threshold green/yellow" TYPE="">
                  <VALUE/>
               </ATTRIBUTES>
               <ATTRIBUTES NAME="Score threshold yellow/red" TYPE="">
                  <VALUE/>
               </ATTRIBUTES>
               <ATTRIBUTES NAME="Score weighting SBU" TYPE="">
                  <VALUE/>
               </ATTRIBUTES>
            </CHAPTER>
	</xsl:template>
   
</xsl:stylesheet>