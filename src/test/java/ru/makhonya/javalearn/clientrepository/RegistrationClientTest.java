package ru.makhonya.javalearn.clientrepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegistrationClientTest {
    private final Map<Long, Client> clients = new HashMap<>();
    ClientRegistration clientRegistration;
    LocalDateTime start;
    LocalDateTime end;

    private static final Long lizaId = ThreadLocalRandom.current().nextLong();
    private static final Long katyId = ThreadLocalRandom.current().nextLong();
    private static final Long lilyId = ThreadLocalRandom.current().nextLong();


    @BeforeEach
    void setUp() {
        LocalDateTime now = LocalDateTime.now();
        start = now.minusSeconds(5);
        clients.put(lizaId,
                new Client(lizaId, "Лиза", "8992222", StatusClient.VIP, now.minusSeconds(4))
        );

        clients.put(katyId,
                new Client(katyId, "Катя", "8997222", StatusClient.VIP, now.minusSeconds(3))
        );

        clients.put(lilyId,
                new Client(lilyId, "Лиля", "8933333", StatusClient.USUAL, now.minusSeconds(2))
        );

        end = now.minusSeconds(1);

        clientRegistration = new ClientRegistration(clients);
    }


    @Test
    void inRepository_whenValidParametersProvided_thenClientIsStoredWithGeneratedIdAndCurrentTimestamp() {
        assertEquals(3, clientRegistration.getAllClients().size(),
                "После добавления клиент должен быть единственным в репозитории");
    }

    @Test
    void getClient_whenID_thenReturnsCurrentClient() {
        assertEquals(clients.get(katyId), clientRegistration.getClient(katyId),
                "Метод должен вернуть текущий клиент по id");
    }

    @Test
    void getClientByPhone_whenPhone_thenReturnsCurrentClient() {
        assertEquals(clients.get(lizaId), clientRegistration.getClientByPhone("8992222"),
                "Метод должен вернуть текущий клиент по телефону");
    }

    @Test
    void getVipClientsByDate_whenStartEndLocalDateTime_thenReturnsListClient() {
        List<Client> clientArrayList = new ArrayList<>();

        clientArrayList.add(clients.get(katyId));
        clientArrayList.add(clients.get(lizaId));

        assertEquals(clientArrayList, clientRegistration.getVipClientsByDate(start, end),
                "Метод должен вернуть отсортированный по убыванию массив VIP клиентов за период указанный");
    }
}