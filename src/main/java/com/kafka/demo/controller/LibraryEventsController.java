package com.kafka.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.demo.domain.LibraryEvent;
import com.kafka.demo.domain.LibraryEventType;
import com.kafka.demo.producer.LibraryEventProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class LibraryEventsController {

    @Autowired //inject the library events producer
    LibraryEventProducer libraryEventProducer;

    final Logger logger = LoggerFactory.getLogger(LibraryEventsController.class);

    @PostMapping("/v1/libraryevent")
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException {

        logger.info("Before sendLibraryEvent");
        libraryEventProducer.sendLibraryEvent(libraryEvent); //for async
        //SendResult<Integer, String> sendResult = libraryEventProducer.sendLibraryEventSynchronous(libraryEvent); //for sync
        //logger.info("Send Result is {} ", sendResult.toString());
        //libraryEvent.setLibraryEventType(LibraryEventType.NEW);
        //libraryEventProducer.sendLibraryEvent_App2(libraryEvent); //for sync
        logger.info("After sendLibraryEvent");
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }

    //PUT
    @PutMapping("/v1/libraryevent")
    public ResponseEntity<?> putLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException {

        logger.info("Before sendLibraryEvent");
        if(libraryEvent.getLibraryEventId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please supply libraryEventId");
        }
        //libraryEventProducer.sendLibraryEvent(libraryEvent); //for async
        //SendResult<Integer, String> sendResult = libraryEventProducer.sendLibraryEventSynchronous(libraryEvent); //for sync
        //logger.info("Send Result is {} ", sendResult.toString());
        libraryEvent.setLibraryEventType(LibraryEventType.UPDATE);
        libraryEventProducer.sendLibraryEvent_App2(libraryEvent); //for sync
        logger.info("After sendLibraryEvent");
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }


}
