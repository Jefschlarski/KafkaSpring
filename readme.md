Na imagem Estrutura.png tem um simples diagrama sobre a transição de dados desse projeto


Cada api tem o arquivo 'application.properties' que fica no diretorio src/main/resources, conferir as configurações antes de iniciar.

POSTGRES E DOCKER

Caso não tenha a imagem do postgres use no terminal o comando 'docker pull postgres' para baixar a imagem.

apos baixar a imagem rode o comando 'docker run -p 5432:5432 -e POSTGRES_PASSWORD=1234 postgres .

Se conecte ao banco usando o dbeaver ou similar e crie o banco car_post_analytics e car_post_storage.

BROKER KAFKA

inicie um broker kafka na porta 9092 pode usar o Conduktor ou uma imagem docker 


RODANDO OS SERVIÇOS

inicie os 3 serviços java, confira se o trecho 'spring.jpa.hibernate.ddl-auto' no 'aplication.properties' dos serviços car e data esta marcado como 'create' isso
criara as tabelas necessarias no db ao iniciar pela primeira vez o serviço, apos isso marcar como 'none'.

Exemplos de uso com o postman

Criar um owner(usuario)
POST: http://localhost:8085/owner
{
    "name":"Teste",
    "type":"Profissional",
    "contactNumber":"12345677"    
}

POST: http://localhost:8085/api/car/post
Publicar um carro (ID é o ID do owner(usuario) criado anteriormente)
{
    "model":"gol",
    "brand":"volkswagen",
    "price":12000.00,
    "description":"novo na promoção top",
    "engineVersion":"1.0",
    "city":"SC",
    "createdDate":"07/10/23",
    "ownerId":"1"
}

no diretorio 'api\src\main\java\com\portal\api\dto\' se encontram os modelos usados a cima.




