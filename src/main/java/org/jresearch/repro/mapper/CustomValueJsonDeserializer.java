package org.jresearch.repro.mapper;

import org.dominokit.jackson.JsonDeserializationContext;
import org.dominokit.jackson.JsonDeserializer;
import org.dominokit.jackson.JsonDeserializerParameters;
import org.dominokit.jackson.annotation.CustomDeserializer;
import org.dominokit.jackson.stream.JsonReader;
import org.dominokit.jackson.stream.JsonToken;

@CustomDeserializer(CustomValue.class)
public class CustomValueJsonDeserializer extends JsonDeserializer<CustomValue> {

  private static final CustomValueJsonDeserializer INSTANCE = new CustomValueJsonDeserializer();

  public static CustomValueJsonDeserializer getInstance() {
    return INSTANCE;
  }

  @Override
  protected CustomValue doDeserialize(JsonReader reader, JsonDeserializationContext ctx, JsonDeserializerParameters params) {
    JsonToken token = reader.peek();
    if (JsonToken.NULL == token) {
      reader.nextNull();
      return null;
    }
    if (JsonToken.STRING == token) {
      return new CustomValue(reader.nextString());
    }
    reader.skipValue();
    return null;
  }
}
