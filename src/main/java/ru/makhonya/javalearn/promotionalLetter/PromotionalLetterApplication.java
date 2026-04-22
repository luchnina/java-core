package ru.makhonya.javalearn.promotionalLetter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionalLetterApplication {

    static void main() {

    }

    public static List<String> generateEmails(List<Client> clients) {
        return clients.stream()
                .filter(Client::isLegalAge)
                .sorted(Comparator.comparing(Client::getPayroll).reversed())
                .map(client -> "Уважаемый " + client.getName() + ", мы рады предложить Вам скидку в 15%")
                .collect(Collectors.toList());
    }
}
