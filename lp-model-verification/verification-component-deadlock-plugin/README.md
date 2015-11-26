Verification component plugin
====================

Information   | Value
------------- | --------
Component     | Verification Plugin
Partner       | Unicam
WP            | 4
Responsible   | Andrea Polini <andrea.polini@unicam.it>
Collaborators | Damiano Falcioni <damiano.falcioni@unicam.it>
Roadmap       | http://wiki.learnpad.eu/LearnPAdWiki/bin/view/Component/Model+Verification

# Summary
This component is a plugin for the [verification-component](../verification-component/) that provide deadlock check capabilities over a LearnPAd model.

# How it works?
The plugin is specific for the [verification-component](../verification-component/) and provide deadlock verification of a LearnPAd model. In order to work it must be placed in the plugin folder defined on the [verification-component](../verification-component/) and it will be automatically recognized.
This operation is currently automated by maven during the installation phase of the [verification-component](../verification-component/).

# Configuration
No configuration needed

# Interfaces
In order to be recognized by the [verification-component](../verification-component/), this plugin expose this [interface](./src/main/java/eu/learnpad/verification/plugin/interfaces/Plugin.java)
and define this [MANIFEST.MF](./src/main/resources/custom/MANIFEST.MF)

The output structure of the verification provided by this plugin is reported in the following:

		<FormalVerificationResult>
		   <VerificationType>..type of the verification..</VerificationType>
			<DefinitionID>..Model ID..</DefinitionID>
			<Status>..OK or KO..</Status>
			<Description>..detailed description of the result..</Description>
			<CounterExampleTrace>
				<Step num="1">
					<ObjectID>..bpmn object id..</ObjectID>
					<ObjectID>..bpmn object id..</ObjectID>
					<ObjectID>..bpmn object id..</ObjectID>
				</Step>
				<Step num="2">
					<ObjectID>..bpmn object id..</ObjectID>
					<ObjectID>..bpmn object id..</ObjectID>
				</Step>
				...
			</CounterExampleTrace>
			<CounterExampleTrace>
				<Step num="1">
					<ObjectID>..bpmn object id..</ObjectID>
				</Step>
				...
			</CounterExampleTrace>
			...
		</FormalVerificationResult>

In case of any error in the verification phase, the plugin output will look like in the following:

		<ErrorResult>
			<Status>ERROR</Status>
			<Description>..error message..</Description>
		</ErrorResult>