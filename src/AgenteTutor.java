import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenteTutor extends Agent {
    @Override
    protected void setup() {
        System.out.println("[Tutor] Agente Tutor " + getLocalName() + " pronto para auxiliar!");
        
        addBehaviour(new AtenderDuvidasCyclicBehaviour());
    }

    private class AtenderDuvidasCyclicBehaviour extends CyclicBehaviour{
        @Override
        public void action() {
            ACLMessage mensagem = receive();

            if (mensagem != null) {
                processarMensagem(mensagem);
            }
            else {
                block();
                return;
            }
        }
    }

    private void processarMensagem(ACLMessage mensagem) {
        String pergunta = mensagem.getContent().toLowerCase();
        System.out.println("[Tutor] Dúvida recebida com sucesso de " + mensagem.getSender().getLocalName() + ": \"" + mensagem.getContent() + "\"");
        
        ACLMessage resposta = mensagem.createReply();
        resposta.setPerformative(ACLMessage.INFORM);

        if (pergunta.contains("historico") || pergunta.contains("histórico")) {
            resposta.setContent("Para emitir o historico, acesse a aba de Ensino no SIGAA e selecione a opcao \"Emitir Histprico\"");

        }

        else if (pergunta.contains("nota") || pergunta.contains("notas")) {
            resposta.setContent("Para consultar suas notas no SIGAA, acesse a aba de Ensino e selecione a opção \"Consultar Minhas Notas\"");
        }

        else if (pergunta.contains("estagio") && pergunta.contains("vagas")) {
            resposta.setContent("Para consultar vagas de estágio no SIGAA, acesse a aba de Estágio e selecione a opção \"Mural de Vagas\"");
        }

        else {
            resposta.setContent("Lamento, isso está além das minhas capacidades no momento");
        }

        send(resposta);
        System.out.println("[Tutor] Resposta enviada com sucesso!");
    }
}