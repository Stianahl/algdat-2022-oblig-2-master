package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen


    private Node<T> finnNode(int indeks){
        Node<T> p;

        if(indeks < antall/2){
            p = hode;
            for(int i = 0; i < indeks; i++){
                p = p.neste;
            }
        }
        else{
            p = hale;
            for(int i = antall-1; i > indeks; i--){
                p = p.forrige;
            }
        }
        return p;
    }

    private static void fratilKontroll(int antall, int fra, int til){
        if(fra < 0){
            throw new IndexOutOfBoundsException("fra(" + fra + ") er negativ!");
        }
        if(til > antall){
            throw new IndexOutOfBoundsException("til(" + til + ") > antall(" + antall + ")");
        }
        if(fra > til){
            throw new IllegalArgumentException("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
        }
    }

    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {
        this();

        Objects.requireNonNull(a, "Tabellen a er null!");

        int i = 0;

        for(; i < a.length && a[i] == null; i++);

        if(i < a.length){
            Node<T> p = hode = new Node<>(a[i],null, null);
            antall = 1;

            for(i++; i < a.length; i++){
                if(a[i] != null){
                    p = p.neste = new Node<>(a[i], p, null);
                    antall++;
                }
            }
            hale = p;
        }
    }

    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall, fra, til);

        Node<T> f = finnNode(fra);
        DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>();

        if(antall < 1){
            return subliste;
        }

        for (; fra < til; fra++){
            subliste.leggInn(f.verdi);
            f = f.neste;

        }

        return subliste;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        if(tom()){
            hode = hale = new Node<>(verdi, null, null);
        }
        else{
            hale = hale.neste = new Node<>(verdi, hale, null);
        }
        antall++;
        endringer++;

        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");
        indeksKontroll(indeks, true);

        if(tom() && indeks == 0){
            hode = hale = new Node<>(verdi, null, null);
        }
        else if(indeks == 0){
            hode = new Node<>(verdi, null, hode);
            hode.neste.forrige = hode;
        }
        else if(indeks == antall){
            hale = hale.neste = new Node<>(verdi, hale, null);
            hale.forrige.neste = hale;
        }
        else{
            Node<T> p = hode;
            for(int i = 0; i < indeks; i++){
                p = p.neste;
            }
            p = new Node<>(verdi, p.forrige, p);
            p.neste.forrige = p.forrige.neste = p;
        }
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        if(verdi == null){
            return -1;
        }

        Node<T> p = hode;

        for(int i = 0; i < antall; i++){
            if(p.verdi.equals(verdi)){
                return i;
            }
            p = p.neste;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi, "Ikke tillatt med null-verdier!");
        indeksKontroll(indeks, false);

        Node<T> p = finnNode(indeks);
        T gammelVerdi = p.verdi;

        p.verdi = nyverdi;
        endringer++;
        return gammelVerdi;
    }

    @Override
    public boolean fjern(T verdi) {
        if(verdi == null){
            return false;
        }

        Node<T> p = hode;

        while(p != null){
            if(p.verdi.equals(verdi)){
                break;
            }
            p = p.neste;
        }

        if(p == null){
            return false;
        }

        if(p == hode){
            hode = hode.neste;

            if(hode != null){
                hode.forrige = null;
            }
            else{
                hale = null;
            }
        }
        else if(p == hale){
            hale = hale.forrige;
            hale.neste = null;
        }
        else{
            p.forrige.neste = p.neste;
            p.neste.forrige = p.forrige;
        }

        p.verdi = null;
        p.forrige = p.neste = null;

        antall--;
        endringer++;
        return true;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);
        Node<T> temp;

        if(indeks == 0){
            if(hode.neste == null) {
                temp = hode;
                hode = null;
                hale = null;
            }
            else{
                temp = hode;
                hode = hode.neste;
                hode.forrige = null;
            }
        }
        else if(indeks == antall - 1){
            temp = hale;
            hale = hale.forrige;
            hale.neste = null;
        }
        else{
            Node<T> p = finnNode(indeks - 1);

            temp = p.neste;

            p.neste = p.neste.neste;
            p.neste.forrige = p;
        }

        antall--;
        endringer++;
        return temp.verdi;
    }

    @Override
    public void nullstill() {
        for(int i = 0; i < antall; i++){
            fjern(0);
        }
        antall = 0;
        endringer++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('[');

        if(!tom()){
            Node<T> p = hode;
            sb.append(p.verdi);

            p = p.neste;

            while(p != null){
                sb.append(',').append(' ').append(p.verdi);
                p = p.neste;
            }
        }
        sb.append(']');

        return sb.toString();
    }

    public String omvendtString() {
        StringBuilder osb = new StringBuilder();

        osb.append('[');

        if(!tom()){
            Node<T> p = hale;
            osb.append(p.verdi);

            p = p.forrige;

            while(p != null){
                osb.append(',').append(' ').append(p.verdi);
                p = p.forrige;
            }
        }
        osb.append(']');

        return osb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);

        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException("Feil i iteratorendringer!");
            }

            if(!hasNext()){
                throw new NoSuchElementException("Ingen verdier!");
            }

            fjernOK = true;
            T denneVerdi = denne.verdi;
            denne = denne.neste;

            return denneVerdi;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


