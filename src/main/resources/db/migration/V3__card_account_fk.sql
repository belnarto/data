ALTER TABLE public.card ADD CONSTRAINT card_pk PRIMARY KEY (id);

ALTER TABLE public.account ADD CONSTRAINT account_pk PRIMARY KEY (id);

ALTER TABLE public.account ADD COLUMN card_id uuid;

ALTER TABLE public.account ADD CONSTRAINT account_fk FOREIGN KEY (card_id) REFERENCES public.card(id);
