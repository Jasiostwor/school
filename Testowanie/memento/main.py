from datetime import date

class Person:
    def __init__(self, imie, nazwisko, data_urodzenia, miejsce_urodzenia, email, telefon):
        self.imie = imie
        self.nazwisko = nazwisko
        self.data_urodzenia = data_urodzenia
        self.miejsce_urodzenia = miejsce_urodzenia
        self.email = email
        self.telefon = telefon

    def __str__(self):
        return (f"Person [Imię: {self.imie}, Nazwisko: {self.nazwisko}, "
                f"Data urodzenia: {self.data_urodzenia}, Miejsce urodzenia: {self.miejsce_urodzenia}, "
                f"Email: {self.email}, Telefon: {self.telefon}]")

    def save_state(self):
        return PersonBackup(self.imie, self.nazwisko, self.data_urodzenia, self.miejsce_urodzenia, self.email, self.telefon)

    def restore_state(self, memento):
        self.imie = memento.imie
        self.nazwisko = memento.nazwisko
        self.data_urodzenia = memento.data_urodzenia
        self.miejsce_urodzenia = memento.miejsce_urodzenia
        self.email = memento.email
        self.telefon = memento.telefon


class PersonBackup:
    def __init__(self, imie, nazwisko, data_urodzenia, miejsce_urodzenia, email, telefon):
        self.imie = imie
        self.nazwisko = nazwisko
        self.data_urodzenia = data_urodzenia
        self.miejsce_urodzenia = miejsce_urodzenia
        self.email = email
        self.telefon = telefon

    def __str__(self):
        return (f"Backup [Imię: {self.imie}, Nazwisko: {self.nazwisko}, "
                f"Data urodzenia: {self.data_urodzenia}, Miejsce urodzenia: {self.miejsce_urodzenia}, "
                f"Email: {self.email}, Telefon: {self.telefon}]")

# Przykład użycia
if __name__ == "__main__":
    person = Person("Jan", "Kowalski", date(1990, 5, 21), "Warszawa", "jan.kowalski@example.com", 123456789)
    print(person)

    backup = person.save_state()

    person.imie = "Anna"
    person.nazwisko = "Nowak"
    print("Po zmianie danych: ")
    print(person)

    person.restore_state(backup)
    print("Po przywróceniu danych: ")
    print(person)
