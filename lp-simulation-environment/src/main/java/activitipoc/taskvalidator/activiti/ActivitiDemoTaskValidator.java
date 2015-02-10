/**
 *
 */
package activitipoc.taskvalidator.activiti;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.activiti.engine.TaskService;

import activitipoc.ITaskValidator;

/**
 * @author jorquera
 *
 */
public class ActivitiDemoTaskValidator implements
		ITaskValidator<String, Map<String, Object>> {

	private final TaskService taskService;

	/**
	 * @param taskService
	 */
	public ActivitiDemoTaskValidator(TaskService taskService) {
		super();
		this.taskService = taskService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.ITaskValidator#taskResultIsValid(java.lang.String,
	 * java.lang.String, java.lang.Object, java.lang.Object)
	 */
	public boolean taskResultIsValid(String processId, String taskId,
			String inData, Map<String, Object> proposedResult) {

		String taskDefId = taskService.createTaskQuery().taskId(taskId)
				.singleResult().getTaskDefinitionKey();

		if (taskDefId.equals("validateIntegration")) {

			if (!proposedResult.containsKey("integrationValidated")
					|| !(proposedResult.get("integrationValidated").equals(
							"true") || proposedResult.get(
									"integrationValidated").equals("false"))) {
				// proposed result does not even contains the expected response!
				return false;
			}

			// Since the input is a simple string, we need to parse it with
			// regexes in order to get the input parameters

			boolean proposedAnswer = proposedResult.get("integrationValidated")
					.equals("true");

			Pattern approvedPattern = Pattern
					.compile("Integration approved : ((?:true)|(?:false))");
			Pattern checkedPattern = Pattern
					.compile("Integration checked : ((?:true)|(?:false))");

			Matcher approvedMtchr = approvedPattern.matcher(inData);
			Matcher checkedMtchr = checkedPattern.matcher(inData);

			if (!approvedMtchr.find() || !checkedMtchr.find()) {
				throw new RuntimeException(
						"Could not match parameters for task validateIntegration with inData "
								+ inData);
			}

			boolean integrationApproved = approvedMtchr.group(1).equals("true");
			boolean integrationChecked = checkedMtchr.group(1).equals("true");

			// proposed answer must be true iff integration is approved and
			// checked
			return proposedAnswer == (integrationApproved && integrationChecked);
		} else {
			// by default: return true
			return true;
		}
	}
}
