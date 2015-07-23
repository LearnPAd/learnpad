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
package eu.learnpad.core.impl.cw;

import java.util.List;
import java.util.Set;

import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.cw.CoreFacade;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.gm.rest.certif.data.CertificateDefinitionData;
import eu.learnpad.gm.rest.score.data.SessionScoreData;

@Component
public class XwikiCoreFacade implements XWikiRestComponent, CoreFacade {

	@Override
	public void commentNotification(String modelSetId, String commentId,
			String action) throws LpRestException {
		// TODO Auto-generated method stub

	}

	@Override
	public void resourceNotification(String modelSetId, String resourceId,
			String artifactIds, String action) throws LpRestException {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] getModel(String modelSetId, String type)
			throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToEditCount(String userId, Integer nbEdits) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getEditCount(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeSessionScore(SessionScoreData data) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SessionScoreData> getSessionScores(String userId,
			String processId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getGlobalScore(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void storeUserCertificate(String userId,
			String certificatedDefinitionId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<CertificateDefinitionData> getCertificates(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
