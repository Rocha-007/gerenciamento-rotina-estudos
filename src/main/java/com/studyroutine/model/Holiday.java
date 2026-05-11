package com.studyroutine.model;

import java.time.LocalDate;

/**
 * Modelo que representa um feriado nacional.
 * Utilizado para integração com API pública de feriados.
 * 
 * Exemplo de resposta da API:
 * {
 *   "date": "2024-01-01",
 *   "localName": "Ano Novo",
 *   "name": "New Year's Day",
 *   "countryCode": "BR",
 *   "fixed": true,
 *   "global": true,
 *   "counties": null,
 *   "launchYear": null,
 *   "type": "Public"
 * }
 */
public class Holiday {
    private LocalDate date;
    private String localName;
    private String name;
    private String countryCode;
    private boolean fixed;
    private boolean global;
    private String type;

    // Constructors
    public Holiday() {
    }

    public Holiday(LocalDate date, String localName, String name, String countryCode, boolean fixed, 
                   boolean global, String type) {
        this.date = date;
        this.localName = localName;
        this.name = name;
        this.countryCode = countryCode;
        this.fixed = fixed;
        this.global = global;
        this.type = type;
    }

    // Getters and Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "date=" + date +
                ", localName='" + localName + '\'' +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", fixed=" + fixed +
                ", global=" + global +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Holiday holiday = (Holiday) o;

        if (date != null ? !date.equals(holiday.date) : holiday.date != null) return false;
        if (localName != null ? !localName.equals(holiday.localName) : holiday.localName != null) return false;
        if (name != null ? !name.equals(holiday.name) : holiday.name != null) return false;
        return countryCode != null ? countryCode.equals(holiday.countryCode) : holiday.countryCode == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (localName != null ? localName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        return result;
    }
}
