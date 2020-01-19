package de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log
@Service
public class PositionssenderConsumer {

  @Autowired
  private PositionssenderRepository positionsRepository;

  @KafkaListener(topics = "positionssender", groupId = "fae-team-3-service")
  public void consume(String input) throws IOException {
    log.info(String.format("#### -> Consumed message -> %s", input));

    ObjectMapper mapper = new ObjectMapper();
    Positionssender positionssender = mapper.readValue(input, Positionssender.class);
    positionsRepository.save(positionssender);
  }
}