# ProjectJava
## Werking

Een gebruiker kan zich aanmelden/registreren op de applicatie. Eenmaal hij is ingelogd kan de gebruiker muziek opzoeken die word opgehaald door middel van de Deezer API. De gebruiker kan de titel, artiest, genre, cover, album en tijdsduur van het nummer bekijken.
De gebruiker kan ook een nummer een rating geven van 1 - 5 deze word bijgehouden in onze database.

Een gebruiker kan een playlist maken deze word bijgehouden in de database.
Als een gebruiker een nummer opzoekt heeft hij e optie om deze aan een van zijn playlisten toe te voegen/verwijderen.

Een gebruiker kan zijn naam/wachtwoord wijzigen.

## Database
Entiteiten:

Account:
- id
- name
- password
-- SQL

Rating:
- id
- rating
- userId
- songId
-- MONNGODB

Song:
- id
- title
- artist
- genre
- cover
- album
- duration
- userId
-- MONGODB

Playlist:
- id
- name
- songId
- userId
--MONGODB
