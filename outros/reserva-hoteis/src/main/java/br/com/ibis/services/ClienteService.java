package br.com.ibis.services;

import br.com.ibis.controllers.models.Cliente;
import br.com.ibis.email.SmtpClient;
import br.com.ibis.repositories.ClienteRepository;
import br.com.ibis.repositories.entities.ClienteEntity;
import br.com.ibis.services.exceptions.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final SmtpClient smtpClient;

    public ClienteService(ClienteRepository clienteRepository, SmtpClient smtpClient) {
        this.clienteRepository = clienteRepository;
        this.smtpClient = smtpClient;
    }

    public void cadastrarCliente(Cliente cliente) {
        if (Period.between(cliente.getDataNascimento(), LocalDate.now()).getYears() < 18) {
            throw new ClienteMenorDeIdadeException();
        }
        if (this.clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new EmailJaEstaSenUsadoException();
        }

        String randomSixDigitNumber  = randomSixDigitNumber();
        ClienteEntity entity = new ClienteEntity(cliente.getNome(),
                cliente.getEmail(), cliente.getTelefone(), cliente.getDataNascimento(), randomSixDigitNumber);
        this.clienteRepository.save(entity);
        this.smtpClient.sendEmail(cliente.getEmail(), randomSixDigitNumber);
    }

    public Cliente obterCliente(String email) {
        return this.clienteRepository.findByEmail(email).map(this::toClient).orElseThrow(EmailNaoCadastradoException::new);
    }

    public List<Cliente> obterListaDeClientes() {
        return this.clienteRepository.findAll().stream().map(this::toClient).collect(Collectors.toList());
    }

    public void ativarEmail(String email, String code) {

        ClienteEntity cliente = this.clienteRepository.findByEmail(email).orElseThrow(EmailNaoCadastradoException::new);
        if (!Objects.equals(cliente.getRandom2FA(), code)) {
            throw new CodigoDiferenteException();
        } else if (cliente.getEmailConfirmado()) {
            throw new EmailJaConfirmadoException();
        }

        cliente.setEmailConfirmado(true);
        this.clienteRepository.save(cliente);
    }

    private Cliente toClient(ClienteEntity clienteEntity) {
        return new Cliente(clienteEntity.getNome(), clienteEntity.getEmail(), clienteEntity.getTelefone(),
                clienteEntity.getDataNascimento());
    }

    public String randomSixDigitNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        System.out.println("2FA Code: " + randomNumber);
        return String.valueOf(randomNumber);
    }
}
