from django.db import models
from django.contrib.auth.models import User

# Model dla Klasy
class Klasa(models.Model):
    nazwa = models.CharField(max_length=10)
    wychowawca = models.ForeignKey('Nauczyciel', on_delete=models.SET_NULL, null=True, related_name='wychowawca_klasy')

    def __str__(self):
        return self.nazwa

# Model dla Przedmiotu
class Przedmiot(models.Model):
    nazwa = models.CharField(max_length=100)
    nauczyciel = models.ForeignKey('Nauczyciel', on_delete=models.SET_NULL, null=True)

    def __str__(self):
        return self.nazwa

# Model dla Ucznia
class Uczen(models.Model):
    nazwa = models.CharField(max_length=100, default="tak")
    user = models.OneToOneField(User, on_delete=models.CASCADE)  # Korzystamy z wbudowanego modelu użytkownika
    klasa = models.ForeignKey(Klasa, on_delete=models.SET_NULL, null=True)
    
    def __str__(self):
        return self.user.get_full_name()

# Model dla Nauczyciela
class Nauczyciel(models.Model):
    nazwa = models.CharField(max_length=100, default="tak")
    user = models.OneToOneField(User, on_delete=models.CASCADE)  # Korzystamy z wbudowanego modelu użytkownika

    def __str__(self):
        return self.user.get_full_name()

# Model dla Oceny
class Ocena(models.Model):
    WARTOSCI_OCEN = [
        ('1', 'Niedostateczny'),
        ('2', 'Dopuszczający'),
        ('3', 'Dostateczny'),
        ('4', 'Dobry'),
        ('5', 'Bardzo dobry'),
        ('6', 'Celujący'),
    ]
    uczen = models.ForeignKey(Uczen, on_delete=models.CASCADE)
    przedmiot = models.ForeignKey(Przedmiot, on_delete=models.CASCADE)
    wartosc = models.CharField(max_length=1, choices=WARTOSCI_OCEN)
    data_wystawienia = models.DateField(auto_now_add=True)

    def __str__(self):
        return f'{self.uczen} - {self.przedmiot}: {self.wartosc}'