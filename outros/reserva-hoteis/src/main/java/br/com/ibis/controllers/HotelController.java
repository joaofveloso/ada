package br.com.ibis.controllers;

import br.com.ibis.controllers.models.Hotel;
import br.com.ibis.controllers.models.ParametroFiltroHoteis;
import br.com.ibis.services.HotelService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hoteis")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarHotel(@RequestBody Hotel hotel) {

        this.hotelService.cadastrarHotel(hotel);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Hotel obterHotel(@PathVariable("id") String id) {

        return this.hotelService.obterHotel(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Hotel> obterListaHoteis(@RequestParam Map<String, String> queryParams, Pageable pageable) {
        return this.hotelService.obterListaHoteis(queryParams, pageable);
    }
}
