package ru.makhonya.javalearn.clientrepository;

import java.time.Instant;
import java.util.List;

public interface ClientRepository {

    Client getClient(Long id);

    Client getClientByPhone(String phone);

    List<Client> getVipClientsByDate(Instant startDate, Instant endDate);
}
