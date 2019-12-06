package VarrockMiner;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class MineTinOne extends Task<ClientContext> {
    public MineTinOne(ClientContext ctx) {
        super(ctx);
    }

    private int[] tinRockIDs = {11360, 11361};
    private int tinOreID = 438;
    private Tile mineTile = new Tile(3285, 3366);
    Tile rockTile1 = new Tile(3281, 3363);
    Tile rockTile2 = new Tile(3282, 3364);
    int[] badRockID = {11390, 11391};

    Tile rockLocation = Tile.NIL;


    @Override
    public boolean activate() {
        return ctx.inventory.select().id(tinOreID).count() < 14
                && ctx.players.local().tile().distanceTo(mineTile) < 10
                && ctx.players.local().animation() == -1;
    }

    @Override
    public void execute() {
        GameObject tinRock1 = ctx.objects.select().at(rockTile1).poll();
        GameObject tinRock2 = ctx.objects.select().at(rockTile2).poll();
        tinRock1.interact("Mine");
        ctx.input.move(tinRock2.centerPoint());
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() == -1;
            }
        },100,50);
        tinRock2.interact("Mine");
        ctx.input.move(tinRock1.centerPoint());
    }
}


