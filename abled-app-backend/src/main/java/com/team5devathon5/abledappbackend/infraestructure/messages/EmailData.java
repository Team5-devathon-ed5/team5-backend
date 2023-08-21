package com.team5devathon5.abledappbackend.infraestructure.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailData {

    private String from;
    private String to;
    private String subject;
    private String html;
}
