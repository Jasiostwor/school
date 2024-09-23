from django.db import models

# Create your models here.

class Grupa(models.Model):
    nazwa = models.CharField(max_length=100)

    def __str__(self):
        return self.nazwa

class Person(models.Model):
    nazwisko = models.CharField(max_length=100)
    imie = models.CharField(max_length=40)
    stanowisko = models.CharField(max_length=50)
    grupa = models.ForeignKey(Grupa, on_delete=models.SET_NULL, null=True)

    def __str__(self):
        return (self.imie+" "+self.nazwisko)