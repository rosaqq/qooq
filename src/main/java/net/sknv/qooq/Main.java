package net.sknv.qooq;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Main extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {
        //https://discord.com/api/oauth2/authorize?client_id=714593234285756517&scope=bot&permissions=8
        JDABuilder.createDefault(Utils.getToken()).addEventListeners(new Main()).build();
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
        String[] split = text.split(" ");
        switch (split[1]) {
            case "asd":
                msg.getChannel().sendMessage("you said asd").queue();
                break;
            case "bj":
                msg.getChannel().sendMessage("you said bj").queue();
                break;
            default:
                msg.getChannel().sendMessage("command unknown").queue();
        }
    }
}
