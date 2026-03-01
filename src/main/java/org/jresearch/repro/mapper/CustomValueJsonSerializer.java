package org.jresearch.repro.mapper;

import org.dominokit.jackson.JsonSerializationContext;
import org.dominokit.jackson.JsonSerializer;
import org.dominokit.jackson.JsonSerializerParameters;
import org.dominokit.jackson.annotation.CustomSerializer;
import org.dominokit.jackson.stream.JsonWriter;

@CustomSerializer(CustomValue.class)
public class CustomValueJsonSerializer extends JsonSerializer<CustomValue> {

  private static final CustomValueJsonSerializer INSTANCE = new CustomValueJsonSerializer();

  public static CustomValueJsonSerializer getInstance() {
    return INSTANCE;
  }

  @Override
  protected void doSerialize(JsonWriter writer, CustomValue value, JsonSerializationContext ctx, JsonSerializerParameters params) {
    if (value == null) {
      writer.nullValue();
      return;
    }
    writer.value(value.value());
  }
}
