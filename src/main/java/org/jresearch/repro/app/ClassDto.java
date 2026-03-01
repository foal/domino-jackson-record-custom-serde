package org.jresearch.repro.app;

import org.jresearch.repro.mapper.CustomValue;
import org.dominokit.jackson.annotation.JSONMapper;

@JSONMapper
public class ClassDto {

  private CustomValue value;

  public ClassDto() {
  }

  public ClassDto(CustomValue value) {
    this.value = value;
  }

  public CustomValue getValue() {
    return value;
  }

  public void setValue(CustomValue value) {
    this.value = value;
  }
}
