package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryPositiveTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    // 1. Отправка заявки с верно заполненными полями;
    @Test
    public void shouldReturnSuccessIfFieldsAreFilledInCorrectly() {

        LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateText = deliveryDateCard.format(formatter);

        $("[data-test-id=city] [placeholder='Город']").setValue("Москва");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id=name] [type=text]").setValue("Ибрагим Ибрагимов");
        $("[data-test-id=phone] [type=tel]").setValue("+79806314388");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + dateText));
    }

    // 2. Отправка заявки с указанием фамилии через дефис;
    @Test
    public void shouldReturnSuccessfullyIfSurnameWithHyphen() {
        LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateText = deliveryDateCard.format(formatter);

        $("[data-test-id=city] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова-Смирнова");
        $("[data-test-id=phone] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + dateText));
    }

    // 3. Отправка заявки с указанием города через дефис;
    @Test
    public void shouldReturnSuccessfullyIfCityWithHyphen() {
        LocalDate deliveryDateCard = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateText = deliveryDateCard.format(formatter);

        $("[data-test-id=city] [placeholder='Город']").setValue("Ростов-на-Дону");
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] [class='input__box'] [placeholder='Дата встречи']").setValue(dateText);
        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова-Смирнова");
        $("[data-test-id=phone] [type=tel]").setValue("+79883339918");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + dateText));
    }
}
