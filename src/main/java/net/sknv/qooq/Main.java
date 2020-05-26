package net.sknv.qooq;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {
        //https://discord.com/api/oauth2/authorize?client_id=714593234285756517&scope=bot&permissions=8
        new JDABuilder(Utils.getToken()).addEventListeners(new Main()).build();
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        if (!event.getAuthor().isBot()) {
            channel.sendMessage("Sogres is a cook").queue();
        }
    }
}
