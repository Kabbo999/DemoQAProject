
package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.TextBoxPage;

public class TextBoxTest extends BaseTest {

    private TextBoxPage textBoxPage;

    // Navigate to Text Box page before every test
    @BeforeMethod
    public void setUpTextBoxPage() {

        textBoxPage = new TextBoxPage(driver);

        textBoxPage.clickOnElements();
        textBoxPage.clickOnTextBox();
    }

    // Test Case 1: Verify all UI elements are visible
    @Test
    public void verifyUIElements() {

        Assert.assertTrue(
                textBoxPage.isFullNameVisible(),
                "Full Name field is not visible"
        );

        Assert.assertTrue(
                textBoxPage.isEmailVisible(),
                "Email field is not visible"
        );

        Assert.assertTrue(
                textBoxPage.isCurrentAddressVisible(),
                "Current Address field is not visible"
        );

        Assert.assertTrue(
                textBoxPage.isPermanentAddressVisible(),
                "Permanent Address field is not visible"
        );

        Assert.assertTrue(
                textBoxPage.isSubmitVisible(),
                "Submit button is not visible"
        );
    }

    // Test Case 2: Verify valid email is accepted
    @Test
    public void verifyValidEmailIsAccepted() {

        textBoxPage.enterEmail("test@gmail.com");
        textBoxPage.clickSubmit();

        Assert.assertFalse(
                textBoxPage.isEmailErrorDisplayed(),
                "Email validation error displayed for valid email"
        );
    }

    // Test Case 3: Verify Full Name is submitted
    @Test
    public void verifyFullName() {

        textBoxPage.enterFullName("Mohammad Abdullah");
        textBoxPage.clickSubmit();

        String actualName = textBoxPage.getOutput();

        Assert.assertEquals(
                actualName,
                "Name:Mohammad Abdullah",
                "Full Name validation failed"
        );
    }

    // Test Case 4: Verify Current Address is submitted
    @Test
    public void verifyCurrentAddress() {

        textBoxPage.enterCurrentAddress("8215 Britton");
        textBoxPage.clickSubmit();

        String actualCurrentAddress =
                textBoxPage.getCurrentAddressOutput();

        Assert.assertEquals(
                actualCurrentAddress,
                "Current Address :8215 Britton",
                "Current Address validation failed"
        );
    }
}
