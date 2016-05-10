# --- !Ups

CREATE TABLE "articulos" (
  "id" SERIAL NOT NULL PRIMARY KEY,
  "cuerpo" VARCHAR NOT NULL,
  "ley_id" INT NOT NULL,
  "capitulo_id" INT NOT NULL
  );

# --- !Downs

drop table "articulos";