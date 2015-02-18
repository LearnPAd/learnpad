/**
 *
 */
package activitipoc.uihandler.webserver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import activitipoc.Main;

/**
 *
 * To speed up testing, we compare page MD5 digests instead of the full page
 * content
 *
 * @author jorquera
 *
 */
public class WebServerTest {

	WebServer server;

	@Before
	public void init() {
		try {

			server = new WebServer(Main.PORT, "ui", "tasks", "process",
					mock(UIProcessServlet.class));
		} catch (Exception e) {
			System.err.println(e);
			fail("got exception during setup");
		}
	}

	@After
	public void stop() {
		server.stop();
	}

	@Test
	public void testListeningOnPort() {

		// check port listening
		try {
			new Socket("localhost", Main.PORT).close();
		} catch (Exception e) {
			fail("could not verify listening port on " + Main.PORT);
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testWebPagesAccessibles() {

		for (List<String> page : Arrays.asList(Arrays.asList("ui", "ui.html"),
				Arrays.asList("uiprocess", "ui-process.html"))) {

			try {
				URL url = new URL("http://localhost:" + Main.PORT + "/"
						+ page.get(0));

				// sadly, since we currently dynamically modify the served pages
				// to insert the server url, we still need to read the full
				// local file to do the replacement. And due to the fact that
				// the checksum of the String is different than the one of the
				// file (due to string encoding?), we need to do the same for
				// the fetched page.
				// So in this case we do not really gain speed by comparing only
				// checksums.

				String fetchedPageContent = readStream(url.openStream());

				String localPageContent = readStream(WebServerTest.class
						.getClassLoader().getResourceAsStream(page.get(1)));

				// set server ip for local content (since it is done on the
				// pages served by the server)
				localPageContent = localPageContent.replace(
						"#serveripaddress#", "\"" + WebServer.getIPAdress()
								+ ":" + Main.PORT + "\"");

				byte[] fetchedFileDigest = checksum(new ByteArrayInputStream(
						fetchedPageContent.getBytes()),
						MessageDigest.getInstance("MD5"));

				byte[] localFileDigest = checksum(new ByteArrayInputStream(
						localPageContent.getBytes()),
						MessageDigest.getInstance("MD5"));

				assertTrue(MessageDigest.isEqual(fetchedFileDigest,
						localFileDigest));

			} catch (Exception e) {
				System.err.println(e);
				fail("got exception for page " + page);
			}
		}

	}

	@Test
	public void checkStaticResourcesAccessibles() throws IOException {

		for (String file : getFilesRecursively("static")) {

			String fileName = file.substring(file.indexOf("/") + 1);
			try {

				URL localFileURL = WebServerTest.class.getClassLoader()
						.getResource(file);

				if (!new File(localFileURL.toURI()).isDirectory()) {

					URL url = new URL("http://localhost:" + Main.PORT
							+ "/resources/" + fileName);

					byte[] fetchedFileDigest = checksum(url.openStream(),
							MessageDigest.getInstance("MD5"));

					byte[] localFileDigest = checksum(WebServerTest.class
							.getClassLoader().getResourceAsStream(file),
							MessageDigest.getInstance("MD5"));

					assertTrue(MessageDigest.isEqual(localFileDigest,
							fetchedFileDigest));
				}
			} catch (Exception e) {
				System.err.println(e);
				fail("got exception for file " + file);
			}
		}
	}

	public Collection<String> getFilesRecursively(String root) {
		Set<String> result = new HashSet<String>();

		InputStream in = WebServerTest.class.getClassLoader()
				.getResourceAsStream(root);
		BufferedReader rdr = new BufferedReader(new InputStreamReader(in));
		String line;

		try {
			while ((line = rdr.readLine()) != null) {
				URL localFileURL = WebServerTest.class.getClassLoader()
						.getResource(root + "/" + line);

				// inner folder -> recurse
				if (new File(localFileURL.toURI()).isDirectory()) {
					result.addAll(getFilesRecursively(root + "/" + line));
				} else {
					result.add(root + "/" + line);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Utility method to convert an input stream to a String
	 *
	 * @param stream
	 * @return
	 * @throws IOException
	 */
	private String readStream(InputStream stream) throws IOException {
		String res = "";

		BufferedReader in = new BufferedReader(new InputStreamReader(stream));

		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			res += inputLine;
		}
		in.close();

		return res;
	}

	private byte[] checksum(InputStream is, MessageDigest digest)
			throws IOException {
		DigestInputStream cis = new DigestInputStream(is, digest);
		while (cis.read() >= 0) {
			// read the stream in full
		}
		cis.close();
		return cis.getMessageDigest().digest();
	}

}
