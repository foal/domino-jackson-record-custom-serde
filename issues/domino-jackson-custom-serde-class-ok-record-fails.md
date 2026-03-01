# Bug report draft: custom deserializer works for class DTO, but same field in record DTO generates wrong deserializer

## Summary
With a simple custom serde, `domino-jackson-processor` 1.0.5 behaves inconsistently:

- Class DTO uses the registered custom deserializer correctly.
- Record DTO with the same field type generates reference to `*BeanJsonDeserializerImpl` instead of custom deserializer.

This leads to compilation failure because that bean deserializer is not generated.

## Environment
- Date reproduced: 2026-03-01
- Maven: 3.9.11
- Java target: 17
- Runtime JDK: 25.0.2
- `org.dominokit:domino-jackson`: 1.0.5
- `org.dominokit:domino-jackson-processor`: 1.0.5

## Minimal reproducible project
Standalone repro repository:
- `https://github.com/foal/domino-jackson-record-custom-serde`

Single Maven module contains:
- `CustomValue` (simple value object)
- `CustomValueJsonSerializer`
- `CustomValueJsonDeserializer`
- custom mapper registration via annotations:
`@CustomDeserializer(CustomValue.class)` on `CustomValueJsonDeserializer`
`@CustomSerializer(CustomValue.class)` on `CustomValueJsonSerializer`
- two DTOs with the same field type:
`ClassDto` (class + getter/setter)
`RecordDto` (record)

Both are annotated with `@JSONMapper`.

## Build command
```bash
git clone https://github.com/foal/domino-jackson-record-custom-serde.git
cd domino-jackson-record-custom-serde
mvn -DskipTests clean compile
```

## Actual generated code
### Class DTO (correct)
`ClassDtoBeanJsonDeserializerImpl` uses custom deserializer:
```java
import org.jresearch.repro.mapper.CustomValueJsonDeserializer;
...
return CustomValueJsonDeserializer.getInstance();
```

### Record DTO (incorrect)
`RecordDtoBeanJsonDeserializerImpl` uses bean deserializer instead of custom:
```java
import org.jresearch.repro.mapper.CustomValueBeanJsonDeserializerImpl;
...
return CustomValueBeanJsonDeserializerImpl.getInstance();
```

## Build failure
```text
[ERROR] .../RecordDtoBeanJsonDeserializerImpl.java:[20,34] cannot find symbol
  symbol:   class CustomValueBeanJsonDeserializerImpl
```

## Expected behavior
Record DTO deserializer should use the same custom deserializer as class DTO:
```java
return CustomValueJsonDeserializer.getInstance();
```

## Notes
- Serializer path looks fine for both class and record (`CustomValueJsonSerializer` is used).
- Problem is specifically in deserializer generation path for record constructor parameters.
- Custom mapper annotation style follows:
https://dominokit.com/solutions/domino-jackson/v1/docs/custom-mappers
