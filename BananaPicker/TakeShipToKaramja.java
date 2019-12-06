package BananaPicker;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Npc;

import java.util.concurrent.Callable;

public class TakeShipToKaramja extends Task<ClientContext> {
    public TakeShipToKaramja(ClientContext ctx) {
        super(ctx);
    }

    int[] seamanID = {3645, 3644, 3646};
    int plankID = 2084;
    int bananaID = 1963;
    int karamjaPlankID = 2082;



    @Override
    public boolean activate() {
        Npc seaman = ctx.npcs.select().id(seamanID).poll();
        return ctx.players.local().tile().distanceTo(seaman) < 8
                && ctx.inventory.select().id(bananaID).count() == 0;
    }

    @Override
    public void execute() {
        System.out.println("talk to the seaman, goto karamja");

        Npc seaman = ctx.npcs.select().id(seamanID).nearest().poll();
        seaman.interact("Pay-Fare");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.chat.chatting();
            }
        }, 100, 30);

        ctx.chat.clickContinue();
        Condition.sleep(500);
        ctx.chat.continueChat("Yes please.");
        Condition.sleep(500);
        ctx.chat.clickContinue();

        Condition.sleep(2000);
        GameObject karamjaPlank = ctx.objects.select().id(karamjaPlankID).poll();
        karamjaPlank.interact("Cross");
        Condition.sleep(2000);




    }
}
