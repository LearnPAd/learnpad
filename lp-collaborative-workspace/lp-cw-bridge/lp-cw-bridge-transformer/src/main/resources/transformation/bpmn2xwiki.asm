<?xml version = '1.0' encoding = 'ISO-8859-1' ?>
<asm version="1.0" name="0">
	<cp>
		<constant value="bpmn2xwiki"/>
		<constant value="links"/>
		<constant value="NTransientLinkSet;"/>
		<constant value="col"/>
		<constant value="J"/>
		<constant value="getDocumentRoot"/>
		<constant value="main"/>
		<constant value="A"/>
		<constant value="OclParametrizedType"/>
		<constant value="#native"/>
		<constant value="Collection"/>
		<constant value="J.setName(S):V"/>
		<constant value="OclSimpleType"/>
		<constant value="OclAny"/>
		<constant value="J.setElementType(J):V"/>
		<constant value="String"/>
		<constant value="name"/>
		<constant value="__initname"/>
		<constant value="J.registerHelperAttribute(SS):V"/>
		<constant value="Sequence"/>
		<constant value="QJ.first():J"/>
		<constant value="TransientLinkSet"/>
		<constant value="A.DocumentRoot():V"/>
		<constant value="A.__matcher__():V"/>
		<constant value="A.__exec__():V"/>
		<constant value="7:16-7:22"/>
		<constant value="9:52-9:64"/>
		<constant value="self"/>
		<constant value="__resolve__"/>
		<constant value="1"/>
		<constant value="J.oclIsKindOf(J):B"/>
		<constant value="18"/>
		<constant value="NTransientLinkSet;.getLinkBySourceElement(S):QNTransientLink;"/>
		<constant value="J.oclIsUndefined():B"/>
		<constant value="15"/>
		<constant value="NTransientLink;.getTargetFromSource(J):J"/>
		<constant value="17"/>
		<constant value="30"/>
		<constant value="2"/>
		<constant value="A.__resolve__(J):J"/>
		<constant value="QJ.including(J):QJ"/>
		<constant value="QJ.flatten():QJ"/>
		<constant value="e"/>
		<constant value="value"/>
		<constant value="resolveTemp"/>
		<constant value="S"/>
		<constant value="NTransientLink;.getNamedTargetFromSource(JS):J"/>
		<constant value="__matcher__"/>
		<constant value="A.__matchStartEvent2Page():V"/>
		<constant value="A.__matchEndEvent2Page():V"/>
		<constant value="A.__matchTask2Page():V"/>
		<constant value="__exec__"/>
		<constant value="StartEvent2Page"/>
		<constant value="NTransientLinkSet;.getLinksByRule(S):QNTransientLink;"/>
		<constant value="A.__applyStartEvent2Page(NTransientLink;):V"/>
		<constant value="EndEvent2Page"/>
		<constant value="A.__applyEndEvent2Page(NTransientLink;):V"/>
		<constant value="Task2Page"/>
		<constant value="A.__applyTask2Page(NTransientLink;):V"/>
		<constant value="0"/>
		<constant value="!"/>
		<constant value="J.lastIndexOf(J):J"/>
		<constant value="J.+(J):J"/>
		<constant value="J.size():J"/>
		<constant value="J.substring(JJ):J"/>
		<constant value="7:45-7:49"/>
		<constant value="7:60-7:64"/>
		<constant value="7:77-7:80"/>
		<constant value="7:60-7:81"/>
		<constant value="7:82-7:83"/>
		<constant value="7:60-7:83"/>
		<constant value="7:85-7:89"/>
		<constant value="7:85-7:96"/>
		<constant value="7:45-7:97"/>
		<constant value="DocumentRoot"/>
		<constant value="XWIKI"/>
		<constant value="14:3-14:13"/>
		<constant value="14:33-14:34"/>
		<constant value="14:3-14:35"/>
		<constant value="13:2-15:3"/>
		<constant value="t"/>
		<constant value="__matchStartEvent2Page"/>
		<constant value="StartEvent"/>
		<constant value="BPMN2"/>
		<constant value="IN"/>
		<constant value="MMOF!Classifier;.allInstancesFrom(S):QJ"/>
		<constant value="TransientLink"/>
		<constant value="NTransientLink;.setRule(MATL!Rule;):V"/>
		<constant value="s"/>
		<constant value="NTransientLink;.addSourceElement(SJ):V"/>
		<constant value="Page"/>
		<constant value="NTransientLink;.addTargetElement(SJ):V"/>
		<constant value="NTransientLinkSet;.addLink2(NTransientLink;B):V"/>
		<constant value="20:5-22:3"/>
		<constant value="__applyStartEvent2Page"/>
		<constant value="NTransientLink;"/>
		<constant value="NTransientLink;.getSourceElement(S):J"/>
		<constant value="NTransientLink;.getTargetElement(S):J"/>
		<constant value="3"/>
		<constant value="J.oclType():J"/>
		<constant value="page"/>
		<constant value="21:11-21:12"/>
		<constant value="21:11-21:22"/>
		<constant value="21:11-21:27"/>
		<constant value="21:3-21:27"/>
		<constant value="24:3-24:13"/>
		<constant value="24:3-24:29"/>
		<constant value="24:36-24:37"/>
		<constant value="24:3-24:38"/>
		<constant value="23:2-25:3"/>
		<constant value="link"/>
		<constant value="__matchEndEvent2Page"/>
		<constant value="EndEvent"/>
		<constant value="30:5-32:3"/>
		<constant value="__applyEndEvent2Page"/>
		<constant value="31:11-31:12"/>
		<constant value="31:11-31:22"/>
		<constant value="31:11-31:27"/>
		<constant value="31:3-31:27"/>
		<constant value="34:3-34:13"/>
		<constant value="34:3-34:29"/>
		<constant value="34:36-34:37"/>
		<constant value="34:3-34:38"/>
		<constant value="33:2-35:3"/>
		<constant value="__matchTask2Page"/>
		<constant value="Task"/>
		<constant value="40:5-42:3"/>
		<constant value="__applyTask2Page"/>
		<constant value="41:11-41:12"/>
		<constant value="41:11-41:22"/>
		<constant value="41:11-41:27"/>
		<constant value="41:3-41:27"/>
		<constant value="44:3-44:13"/>
		<constant value="44:3-44:29"/>
		<constant value="44:36-44:37"/>
		<constant value="44:3-44:38"/>
		<constant value="43:2-45:3"/>
	</cp>
	<field name="1" type="2"/>
	<field name="3" type="4"/>
	<field name="5" type="4"/>
	<operation name="6">
		<context type="7"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<push arg="8"/>
			<push arg="9"/>
			<new/>
			<dup/>
			<push arg="10"/>
			<pcall arg="11"/>
			<dup/>
			<push arg="12"/>
			<push arg="9"/>
			<new/>
			<dup/>
			<push arg="13"/>
			<pcall arg="11"/>
			<pcall arg="14"/>
			<set arg="3"/>
			<push arg="15"/>
			<push arg="9"/>
			<findme/>
			<push arg="16"/>
			<push arg="17"/>
			<pcall arg="18"/>
			<getasm/>
			<push arg="19"/>
			<push arg="9"/>
			<new/>
			<call arg="20"/>
			<set arg="5"/>
			<getasm/>
			<push arg="21"/>
			<push arg="9"/>
			<new/>
			<set arg="1"/>
			<getasm/>
			<pcall arg="22"/>
			<getasm/>
			<pcall arg="23"/>
			<getasm/>
			<pcall arg="24"/>
		</code>
		<linenumbertable>
			<lne id="25" begin="16" end="18"/>
			<lne id="26" begin="23" end="26"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="27" begin="0" end="38"/>
		</localvariabletable>
	</operation>
	<operation name="28">
		<context type="7"/>
		<parameters>
			<parameter name="29" type="4"/>
		</parameters>
		<code>
			<load arg="29"/>
			<getasm/>
			<get arg="3"/>
			<call arg="30"/>
			<if arg="31"/>
			<getasm/>
			<get arg="1"/>
			<load arg="29"/>
			<call arg="32"/>
			<dup/>
			<call arg="33"/>
			<if arg="34"/>
			<load arg="29"/>
			<call arg="35"/>
			<goto arg="36"/>
			<pop/>
			<load arg="29"/>
			<goto arg="37"/>
			<push arg="19"/>
			<push arg="9"/>
			<new/>
			<load arg="29"/>
			<iterate/>
			<store arg="38"/>
			<getasm/>
			<load arg="38"/>
			<call arg="39"/>
			<call arg="40"/>
			<enditerate/>
			<call arg="41"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="42" begin="23" end="27"/>
			<lve slot="0" name="27" begin="0" end="29"/>
			<lve slot="1" name="43" begin="0" end="29"/>
		</localvariabletable>
	</operation>
	<operation name="44">
		<context type="7"/>
		<parameters>
			<parameter name="29" type="4"/>
			<parameter name="38" type="45"/>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<load arg="29"/>
			<call arg="32"/>
			<load arg="29"/>
			<load arg="38"/>
			<call arg="46"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="27" begin="0" end="6"/>
			<lve slot="1" name="43" begin="0" end="6"/>
			<lve slot="2" name="16" begin="0" end="6"/>
		</localvariabletable>
	</operation>
	<operation name="47">
		<context type="7"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<pcall arg="48"/>
			<getasm/>
			<pcall arg="49"/>
			<getasm/>
			<pcall arg="50"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="27" begin="0" end="5"/>
		</localvariabletable>
	</operation>
	<operation name="51">
		<context type="7"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<push arg="52"/>
			<call arg="53"/>
			<iterate/>
			<store arg="29"/>
			<getasm/>
			<load arg="29"/>
			<pcall arg="54"/>
			<enditerate/>
			<getasm/>
			<get arg="1"/>
			<push arg="55"/>
			<call arg="53"/>
			<iterate/>
			<store arg="29"/>
			<getasm/>
			<load arg="29"/>
			<pcall arg="56"/>
			<enditerate/>
			<getasm/>
			<get arg="1"/>
			<push arg="57"/>
			<call arg="53"/>
			<iterate/>
			<store arg="29"/>
			<getasm/>
			<load arg="29"/>
			<pcall arg="58"/>
			<enditerate/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="42" begin="5" end="8"/>
			<lve slot="1" name="42" begin="15" end="18"/>
			<lve slot="1" name="42" begin="25" end="28"/>
			<lve slot="0" name="27" begin="0" end="29"/>
		</localvariabletable>
	</operation>
	<operation name="17">
		<context type="45"/>
		<parameters>
		</parameters>
		<code>
			<load arg="59"/>
			<load arg="59"/>
			<push arg="60"/>
			<call arg="61"/>
			<pushi arg="38"/>
			<call arg="62"/>
			<load arg="59"/>
			<call arg="63"/>
			<call arg="64"/>
		</code>
		<linenumbertable>
			<lne id="65" begin="0" end="0"/>
			<lne id="66" begin="1" end="1"/>
			<lne id="67" begin="2" end="2"/>
			<lne id="68" begin="1" end="3"/>
			<lne id="69" begin="4" end="4"/>
			<lne id="70" begin="1" end="5"/>
			<lne id="71" begin="6" end="6"/>
			<lne id="72" begin="6" end="7"/>
			<lne id="73" begin="0" end="8"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="27" begin="0" end="8"/>
		</localvariabletable>
	</operation>
	<operation name="74">
		<context type="7"/>
		<parameters>
		</parameters>
		<code>
			<push arg="74"/>
			<push arg="75"/>
			<new/>
			<store arg="29"/>
			<load arg="29"/>
			<pop/>
			<getasm/>
			<load arg="29"/>
			<set arg="5"/>
		</code>
		<linenumbertable>
			<lne id="76" begin="6" end="6"/>
			<lne id="77" begin="7" end="7"/>
			<lne id="78" begin="6" end="8"/>
			<lne id="79" begin="6" end="8"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="80" begin="3" end="8"/>
			<lve slot="0" name="27" begin="0" end="8"/>
		</localvariabletable>
	</operation>
	<operation name="81">
		<context type="7"/>
		<parameters>
		</parameters>
		<code>
			<push arg="82"/>
			<push arg="83"/>
			<findme/>
			<push arg="84"/>
			<call arg="85"/>
			<iterate/>
			<store arg="29"/>
			<getasm/>
			<get arg="1"/>
			<push arg="86"/>
			<push arg="9"/>
			<new/>
			<dup/>
			<push arg="52"/>
			<pcall arg="87"/>
			<dup/>
			<push arg="88"/>
			<load arg="29"/>
			<pcall arg="89"/>
			<dup/>
			<push arg="80"/>
			<push arg="90"/>
			<push arg="75"/>
			<new/>
			<pcall arg="91"/>
			<pusht/>
			<pcall arg="92"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="93" begin="19" end="24"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="88" begin="6" end="26"/>
			<lve slot="0" name="27" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="94">
		<context type="7"/>
		<parameters>
			<parameter name="29" type="95"/>
		</parameters>
		<code>
			<load arg="29"/>
			<push arg="88"/>
			<call arg="96"/>
			<store arg="38"/>
			<load arg="29"/>
			<push arg="80"/>
			<call arg="97"/>
			<store arg="98"/>
			<load arg="98"/>
			<dup/>
			<getasm/>
			<load arg="38"/>
			<call arg="99"/>
			<get arg="16"/>
			<call arg="39"/>
			<set arg="16"/>
			<pop/>
			<getasm/>
			<get arg="5"/>
			<load arg="98"/>
			<set arg="100"/>
		</code>
		<linenumbertable>
			<lne id="101" begin="11" end="11"/>
			<lne id="102" begin="11" end="12"/>
			<lne id="103" begin="11" end="13"/>
			<lne id="104" begin="9" end="15"/>
			<lne id="93" begin="8" end="16"/>
			<lne id="105" begin="17" end="17"/>
			<lne id="106" begin="17" end="18"/>
			<lne id="107" begin="19" end="19"/>
			<lne id="108" begin="17" end="20"/>
			<lne id="109" begin="17" end="20"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="3" name="80" begin="7" end="20"/>
			<lve slot="2" name="88" begin="3" end="20"/>
			<lve slot="0" name="27" begin="0" end="20"/>
			<lve slot="1" name="110" begin="0" end="20"/>
		</localvariabletable>
	</operation>
	<operation name="111">
		<context type="7"/>
		<parameters>
		</parameters>
		<code>
			<push arg="112"/>
			<push arg="83"/>
			<findme/>
			<push arg="84"/>
			<call arg="85"/>
			<iterate/>
			<store arg="29"/>
			<getasm/>
			<get arg="1"/>
			<push arg="86"/>
			<push arg="9"/>
			<new/>
			<dup/>
			<push arg="55"/>
			<pcall arg="87"/>
			<dup/>
			<push arg="88"/>
			<load arg="29"/>
			<pcall arg="89"/>
			<dup/>
			<push arg="80"/>
			<push arg="90"/>
			<push arg="75"/>
			<new/>
			<pcall arg="91"/>
			<pusht/>
			<pcall arg="92"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="113" begin="19" end="24"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="88" begin="6" end="26"/>
			<lve slot="0" name="27" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="114">
		<context type="7"/>
		<parameters>
			<parameter name="29" type="95"/>
		</parameters>
		<code>
			<load arg="29"/>
			<push arg="88"/>
			<call arg="96"/>
			<store arg="38"/>
			<load arg="29"/>
			<push arg="80"/>
			<call arg="97"/>
			<store arg="98"/>
			<load arg="98"/>
			<dup/>
			<getasm/>
			<load arg="38"/>
			<call arg="99"/>
			<get arg="16"/>
			<call arg="39"/>
			<set arg="16"/>
			<pop/>
			<getasm/>
			<get arg="5"/>
			<load arg="98"/>
			<set arg="100"/>
		</code>
		<linenumbertable>
			<lne id="115" begin="11" end="11"/>
			<lne id="116" begin="11" end="12"/>
			<lne id="117" begin="11" end="13"/>
			<lne id="118" begin="9" end="15"/>
			<lne id="113" begin="8" end="16"/>
			<lne id="119" begin="17" end="17"/>
			<lne id="120" begin="17" end="18"/>
			<lne id="121" begin="19" end="19"/>
			<lne id="122" begin="17" end="20"/>
			<lne id="123" begin="17" end="20"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="3" name="80" begin="7" end="20"/>
			<lve slot="2" name="88" begin="3" end="20"/>
			<lve slot="0" name="27" begin="0" end="20"/>
			<lve slot="1" name="110" begin="0" end="20"/>
		</localvariabletable>
	</operation>
	<operation name="124">
		<context type="7"/>
		<parameters>
		</parameters>
		<code>
			<push arg="125"/>
			<push arg="83"/>
			<findme/>
			<push arg="84"/>
			<call arg="85"/>
			<iterate/>
			<store arg="29"/>
			<getasm/>
			<get arg="1"/>
			<push arg="86"/>
			<push arg="9"/>
			<new/>
			<dup/>
			<push arg="57"/>
			<pcall arg="87"/>
			<dup/>
			<push arg="88"/>
			<load arg="29"/>
			<pcall arg="89"/>
			<dup/>
			<push arg="80"/>
			<push arg="90"/>
			<push arg="75"/>
			<new/>
			<pcall arg="91"/>
			<pusht/>
			<pcall arg="92"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="126" begin="19" end="24"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="88" begin="6" end="26"/>
			<lve slot="0" name="27" begin="0" end="27"/>
		</localvariabletable>
	</operation>
	<operation name="127">
		<context type="7"/>
		<parameters>
			<parameter name="29" type="95"/>
		</parameters>
		<code>
			<load arg="29"/>
			<push arg="88"/>
			<call arg="96"/>
			<store arg="38"/>
			<load arg="29"/>
			<push arg="80"/>
			<call arg="97"/>
			<store arg="98"/>
			<load arg="98"/>
			<dup/>
			<getasm/>
			<load arg="38"/>
			<call arg="99"/>
			<get arg="16"/>
			<call arg="39"/>
			<set arg="16"/>
			<pop/>
			<getasm/>
			<get arg="5"/>
			<load arg="98"/>
			<set arg="100"/>
		</code>
		<linenumbertable>
			<lne id="128" begin="11" end="11"/>
			<lne id="129" begin="11" end="12"/>
			<lne id="130" begin="11" end="13"/>
			<lne id="131" begin="9" end="15"/>
			<lne id="126" begin="8" end="16"/>
			<lne id="132" begin="17" end="17"/>
			<lne id="133" begin="17" end="18"/>
			<lne id="134" begin="19" end="19"/>
			<lne id="135" begin="17" end="20"/>
			<lne id="136" begin="17" end="20"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="3" name="80" begin="7" end="20"/>
			<lve slot="2" name="88" begin="3" end="20"/>
			<lve slot="0" name="27" begin="0" end="20"/>
			<lve slot="1" name="110" begin="0" end="20"/>
		</localvariabletable>
	</operation>
</asm>
