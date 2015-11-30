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
package eu.learnpad.mv.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.mv.rest.data.VerificationId;
import eu.learnpad.mv.rest.data.VerificationResults;
import eu.learnpad.mv.rest.data.VerificationStatus;
import eu.learnpad.mv.rest.data.VerificationsAvailable;


public interface Verification {
    
    @GET
    @Path("/getavailableverifications")
    @Produces(MediaType.APPLICATION_XML)
    VerificationsAvailable getAvailableVerifications() throws LpRestException;

    @GET
    @Path("/startverification")
    @Produces(MediaType.APPLICATION_XML)
    VerificationId startVerification(@QueryParam("modelsetid") String modelSetId, @QueryParam("verificationtype") String verificationType) throws LpRestException;

    @GET
    @Path("/getverificationstatus")
    @Produces(MediaType.APPLICATION_XML)
    VerificationStatus getVerificationStatus(@QueryParam("verificationprocessid") String verificationProcessId) throws LpRestException;

    @GET
    @Path("/getverificationresult")
    @Produces(MediaType.APPLICATION_XML)
    VerificationResults getVerificationResult(@QueryParam("verificationprocessid") String verificationProcessId) throws LpRestException;
}
