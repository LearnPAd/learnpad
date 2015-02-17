/**
 *
 */
package activitipoc;

import java.util.Collection;
import java.util.Map;

/**
 * This interface defines transformations between tasks IO and form data
 *
 *
 * @author jorquera
 *
 */
public interface IFormHandler {

	static interface FormResult {

		/**
		 *
		 * @return the parameters to initialize the process
		 */
		public Map<String, Object> getProperties();

		/**
		 *
		 * @return a mapping from roles to users (some roles may be mapped to
		 *         several users)
		 */
		public Map<String, Collection<String>> getRolesToUsersMapping();
	}

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
	 * @param singleRoles
	 * @param groupRoles
	 * @param users
	 * @return
	 */
	public String createStartingFormString(String processId,
			Collection<String> singleRoles, Collection<String> groupRoles,
			Collection<String> users);

	/**
	 * Transform a given form result data string into a result object
	 *
	 * @param data
	 * @return
	 */
	public FormResult parseResult(String data);

}
