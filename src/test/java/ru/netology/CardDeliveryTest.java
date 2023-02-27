package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;




import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    // 1. Отправка заявки с верно заполненными полями;
    @Test
    public void deliveryCard() {

        $("[data-test-id=city] [placeholder='Город']").setValue("Москва");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']");
        $("[data-test-id=name] [type=text]").setValue("Ибрагим Ибрагимов");
        $("[data-test-id=phone] [type=tel]").setValue("+79806314388");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " ));

    }
}
