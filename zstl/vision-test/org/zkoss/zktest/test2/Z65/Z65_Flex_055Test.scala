
package org.zkoss.zktest.test2.Z65

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase

@Tags(tags = "Z65-Flex-055.zul,Flex")
class Z65_Flex_055Test extends ZTL4ScalaTestCase {

def testClick() = {
  val zscript = """<zk><hbox>
    <window border="normal" height="360px"
        title="minimum Flex: [Calendar, Hlayout]" width="480px">
        <hlayout vflex="min" width="200px">
            <calendar hflex="1" vflex="1"/>
            <calendar height="60px" hflex="1"/>
            <calendar height="100px" hflex="1"/>
        </hlayout>
    </window>
    <window border="normal" height="360px"
        title="minimum Flex: [Calendar, Hbox]" width="480px">
        <hbox vflex="min" width="200px">
            <calendar hflex="1" vflex="1"/>
            <calendar height="60px" hflex="1"/>
            <calendar height="100px" hflex="1"/>
        </hbox>
    </window>
</hbox>
<hbox>
    <window border="normal" height="360px"
        title="minimum Flex: [Calendar, Hlayout, rounded]" width="480px">
        <hlayout vflex="min" width="200px">
            <calendar hflex="1" vflex="1"/>
            <calendar height="60px" hflex="1"/>
            <calendar height="100px" hflex="1"/>
        </hlayout>
    </window>
    <window border="normal" height="360px"
        title="minimum Flex: [Calendar, Hbox, rounded]" width="480px">
        <hbox vflex="min" width="200px">
            <calendar hflex="1" vflex="1"/>
            <calendar height="60px" hflex="1"/>
            <calendar height="100px" hflex="1"/>
        </hbox>
    </window>
</hbox>
</zk>"""  
  runZTL(zscript,
    () => {
      verifyImage()
    })
    
  }
}