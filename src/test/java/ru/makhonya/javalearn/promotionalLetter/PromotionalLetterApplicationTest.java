package ru.makhonya.javalearn.promotionalLetter;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.makhonya.javalearn.promotionalLetter.PromotionalLetterApplication.generateEmails;

class PromotionalLetterApplicationTest {

    @Test
    public void testGenerateEmailsForEligibleClients() {
        List<Client> clients = getClients();
        List<String> emails = generateEmails(clients);

        assertEquals(3, emails.size(),
                "Должно быть 3 письма для клиентов старше 18 лет.");
        assertTrue(emails.contains("Уважаемый Анна Петрова, мы рады предложить Вам скидку в 15%"),
                "Письмо для Анны Петровой не найдено.");
        assertTrue(emails.contains("Уважаемый Олег Кузнецов, мы рады предложить Вам скидку в 15%"),
                "Письмо для Олега Кузнецова не найдено.");
        assertTrue(emails.contains("Уважаемый Мария Сидорова, мы рады предложить Вам скидку в 15%"),
                "Письмо для Марии Сидоровой не найдено.");

        assertTrue(emails.get(0).contains("Олег Кузнецов"),
                "Письмо должно быть для клиента с наивысшим доходом.");
        assertTrue(emails.get(1).contains("Анна Петрова"),
                "Письмо должно быть для клиента с вторым по величине доходом.");
        assertTrue(emails.get(2).contains("Мария Сидорова"),
                "Письмо должно быть для клиента с минимальным доходом.");
    }

    private static @NonNull List<Client> getClients() {
        Client client1 = new Client(
                "Иван Иванов", LocalDate.of(2011, 4, 10), 50000
        );
        Client client2 = new Client(
                "Анна Петрова", LocalDate.of(2004, 6, 15), 70000
        );
        Client client3 = new Client(
                "Мария Сидорова", LocalDate.of(2003, 7, 25), 30000
        );
        Client client4 = new Client(
                "Олег Кузнецов", LocalDate.of(2005, 1, 1), 90000
        );

        return Arrays.asList(client1, client2, client3, client4);
    }
}