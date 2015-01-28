/**
 *
 */
package activitipoc;

import java.util.Map;

/**
 * This interface define transformations between tasks IO and form data
 *
 *
 * @author jorquera
 *
 */
public interface IFormHandler {

	/**
	 * Generate the JSON String required to instantiate a form corresponding to
	 * the given task
	 *
	 * @param activitiFormService
	 * @param taskId
	 * @return
	 */
	public String createFormString(String taskId);

	/**
	 * Transform a given form result data string into a map of key-values pairs
	 *
	 * @param data
	 * @return
	 */
	public Map<String, Object> parseResult(String data);

}
