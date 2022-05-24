package qa.demo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AuthFormTests {

    @BeforeAll
    static void SetUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void FillForm() {

        String name = "Anna";
        String secondName = "Banana";
        String email = "Anna@Banana.ru";
        String mobile = "79123654125";
        String subjects = "English";
        String currentAddress = "Saratov";

        open("/automation-practice-form");
        $("#app").shouldHave(text("Practice Form"));
        $("#firstName").setValue(name);
        $("#lastName").setValue(secondName);
        $("#userEmail").setValue(email);
        $("#gender-radio-1").doubleClick();
        $("#userNumber").setValue(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__day--021").click();
        $("#subjectsInput").setValue(subjects).pressEnter();
        $$(".custom-checkbox").get(1).click();
        $("input#uploadPicture").uploadFile(new File("src\\test\\java\\resources\\cat.png"));
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Noida").pressEnter();
        $(".btn-primary").click();
        $(".modal-body").shouldHave(text("Anna Banana"),text(email),text("Male"),text("21 November,1995"),
                text(subjects),text("Reading"),text("cat.PNG"),text(currentAddress),text("NCR Noida"));





    }


}
