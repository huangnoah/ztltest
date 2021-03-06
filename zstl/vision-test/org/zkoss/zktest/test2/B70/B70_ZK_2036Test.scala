package org.zkoss.zktest.test2.B70

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test

@Tags(tags = "B70-ZK-2036.zul")
class B70_ZK_2036Test extends ZTL4ScalaTestCase {

@Test
def testClick() = {
  val zscript = """<window title="Header Visibility" border="normal">
	<label multiline="true">
		1. All the listbox should not show Head5 and Cell5.
		2. The Head6 should be below the AUX2.
		3. Click the checkbox, Head5 and Cell 5 will show up.
		4. The Listheader and Listcell should be aligned.
	</label>
    <listbox>
        <auxhead>
            <auxheader align="center" colspan="5" label="AUX 1"/>
            <auxheader align="center" colspan="1" label="AUX 2"/>
        </auxhead>
        <listhead>
            <listheader label="Head1" hflex="min" />
            <listheader label="Head2"  hflex="min" />
            <listheader label="Head3"  hflex="min" />
            <listheader label="Head4"  hflex="min"  />
            <listheader visible="false" id="mh" label="Head5" hflex="min" />
            <listheader label="Head6"  hflex="min"  />
        </listhead>
        <listitem>
            <listcell label="Cell1" />
            <listcell label="Cell2" />
            <listcell label="Cell3" />
            <listcell label="Cell4" />
            <listcell label="Cell5" />
            <listcell label="Cell6" />
        </listitem>
    </listbox>
    <listbox>
        <auxhead>
            <auxheader align="center" colspan="5" label="AUX 1"/>
            <auxheader align="center" colspan="1" label="AUX 2"/>
        </auxhead>
        <listhead>
            <listheader label="Head1" hflex="1" />
            <listheader label="Head2"  hflex="1" />
            <listheader label="Head3"  hflex="1" />
            <listheader label="Head4"  hflex="1"  />
            <listheader visible="false" id="lh" label="Head5" hflex="1"/>
            <listheader label="Head6"  hflex="1"  />
        </listhead>
        <listitem>
            <listcell label="Cell1" />
            <listcell label="Cell2" />
            <listcell label="Cell3" />
            <listcell label="Cell4" />
            <listcell label="Cell5" />
            <listcell label="Cell6" />
        </listitem>
    </listbox>
    <listbox>
        <auxhead>
            <auxheader align="center" colspan="5" label="AUX 1"/>
            <auxheader align="center" colspan="1" label="AUX 2"/>
        </auxhead>
        <listhead>
            <listheader label="Head1"/>
            <listheader label="Head2" />
            <listheader label="Head3"/>
            <listheader label="Head4"/>
            <listheader visible="false" id="zh" label="Head5"/>
            <listheader label="Head6"/>
        </listhead>
        <listitem>
            <listcell label="Cell1" />
            <listcell label="Cell2" />
            <listcell label="Cell3" />
            <listcell label="Cell4" />
            <listcell label="Cell5" />
            <listcell label="Cell6" />
        </listitem>
    </listbox>
 <listbox>
        <auxhead>
            <auxheader align="center" colspan="5" label="AUX 1"/>
            <auxheader align="center" colspan="1" label="AUX 2"/>
        </auxhead>
        <listhead>
            <listheader label="Head1" width="100px"/>
            <listheader label="Head2" width="100px"/>
            <listheader label="Head3" width="100px"/>
            <listheader label="Head4" width="100px"/>
            <listheader visible="false" id="wh" label="Head5" width="100px"/>
            <listheader label="Head6" width="100px"/>
        </listhead>
        <listitem>
            <listcell label="Cell1" />
            <listcell label="Cell2" />
            <listcell label="Cell3" />
            <listcell label="Cell4" />
            <listcell label="Cell5" />
            <listcell label="Cell6" />
        </listitem>
    </listbox>
	<checkbox label="Change Visibility">
	  <attribute name="onCheck">
		  lh.setVisible(self.isChecked());
		  mh.setVisible(self.isChecked());
		  zh.setVisible(self.isChecked());
		  wh.setVisible(self.isChecked());
	  </attribute>
	</checkbox>
</window>"""  
  runZTL(zscript,
    () => {
      verifyTrue("All the listbox should not show Head5 and Cell5.", 
          jq(".z-listbox .z-listheader:contains(Head5)[style*=hidden]").length() == 4 && 
          jq(".z-listbox .z-listcell:contains(Cell5):visible").length() == 0)
      verifyImage()
      click(jq(".z-checkbox"))
      waitResponse()
      verifyImage()
      
    })
    
  }
}