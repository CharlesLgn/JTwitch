package com.twitch.socket;

import com.twitch.action.MessageAction;
import com.twitch.message.Message;

import java.net.URISyntaxException;
import java.util.List;

public class ConnectedTwitchBot {

  private final TwitchSocket twitchSocket;

  public ConnectedTwitchBot(String botAccount, String token, List<MessageAction> messageActions, SecurityType securityType) throws InterruptedException, URISyntaxException {
    twitchSocket = new TwitchSocket(botAccount, token, messageActions, securityType, this);
    twitchSocket.connectBlocking();
  }

  /** Join a streamer channel */
  public ConnectedTwitchBot join(String streamer) {
    twitchSocket.send("JOIN #" + streamer);
    return this;
  }

  /** Leave a streamer channel */
  public ConnectedTwitchBot leave(String streamer) {
    twitchSocket.send("PART #" + streamer);
    return this;
  }

  /** send a message to a specific channel. */
  public void send(String streamer, String message) {
    twitchSocket.send("PRIVMSG #" + streamer + " :" + message);
  }

  /** reply to a message. */
  public void answer(Message messageToAnswer, String message) {
    twitchSocket.send("@reply-parent-msg-id=" + messageToAnswer.getId() + " PRIVMSG #" + messageToAnswer.getStreamerName() + " :" + message);
  }
}