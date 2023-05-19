package br.com.ibis.controllers;

import br.com.ibis.controllers.models.Cliente;
import br.com.ibis.controllers.models.Reserva;
import br.com.ibis.services.ClienteService;
import br.com.ibis.services.ReservaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ReservaService reservaService;

    public ClienteController(ClienteService clienteService, ReservaService reservaService) {
        this.clienteService = clienteService;
        this.reservaService = reservaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarCliente(@Validated @RequestBody Cliente cliente) {
        this.clienteService.cadastrarCliente(cliente);
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente obterCliente(@PathVariable("email") String email) {

        return this.clienteService.obterCliente(email);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> obterListaDeClientes() {

        return this.clienteService.obterListaDeClientes();
    }

    @PostMapping("/{email}/reservas")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarReserva(@PathVariable("email") String email, @RequestBody Reserva reserva) {

        this.reservaService.criarReserva(email, reserva);
    }

    @PutMapping("/{email}/2fa/{code}")
    @ResponseStatus(HttpStatus.OK)
    public void ativarEmail(@PathVariable("email") String email, @PathVariable("code") String code) {

        this.clienteService.ativarEmail(email, code);
    }
}
