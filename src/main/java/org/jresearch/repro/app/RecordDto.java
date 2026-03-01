package org.jresearch.repro.app;

import org.jresearch.repro.mapper.CustomValue;
import org.dominokit.jackson.annotation.JSONMapper;

@JSONMapper
public record RecordDto(CustomValue value) {
}
