package br.com.ibis.glue;

import br.com.ibis.controllers.models.Cliente;
import br.com.ibis.repositories.ClienteRepository;
import br.com.ibis.repositories.entities.ClienteEntity;
import br.com.ibis.util.ClienteBuilder;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class ClienteStep {

    private ClienteBuilder clienteBuilder;
    private Response response;

    @Autowired
    private ClienteRepository clienteRepository;

    @Before
    public void setup() {
        clienteBuilder = ClienteBuilder.builder();
    }

    @Dado("que os seguintes cliente estão cadastrados no banco de dados")
    public void cadastraBanco(io.cucumber.datatable.DataTable dataTable) {

        this.clienteRepository.deleteAll();
        List<Map<String, String>> maps = dataTable.asMaps();
        maps.forEach(row -> {
            ClienteEntity entity = new ClienteEntity(
                    row.get("nome"), row.get("e-mail"), row.get("telefone"),
                    null, null);
            this.clienteRepository.save(entity);
        });
    }

    @Dado("que o cliente preenche {string} que é seu nome")
    public void que_o_cliente_preenche_o_seu_nome(String string) {
        clienteBuilder = ClienteBuilder.builder();
        clienteBuilder.nome(string);
    }
    @Dado("que o cliente preenche o seu e-mail {string}")
    public void que_o_cliente_preenche_o_seu_e_mail(String string) {
        clienteBuilder.email(string);
    }
    @Dado("que o cliente preenche o seu telefone {string}")
    public void que_o_cliente_preenche_o_seu_telefone(String string) {
        clienteBuilder.telefone(string);
    }
    @Dado("que o cliente preenche a sua data de nascimento {string}")
    public void que_o_cliente_preenche_a_sua_data_de_nascimento(String string) {
        clienteBuilder.dataNascimento(string);
    }
    @Quando("o cliente clica para se cadastrar")
    public void o_cliente_clica_para_se_cadastrar() {
        Cliente build = clienteBuilder.build();
        this.response = RestAssured
            .given()
                .body(build)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post("clientes");
    }
    @Então("o status da resposta deve ser {int}")
    public void o_status_da_resposta_deve_ser(Integer int1) {
        Assertions.assertEquals(int1, this.response.getStatusCode());
    }
    @Dado("que o cliente preenche os seus dados:")
    public void queOClientePreencheOsSeusDados(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps();
        Map<String, String> row = maps.get(0);
        clienteBuilder
                .nome(row.get("nome"))
                .email(row.get("e-mail"))
                .telefone(row.get("telefone"))
                .dataNascimento(row.get("data nascimento"));
    }
}
