package ChocCrusher;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

public class Bank extends Task<ClientContext> {
    public Bank(ClientContext ctx) {
        super(ctx);
    }

    public int chocBarID = 1973;
    public int knifeID = 946;
    public int chocDustID = 1975;

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(chocDustID).count() == 27
                || ctx.inventory.select().id(chocBarID).count() == 0;
    }

    @Override
    public void execute() {
        System.out.println("bank dust");
        ctx.bank.open();
        ctx.bank.deposit(chocDustID, 27);
        ctx.bank.withdraw(chocBarID, 27);
        ctx.bank.close();
    }
}
