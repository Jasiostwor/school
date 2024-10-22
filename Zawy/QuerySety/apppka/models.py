from django.db import models

class Group(models.Model):
    name = models.CharField(max_length=100)

    def __str__(self):
        return self.name

class Person(models.Model):
    nazwisko = models.CharField(max_length=100)
    imie = models.CharField(max_length=100)
    stanowisko = models.CharField(max_length=100)
    grupa = models.ForeignKey(Group, on_delete=models.CASCADE)

    def __str__(self):
        return f"{self.nazwisko} {self.imie}"
