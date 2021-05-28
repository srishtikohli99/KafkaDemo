package com.kafka.demo.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LibraryEvent {

    private Integer libraryEventId; //for null key round robin partitions
    private Book book;
    private LibraryEventType libraryEventType;

//    public Integer getLibraryEventId() {
//        return libraryEventId;
//    }

//    public void setLibraryEventId(Integer libraryEventId) {
//        this.libraryEventId = libraryEventId;
//    }
//
//    public void setLibraryEventType(LibraryEventType aNew) {
//    }
}
