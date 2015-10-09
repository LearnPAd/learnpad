package eu.learnpad.simulator.utils;

/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2015 Linagora
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

import java.util.HashMap;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;

/**
 * Manages a collection of {@link BPMNExplorer} for the different process
 * definitions. Note that the {@link BPMNExplorer} instances are created lazily
 * and cached. Since they are immutable it should not create problem.
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class BPMNExplorerRepository {

	private final RepositoryService repositoryService;

	/**
	 * We will use them to get the subprocess associated with a task. Since
	 * several process instances share the same BPMN file, and all tasks from an
	 * instance will need it, we should keep them in memory to avoid constantly
	 * opening and closing files.
	 */
	private final Map<String, BPMNExplorer> explorerMap = new HashMap<String, BPMNExplorer>();

	public BPMNExplorerRepository(RepositoryService repositoryService) {
		super();
		this.repositoryService = repositoryService;
	}

	public BPMNExplorer getExplorer(String processDefinitionId) {

		synchronized (explorerMap) {
			if (!explorerMap.containsKey(processDefinitionId)) {
				// create a BPMN explorer and put it in cache
				BpmnModel model = repositoryService
						.getBpmnModel(processDefinitionId);
				String processDefinitionKey = repositoryService
						.createProcessDefinitionQuery()
						.processDefinitionId(processDefinitionId)
						.singleResult().getKey();
				try {
					explorerMap.put(processDefinitionId, new BPMNExplorer(
							processDefinitionKey, model));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}

		return explorerMap.get(processDefinitionId);
	}
}
