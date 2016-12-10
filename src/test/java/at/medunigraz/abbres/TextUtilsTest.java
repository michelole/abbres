package at.medunigraz.abbres;

import at.medunigraz.imi.abbres.TextUtils;
import junit.framework.TestCase;

public class TextUtilsTest extends TestCase {
	public void testLeft() {
		assertEquals("bd.", TextUtils.left("bd. Tbl."));
		assertEquals("bd.", TextUtils.left("bd. Tbl. tgl."));
	}
	
	public void testRight() {
		assertEquals("tgl.", TextUtils.right("Tbl. tgl."));
		assertEquals("tgl.", TextUtils.right("bd. Tbl. tgl."));
	}
}
