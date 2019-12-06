package VarrockMiner;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

import java.util.Random;

public class WalkToBank extends Task<ClientContext>{
    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    private Tile bankTile = new Tile(3253, 3420);
    public static final Tile[] bankPathTiles = {new Tile(3285, 3366, 0), new Tile(3287, 3370, 0), new Tile(3289, 3374, 0), new Tile(3292, 3377, 0), new Tile(3293, 3381, 0), new Tile(3293, 3385, 0), new Tile(3293, 3389, 0), new Tile(3293, 3393, 0), new Tile(3292, 3397, 0), new Tile(3291, 3401, 0), new Tile(3291, 3405, 0), new Tile(3291, 3409, 0), new Tile(3288, 3413, 0), new Tile(3287, 3417, 0), new Tile(3285, 3421, 0), new Tile(3281, 3422, 0), new Tile(3277, 3424, 0), new Tile(3273, 3426, 0), new Tile(3270, 3429, 0), new Tile(3266, 3429, 0), new Tile(3262, 3429, 0), new Tile(3258, 3429, 0), new Tile(3254, 3426, 0), new Tile(3253, 3422, 0)};
    public static final Tile[] bankPathTiles2 = {new Tile(3282, 3363, 0), new Tile(3285, 3366, 0), new Tile(3288, 3369, 0), new Tile(3289, 3373, 0), new Tile(3290, 3377, 0), new Tile(3290, 3381, 0), new Tile(3290, 3385, 0), new Tile(3290, 3389, 0), new Tile(3290, 3393, 0), new Tile(3290, 3397, 0), new Tile(3290, 3401, 0), new Tile(3290, 3405, 0), new Tile(3290, 3409, 0), new Tile(3290, 3413, 0), new Tile(3290, 3417, 0), new Tile(3289, 3421, 0), new Tile(3287, 3425, 0), new Tile(3283, 3427, 0), new Tile(3279, 3427, 0), new Tile(3275, 3427, 0), new Tile(3271, 3427, 0), new Tile(3267, 3427, 0), new Tile(3263, 3427, 0), new Tile(3259, 3428, 0), new Tile(3255, 3427, 0), new Tile(3254, 3423, 0)};
    public static final Tile[] bankPathTiles3 = {new Tile(3285, 3363, 0), new Tile(3286, 3367, 0), new Tile(3287, 3371, 0), new Tile(3289, 3375, 0), new Tile(3290, 3379, 0), new Tile(3290, 3383, 0), new Tile(3290, 3387, 0), new Tile(3290, 3391, 0), new Tile(3290, 3395, 0), new Tile(3291, 3399, 0), new Tile(3292, 3403, 0), new Tile(3290, 3407, 0), new Tile(3287, 3410, 0), new Tile(3283, 3413, 0), new Tile(3281, 3417, 0), new Tile(3279, 3421, 0), new Tile(3276, 3424, 0), new Tile(3272, 3426, 0), new Tile(3268, 3426, 0), new Tile(3264, 3426, 0), new Tile(3260, 3427, 0), new Tile(3256, 3428, 0), new Tile(3254, 3424, 0), new Tile(3253, 3420, 0)};

    TilePath pathToBank = ctx.movement.newTilePath(bankPathTiles);
    TilePath pathToBank2 = ctx.movement.newTilePath(bankPathTiles2);
    TilePath pathToBank3 = ctx.movement.newTilePath(bankPathTiles3);




    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 28
                && ctx.players.local().tile().distanceTo(bankTile) > 8;
    }

    @Override
    public void execute() {
        int max = 3;
        int min = 1;
        int chance = (int) ((Math.random() * ((max - min) + 1)) + min);

        System.out.println("walk to bank");
        //if (!ctx.players.local().inMotion() || ctx.movement.destination().tile().distanceTo(ctx.players.local().tile()) < 10) {
            if (chance == 1) {
                System.out.println("path 1");
                if (!ctx.players.local().inMotion() || ctx.movement.destination().tile().distanceTo(ctx.players.local().tile()) < 10) {
                    pathToBank.traverse();
                }
            } else if (chance == 2) {
                if (!ctx.players.local().inMotion() || ctx.movement.destination().tile().distanceTo(ctx.players.local().tile()) < 10) {
                    System.out.println("path 2");
                    pathToBank2.traverse();
                }
            } else {
                if (!ctx.players.local().inMotion() || ctx.movement.destination().tile().distanceTo(ctx.players.local().tile()) < 10) {
                    System.out.println("path 3");
                    pathToBank3.traverse();
                }
            }
        //}
    }
}
