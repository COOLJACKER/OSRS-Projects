package rcingAirs;

import org.powerbot.script.rt4.ClientContext;

public class Run extends Task<ClientContext> {
    public Run(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.movement.energyLevel() == 100
                && ctx.players.local().animation() == -1;
    }

    @Override
    public void execute() {
        ctx.movement.running();
    }
}
