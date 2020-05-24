package nl.team2.parque_banque_server.controller;

public class NewCompanyAccountController {

    //De klant is ingelogd en de newcompanyaccount.html moet getoond worden: @Getmapping
    //public void openNewCompanyAccountPage

    //**** Vraag: moet de klant eerst het kvk nr invullen van het bedrijf waarvoor hij/zij een rekening wil openen?
    //Of kan het systeem op basis van het klantnummer de bijbehorende zakelijke rekeningen uit DB halen en de daarbij horende bedrijfsgegevens tonen?

    //De klant is al gekoppeld aan 1 of meerdere zakelijke rekeningen: bedrijfsnamen (distict) die bij rekeningen horen tonen.
    //Contact met DB of service (waar de bedrijfsgegevens staan)
    //en tonen in dropdown

    //De klant is al gekoppeld aan 1 of meerdere zakelijke rekeningen en selecteert 1 van zijn/haar bestaande bedrijven.
    //Klant bevestigt dat hij/zij dit type rekening wil openen
    //Bank maakt IBAN aan met tenaamstelling bedrijf + naam beheerder
    //Bank neemt rekeninggegevens op in de administratie

    //De klant is al gekoppeld aan 1 of meerdere zakelijke rekeningen en kiest voor het openen van een rekening voor een nieuw bedrijf
    //Formulier tonen waarin de klant bedrijfsgegevens (kvk, naam, sector) kan opgeven:
    //**** Vraag: gebeurt dit op newcompanyaccount.html of op een nieuwe pagina? confirmcompany.html?
    //Contact met DB voor lijst met sectoren
    //Bank neemt bedrijf op in database
    //Bank maakt IBAN aan met tenaamstelling bedrijf + naam beheerder
    //Bank neemt rekeninggegevens op in de administratie

    //De klant is nog niet gekoppeld aan een zakelijke rekening
    //Klant bevestigt dat hij/zij dit type rekening wil openen
    //Formulier tonen waarin Klant bedrijfsgegevens invult
    //Contact met DB voor lijst met sectoren
    //Bank neemt bedrijf op in database
    //Bank maakt IBAN aan met tenaamstelling bedrijf + naam beheerder
    //Bank neemt rekeninggegevens op in de administratie

    //**** Vraag: Moeten we een aparte pagina maken waarop het volgende gebeurt?
    //Klant krijgt bevestiging en felicitatie dat zakelijke rekening is aangemaakt

    //**** Vraag: Moet het volgende in deze controller gedefinieerd worden?
    //Klant  ziet rekening in zijn/haar rekeningoverzicht
}
