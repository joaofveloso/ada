package br.com.ibis.services;

import br.com.ibis.controllers.models.Hotel;
import br.com.ibis.controllers.models.ParametroFiltroHoteis;
import br.com.ibis.repositories.HotelRepository;
import br.com.ibis.repositories.entities.HotelEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void cadastrarHotel(Hotel hotel) {

        this.hotelRepository.save(new HotelEntity(hotel.getNome(), hotel.getPais(), hotel.getEstado(),
                hotel.getCidade(), hotel.getQuantidadeDeQuartos()));
    }

    public Hotel obterHotel(String id) {

        return this.hotelRepository.findById(id).map(this::toHotel).orElseThrow();
    }

    public List<Hotel> obterListaHoteis(Map<String, String> queryParams, Pageable pageable) {

        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setPais(queryParams.get(ParametroFiltroHoteis.PAIS.name()));
        hotelEntity.setEstado(queryParams.get(ParametroFiltroHoteis.ESTADO.name()));
        hotelEntity.setCidade(queryParams.get(ParametroFiltroHoteis.CIDADE.name()));

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<HotelEntity> example = Example.of(hotelEntity, exampleMatcher);

        return this.hotelRepository.findAll(example, pageable).stream().map(this::toHotel).collect(Collectors.toList());
    }

    private Hotel toHotel(HotelEntity hotelEntity) {

        return new Hotel(hotelEntity.getId().toString(), hotelEntity.getNome(), hotelEntity.getPais(),
                hotelEntity.getEstado(), hotelEntity.getEstado(), hotelEntity.getQuantidadeDeQuartos());
    }
}
