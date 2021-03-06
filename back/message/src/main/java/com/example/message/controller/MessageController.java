package com.example.message.controller;

import com.example.message.dto.MessageDTO;
import com.example.message.dto.NewMessageDTO;
import com.example.message.dto.RequestDTO;
import com.example.message.dto.UserDTO;
import com.example.message.model.Message;
import com.example.message.model.Notification;
import com.example.message.service.MessageService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * GET /server/request
     *
     * @return return all requests
     */
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<Message>> getAll() {
        return new ResponseEntity<List<Message>>(this.messageService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/inbox", produces = "application/json")
    public ResponseEntity<List<MessageDTO>> getAllMessagesFromConversation(@RequestParam(value = "userId") Long userId, @RequestParam(value = "conversationId") Long conversationId) {

        return new ResponseEntity<List<MessageDTO>>(this.messageService.getAllMessagesForUser(userId, conversationId), HttpStatus.OK);
    }

    /**
     * POST /server/request/
     *
     * @return returns status of new request creation
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> sendMessage(@RequestBody MessageDTO message) throws Exception {
        List<RequestDTO>  requestList = (this.getRequests()).getBody();
        boolean status = this.messageService.sendMessage(message, requestList);
        Notification not = new Notification();

        if (status){
            not.setText("Message successfully sent.");
            return new ResponseEntity<Notification>(not, HttpStatus.OK);
        }
        else{
            not.setText("You dont have any reserved or paid requests from this user.");
            return new ResponseEntity<Notification>(not, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/newMessage", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> newMessage(@RequestBody NewMessageDTO message) {
        try {
            List<UserDTO> userList = (this.getUsernames()).getBody();
            List<RequestDTO> requestList = (this.getRequests()).getBody();
            System.err.println("VELICINA LISTE U KONTROLERU: " + requestList.size());
            boolean status = this.messageService.sendMessage(this.messageService.convertMessage(message, userList), requestList);

            Notification not = new Notification();

            if (status) {
                not.setText("Message successfully sent.");
                return new ResponseEntity<Notification>(not, HttpStatus.OK);
            } else {
                not.setText("You dont have any reserved or paid requests from this user.");
                return new ResponseEntity<Notification>(not, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {
            System.err.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<RequestDTO>> getRequests() throws Exception {
        System.out.println("Getting all requests");
        List<RequestDTO> response = restTemplate.exchange("http://request/request/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<RequestDTO>>() {}).getBody();

        return new ResponseEntity<List<RequestDTO>>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<UserDTO>> getUsernames() throws Exception {
        System.out.println("Getting all usernames");
        List<UserDTO> response = restTemplate.exchange("http://user/user/usernames/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDTO>>() {}).getBody();

        return new ResponseEntity<List<UserDTO>>(response, HttpStatus.OK);
    }
}
