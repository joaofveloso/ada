package br.com.ibis.controllers.models;

import java.time.LocalDate;

public class Reserva {

    private String hotelId;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public String getHotelId() {
        return hotelId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }
}
