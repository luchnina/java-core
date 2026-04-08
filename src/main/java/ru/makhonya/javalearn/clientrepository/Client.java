package ru.makhonya.javalearn.clientrepository;

import org.jspecify.annotations.NonNull;

import java.time.Instant;
import java.util.Objects;

public class Client {
    Long id;
    String name;
    String phone;
    StatusClient status;
    Instant date;

    public Client(Long id, String name, String phone, StatusClient status, Instant date) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.date = date;
    }

    public boolean isVip() {
        return this.status == StatusClient.VIP;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public Instant getDate() {
        return this.date;
    }

    public StatusClient getStatus() {
        return this.status;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    @NonNull
    public String toString() {
        return this.getClass().getSimpleName() +
                "(id=" + id + "name=" + name + "status=" + status + "phone=" + phone + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return this.id.equals(client.id) && this.name.equals(client.name) && Objects.equals(this.phone, client.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.phone);
    }
}
