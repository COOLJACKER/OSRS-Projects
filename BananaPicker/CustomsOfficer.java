package BananaPicker;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Npc;

import java.util.concurrent.Callable;

public class CustomsOfficer extends Task<ClientContext> {
    public CustomsOfficer(ClientContext ctx) {
        super(ctx);
    }

    int customsOfficerID = 3648;
    int bananaID = 1963;
    Tile plankTile = new Tile(3032, 3217);
    int plankID = 2084;

    @Override
    public boolean activate() {
        Npc customsOfficer = ctx.npcs.select().id(customsOfficerID).poll();
        return ctx.players.local().tile().distanceTo(customsOfficer) < 8
                && ctx.inventory.select().id(bananaID).count() > 26
                && ctx.players.local().tile().distanceTo(plankTile) != 0
                && customsOfficer.inViewport();
    }

    @Override
    public void execute() {

        System.out.println("talk to customs officers");

        Npc customsOfficer = ctx.npcs.select().id(customsOfficerID).poll();

        if (customsOfficer.inViewport()) {
            customsOfficer.interact("Pay-Fare");

            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.chat.chatting();
                }
            }, 100, 10);

            Condition.sleep(1000);
            ctx.chat.continueChat();
            Condition.sleep(1000);
            ctx.chat.continueChat("Can I journey on this ship?");
            Condition.sleep(1000);
            ctx.chat.continueChat();
            Condition.sleep(1000);
            ctx.chat.continueChat();
            Condition.sleep(1000);
            ctx.chat.continueChat("Search away, I have nothing to hide.");
            Condition.sleep(1000);
            ctx.chat.continueChat();
            Condition.sleep(1000);
            ctx.chat.continueChat();
            Condition.sleep(1000);
            ctx.chat.continueChat("Ok.");
            Condition.sleep(1000);
            ctx.chat.continueChat();
        } else {
            ctx.camera.turnTo(customsOfficer);
        }


    }
}
