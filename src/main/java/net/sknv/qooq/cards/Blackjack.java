package net.sknv.qooq.cards;

import net.dv8tion.jda.api.entities.Message;

public class Blackjack {

    public static boolean bet(Message msg) {
        String[] text = msg.getContentRaw().split(" ");
        if (text.length < 3) {
            msg.getChannel().sendMessage("Please bet some money").queue();
        }
        else {
            int money = 0;
            try {
                money = Integer.parseInt(text[2]);
            }
            catch (NumberFormatException a) {
                msg.getChannel().sendMessage("That's not a number idiot").queue();
                return false;
            }
            msg.getChannel().sendMessage("You bet " + money + " moneys").queue();
        }
        return true;
    }

}
