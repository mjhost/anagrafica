#Archivio persone (della parrocchia)
##ipotesi mongodb

mongodb come archivio di grafi (frontend in react)

2 collection: 
    1 entities
    2 relations

tipi di entities:
    1 persone
    2 eventi di gruppo
    3 gruppi di persone

tipi di relazioni:
    1 persona - 1 evento (ha partecipato, ha condotto)
    1 persona - 1 persona (parentele, convivenza, assistenza, religiosa)
    1 persona - 1 gruppo di persone (affiliazioni)

tipi di ricerche:
    ricerca per relazione evento 
        - tutte le persone che hanno una relazione con l'evento x 
        - tutte le persone che hanno una relazione con gli eventi di tipo x 
    ricerca per gruppi di persone
        - tutti gli affiliati ad un gruppo
    ricerca per persona
        - la persona + tutte le persone che hanno una relazione con la persona
    

persona = {
    _id: 
    tipo: persona
    nome:
    cognome:
    nascita: {
      data:
      luogo:
    }
    recapiti: {
        indirizzi: [
            {
              via: 
              cap:
              pr:
              comune:
              note:
              latlng:
            }
        ]
        tel: [
            {
                tipo: 
                numero:
                note:
            }
        ]
    }
    relazioni: [
        {
            id: -> foreign key
            tipo: -> enum o lista di entità
            note: -> 
        }
    ]
    hobby: [] -> enum -> potrebbe essere una relazione con un gruppo di praticanti di cose
    studi: [] -> potrebbe essere una relazione con un gruppo di tipo studio-materia
    note: 
}

gruppo = {
    nome:
    note:
}

evento = {
    nome: 
    data: 
    note:
}



