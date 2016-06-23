package eu.learnpad.core.impl.or.tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import eu.learnpad.core.impl.or.XwikiBridgeInterfaceRestResource;
import eu.learnpad.exception.LpRestException;

public class XwikiBridgeInterfaceRestResourceTest extends AbstractUnitTest {
	private XwikiBridgeInterfaceRestResource bridge;
	
	public XwikiBridgeInterfaceRestResourceTest(){
		this.bridge = new XwikiBridgeInterfaceRestResource();
	}
		
	@Test
	@Ignore
	public void calculateKPITest() {
		boolean exceptionRaised = false;
		
		String modelSetId = this.randomId(); 
		try {
			this.bridge.calculateKPI(modelSetId);
		} catch (LpRestException e) {
			exceptionRaised = true;
		}
		Assert.assertFalse(exceptionRaised);
	}

	@Test
	@Ignore
	public void getHandlingProcessStatusTest(){
		boolean exceptionRaised = false;
		
		String kbProcessProcessId = this.randomId(); 
		try {
			this.bridge.getHandlingProcessStatus(kbProcessProcessId);
		} catch (LpRestException e) {
			exceptionRaised = true;
		}
		Assert.assertFalse(exceptionRaised);
	}
	
	public static void main (String[] args){
		XwikiBridgeInterfaceRestResourceTest me = new XwikiBridgeInterfaceRestResourceTest();		
		me.calculateKPITest();
		me.getHandlingProcessStatusTest();
	}
}