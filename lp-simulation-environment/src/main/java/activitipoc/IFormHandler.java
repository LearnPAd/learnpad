/**
 *
 */
package activitipoc;

import java.util.Map;

/**
 * This interface defines transformations between tasks IO and form data
 *
 *
 * @author jorquera
 *
 */
public interface IFormHandler {

	/**
	 * Generate the String required to instantiate a form corresponding to the
	 * given task
	 *
	 * @param activitiFormService
	 * @param taskId
	 * @return
	 */
	public String createFormString(String taskId);

	/**
	 * Generate the String required to instantiate a form corresponding to the
	 * starting activity of a process
	 *
	 * @param processId
	 * @return
	 */
	public String createStartingFormString(String processId);

	/**
	 * Transform a given form result data string into a map of key-values pairs
	 *
	 * @param data
	 * @return
	 */
	public Map<String, Object> parseResult(String data);

}
