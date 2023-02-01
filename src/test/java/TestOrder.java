import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class TestOrder {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    void shouldTest() {


        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Самара");
        String planningDate = generateDate(4);
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(planningDate);
        $("[name=\"name\"]").setValue("Мария Маревич");
        $("[name=\"phone\"]").setValue("+7908888888");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Успешно!\")]").should(visible, Duration.ofSeconds(15));
        $x("//*[contains(text(),\"Встреча успешно забронирована на \")]");
        $("[data-test-id='success-notification']").shouldHave(text(planningDate));
    }
}
