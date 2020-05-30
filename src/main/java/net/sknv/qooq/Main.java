package net.sknv.qooq;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.sknv.qooq.cards.Blackjack;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {

    Blackjack bj;

    public static void main(String[] args) throws LoginException {
        // Invite bot to server w/ admin permissions
        // https://discord.com/api/oauth2/authorize?client_id=714593234285756517&scope=bot&permissions=8
        JDABuilder.createDefault(Utils.getToken()).addEventListeners(new Main()).build();
    }

    public Main() {
        bj = new Blackjack();
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Message msg = event.getMessage();
        if (msg.getContentRaw().startsWith("kek ")) {
            parse(msg);
        }
    }

    private void parse(Message msg) {
        String text = msg.getContentRaw();
        String[] params = text.split(" ");
        switch (params[1]) {
            case "asd":
                msg.getChannel().sendMessage("you said asd").queue();
                break;
            case "bj":
                bj.bet(msg);
                break;
            default:
                msg.getChannel().sendMessage("command unknown").queue();
        }
    }
}
