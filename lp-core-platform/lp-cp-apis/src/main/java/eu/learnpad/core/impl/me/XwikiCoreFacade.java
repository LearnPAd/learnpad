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
package eu.learnpad.core.impl.me;

import java.util.Set;

import eu.learnpad.cw.rest.data.Feedbacks;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.gm.rest.certif.data.CertificateDefinitionData;
import eu.learnpad.me.CoreFacade;
import eu.learnpad.mv.rest.data.MVResults;

public class XwikiCoreFacade implements CoreFacade {

	@Override
	public void putModelSet(String modelSetId, String type, byte[] modelSetFile)
			throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
	}

	@Override
	public String startModelSetVerification(String modelSetId, String type,
			String verification) throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MVResults checkModelSetVerification(String verificationProcessId)
			throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedbacks getFeedbacks(String modelSetId) throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String storeCertificateDefinition(CertificateDefinitionData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CertificateDefinitionData> getCertificateDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CertificateDefinitionData getCertificate(String certificateId) {
		// TODO Auto-generated method stub
		return null;
	}

}
