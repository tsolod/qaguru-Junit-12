package qa.demo;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AuthFormTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @CsvSource(value = {
            "Anna, Anna@gg.com, Samara, Saratov",
            "Борис, boris@ff.ru, NY, Moscow"})
    @ParameterizedTest (name = "Заполнение формы регистрации {0}")
    void fillFormLog(String userName, String email, String adress, String secondAdress ) {
        open("/text-box");
        $("#userName").setValue(userName);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(adress);
        $("#permanentAddress").setValue(secondAdress);
        $("#submit").click();
        $("#output").shouldHave(text(userName), text(email), text(adress), text(secondAdress));
    }

    @Disabled
    @Test
    public void fillForm() {

        String name = "AnnaAsф";
        String secondName = "Banana";
        String email = "Anna@Banana.ru";
        String mobile = "79123654125";
        String subjects = "English";
        String currentAddress = "Saratov";
        String imgPath = "cat.PNG";

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
        $("#uploadPicture").uploadFromClasspath(imgPath);
        //$("input#uploadPicture").uploadFile(new File("src\test\resources\cat.PNG"));
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Noida").pressEnter();
        $(".btn-primary").click();
        $(".modal-body").shouldHave(text("Anna Banana"),text(email),text("Male"),text("21 November,1995"),
                text(subjects),text("Reading"),text("cat.PNG"),text(currentAddress),text("NCR Noida"));
    }



    }



