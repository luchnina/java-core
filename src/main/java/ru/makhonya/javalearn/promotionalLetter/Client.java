package ru.makhonya.javalearn.promotionalLetter;


import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class Client {

    private String name;
    private LocalDate dateBirth;
    private int payroll;

    public Client(String name, LocalDate dateBirth, int payroll) {
        setName(name);
        setDateBirth(dateBirth);
        setPayroll(payroll);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = Optional.ofNullable(dateBirth)
                .orElse(dateBirth);
    }

    public int getPayroll() {
        return payroll;
    }

    public void setPayroll(Integer payroll) {
        this.payroll = payroll;
    }

    public boolean isLegalAge() {
        return dateBirth.isBefore(LocalDate.now().minusYears(18));
    }

    @Override
    public String toString() {
        return String.join(", ", name, dateBirth.toString(), String.valueOf(payroll));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return dateBirth == client.dateBirth && name.equals(client.name) && payroll == client.payroll;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateBirth, payroll);
    }
}
