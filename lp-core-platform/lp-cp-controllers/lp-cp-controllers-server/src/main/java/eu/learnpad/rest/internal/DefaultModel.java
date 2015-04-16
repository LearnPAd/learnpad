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
package eu.learnpad.rest.internal;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;
import org.xwiki.rest.XWikiRestException;

import eu.learnpad.rest.Model;
import eu.learnpad.rest.utils.CPRestUtils;
import eu.learnpad.rest.utils.CWRestUtils;
import eu.learnpad.rest.utils.RestUtils;

@Component
@Named("eu.learnpad.rest.internal.DefaultModel")
public class DefaultModel implements XWikiRestComponent, Model {

	@Inject
	Logger logger;

	@Inject
	RestUtils restUtils;

	@Inject
	CPRestUtils cpRestUtils;

	@Inject
	CWRestUtils cwRestUtils;

	@Override
	public byte[] getModel(String modelId, String type)
			throws XWikiRestException {
		String attachmentName = modelId + "." + type;
		return restUtils.getAttachment(modelId, "WebHome", attachmentName);
	}

	@Override
	public void putModel(String modelId, String type, byte[] file)
			throws XWikiRestException {
		if (!restUtils.isPage(modelId, "WebHome")) {
			restUtils.createEmptyPage(modelId, "WebHome");
		}
		String attachmentName = modelId + "." + type;
		restUtils.putAttachment(modelId, "WebHome", attachmentName, file);
		cwRestUtils.postNotifyModel(modelId, type);
	}
}
