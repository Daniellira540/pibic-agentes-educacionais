import jade.core.Agent;

public class AgenteHelloWorld extends Agent {
    @Override
    protected void setup() {
        System.out.println("Ola! Meu nome e " + getAID().getName());
    }
}