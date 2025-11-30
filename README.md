# Charge Manager - Sistema de Gerenciamento de Cobranças

Este projeto implementa um sistema completo de gerenciamento de cobranças, com comunicação via HTTP e integração com um gateway de pagamentos , oASAAS.

## Organização do projeto

### Entregas

1.  Módulos ativados via docker com uma rota funcional
    passando por todos os módulos e camadas indo até o BD 
2. Comunicação o ASAAS e disponibilização webhook funcional
3. Implementação das regras de negócio no manager e interação com os demais
   componentes/módulos
    
### Executando o projeto

- Para limpar a pasta target/, compilar o projeto e gerar o WAR:

``
Abra o painel do Maven -> Lifecycle -> clean -> package
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


