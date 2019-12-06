package GiantFrogKiller;

import org.powerbot.script.Filter;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

public class Attack extends Task<ClientContext> {
    public Attack(ClientContext arg0) {
        super(arg0);
    }

    int giantFrogID = 8700;
    int cookedTroutID = 333;

    @Override
    public boolean activate() {
        Npc frogToAttack = ctx.npcs.select().id(giantFrogID).nearest().poll();


        return ctx.inventory.select().id(cookedTroutID).count() > 0
                && !ctx.players.local().inMotion()
                && ctx.players.local().animation() == -1
                && !ctx.players.local().healthBarVisible();
    }

    @Override
    public void execute() {
        Npc frogToAttack = ctx.npcs.select().id(giantFrogID).select(new Filter<Npc>() {
            @Override
            public boolean accept(Npc npc) {
                return !npc.healthBarVisible();
            }
        }).nearest().poll();

        frogToAttack.interact("Attack");
        ctx.camera.turnTo(frogToAttack);

    }
}
