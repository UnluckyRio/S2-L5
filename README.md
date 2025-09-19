# Gestione Collezione Giochi

Applicazione Maven per la gestione di una collezione di videogiochi e giochi da tavolo sviluppata per il corso Epicode.

## Descrizione

L'applicazione permette di gestire una collezione di giochi composta da:
- **Videogiochi**: con attributi specifici come piattaforma, durata e genere
- **Giochi da Tavolo**: con attributi specifici come numero di giocatori e durata partita

## Funzionalità

### Operazioni CRUD
1. **Aggiungi gioco** - Inserisce un nuovo videogioco o gioco da tavolo
2. **Cerca per ID** - Trova un gioco specifico tramite il suo ID univoco
3. **Cerca per prezzo** - Filtra i giochi con prezzo inferiore a un valore specificato
4. **Cerca per numero giocatori** - Trova giochi da tavolo per un numero specifico di giocatori
5. **Rimuovi gioco** - Elimina un gioco dalla collezione
6. **Aggiorna gioco** - Modifica i dati di un gioco esistente

### Statistiche
7. **Visualizza statistiche** - Mostra:
   - Conteggio totale videogiochi e giochi da tavolo
   - Gioco con prezzo più elevato
   - Media dei prezzi di tutta la collezione
8. **Visualizza tutti i giochi** - Lista completa della collezione

## Struttura del Progetto

```
src/main/java/it/epicode/gestione/
├── Main.java                           # Classe principale
├── model/
│   ├── Gioco.java                     # Classe astratta base
│   ├── Videogioco.java                # Classe per videogiochi
│   ├── GiocoDaTavolo.java             # Classe per giochi da tavolo
│   └── Genere.java                    # Enum per generi videogiochi
├── service/
│   └── Collezione.java                # Logica di business
├── ui/
│   └── MenuPrincipale.java            # Interfaccia utente
└── exception/
    ├── ElementoDuplicatoException.java # Eccezione per ID duplicati
    ├── ElementoNonTrovatoException.java # Eccezione per elementi non trovati
    └── InputNonValidoException.java    # Eccezione per input non validi
```

## Tecnologie Utilizzate

- **Java 17** - Linguaggio di programmazione
- **Maven** - Gestione dipendenze e build
- **Java Streams** - Per operazioni sui dati
- **Lambda Functions** - Per programmazione funzionale
- **Scanner** - Per input utente

## Caratteristiche Tecniche

### Validazione Input
- Controllo ID univoci per prevenire duplicati
- Validazione range per numero giocatori (2-10)
- Validazione prezzi positivi
- Controllo anni di pubblicazione (1950-2024)

### Gestione Errori
- Eccezioni personalizzate per diversi tipi di errore
- Gestione robusta dell'input utente
- Messaggi di errore informativi

### Programmazione Funzionale
- Utilizzo estensivo di Java Streams
- Lambda expressions per filtri e trasformazioni
- Operazioni map, filter, reduce per elaborazione dati

## Come Eseguire

### Prerequisiti
- Java 17 o superiore
- Maven (opzionale, per build automatico)

### Compilazione ed Esecuzione

#### Con Maven (se disponibile):
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="it.epicode.gestione.Main"
```

#### Senza Maven:
```bash
# Compilazione
javac -d target/classes -cp src/main/java src/main/java/it/epicode/gestione/model/*.java src/main/java/it/epicode/gestione/exception/*.java src/main/java/it/epicode/gestione/service/*.java src/main/java/it/epicode/gestione/ui/*.java src/main/java/it/epicode/gestione/*.java

# Esecuzione
java -cp target/classes it.epicode.gestione.Main
```

## Esempio di Utilizzo

1. Avvia l'applicazione
2. Scegli "1" per aggiungere un gioco
3. Seleziona il tipo (Videogioco o Gioco da Tavolo)
4. Inserisci i dati richiesti
5. Usa le altre opzioni del menu per gestire la collezione

## Generi Videogiochi Disponibili

- Azione
- Avventura
- RPG
- Strategia
- Simulazione
- Sport
- Corsa
- Puzzle
- Horror
- Sparatutto
- Platform
- Arcade

## Autore

Sviluppato per il corso Epicode - Esercizio S2-L5

## Versione

1.0.0