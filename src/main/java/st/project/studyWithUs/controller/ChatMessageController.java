package st.project.studyWithUs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import st.project.studyWithUs.model.ChatMessage;

@Profile("stomp")
@RestController
public class ChatMessageController {

    private final SimpMessagingTemplate template;
    @Autowired
    public ChatMessageController(SimpMessagingTemplate template) {
        this.template = template;
    }


    @MessageMapping("/chat/join")
    public void join(ChatMessage message) {
        message.setMessage(message.getWriter() + "님이 입장하셨습니다.");
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
    }

//    @MessageMapping("/chat/quit")
//    public void quit(ChatMessage message) {
//        message.setMessage(message.getWriter() + "님이 퇴장하셨습니다.");
//        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
//    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
    }





}
