-- public.account definition

CREATE TABLE public.card
(
    id     uuid         NOT NULL,
    "name" varchar(255) NOT NULL,
    "type" varchar(255) NOT NULL
);