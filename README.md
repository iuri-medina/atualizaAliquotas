# atualizaAliquotas
Programa feito em Java para padronizar a descrição (nome) das aliquotas dentro do sistema.

O programa se conecta com o banco de dados Postgresql via driver JDBC e realiza uma consulta consulta retornando todas as aliquotas que não forem isentas. Depois é feito a padronização da descrição pelo metodo corrigeDescricao() e logo após isso, o programa realiza um UPDATE no banco de dados atualizando a descrição das aliquotas já padronizadas. Para realizar a conexão com o banco de dados, o programa lê o arquivo conexão.properties, onde devem ser informados os dados do servidor banco de dados.

Imagem de exemplo das aliquotas padronizadas:
![image](https://github.com/iuri-medina/atualizaAliquotas/assets/88865565/16d95a1a-b71d-45c9-8aae-da3df04e7888)
