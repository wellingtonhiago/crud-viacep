# CRUD Biblioteca com a API viacep

### **Framework:** Spring Boot

### **Linguagem de programação:** Java

### API: viacep

### **Banco de dados:** MongoDB atlas

Utilizei o MongoDB Atlas devido à minha familiaridade com ele, além de oferecer segurança, escalabilidade e gerenciamento do banco na nuvem. As configurações são enormes e ele é capaz de lidar com grandes volumes de dados, sendo ideal para bancos orientados a documentos. Com MongoDB Atlas, você pode ter acesso a recursos avançados de monitoramento e gerenciamento, além de garantir a integridade e a segurança dos seus dados.

## Sobre o projeto

Iniciei o projeto utilizando as tecnologias Spring Boot e Java, optando por implementar a arquitetura MVC para organizar o código. Essa arquitetura é uma das mais utilizadas na programação de software, pois separa as responsabilidades em diferentes camadas, o que ajuda a manter o código mais organizado e fácil de manter.

Além disso, utilizei a API ViaCEP, uma API de endereçamento que fornece informações precisas de endereços com base no CEP. A escolha dessa API foi estratégica, pois me permitiu obter informações de endereço de forma rápida e eficiente, sem precisar pesquisar manualmente em outras fontes.

O banco de dados utilizado foi o MongoDB, com a sua robustei foi possível criar e passar classes como atributos para outras classes, como um endereço que contém rua, cep e outros atributos para usuário que além de imprementar o atributo endereço ainda tem nome, idade, email etc.

## Pacote config

### `ViaCEPConfig`

```java
@Configuration
public class ViaCEPConfig {

    @Bean
    public ViaCEPClient viaCEPClient() {
        return new ViaCEPClient();
    }
}
```

Esse é um código em Java que utiliza a anotação `@Configuration` para indicar que a classe `ViaCEPConfig` é uma classe de configuração para a aplicação.

Dentro dessa classe, é definido um método `viaCEPClient()` que utiliza a anotação `@Bean` para indicar que ele é um bean gerenciado pelo Spring Framework. Esse bean é criado a partir de uma instância da classe `ViaCEPClient`, que é uma classe que faz uma chamada à API do ViaCEP para buscar informações de endereços a partir de CEPs.

## Pacote controller

### `AddressController`

```java
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private ViaCEPClient viaCEPClient;

    public void EnderecoController(ViaCEPClient viaCEPClient) {
        this.viaCEPClient = viaCEPClient;
    }

    @GetMapping("/{cep}")
    public ViaCEPEndereco getEndereco(@PathVariable String cep) throws IOException {
        return viaCEPClient.getEndereco(cep);
    }
}
```

Esse é um código em Java que define um controlador REST com um método `getEndereco()` que é acionado quando uma requisição HTTP GET é feita na URL `/address/{cep}`. Esse método utiliza o objeto `viaCEPClient` (injetado através da anotação `@Autowired`) para buscar informações do endereço correspondente ao CEP informado e retorna essas informações como resposta da requisição.

Esse controlador poderia ser utilizado em um sistema que precisa buscar informações de endereços a partir de CEPs. Quando o usuário informa um CEP, a aplicação envia uma requisição HTTP para o endpoint `/address/{cep}` e o controlador retorna as informações do endereço correspondente em formato JSON, por exemplo, para que a aplicação cliente possa utilizá-las em sua interface gráfica.

### `ClientController`

```java
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clients;

    @PostMapping
    public ResponseEntity<Client> register(@RequestBody Client client) {
        return ResponseEntity.ok(clients.save(client));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Client> getClient(@PathVariable String cpf){
        return ResponseEntity.ok(clients.findByCPF(cpf));
    }

    @PatchMapping("/{cpf}")
    public ResponseEntity<Client> updateClient(@PathVariable String cpf, @RequestBody Map<String, Object> updates) {
        Client client = clients.findByCPF(cpf);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(clients.save(ClientService.update(client, updates)));
    }

}
```

Esse trecho de código em Java define um controlador REST muito útil para gerenciar clientes em uma aplicação. Ele oferece métodos que permitem cadastrar, buscar e atualizar informações de clientes em uma base de dados. Os métodos `register()`, `getClient()` e `updateClient()` são acionados a partir de requisições HTTP enviadas para o servidor. Essas funções utilizam a injeção de dependência do Spring para acessar o objeto `ClientRepository`, que é responsável por armazenar os dados na base de dados. Com esse código, fica mais fácil criar uma aplicação robusta e escalável para gerenciar os clientes de uma empresa.

No método `registe()` pode-se cadastrar clientes em json com as seguintes informações: `cpf`, `name`, `email` e `cep`. O cep vai direto na classe `address` que busca a localização pela API viacep e assim, informando apenas o `cep`, já é possível buscar todo um endereçamento para `client`.

## `EmployeeController`

```java
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employees;

    @PostMapping
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
        return ResponseEntity.ok(employees.save(employee));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String cpf){
        return ResponseEntity.ok(employees.findByCPF(cpf));
    }

    @PatchMapping("/{cpf}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String cpf, @RequestBody Map<String, Object> updates) {
        Employee employee = employees.findByCPF(cpf);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employees.save(EmployeeService.update(employee, updates)));
    }

    @DeleteMapping("/{cpf}")
    public void deleteEmployee(@PathVariable String cpf){
        employees.delete(employees.findByCPF(cpf));
    }

    @GetMapping("/count/{cep}")
    public int count(@PathVariable String cep){
        return employees.findAllByCEP(Collections.singleton(cep)).size();
    }

}
```

