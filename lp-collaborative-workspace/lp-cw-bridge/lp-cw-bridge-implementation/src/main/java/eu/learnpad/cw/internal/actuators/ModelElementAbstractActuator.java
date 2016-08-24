/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package eu.learnpad.cw.internal.actuators;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.model.reference.EntityReference;

import com.xpn.xwiki.objects.BaseObject;

import eu.learnpad.cw.UICWBridge;
import eu.learnpad.cw.internal.utils.LPCodeLabels;

public abstract class ModelElementAbstractActuator extends SimpleEventActuator {

	protected List<String> referenceLabelList;
	
	public ModelElementAbstractActuator(UICWBridge cwBridge) {			
		this(cwBridge,null);
	}

	public ModelElementAbstractActuator(UICWBridge cwBridge, Logger logger) {			
		super(cwBridge, logger);
		
		this.referenceLabelList = new ArrayList<String>();
		this.referenceLabelList.add(LPCodeLabels.getBASE_ELEMENT_CLASS());
		this.referenceLabelList.add(LPCodeLabels.getMODEL_CLASS());
		this.referenceLabelList.add(LPCodeLabels.getMODELSET_CLASS());
	}

	@Override
	protected void processEvent() {
		for (String referenceLabel : this.referenceLabelList) {
			EntityReference reference = this.targetEntityReference(referenceLabel);
			BaseObject xObject = this.editedDocument.getXObject(reference);
			if (xObject != null) {
				String userId = this.xcontext.getUserReference().getName();
				String resourceId = this.forgeResourceID(null);

				String modelSetId = xObject.getStringValue(LPCodeLabels
						.getMODELSETID_LABEL(referenceLabel));
				String modelId = xObject.getStringValue(LPCodeLabels
						.getMODELID_LABEL(referenceLabel));
				String artifactId = xObject.getStringValue(LPCodeLabels
						.getARTIFACTID_LABEL(referenceLabel));

				logger.info(modelSetId + modelId + artifactId + userId);
				this.notifyCWBridge(modelSetId, modelId, artifactId, resourceId, userId);
			}
		}
	}
	
	@Override
	protected EntityReference targetEntityReference(String optionalLabel) {
		EntityReference reference = new DocumentReference(this.xcontext.getMainXWiki(),LPCodeLabels.getCLASSES_SPACE(),optionalLabel);
		return reference;
	}

}
