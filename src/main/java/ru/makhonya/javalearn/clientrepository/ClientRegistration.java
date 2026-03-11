package ru.makhonya.javalearn.clientrepository;

import org.jspecify.annotations.NonNull;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ClientRegistration implements ClientRepository {
    Map<Long, Client> clients = new HashMap<>();
    Map<String, Client> clientsByPhone = new HashMap<>();
    NavigableMap<StatisticKey, Client> clientsByUpdated = new TreeMap<>();

    public ClientRegistration(Map<Long, Client> clients) {
        this.clients.putAll(clients);
        for (Client client : clients.values()) {
            this.clientsByPhone.put(client.getPhone(), client);
            this.clientsByUpdated.put(
                    new StatisticKey(client.getStatus(), client.getDate(), client.getId()),
                    client
            );
        }
    }

    /**
     * Добавляет нового клиента в репозиторий {@code clients}.
     *
     * <p>Метод генерирует уникальный идентификатор {@code id} и фиксирует момент
     * создания {@code LocalDateTime.now()}. Затем создаёт объект {@link Client}
     * со всеми переданными параметрами и сохраняет его в {@code clients},
     * где ключом выступает сгенерированный {@code id}.
     *
     * @param name имя клиента; не должно быть {@code null}
     * @param phone телефон клиента; не должно быть {@code null}
     * @param statusClient статус клиента (например, {@link StatusClient#VIP});
     * @throws IllegalArgumentException если {@code name} или {@code phone}
     * равны {@code null}
     */
    public void inRepository(String name, String phone, StatusClient statusClient) {
        if (name == null) {
            throw new IllegalArgumentException("Имя клиента не может быть null");
        }

        if (phone == null) {
            throw new IllegalArgumentException("Номер телефона клиента не может быть null");
        }

        Long id = ThreadLocalRandom.current().nextLong();
        LocalDateTime localDateTime = LocalDateTime.now();

        Client client = new Client(id, name, phone, statusClient, localDateTime);
        clients.put(id, client);
        clientsByPhone.put(client.getPhone(), client);
        clientsByUpdated.put(new StatisticKey(statusClient, localDateTime, id), client);
    }

    public Client getClient(Long id) {
        return clients.get(id);
    }

    public Map<Long, Client> getAllClients() {
        return clients;
    }

    /**
     * Возвращает клиента по указанному номеру телефона.
     *
     * @param phone номер телефона
     * @return данные клиента по указзаному номеру
     * @throws IllegalArgumentException если номер телефона не указан
     */
    public Client getClientByPhone(String phone) {

        if (phone == null) {
            throw new IllegalArgumentException("Укажите номер телефона для поиска клиента");
        }

        return clientsByPhone.get(phone);
    }

    /**
     * Возвращает отсортированный по убиванию массив VIP клиентов за указанный период.
     *
     * @param startDate начало периода (может быть null → нет нижней границы)
     * @param endDate конец периода (может быть null → нет верхней границы)
     * @return отсортированный массив
     */
    public List<Client> getVipClientsByDate(LocalDateTime startDate, LocalDateTime endDate) {

        LocalDateTime start = (startDate != null) ? startDate : LocalDateTime.MIN;
        LocalDateTime end = (endDate != null) ? endDate : LocalDateTime.MAX;

        return new ArrayList<>(
                clientsByUpdated
                        .subMap(StatisticKey.minOfDate(start), true,
                                StatisticKey.maxOfDate(end), true)
                        .reversed()
                        .values()
        );
    }

    private record StatisticKey(
            StatusClient status,
            LocalDateTime date,
            Long id
    ) implements Comparable<StatisticKey> {

        public static StatisticKey minOfDate(LocalDateTime date) {
            return new StatisticKey(StatusClient.VIP, date, Long.MIN_VALUE);
        }

        public static StatisticKey maxOfDate(LocalDateTime date) {
            return new StatisticKey(StatusClient.VIP, date, Long.MAX_VALUE);
        }

        @Override
        public int compareTo(@NonNull StatisticKey o) {
            return Comparator.comparing(StatisticKey::status)
                    .thenComparing(StatisticKey::date)
                    .thenComparing(StatisticKey::id)
                    .compare(this, o);
        }
    }
}