Esse é um código em Java que define um controlador REST para gerenciar funcionários de uma empresa. Ele permite cadastrar, buscar, atualizar e deletar informações dos funcionários em uma base de dados. Além disso, ele possui um método que conta o número de funcionários cadastrados com um determinado CEP, Assim é possível saber qual o cep onde tem mais funcionários. Esses métodos utilizam a injeção de dependência do Spring para acessar um objeto `EmployeeRepository` que é responsável pela persistência dos dados na base de dados. Com esse código, é possível criar uma aplicação robusta e escalável para gerenciar os funcionários de uma empresa.

## Pacote Model

## `Address`

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;

    public Address(String cep) throws IOException {
        this.cep = cep;

        ViaCEPClient client = new ViaCEPClient();
        ViaCEPEndereco endereco = client.getEndereco(cep);

        this.logradouro = endereco.getLogradouro();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.localidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
        this.ibge = endereco.getIbge();
    }
}
```

Classe de endereço, que contém informações como `CEP`, `logradouro`, `bairro`, `localidade`, `estado`, entre outros. Essa classe possui construtores com e sem parâmetros, permitindo criar um objeto de endereço informando apenas o CEP ou com todas as informações. O construtor que recebe apenas o CEP utiliza a biblioteca `ViaCEPClient` para obter as informações do endereço a partir do CEP informado. Esse código é muito útil para quem precisa obter informações de endereço a partir do CEP em sua aplicação Java.

## `Cliente`

```java
@Document("client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    String cpf;
    String name;
    String email;
    Address cep;

}
```

classe de cliente com anotações para utilização do Spring Data MongoDB, que é um framework para integração com o banco de dados MongoDB. A classe possui atributos como `CPF`, `name`, `email` e um objeto de endereço. O atributo CPF é marcado com a anotação `@Id`, indicando que ele será utilizado como identificador único do cliente no banco de dados. Além disso, a classe possui as anotações `@Document` e `@Data`, indicando que ela será mapeada como um documento no MongoDB e que ela terá getters, setters e outros métodos úteis gerados automaticamente. Esse código é útil para quem precisa armazenar informações de clientes em um banco de dados MongoDB e quer utilizar o Spring Data para facilitar a integração.

## `Employee`

```java
@Document("employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
        @Id
        String cpf;
        String name;
        String email;
        BigDecimal salary;
        Address cep;

}
```

Classe `Employee` para criar os empregados da biblioteca, ligeiramente seguindo a mesma arquitetura da classe `Client` e tendo um objeto de `Address`.

## Pacote repo

## `ClientRepository`

```java
@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    @Query("{cpf:'?0'}")
    Client findByCPF(String cpf);

}
```

Esse código em Java é uma interface que estende a classe MongoRepository do Spring Data MongoDB, e define um repositório para a classe Client, que representa um cliente de uma empresa. A interface possui um método `findByCPF` que é definido com a anotação `@Query` indicando uma consulta personalizada no banco de dados MongoDB, buscando pelo CPF do cliente. Esse método retorna um objeto do tipo `Client`, correspondente ao cliente encontrado no banco de dados. Esse código é útil para quem precisa armazenar informações de clientes em um banco de dados MongoDB e quer utilizar o Spring Data para facilitar a integração. Ele também mostra como é possível criar consultas personalizadas utilizando a anotação `@Query`.

## `EmployeeRepository`

```java
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    @Query("{cpf:'?0'}")
    Employee findByCPF(String cpf);

    @Query("{cep:'?0'}")
    List<Employee> findAllByCEP(Iterable<String> string);
}
```

A interface **`EmployeeRepository`** é uma interface de repositório do Spring Data MongoDB que estende a interface **`MongoRepository`** para a classe **`Employee`**. Ela define dois métodos usando anotações **`@Query`**.

O primeiro método, **`findByCPF`**, recebe um parâmetro **`String`** chamado **`cpf`** e busca no banco de dados o **`Employee`** com o campo **`cpf`** correspondente.

O segundo método, **`findAllByCEP`**, recebe um parâmetro **`Iterable<String>`** chamado **`string`** e busca no banco de dados todos os objetos **`Employee`** com o campo **`cep`** correspondente. Ele retorna uma lista **`List<Employee>`**.

## Pacote Service

## `ClientService`

```java
public class ClientService {

    public static Client update(Client client, Map<String, Object> updates) {

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            switch (key) {
                case "cpf":
                    client.setCpf((String) value);
                    break;
                case "name":
                    client.setName((String) value);
                    break;
                case "email":
                    client.setEmail((String) value);
                    break;
                case "cep":
                    client.setCep((Address) value);
                    break;
            }
        }

        return client;
    }
}
```

O código acima apresenta uma classe chamada **`ClientService`** que contém um método estático chamado **`update`**. Esse método recebe um objeto **`Client`** e um mapa de atualizações, onde a chave é uma **`String`** representando o atributo a ser atualizado e o valor é o novo valor a ser atribuído a esse atributo. O método usa um loop **`for`** para iterar sobre as entradas do mapa e, para cada entrada, verifica qual é o atributo a ser atualizado usando uma instrução **`switch`**. Em seguida, ele atualiza o valor do atributo no objeto **`Client`** passado como parâmetro e retorna esse objeto atualizado.

## `EmployeeService`

```java
public class EmployeeService {

    public static Employee update(Employee employee, Map<String, Object> updates) {

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            switch (key) {
                case "cpf":
                    employee.setCpf((String) value);
                    break;
                case "name":
                    employee.setName((String) value);
                    break;
                case "email":
                    employee.setEmail((String) value);
                    break;
                case "salary":
                    employee.setSalary((BigDecimal) value);
                    break;
                case "cep":
                    employee.setCep((Address) value);
                    break;

            }
        }

        return employee;
    }
}
```

Da mesma forma que foi feito com o método `update` em `ClientService` foi realizado aqui.
