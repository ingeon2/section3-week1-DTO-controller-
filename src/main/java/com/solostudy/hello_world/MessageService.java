package com.solostudy.hello_world;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    //여기까지 DI, 아래는 서비스 로직

    public Message createMessage(Message message){
        return messageRepository.save(message);
        //save 매서드는 스프링에서 주는 CrudRepository<Message, Long> 클래스에 있는 매서드일걸~
    }
}
