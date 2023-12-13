# Scheduler


O Scheduler Java é uma biblioteca simples para agendar e executar comandos em horários específicos. Este projeto é ideal para automação de tarefas recorrentes, como a execução de scripts em lotes ou scripts Python, em momentos predefinidos.
Funcionalidades

    Agendamento preciso de comandos em horários específicos.
    Suporte para execução de comandos em lotes e scripts Python.
    Flexibilidade na escolha de estratégias de agendamento e execução.
    Fácil integração e extensibilidade.
# Como Usar

    Adicione a Biblioteca ao seu Projeto:
    Faça o download do último release.
    Adicione o JAR ao seu projeto.
    
# Exemplo básico

    import com.zugno.scheduler.schedule.Scheduler;
    import com.zugno.scheduler.schedule.SimpleScheduler;
    import com.zugno.scheduler.script.BatchCommand;
    import com.zugno.scheduler.script.Command;
    
    public class Main {

    public static void main(String[] args) {
        Scheduler scheduler = new SimpleScheduler();

        // Agendamento de um comando Batch
        Command batchCommand = new BatchCommand("caminho/do/arquivo.bat");
        scheduler.schedule(batchCommand);

        // Aguarde um tempo para dar tempo de execução
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Encerre o Scheduler
        scheduler.shutdown();
      }
    }

Documentação:

  A documentação completa pode ser encontrada no [JavaDoc](https://gianlz.github.io/Scheduler.github.io/).

Contribuição:

  Sinta-se à vontade para contribuir ou relatar problemas no GitHub.
