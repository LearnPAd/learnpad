/**
 *
 */
package activitipoc;

/**
 *
 * @author jorquera
 *
 * @param <Input>
 *            the type of data which is submitted in input of a task
 * @param <Output>
 *            the type of data which is submitted in output of a task
 */
public interface ITaskValidator<Input, Output> {

	/**
	 *
	 * @param processId
	 *            the id of the concerned process
	 * @param taskId
	 *            the id of the concerned task
	 * @param inData
	 *            the data which was given in input of the task
	 * @param proposedResult
	 *            the proposed result for the task
	 * @return whether or not the proposed result is valid
	 */
	public boolean taskResultIsValid(String processId, String taskId,
			Input inData, Output proposedResult);
}
