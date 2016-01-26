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

import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.me.CoreFacade;
import eu.learnpad.mv.rest.data.VerificationId;
import eu.learnpad.mv.rest.data.VerificationResults;
import eu.learnpad.mv.rest.data.VerificationStatus;
import eu.learnpad.mv.rest.data.VerificationsAvailable;
import eu.learnpad.core.rest.RestResource;
import eu.learnpad.cw.rest.data.Feedbacks;
import eu.learnpad.cw.rest.data.PFResults;

/*
 * The methods inherited form the CoreFacade in this
 * class should be implemented as a REST invocation
 * toward the CoreFacade binded at the provided URL
 */
public class XwikiCoreFacadeRestResource extends RestResource implements CoreFacade {

	public XwikiCoreFacadeRestResource() {
		this("localhost",8080);
	}

	public XwikiCoreFacadeRestResource(String coreFacadeHostname,
			int coreFacadeHostPort) {
		// This constructor could change in the future
		this.updateConfiguration(coreFacadeHostname, coreFacadeHostPort);
	}
	
	public void updateConfiguration(String coreFacadeHostname, int coreFacadeHostPort){
// This constructor has to be fixed, since it requires changes on the class
//		eu.learnpad.core.rest.RestResource
		
	}
	
	@Override
	public void putModelSet(String modelSetId, String type, byte[] modelSetFile)
			throws LpRestExceptionImpl {
		// TODO Auto-generated method stub

	}

	@Override
	public PFResults getFeedbacks(String modelSetId) throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public VerificationId startModelSetVerification(String modelSetId, String type, String verification)
            throws LpRestException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VerificationStatus checkModelSetVerification(String verificationProcessId) throws LpRestException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VerificationResults getModelSetVerificationResults(String verificationProcessId) throws LpRestException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VerificationsAvailable getAvailableVerifications() throws LpRestException {
        // TODO Auto-generated method stub
        return null;
    }

}
