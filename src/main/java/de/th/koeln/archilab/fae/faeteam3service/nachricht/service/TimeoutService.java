package de.th.koeln.archilab.fae.faeteam3service.nachricht.service;

import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.AusnahmesituationRepository;
import de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance.Nachricht;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Log
public class TimeoutService {

  @Autowired
  private NachrichtenService nachrichtenService;

  @Autowired
  AusnahmesituationRepository ausnahmesituationRepository;

  @Async
  public void checkTimeout(String ausnahmesituationId, Nachricht gesendeteNachricht) {
    try {
      Thread.sleep(20000);
    } catch (InterruptedException e) {
      log.info(e.getMessage());
      Thread.currentThread().interrupt();
    }

    ausnahmesituationRepository.findById(ausnahmesituationId).ifPresent(ausnahmesituation -> {
      for (Nachricht nachricht : ausnahmesituation.getNachrichten()) {
        if (nachricht.getAntwort() == null
            && nachricht.getEntityId().equals(gesendeteNachricht.getEntityId())) {
          nachrichtenService.sendeNachrichtToKontaktperson(ausnahmesituation);
        }
      }
    });
  }
}
