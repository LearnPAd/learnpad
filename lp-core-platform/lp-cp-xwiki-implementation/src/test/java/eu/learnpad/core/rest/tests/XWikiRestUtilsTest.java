package eu.learnpad.core.rest.tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import eu.learnpad.core.rest.RestResource;
import eu.learnpad.core.rest.XWikiRestUtils;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;

public class XWikiRestUtilsTest {

	@Test
	@Ignore
	public void testEmailRetreival(){
		String wikiName = RestResource.CORE_REPOSITORY_WIKI;
//		TODO Dummy User To Be Created On Purpose and by Hand on the Wiki Instance
		String username = "dummy";
		String expectedEmail = "nothing@somewhere.org";
		
		System.err.println("Testing with : " + username + " - " + expectedEmail);
		
		String email = "";
		try {
			email = XWikiRestUtils.getEmailAddress(wikiName, username);
		} catch (LpRestExceptionXWikiImpl e) {
			Assert.fail();
		}
		
		Assert.assertEquals(email, expectedEmail);
		
	}

}
