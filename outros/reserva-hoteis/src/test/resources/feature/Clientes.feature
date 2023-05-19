#encoding: UTF-8
#language: pt
Funcionalidade: Gerenciamento de clientes

  @Contexto:
    Dado que os seguintes cliente estão cadastrados no banco de dados:
      | nome        | e-mail                  | telefone        | data nascimento |
      | João Pedro  | joao_pedro@outlook.com  | +5512336523656  | 05/06/1985      |
      | João Paulo  | joao_paulo@outlook.com  | +5512336525656  | 12/06/1989      |
      | Predo Paulo | pedro_paulo@outlook.com | +5519936525451  | 23/03/1992      |

Cenário: Cliente se cadastra no sistema com sucesso
  Dado que o cliente preenche "Joao Ada" que é seu nome
  E que o cliente preenche o seu e-mail "joao.veloso.fodra@outlook.com"
  E que o cliente preenche o seu telefone "+5511995632562"
  E que o cliente preenche a sua data de nascimento "11/05/1989"
  Quando o cliente clica para se cadastrar
  Então o status da resposta deve ser 201

Cenário: Cliente se cadastra no sistema com email que ja foi utilizado
  Dado que o cliente preenche os seus dados:
    | nome        | e-mail                  | telefone        | data nascimento |
    | João Pedro  | joao_pedro@outlook.com  | +5512336523656  | 05/06/1985      |
  Quando o cliente clica para se cadastrar
  Então o status da resposta deve ser 400

Cenário: Cliente se cadastra no sistema sem preencher o nome
  Dado que o cliente preenche os seus dados:
    | nome        | e-mail                  | telefone        | data nascimento |
    |             | joao_pedro@outlook.com  | +5512336523656  | 05/06/1985      |
  Quando o cliente clica para se cadastrar
  Então o status da resposta deve ser 400

Cenário: Cliente se cadastra no sistema sem preencher e-mail
  Dado que o cliente preenche os seus dados:
    | nome        | e-mail                  | telefone        | data nascimento |
    | João Pedro  |                         | +5512336523656  | 05/06/1985      |
  Quando o cliente clica para se cadastrar
  Então o status da resposta deve ser 400

  Cenário: Cliente se cadastra no sistema sem preencher o telefone
    Dado que o cliente preenche os seus dados:
      | nome        | e-mail                  | telefone        | data nascimento |
      | João Pedro  | joao_pedro@outlook.com  |                 | 05/06/1985      |
    Quando o cliente clica para se cadastrar
    Então o status da resposta deve ser 400