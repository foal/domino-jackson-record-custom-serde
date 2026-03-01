# domino-jackson custom serde repro

## Goal
Reproduce inconsistency in `domino-jackson-processor` 1.0.5:

- class DTO uses custom deserializer correctly
- record DTO with same field type does not

## Build
```bash
git clone https://github.com/foal/domino-jackson-record-custom-serde.git
cd domino-jackson-record-custom-serde
mvn -DskipTests clean compile
```

## What to inspect
Generated sources:
- `target/generated-sources/annotations/org/jresearch/repro/app/ClassDtoBeanJsonDeserializerImpl.java`
- `target/generated-sources/annotations/org/jresearch/repro/app/RecordDtoBeanJsonDeserializerImpl.java`

Expected mismatch:
- `ClassDtoBeanJsonDeserializerImpl` uses `CustomValueJsonDeserializer.getInstance()`
- `RecordDtoBeanJsonDeserializerImpl` references `CustomValueBeanJsonDeserializerImpl.getInstance()`

Compilation fails with `cannot find symbol` for `CustomValueBeanJsonDeserializerImpl`.

## Mapping style used
Custom serde is registered using the style from Domino-Jackson docs:
https://dominokit.com/solutions/domino-jackson/v1/docs/custom-mappers
