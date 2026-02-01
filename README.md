# Charge Manager - Sistema de Gerenciamento de Cobranças

### Executando o projeto

- Para limpar a pasta target/, compilar o projeto e gerar o WAR:

``
Abra o painel do Maven -> Lifecycle -> clean -> package
``

- Cria imagem contendo o .WAR da aplicação no servidor Apache TomCat

``
docker build -t charge-images/chargemanager:1.0 .
``

- Iniciar o Swarm na Aplicação Docker

``
docker swarm init
``

- Subir serviços no Swarm

``
docker stack deploy -c docker-swarm.yml app
``

- Verifique se os serviços estão em execução

``
docker service ls
``
