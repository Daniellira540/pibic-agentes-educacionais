import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteEstudante extends Agent {
    @Override
    protected void setup() {
        System.out.println("Assistente Educacional " + getLocalName() + " ativo e aguardando interacao.");
        //Comportamento interativo
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    System.out.println("aluno perguntou " + msg.getContent());
                } else {
                    block(); //Pausa até receber novas mensagens
                }
            }
        });
    }
}