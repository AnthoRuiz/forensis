# --- !Ups

CREATE TABLE "leyes" (
  "id" SERIAL NOT NULL PRIMARY KEY,
  "nombre" VARCHAR NOT NULL
  );

# --- !Downs

drop table "leyes";