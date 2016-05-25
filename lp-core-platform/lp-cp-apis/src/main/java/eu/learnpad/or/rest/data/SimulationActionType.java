package eu.learnpad.or.rest.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * All possible actions of simulation events. 
 * 
 * @author sandro.emmenegger
 */
@XmlType
@XmlEnum
public enum SimulationActionType {
	PROCESS_END, PROCESS_START, SESSION_SCORE_UPDATE, SIMULATION_END, SIMULATION_START, TASK_END, TASK_FAILED, TASK_START
}