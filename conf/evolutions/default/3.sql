# --- !Ups

CREATE TABLE "capitulos" (
  "id" SERIAL NOT NULL PRIMARY KEY,
  "nombre" VARCHAR NOT NULL
  );

# --- !Downs

drop table "capitulos";