package org.zkoss.zktest.test2.B65
import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.openqa.selenium.By
import org.openqa.selenium.internal.Locatable
import org.openqa.selenium.interactions.HasTouchScreen
import org.zkoss.ztl.util.Scripts
import org.openqa.selenium.Keys
import org.zkoss.ztl.ZK
import org.junit.Test

@Tags(tags = "B65-ZK-1117.zul")
class B65_ZK_1117Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """<window title="My First Window" border="normal">
        <label multiline="true">
          1.click button, you should get alert : spinner value is null
2.change the spinner value to 1,then click button, you should get alert : spinner value is 1
3.clear the spinner value to empty,then click button, you should get alert : spinner value is null
4. please check the behavior with double spinner, the result should be the same
        </label>
        <spinner id="s"/>
        <button label="click1" onClick='alert("spinner value "+s.getValue())'/>
        <doublespinner id="sd"/>
        <button label="click2" onClick='alert("doublespinner value "+sd.getValue())'/>
      </window>"""

    runZTL(zscript,
      () => {
        0 to 1 foreach spinnerTest _
      })

    def spinnerTest(nth: Int) {
      if (nth != 1 && nth != 0) {
        spinnerTest(0)
      } else {
        val (clickTxt, upperbtn, snpinerinp) = if (nth == 0)
          ("click1", jq(".z-spinner").toWidget().$n("btn-up"), jq(".z-spinner").toWidget().$n("real"))
        else
          ("click2", jq(".z-doublespinner").toWidget().$n("btn-up"), jq(".z-doublespinner").toWidget().$n("real"))

        // click button, you should get the spinner error message. 
        val clickbtn = jq(".z-button:contains(" + clickTxt + ")")
        click(clickbtn)
        waitResponse()
        verifyTrue("Should show a alert box",
          jq(".z-messagebox-window").isVisible())
        click(jq(".z-messagebox-window .z-button"))
        waitResponse()

        // change the spinner value to 1,then click button, you should get alert : spinner value is 1
        click(jq(upperbtn))
        waitResponse()
        blur(snpinerinp)
        click(clickbtn)
        waitResponse()
        verifyTrue("Should show a alert box",
          jq(".z-messagebox-window").isVisible())
        click(jq(".z-messagebox-window .z-button"))

        // clear the spinner value to empty,then click button, you should get the spinner error messag.
        focus(snpinerinp)

        sendKeys(snpinerinp, Keys.END + "" + Keys.BACK_SPACE + "" + Keys.BACK_SPACE + "" + Keys.BACK_SPACE)

        verifyEquals(snpinerinp.get("value"), "")
        waitResponse()
        click(clickbtn)
        waitResponse()
        verifyTrue("Should show a alert box",
          jq(".z-messagebox-window").isVisible())
        click(jq(".z-messagebox-window .z-button"))
      }
    }

  }
}
