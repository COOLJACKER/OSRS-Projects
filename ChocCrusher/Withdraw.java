package ChocCrusher;

import org.powerbot.script.rt4.ClientContext;

public class Withdraw extends Task<ClientContext>{
    public Withdraw(ClientContext ctx) {
        super(ctx);
    }

    public int chocBarID = 1973;
    public int knifeID = 946;
    public int chocDustID = 1975;


    @Override
    public boolean activate() {
        return ctx.inventory.select().id(chocBarID).count() == 0;
    }

    @Override
    public void execute() {
        System.out.println("withdraw bars");
        ctx.bank.withdraw(chocBarID, 27);
    }
}
