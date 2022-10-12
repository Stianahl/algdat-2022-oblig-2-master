# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Stian André Hauge-Larsen, s362089, s362089@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Stian gjorde oppgave 1, 2, 3, 4, 5, 6, 8

# Oppgavebeskrivelse

I oppgave 1 fant jeg kode for antall() og tom() fra kompendiet.
Metoden antall() returnerer antall når den blir kalt, og tom() returnerer antall == 0.
Implementerte så konstruktørene basert på kode fra kompendiet, men her måtte jeg endre noe på koden siden dette er en dobbeltlenketliste, og ikke enkel.
DobbeltLenketListe() er standardkonstruktør, og lager en tom liste.
DobbeltLenketListe(T[] a) konstruktøren tar inn en tabell, og lager en liste i samme rekkefølge.

I oppgave 2 implementerte jeg toString() med kode fra kompediet.
Kodet så metoden omvendtString() som er ganske lik toString(), bare at den bruker hale og forrige-pekere istedenfor hode og neste-pekere.
Implementerte så leggInn(T verdi) metoden basert på kode fra kompendiet, men måtte gjøre noen små endringer for at den skulle fungere med dobbeltlenketliste.
Når en verdi legges til i listen må både forrige og neste pekere tilrettelegges her siden den er dobbeltlenket, mens i den enkelte trenger man bare neste-peker.

I oppgave 3 implementerte jeg først finnNode metoden basert på kode fra kompendiet.
Endret her litt på koden siden søket skulle gå fra venstre mot høyre eller omvendt avhengig av om indeksen lå nærmere hode eller hale-pekeren.
Gjorde dette ved hjelp av en if-setning som sjekka om indeks var mindre enn antall/2.
Implementerte så hent- og oppdater-metodene med kode fra kompendiet.
I del b av oppgaven måtte jeg først kode fratilKontroll(), og gjorde dette med koden fra kompendiet i kap. 1.2.3, bare med andre typer exceptions.
Lagde så subliste-metoden som lager en ny dobbellenketliste som inneholder verdiene fra det oppgitte intervallet.

I oppgave 4 kodet jeg først indeksTil(T verdi) med kode fra kompediet.
Metoden skal finne indeksen til den oppgitte verdien, og returnere denne.
Det returneres -1 hvis verdien ikke finnes i listen, eller hvis verdien er null (som betyr at den ikke er i listen).
Metoden sjekker etter verdien sin indeks ved å kjøre en forløkke, og sammenligne verdien i den nåværende noden med verdien vi leter etter.
Hvis verdien er lik returneres indeksen.
Etter dette kodet jeg inneholder metoden med kode fra kompendiet.
Alt denne metoden gjør er å kalle på indeksTil-metoden og sjekke om den ikke returnerer -1.
Hvis den ikke gjør det returnerer inneholder() true; hvis den returnerer -1 returnerer inneholder() false.

I oppgave 5 implementerte jeg leggInn(int indeks, T verdi) basert på kode fra kompendiet.
Måtte gjøre noen endringer her for å tilpasse metoden for en dobbeltlenketliste.
Hvis en verdi skal legges inn på indeks 0 blir den verdien det nye hodet, og hodet.neste sin forrige-peker blir satt til det nye hodet.
Hvis en verdi skal legges bakerst i lista blir den verdien den nye halen, og hale.forrige sin neste-peker blir den nye halen.
Hvis en verdi skal legges mellom to noder kjøres en for-løkke for å finne posisjonen den skal legges inn.
Det opprettes så en ny node på plassen indeksen tilsvarer med den oppgitte verdien.
Siden denne noden plasseres mellom to noder blir nynode.neste sin forrige-peker, og nynode.forrige sin neste-peker satt til den nye noden.

I oppgave 6 kodet jeg først fjern(int indeks) med kode fra kompendiet.
Måtte gjøre endringer for å forsikre at koden satte riktig neste og forrige-pekere siden det er en dobbeltlenketliste.
La også inn flere if-setninger for de ulike spesialtilfellene hvor siste skal fjernes, og hvis første skal fjernes når det kun er ett element i lista.
Implementerte så fjern(T verdi) med kode fra kompendiet.
Endret her også koden litt for å passe på at forrige og neste-pekere settes riktig.
Metoden finner posisjonen til oppgitte verdien i lista og fjerner den deretter.
Gjør dette ved at neste og forrige-pekerne blir satt slik at de "hopper" over verdien, slik at den lenger ikke er i lista.

I oppgave 7 implementerte jeg bare en enkel nullstill metode med for-løkke og fjern(0).
Denne oppgaven er ikke et krav når man jobber alene, men i testen for oppgave 8 kalles nullstill-metoden.
Kodet derfor denne så testen ikke skulle feile.

I oppgave 8 kodet jeg next() med kode fra kompediet, og la til iteratorendringer-sjekken.
Implementerte så iterator() fra kompendiet, bare at den her returnerer DobbeltLenketListeIterator().
Etter dette kodet jeg DobbeltLenketListeIterator(int indeks) basert på standardkonstruktøren.
Eneste forkjellen mellom disse er at den førstnevnte setter denne = finnNode(indeks) istedenfor denne = hode.
Kodet så iterator(int indeks) som først kaller indeksKontroll() av indeksen, og så kaller DobbeltLenketListeIterator(indeks).


#Warnings
* Linje 27: Warning i kildekoden.
* Linje 82: For-løkka har en "empty body". 
Grunnen til dette er at løkken kun brukes til å finne første verdi som ikke er null.
Kunne eventuelt kanskje brukt en while-løkke, men kompendiet brukte en for, og valgte ta å følge dette.
* Linje 359, 360, 403: Warnings i kildekoden. 