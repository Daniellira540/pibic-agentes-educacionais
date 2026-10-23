import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteAluno extends Agent {
    @Override
    protected void setup() {
        System.out.println("[Aluno] " + getLocalName() + " conectado a plataforma");

        addBehaviour(new EnviarDuvidaWakerBehaviour(this, 2000));

        addBehaviour(new EscutarRespostaCyclicBehaviour());

    }

    private class EnviarDuvidaWakerBehaviour extends WakerBehaviour {

        String[] perguntas = {"Onde posso encontrar meu historico?", "Como posso saber minhas notas?", "Onde acho vagas de estagio?", "Como descubro meu professor de uma materia?"};

        public EnviarDuvidaWakerBehaviour(Agent a, long tempo) {
            super(a, tempo);
        }

        @Override
        protected void onWake() {
            int pergunta = (int) (Math.random() * 4);
            System.out.println("[Aluno] Enviando pergunta numero " + (pergunta + 1));
            ACLMessage mensagem = new ACLMessage(ACLMessage.REQUEST);
            mensagem.addReceiver(new AID("tutor", AID.ISLOCALNAME));
            mensagem.setContent(perguntas[pergunta]);

            send(mensagem);
        }
    }

    
    private class EscutarRespostaCyclicBehaviour extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage resposta = receive();

            if (resposta != null) {
                System.out.println("[Aluno] Resposta recebida do Tutor: " + resposta.getContent());
            }
            else {
                block();
            }
        }
    }
}