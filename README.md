Teste Técnico Base2 Sobre Automação de Testes WEB

Como executar os testes:

É Possivel executar pela propria IDE, selecionado qual Teste deseja executar e simplismente dar play no método
do teste ou podera executar pela linha de comando atraves do comando:

Rodar todos os testes

mvn test  

Allure

Após a execução dos testes é possivel gerar o relatório com a biblioteca Allure Local.

Para visualizar o relatório:

Local: Basta executar o comando na pasta do projeto:

allure:serve

allure open /home/user/myproject/build/allure-report
