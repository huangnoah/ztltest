package org.zkoss.zktest.test2.B70

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.junit.Test

@Tags(tags = "B70-ZK-2086.zul")
class B70_ZK_2086Test extends ZTL4ScalaTestCase {

@Test
def testClick() = {
  val zscript = """<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2086.zul

	Purpose:
		
	Description:
		
	History:
		Tue, Apr 15, 2014 12:33:26 PM, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
<zscript>
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.util.*;
import org.zkoss.zk.ui.ext.*;
import org.zkoss.zk.au.*;
import org.zkoss.zk.au.out.*;
import org.zkoss.zul.*;

public class TestComposer extends GenericForwardComposer{

  
  	public Bandbox bandBox1;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
          
           this.bandBox1.addEventListener(Events.ON_OPEN, new EventListener() {
                public void onEvent(Event event) {
                    //OpenEvent openEvent = (OpenEvent) event;
                    //openEvent.stopPropagation();
                }
            });

	}
}

</zscript>
1. Please open the first bandbox, it should not appear the browser scrollbar into the popup. (same as the second bandbox)
  <window border="normal" title="BandBox and ON_OPEN event listener with ZK7" apply="TestComposer">
  	<div></div>
         <bandbox id="bandBox1">
              <bandpopup height="200px">
                  <listbox multiple="true" vflex="1" checkmark="true" width="250px">
                      <listhead>
                          <listheader label="text">
                              <textbox id="textBox1" width="50%" />
                          </listheader>
                      </listhead>
                      <listitem label="a"/>
                      <listitem label="b"/>
                      <listitem label="c"/>
                      <listitem label="d"/>
                      <listitem label="e"/>
                      <listitem label="f"/>
                      <listitem label="g"/>
                      <listitem label="h"/>
                  </listbox>
              </bandpopup>
          </bandbox>
    Events.ON_OPEN listener (empty - just assigned) => render FAIL ON ZK7 (unwanted horizontal and vertical scrollbar are added). 
    <separator  />
         <bandbox id="bandBox2">
              <bandpopup height="200px">
                  <listbox multiple="true" vflex="1" checkmark="true" width="250px">
                      <listhead>
                          <listheader label="text">
                              <textbox id="textBox2" width="50%" />
                          </listheader>
                      </listhead>
                      <listitem label="1"/>
                      <listitem label="2"/>
                      <listitem label="3"/>
                      <listitem label="4"/>
                      <listitem label="5"/>
                      <listitem label="6"/>
                      <listitem label="7"/>
                      <listitem label="8"/>
                  </listbox>
              </bandpopup>
          </bandbox>
    No listener - OK
    <separator />
    Another problem - on both BandBoxes: second click and popup is showed at wrong position.
  </window>
</zk>

"""  
  runZTL(zscript,
    () => {
      0 to 1 foreach { index => 
        val btn = jq(".z-bandbox").eq(index).toWidget().$n("btn")
      	click(btn)
      	waitResponse()
      	verifyImage()
      	click(btn)
      	waitResponse()
      }
    })
    
  }
}