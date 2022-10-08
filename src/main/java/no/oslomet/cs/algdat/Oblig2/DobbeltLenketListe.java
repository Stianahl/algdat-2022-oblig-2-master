package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


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
        return new DobbeltLenketListe<>();
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        if(antall == 0){
            return true;
        }
        else{
            return false;
        }
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
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
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
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
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


