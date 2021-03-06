/* F60_ZK_83Test.scala

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Wed Mar 21 15:57:13 CST 2012 , Created by benbai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zktest.test2.F60

import org.zkoss.zstl.ZTL4ScalaTestCase
import scala.collection.JavaConversions._
import org.junit.Test;
import org.zkoss.ztl.Element;
import org.zkoss.ztl.JQuery;
import org.zkoss.ztl.Tags;
import org.zkoss.ztl.util.Scripts;
import org.zkoss.ztl.Widget;
import org.zkoss.ztl.ZK;
import org.zkoss.ztl.ZKClientTestCase;
import java.lang._

/**
 * A test class for bug ZK-83
 * @author benbai
 *
 */
@Tags(tags = "F60-ZK-83.zul,F60,A,E,Messagebox")
class F60_ZK_83Test extends ZTL4ScalaTestCase {
	
  def testClick() = {
    val zscript = """
			<zk>
				<zscript>
				import org.zkoss.zktest.test2.ZK83Utils;
    			session.setAttribute(org.zkoss.web.Attributes.PREFERRED_LOCALE, new Locale("en_US"));
				</zscript>
				<vlayout>
					<hlayout>
					<button id="btnOne" label="Say Yes" onClick='Messagebox.show("Say Yes", "Yes", Messagebox.YES, Messagebox.INFORMATION)'/>
					shows a message box with a button called Yes
					</hlayout>
					<hlayout>
					<button id="btnTwo" label="Hi, OK" onClick="ZK83Utils.test0(self)"/>
					shows a message box with a button called OK
					</hlayout>
					<hlayout>
					<button id="btnThree" label="Hi, Cancel and OK" onClick="ZK83Utils.test1(self)"/>
					shows a message box with two buttons: Cancel and OK
					</hlayout>
					<hlayout>
					<button id="btnFour" label="Hi, Cancel, OK and No" onClick="ZK83Utils.test2(self)"/>
					shows a message box with two buttons: Cancel, OK and No. And, the focus is OK.
					</hlayout>
				</vlayout>
			</zk>

    """

    runZTL(zscript,
        () => {
        var btnOne: Widget = engine.$f("btnOne");
        var btnTwo: Widget = engine.$f("btnTwo");
        var btnThree: Widget = engine.$f("btnThree");
        var btnFour: Widget = engine.$f("btnFour");

        def clickAndCheck (wgt: Widget, toCheck: Array[String], toClick: Array[String]) {
          var offsetLeft: Int = 0;
          var messagebox: JQuery = null;
          click(wgt);
          waitResponse();
          messagebox = jq(".z-messagebox-window");
          for (i <- 0 until toCheck.length) {
            verifyTrue("Button "+toCheck(i)+" exists",
                messagebox.find(".z-button:contains("+toCheck(i)+")").exists());
            verifyTrue("Button should in correct order",
                messagebox.find(".z-button:contains("+toCheck(i)+")").offsetLeft() > offsetLeft);
            offsetLeft = messagebox.find(".z-button:contains("+toCheck(i)+")").offsetLeft();
          }
          for (j <- 0 until toClick.length) {
            click(messagebox.find(".z-button:contains("+toClick(j)+")"));
            waitResponse();
          }
        }
        clickAndCheck(btnOne, Array("Yes"), Array("Yes"));
        clickAndCheck(btnTwo, Array("OK"), Array("OK", "OK"));
        clickAndCheck(btnThree, Array("Cancel", "OK"), Array("OK", "OK"));
        clickAndCheck(btnFour, Array("Cancel", "OK", "No"), Array("OK", "OK"));
    }
   );
   }
}