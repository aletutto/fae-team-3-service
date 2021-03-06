package de.th.koeln.archilab.fae.faeteam3service.nachricht.persistance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import de.th.koeln.archilab.fae.faeteam3service.antwort.persistance.Antwort;
import de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation.persistance.Ausnahmesituation;
import de.th.koeln.archilab.fae.faeteam3service.core.AbstractEntity;
import de.th.koeln.archilab.fae.faeteam3service.eventing.EventPublishingEntityListener;
import de.th.koeln.archilab.fae.faeteam3service.eventing.kontaktperson.persistance.Kontaktperson;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@EntityListeners(EventPublishingEntityListener.class)
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Nachricht extends AbstractEntity {

  @Setter
  @JsonUnwrapped
  private NachrichtText nachrichtText;

  @Setter
  @ManyToOne
  @JsonBackReference
  @ToString.Exclude
  private Ausnahmesituation ausnahmesituation;

  @Setter
  @OneToOne(cascade = CascadeType.ALL)
  private Antwort antwort;

  @OneToOne
  @Setter
  private Kontaktperson kontaktperson;

  public Nachricht(String id) {
    super(id);
  }

  public Nachricht(NachrichtText nachrichtenText, Kontaktperson kontaktperson) {
    this.nachrichtText = nachrichtenText;
    this.kontaktperson = kontaktperson;
  }

  @Override
  public String getEventClass() {
    return "nachricht";
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    Nachricht nachricht = (Nachricht) o;
    return nachrichtText.equals(nachricht.nachrichtText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), nachrichtText);
  }
}
