package de.th.koeln.archilab.fae.faeteam3service.eventing.positionssender;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PositionssenderDto implements Serializable {
  private String positionssenderId;
}
