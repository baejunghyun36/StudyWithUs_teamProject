package st.project.studyWithUs.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import st.project.studyWithUs.argumentresolver.Login;
import st.project.studyWithUs.model.ChatRoom;
import st.project.studyWithUs.repository.ChatRoomRepository;
import st.project.studyWithUs.domain.User;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository repository;

    private final String detailViewName;
    private final AtomicInteger seq = new AtomicInteger(0);


    @GetMapping
    public String chatting() {
        return "redirect:/chat/rooms";
    }

    @Autowired
    public ChatRoomController(ChatRoomRepository repository ,@Value("${viewname.chatroom.detail}") String detailViewName) {
        this.repository = repository;
        this.detailViewName = detailViewName;
    }


    @ResponseBody
    @GetMapping("/rooms/{id}")
    public boolean room(@PathVariable String id, Model model) {

        return true;
    }


    @GetMapping("/room/enter/{id}")
    public String room2(@PathVariable String id, Model model) {
        log.info("id값 : {}", id);
        ChatRoom room = repository.getChatRoom(id);
        log.info("스터디방 id : {}, 스터디방 name : {}, 스터디방 session : {}", room.getId(), room.getName(), room.getSessions());

//        model.addAttribute("room", room);
//        model.addAttribute("member", "member" + seq.incrementAndGet());
        return detailViewName;
    }

    @GetMapping("/studyRoom/enter/{id}")
    public String room3(@PathVariable String id, Model model) {
        log.info("id값 : {}", id);
        ChatRoom room = repository.getChatRoom(id);
        log.info("스터디방 id : {}, 스터디방 name : {}, 스터디방 session : {}", room.getId(), room.getName(), room.getSessions());

//        model.addAttribute("room", room);
//        model.addAttribute("member", "member" + seq.incrementAndGet());
        return "studyChat";
    }

    @GetMapping("/studyTitle")
    public String studyTitle(@RequestParam String tID){
        log.info("kajk {}", tID)    ;

        ChatRoom room = repository.getChatRoom(tID);
        return room.getName();

    }

    @ResponseBody
    @GetMapping("/findStudyUser")
    public String findStudyUser(@Login User user){
        return user.getUserName();
    }


    @ResponseBody
    @GetMapping("/findTitle")
    public String findStudyUser(@RequestParam String tID){

        ChatRoom room = repository.getChatRoom(tID);
        return room.getName();

    }

    @ResponseBody
    @GetMapping("/findCurrent")
    public String findCurrent(@RequestParam String tID){
        ChatRoom room = repository.getChatRoom(tID);
        room.setCurrent(room.getCurrent() + 1);
        return Integer.toString(room.getCurrent());
    }
}