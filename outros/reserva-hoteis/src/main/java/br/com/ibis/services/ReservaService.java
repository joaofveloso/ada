package br.com.ibis.services;

import br.com.ibis.controllers.models.Reserva;
import br.com.ibis.repositories.ClienteRepository;
import br.com.ibis.repositories.HotelRepository;
import br.com.ibis.repositories.entities.ClienteEntity;
import br.com.ibis.repositories.entities.HotelEntity;
import br.com.ibis.services.exceptions.EmailNaoCadastradoException;
import br.com.ibis.services.exceptions.EmailNaoConfirmadoException;
import br.com.ibis.services.exceptions.HotelNaoEncontrado;
import br.com.ibis.services.exceptions.HotelSemVagasException;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Random;

@Service
public class ReservaService {

    private final ClienteRepository clienteRepository;
    private final HotelRepository hotelRepository;

    public ReservaService(ClienteRepository clienteRepository, HotelRepository hotelRepository) {
        this.clienteRepository = clienteRepository;
        this.hotelRepository = hotelRepository;
    }

    public void criarReserva(String email, Reserva reserva) {
        ClienteEntity byEmail = this.clienteRepository.findByEmail(email).orElseThrow(EmailNaoCadastradoException::new);
        if (!byEmail.getEmailConfirmado()) {
            throw new EmailNaoConfirmadoException();
        }

        HotelEntity hotelEntity = this.hotelRepository.findCollidingReservaData(
                new ObjectId(reserva.getHotelId()), reserva.getCheckIn(), reserva.getCheckOut()).orElse(this.hotelRepository.findById(reserva.getHotelId()).orElseThrow(HotelNaoEncontrado::new));

        Integer quantidadeDeQuartos = hotelEntity.getQuantidadeDeQuartos();
        Integer quartosOcupados = hotelEntity.getReservas().size();
        if (quartosOcupados >= quantidadeDeQuartos) {
            throw new HotelSemVagasException();
        }

        hotelEntity.getReservas().add(new HotelEntity.DadosReserva(email, reserva.getCheckIn(), reserva.getCheckOut()));

        this.hotelRepository.save(hotelEntity);
    }
}
