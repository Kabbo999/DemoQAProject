

package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ElementsPage;

public class ElementsTest extends BaseTest {

    private ElementsPage elementsPage;

    // Navigate to Text Box page before every test
    @BeforeMethod
    public void setUpTextBoxPage() {

        elementsPage = new ElementsPage(driver);

        elementsPage.clickOnElements();
        elementsPage.clickOnTextBox();
    }

    // Test Case 1: Verify all UI elements are visible
    @Test
    public void verifyUIElements() {

        Assert.assertTrue(
                elementsPage.isFullNameVisible(),
                "Full Name field is not visible"
        );

        Assert.assertTrue(
                elementsPage.isEmailVisible(),
                "Email field is not visible"
        );

        Assert.assertTrue(
                elementsPage.isCurrentAddressVisible(),
                "Current Address field is not visible"
        );

        Assert.assertTrue(
                elementsPage.isPermanentAddressVisible(),
                "Permanent Address field is not visible"
        );

        Assert.assertTrue(
                elementsPage.isSubmitVisible(),
                "Submit button is not visible"
        );
    }

    // Test Case 2: Verify valid email is accepted
    @Test
    public void verifyValidEmailIsAccepted() {

        elementsPage.enterEmail("test@gmail.com");
        elementsPage.clickSubmit();

        Assert.assertFalse(
                elementsPage.isEmailErrorDisplayed(),
                "Email validation error displayed for valid email"
        );
    }

    // Test Case 3: Verify Full Name is submitted
    @Test
    public void verifyFullName() {

        elementsPage.enterFullName("Mohammad Abdullah");
        elementsPage.clickSubmit();

        String actualName = elementsPage.getOutput();

        Assert.assertEquals(
                actualName,
                "Name:Mohammad Abdullah",
                "Full Name validation failed"
        );
    }

    // Test Case 4: Verify Current Address is submitted
    @Test
    public void verifyCurrentAddress() {

        elementsPage.enterCurrentAddress("8215 Britton");
        elementsPage.clickSubmit();

        String actualCurrentAddress =
                elementsPage.getCurrentAddressOutput();

        Assert.assertEquals(
                actualCurrentAddress,
                "Current Address :8215 Britton",
                "Current Address validation failed"
        );
    }
}


