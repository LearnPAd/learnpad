Verification understandability component plugin
====================

Information   | Value
------------- | --------
Component     | Verification Understandability Plugin
Partner       | Unicam, ISTI-CNR
WP            | 4
Responsible   | Andrea Polini <andrea.polini@unicam.it>
Collaborators | Giorgio Spagnolo [spagnolo at isti.cnr.it],  Damiano Falcioni <damiano.falcioni@unicam.it> 
Roadmap       | http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Model+Verification

# Summary
This component is a plugin for the verification component that provide understandability check over a LearnPAd model.

# How it works?
The plugin is specific for the Verification Component and provide understandability verification of a LearnPAd model. In order to work it must be placed in the plugin folder defined on the Verification Component and it will be automatically recognized.
This operation is currently automated by maven during the installation phase of the Verification Component.

# Configuration
No configuration needed

# Interfaces
In order to be recognized by the Verification Component, this plugin expose the following interface src/main/java/eu/learnpad/verification/plugin/interfaces/Plugin.java 
and define this MANIFEST.MF /src/main/resources/custom/MANIFEST.MF

The output structure of the verification provided by this plugin is reported in the following:

	<Result>
			<ProcessName>Empty</ProcessName> ..name of process..
   			<ProcessID>PROCESS_1</ProcessID> ..id of process..
			<Status>Ok or not</Status> ..status of the verification..
			<Description>Summary Description of result</Description>
			<Guidelines>
				<Guideline id="" name=""> ..id and name of the guideline..
					<Description>..detailed description of guideline..</Description>
					<Suggestion>..suggestion of the specific problem..</Suggestion>
					<Elements>..elements of the specific problem..
						<ElementID>..bpmn object id..</ElementID>
					</Elements>
				</Guideline>
				...
				<Guideline id="" name="">
					<Description>..detailed description of guideline..</Description>
					<Suggestion>..suggestion of the specific problem..</Suggestion>
					<Elements>..elements of the specific problem..
						<ElementID>..bpmn object id..</ElementID>
					</Elements>
				</Guideline>	
				...		
			</Guidelines>
	</Result>

In case of any error in the verification phase, the plugin output will look like in the following:

	<Result>
			<Status>ERROR</Status>
			<Description>..error message..</Description>
	</Result>