# Oliot-projekti
Yhteinen lähdehakemisto Oliot projektille

# Kuinka käytän?

Lataa Desktop Appi ja synkkaa sen kautta :)

---

Vaihtoehtoisesti komentorivillä (vaatii git työkalun):

Mene komentorivillä eclipsen workspace kansioosi minne haluat projektin gitistä ladata. Kansiossa suorita komento git clone https://github.com/jamiamikko/Oliot-projekti.git

Nyt kansio on kopioitu lokaalisti. Tämän jälkeen suorita git pull. Tämä komento lataa aina viimeisimmän version gitistä.

Kun olet päivitellyt jotakin kansiossa ja haluat työntää sen gittiin muille saatavaksi:

git add -A

Tämä komento lisää kaikki tehdyt muutokset jonoon.

git commit -m "tähän lyhyt kommentti mitä olet tehty", kommentoidaan luotu jono.

git pull, lataa varmuudeksi vielä uusin versio

git push, työnnä versio gittiin.

Tämän jälkeen gitissä pitäisi näkyä commit listalla juuri tehty muutos.