package eu.learnpad.simulator.monitoring.activiti.scoreprobe;

/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2016 Linagora
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.learnpad.sim.rest.event.ScoreType;
import eu.learnpad.simulator.mon.consumer.GlimpseAbstractConsumer;
import eu.learnpad.simulator.mon.utils.Manager;
import eu.learnpad.simulator.utils.SimulatorProperties;
import it.cnr.isti.labse.glimpse.xml.complexEventException.ComplexEventException;

/**
 *
 * This class instantiates a probe to the monitoring component in order to both
 * send the BPMN file and to receive and propagate score events related to a
 * specific session.
 *
 * Note: This class is instantiated using the static {@link create} method
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ScoreProbeConsumer extends GlimpseAbstractConsumer {

	private static final String USER_ID_PROPERTY = "USERID";

	private final IProbeScoresReceiver scoreReceiver;
	private final String sessionId;

	private final Logger logger = LoggerFactory.getLogger(ScoreProbeConsumer.class);

	private ScoreProbeConsumer(IProbeScoresReceiver scoreReceiver, Properties settings, String plainTextRule,
			List<String> usersInvolvedId, String sessionId, String bpmnId) {
		super(settings, plainTextRule, usersInvolvedId, sessionId, bpmnId);
		this.scoreReceiver = scoreReceiver;
		this.sessionId = sessionId;
	}

	@Override
	public void messageReceived(Message arg0) throws JMSException {
		try {
			ObjectMessage responseFromMonitoring = (ObjectMessage) arg0;
			if (responseFromMonitoring.getObject() instanceof ComplexEventException) {
				ComplexEventException exceptionReceived = (ComplexEventException) responseFromMonitoring.getObject();
				logger.error("Receive exception from probe: {}",
						exceptionReceived.getMessage());
				logger.error("Stack trace:\n{}", exceptionReceived.getStackTrace());
			} else if (responseFromMonitoring.getObject() instanceof Map) {

					@SuppressWarnings("unchecked")
					Map<ScoreType, Float> theScores = (Map<ScoreType, Float>)responseFromMonitoring.getObject();

					String userId = responseFromMonitoring.getStringProperty(USER_ID_PROPERTY);

					// propagate
					for (Entry<ScoreType, Float> score : theScores.entrySet()) {
						scoreReceiver.receiveScore(sessionId, userId, score.getKey(), score.getValue());
					}

				} else {
					logger.debug("Received unexpected message from probe: {}", responseFromMonitoring);
				}
		} catch (ClassCastException asd) {
			asd.printStackTrace();
		}
	}

	/**
	 *
	 * @param scoreReceiver
	 * @param sessionId
	 * @param bpmnId
	 * @param bpmnFile
	 * @param involvedUsers
	 * @return a new score probe consumer
	 */
	public static ScoreProbeConsumer create(IProbeScoresReceiver scoreReceiver, String sessionId, String bpmnId,
			String bpmnFile,
			List<String> involvedUsers) {

		Properties settings = Manager.createConsumerSettingsPropertiesObject(
				"org.apache.activemq.jndi.ActiveMQInitialContextFactory",
				SimulatorProperties.props.getProperty(SimulatorProperties.PROP_GLIMPSE_SERVER), "system", "manager", "TopicCF",
				"jms.serviceTopic", false, "SimulationComponent");

		return new ScoreProbeConsumer(scoreReceiver, settings, bpmnFile, involvedUsers, sessionId, bpmnId);
	}

}
