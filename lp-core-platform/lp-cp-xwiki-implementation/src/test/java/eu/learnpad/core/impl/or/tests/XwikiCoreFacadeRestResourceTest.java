package eu.learnpad.core.impl.or.tests;

import java.io.InputStream;
import java.io.StringReader;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.CharsetEncoder;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import eu.learnpad.core.impl.or.XwikiCoreFacadeRestResource;
import eu.learnpad.core.impl.tests.AbstractUnitTest;
import eu.learnpad.dash.rest.data.KPIValuesFormat;
import eu.learnpad.exception.LpRestException;

public class XwikiCoreFacadeRestResourceTest extends AbstractUnitTest {
	private XwikiCoreFacadeRestResource corefacade;
	
	public XwikiCoreFacadeRestResourceTest(){
		this.corefacade = new XwikiCoreFacadeRestResource();
	}
	
	@Test
	@Ignore
	public void pushKPIValuesTest(){
		
		boolean exceptionRaised = false;
		
		KPIValuesFormat format = this.generateFormat();
		String modelSetId = this.randomId();
		String businessActorId = this.randomId();
		InputStream cockpitContent = this.generateContens(); 
		try {
			this.corefacade.pushKPIValues(modelSetId, format, businessActorId, cockpitContent);
		} catch (LpRestException e) {
			exceptionRaised = true;
		}
		Assert.assertFalse(exceptionRaised);	
	}

	private InputStream generateContens() {
		String fooContent = "<foo> this is fake </foo>";
		InputStream stream = null;
		try {
			stream = new ByteArrayInputStream(fooContent.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;
	}

	private KPIValuesFormat generateFormat() {
		int size = KPIValuesFormat.values().length;
		int index = this.getRandomGenerator().nextInt(size);
		
		return KPIValuesFormat.values()[index];
	}
}
