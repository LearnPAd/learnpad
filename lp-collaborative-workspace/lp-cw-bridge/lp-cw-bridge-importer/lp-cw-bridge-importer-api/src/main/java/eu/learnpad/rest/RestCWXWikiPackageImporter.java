package eu.learnpad.rest;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.xwiki.rest.XWikiRestException;

@Path("/learnpad/cw/package")
public interface RestCWXWikiPackageImporter {
	@PUT
	void putXWikiPackage(@QueryParam("path") String path) throws XWikiRestException;
}
