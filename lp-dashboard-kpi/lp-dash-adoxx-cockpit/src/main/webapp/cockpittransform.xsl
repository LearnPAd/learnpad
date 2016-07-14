<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:output method="text" />
	<xsl:template match="/">
		<xsl:text>[</xsl:text>
		<xsl:apply-templates select="//MODEL" />
		<xsl:text>]</xsl:text>
	</xsl:template>
	<xsl:template match="MODEL">
		<xsl:text>{</xsl:text>
		<xsl:text>dashname:'</xsl:text>
		<xsl:value-of select="@NAME" />
		<!-- default to none values for model and default icon -->
		<xsl:text>', iconCls:'icon-uwd', status: '', trend: '', unit:'', should: '', is: '', prevperiod:'', scor:'', scorbar:'',children:[</xsl:text>
		<xsl:apply-templates select="PERSPECTIVE" />
		<xsl:text>]</xsl:text>
		<xsl:text>}</xsl:text>
		<xsl:if test="position() != last()">
			<xsl:text>,</xsl:text>
		</xsl:if>
	</xsl:template>
	<xsl:template match="PERSPECTIVE">
		<xsl:text>{dashname:'</xsl:text>
		<xsl:value-of select="@NAME" />
		<xsl:text>', iconCls:'icon-pers', status: '', trend: '', unit:'', should: '', is: '', prevperiod:'', scor:'', scorbar:''</xsl:text>
		<xsl:choose>
			<xsl:when test="count(OPERATIONALGOAL) != 0">
				<xsl:text>, children:[</xsl:text>
				<xsl:apply-templates select="OPERATIONALGOAL" />
				<xsl:text>]}</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>, leaf:true}</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:if test="position() != last()">
			<xsl:text>,</xsl:text>
		</xsl:if>
	</xsl:template>


	<xsl:template match="STRATEGICGOAL">
		<xsl:variable name="type"
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/THRESHOLD_TYPE/." />
		<xsl:variable name="current"
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/ISVALUE/." />
		<xsl:variable name="previous">
			<xsl:value-of
				select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Actual State']//PERIOD[last()-1]/ATTRIBUTES[@NAME = 'Status']/ISVALUE" />
		</xsl:variable>
		<xsl:variable name="trend">
			<xsl:choose>
				<xsl:when test="$type = 'bottom up'">
					<xsl:choose>
						<xsl:when test="$current - $previous = 0">
							<xsl:text>0</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &gt; 0">
							<xsl:text>1</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &lt; 0">
							<xsl:text>-1</xsl:text>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text><!-- ERROR --></xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="$type = 'both'">
					<xsl:text><!-- FIXME --></xsl:text>
				</xsl:when>
				<xsl:when test="$type = 'top down'">
					<xsl:choose>
						<xsl:when test="$current - $previous = 0">
							<xsl:text>0</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &gt; 0">
							<xsl:text>-1</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &lt; 0">
							<xsl:text>1</xsl:text>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text><!-- ERROR --></xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:otherwise>
					<xsl:text><!-- ERROR --></xsl:text>
				</xsl:otherwise>
			</xsl:choose>

		</xsl:variable>

		<xsl:variable name="id" select="@ID" />
		<xsl:text>{dashname:'</xsl:text>
		<xsl:call-template name="maskCharacter">
			<xsl:with-param name="string" select="@NAME" />
			<xsl:with-param name="tobereplacedChar" select="','" />
			<xsl:with-param name="replaceChar" select="'&amp;#44;'" />
		</xsl:call-template>
		<xsl:text>', iconCls:'icon-stratgoal', status: '</xsl:text>
		<xsl:value-of
			select="./CHAPTER[@NAME = 'Description']/ATTRIBUTES[@NAME = 'STATUS']/VALUE" />
		<xsl:text>', trend: '</xsl:text>
		<xsl:value-of select="$trend" />
		<xsl:text>', objid:'</xsl:text>
		<xsl:value-of select="$id" />
		<xsl:text>', unit:'</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/MEASURETYPE/." />
		<xsl:text>', should: '</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/SHOULDVALUE/." />
		<xsl:text>', is: '</xsl:text>
		<xsl:value-of select="$current" />
		<xsl:text>', prevperiod:'', scor:'</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/SCORE/." />
		<xsl:text>', scorbar:'</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/SCORE/." />
		<xsl:text>'</xsl:text>
		<xsl:choose>
			<xsl:when
				test="(count(//CRITERION[./PARENT/@ID=$id]) + count(//OPERATIONALGOAL[./PARENT/@ID=$id])) != 0">
				<xsl:variable name="operativegoals">
					<xsl:apply-templates select="//OPERATIONALGOAL[./PARENT/@ID=$id]" />
				</xsl:variable>
				<xsl:variable name="criterion">
					<xsl:apply-templates select="//CRITERION[./PARENT/@ID=$id]" />
				</xsl:variable>
				<xsl:text>, children:[</xsl:text>
				<xsl:value-of select="$operativegoals" />
				<xsl:if
					test="string-length($operativegoals) != 0 and string-length($criterion) != 0">
					<xsl:text>,</xsl:text>
				</xsl:if>
				<xsl:value-of select="$criterion" />
				<xsl:text>]}</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>, leaf:true}</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:if test="position() != last()">
			<xsl:text>,</xsl:text>
		</xsl:if>
	</xsl:template>

	<xsl:template match="OPERATIONALGOAL">
		<xsl:variable name="type"
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/THRESHOLD_TYPE/." />
		<xsl:variable name="current"
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/ISVALUE/." />
		<xsl:variable name="previous">
			<xsl:value-of
				select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Actual State']//PERIOD[last()-1]/ATTRIBUTES[@NAME = 'Status']/ISVALUE" />
		</xsl:variable>
		<xsl:variable name="trend">
			<xsl:choose>
				<xsl:when test="$type = 'bottom up'">
					<xsl:choose>
						<xsl:when test="$current - $previous = 0">
							<xsl:text>0</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &gt; 0">
							<xsl:text>1</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &lt; 0">
							<xsl:text>-1</xsl:text>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text><!-- ERROR --></xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="$type = 'both'">
					<xsl:text><!-- FIXME --></xsl:text>
				</xsl:when>
				<xsl:when test="$type = 'top down'">
					<xsl:choose>
						<xsl:when test="$current - $previous = 0">
							<xsl:text>0</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &gt; 0">
							<xsl:text>-1</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &lt; 0">
							<xsl:text>1</xsl:text>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text><!-- ERROR --></xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:otherwise>
					<xsl:text><!-- ERROR --></xsl:text>
				</xsl:otherwise>
			</xsl:choose>

		</xsl:variable>

		<xsl:variable name="id" select="@ID" />
		<xsl:text>{dashname:'</xsl:text>
		<xsl:call-template name="maskCharacter">
			<xsl:with-param name="string" select="@NAME" />
			<xsl:with-param name="tobereplacedChar" select="','" />
			<xsl:with-param name="replaceChar" select="'&amp;#44;'" />
		</xsl:call-template>
		<xsl:text>', iconCls:'icon-operagoal', status: '</xsl:text>
		<xsl:value-of
			select="./CHAPTER[@NAME = 'Description']/ATTRIBUTES[@NAME = 'STATUS']/VALUE" />
		
		<xsl:text>', trend: '</xsl:text>
		<xsl:value-of select="$trend" />
		<xsl:text>', objid:'</xsl:text>
		<xsl:value-of select="$id" />
		<xsl:text>', unit:'</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/MEASURETYPE/." />
		<xsl:text>', should: '</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/SHOULDVALUE/." />
		<xsl:text>', is: '</xsl:text>
		<xsl:value-of select="$current" />
		<xsl:text>', prevperiod:'', scor:'</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/SCORE/." />
		<xsl:text>', scorbar:'</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/SCORE/." />
		<xsl:text>'</xsl:text>
		<xsl:choose>
			<xsl:when test="count(//LEARNINGGOAL[./PARENT/@ID=$id]) + count(//CRITERION[./PARENT/@ID=$id]) != 0">
				<xsl:text>, children:[</xsl:text>
                                <xsl:apply-templates select="//LEARNINGGOAL[./PARENT/@ID=$id]" />
                                <xsl:if test="count(//LEARNINGGOAL[./PARENT/@ID=$id]) != 0">, </xsl:if> 
				<xsl:apply-templates select="//CRITERION[./PARENT/@ID=$id]" />
				<xsl:text>]}</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>, leaf:true}</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:if test="position() != last()">
			<xsl:text>,</xsl:text>
		</xsl:if>
	</xsl:template>

        <xsl:template match="LEARNINGGOAL">
		<xsl:variable name="type"
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/THRESHOLD_TYPE/." />
		<xsl:variable name="current"
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/ISVALUE/." />
		<xsl:variable name="previous">
			<xsl:value-of
				select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Actual State']//PERIOD[last()-1]/ATTRIBUTES[@NAME = 'Status']/ISVALUE" />
		</xsl:variable>
		<xsl:variable name="trend">
			<xsl:choose>
				<xsl:when test="$type = 'bottom up'">
					<xsl:choose>
						<xsl:when test="$current - $previous = 0">
							<xsl:text>0</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &gt; 0">
							<xsl:text>1</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &lt; 0">
							<xsl:text>-1</xsl:text>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text><!-- ERROR --></xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="$type = 'both'">
					<xsl:text><!-- FIXME --></xsl:text>
				</xsl:when>
				<xsl:when test="$type = 'top down'">
					<xsl:choose>
						<xsl:when test="$current - $previous = 0">
							<xsl:text>0</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &gt; 0">
							<xsl:text>-1</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &lt; 0">
							<xsl:text>1</xsl:text>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text><!-- ERROR --></xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:otherwise>
					<xsl:text><!-- ERROR --></xsl:text>
				</xsl:otherwise>
			</xsl:choose>

		</xsl:variable>

		<xsl:variable name="id" select="@ID" />
		<xsl:text>{dashname:'</xsl:text>
		<xsl:call-template name="maskCharacter">
			<xsl:with-param name="string" select="@NAME" />
			<xsl:with-param name="tobereplacedChar" select="','" />
			<xsl:with-param name="replaceChar" select="'&amp;#44;'" />
		</xsl:call-template>
		<xsl:text>', iconCls:'icon-learning', status: '</xsl:text>
		<xsl:value-of
			select="./CHAPTER[@NAME = 'Description']/ATTRIBUTES[@NAME = 'STATUS']/VALUE" />
		
		<xsl:text>', trend: '</xsl:text>
		<xsl:value-of select="$trend" />
		<xsl:text>', objid:'</xsl:text>
		<xsl:value-of select="$id" />
		<xsl:text>', unit:'</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/MEASURETYPE/." />
		<xsl:text>', should: '</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/SHOULDVALUE/." />
		<xsl:text>', is: '</xsl:text>
		<xsl:value-of select="$current" />
		<xsl:text>', prevperiod:'', scor:'</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/SCORE/." />
		<xsl:text>', scorbar:'</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/SCORE/." />
		<xsl:text>'</xsl:text>
		<xsl:choose>
			<xsl:when test="count(//LEARNINGGOAL[./PARENT/@ID=$id]) + count(//CRITERION[./PARENT/@ID=$id]) != 0">
				<xsl:text>, children:[</xsl:text>
                                <xsl:apply-templates select="//LEARNINGGOAL[./PARENT/@ID=$id]" />
                                <!-- xsl:if test="count(//LEARNINGGOAL[./PARENT/@ID=$id]) != 0">, </xsl:if --> 
				<xsl:apply-templates select="//CRITERION[./PARENT/@ID=$id]" />
				<xsl:text>]}</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>, leaf:true}</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:if test="position() != last()">
			<xsl:text>,</xsl:text>
		</xsl:if>
	</xsl:template>

	<xsl:template match="CRITERION">
		<xsl:variable name="type"
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/THRESHOLD_TYPE/." />
		<xsl:variable name="current"
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/ISVALUE/." />
		<xsl:variable name="previous">
			<xsl:value-of
				select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Target Value(s)']//PERIOD[last()-1]/ATTRIBUTES[@NAME = 'Status']/ISVALUE" />
		</xsl:variable>
		<xsl:variable name="trend">
			<xsl:choose>
				<xsl:when test="$type = 'bottom up'">
					<xsl:choose>
						<xsl:when test="$current - $previous = 0">
							<xsl:text>0</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &gt; 0">
							<xsl:text>1</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &lt; 0">
							<xsl:text>-1</xsl:text>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text><!-- ERROR --></xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="$type = 'both'">
					<xsl:text><!-- FIXME --></xsl:text>
				</xsl:when>
				<xsl:when test="$type = 'top down'">
					<xsl:choose>
						<xsl:when test="$current - $previous = 0">
							<xsl:text>0</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &gt; 0">
							<xsl:text>-1</xsl:text>
						</xsl:when>
						<xsl:when test="$current - $previous &lt; 0">
							<xsl:text>1</xsl:text>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text><!-- ERROR --></xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:otherwise>
					<xsl:text><!-- ERROR --></xsl:text>
				</xsl:otherwise>
			</xsl:choose>

		</xsl:variable>
		<xsl:variable name="id" select="@ID" />
		<xsl:text>{dashname:'</xsl:text>
		<xsl:call-template name="maskCharacter">
			<xsl:with-param name="string" select="@NAME" />
			<xsl:with-param name="tobereplacedChar" select="','" />
			<xsl:with-param name="replaceChar" select="'&amp;#44;'" />
		</xsl:call-template>

		<xsl:text>', iconCls:'icon-crit', status: '</xsl:text>
		<xsl:value-of
			select="./CHAPTER[@NAME = 'Description']/ATTRIBUTES[@NAME = 'STATUS']/VALUE" />
                <xsl:text>', recommendation:'</xsl:text>
		<xsl:value-of
			select="./CHAPTER[@NAME = 'Description']/ATTRIBUTES[@NAME = 'Recommendation']/VALUE" />
                <xsl:text>', comment:'</xsl:text>
		<xsl:value-of
			select="./CHAPTER[@NAME = 'Description']/ATTRIBUTES[@NAME = 'Comment']/VALUE" />
		<xsl:text>', should: '</xsl:text>
                <xsl:text>', iconCls:'icon-crit', trend: '</xsl:text>
		<xsl:value-of
			select="./CHAPTER[@NAME = 'Description']/ATTRIBUTES[@NAME = 'Trend']/VALUE" />
		<xsl:text>', objid:'</xsl:text>
		<xsl:value-of select="$id" />
		<xsl:text>', unit:'</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Description']/ATTRIBUTES[@NAME = 'Unit']/VALUE" />
		<xsl:text>', target:'</xsl:text>
		<xsl:value-of
			select="./CHAPTER[@NAME = 'Description']/ATTRIBUTES[@NAME = 'Target']/VALUE" />
		<xsl:text>', value: '</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Description']/ATTRIBUTES[@NAME = 'IsValue']/VALUE/." />
		<xsl:text>', prevperiod:'</xsl:text>
		<xsl:value-of select="$previous" />
		<xsl:text>', scor:'</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/SCORE/." />
		<xsl:text>', scorbar:'</xsl:text>
		<xsl:value-of
			select="CHAPTER[@NAME = 'Status']/ATTRIBUTES[@NAME = 'Status']/SCORE/." />
		<xsl:text>'</xsl:text>
		<xsl:text>, leaf:true}</xsl:text>
		<xsl:if test="position() != last()">
			<xsl:text>,</xsl:text>
		</xsl:if>
	</xsl:template>
	<xsl:template name="maskCharacter">
		<xsl:param name="string" />
		<xsl:param name="tobereplacedChar" />
		<xsl:param name="replaceChar" />
		<xsl:choose>
			<xsl:when test="contains($string, $tobereplacedChar)">
				<xsl:value-of select="substring-before($string, $tobereplacedChar)" />
				<!-- Recurse through STRING -->
				<xsl:call-template name="maskCharacter">
					<xsl:with-param name="string"
						select="substring-after($string, $tobereplacedChar)" />
					<xsl:with-param name="tobereplacedChar" select="$tobereplacedChar" />
					<xsl:with-param name="replaceChar" select="$replaceChar" />
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$string" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>