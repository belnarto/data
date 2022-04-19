-- public.account definition

CREATE TABLE public.account
(
    id     uuid         NOT NULL,
    "user" varchar(255) NOT NULL,
    amount decimal      NULL
);
CREATE UNIQUE INDEX account_user_idx ON public.account ("user");