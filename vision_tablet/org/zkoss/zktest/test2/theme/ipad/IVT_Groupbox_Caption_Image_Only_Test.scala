package org.zkoss.zktest.test2.theme.ipad;

import org.zkoss.zstl.ZTL4ScalaTestCase;
import org.zkoss.ztl.Tags

@Tags(tags = "IOS,VisionTest")
class IVT_Groupbox_Caption_Image_Only_Test extends ZTL4ScalaTestCase {
	def testClick() = {
		val zscript = """
<groupbox width="300px" height="300px">
	<caption image="/common/img/volumn.gif" />
	Groupbox Content	
</groupbox>
		""";

		runZTL(zscript,
			() => {
				verifyImage();
			});
	}
}