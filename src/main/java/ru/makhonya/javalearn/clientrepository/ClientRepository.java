package ru.makhonya.javalearn.clientrepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ClientRepository {
    void inRepository(String name, String phone, StatusClient statusClient);

    Client getClient(Long id);

    Client getClientByPhone(String phone);

    Map<Long, Client> getAllClients();

    List<Client> getVipClientsByDate(LocalDateTime startDate, LocalDateTime endDate);
}
