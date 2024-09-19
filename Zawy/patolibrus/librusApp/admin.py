from django.contrib import admin
from .models import Klasa, Przedmiot, Uczen, Nauczyciel, Ocena

# Rejestracja modeli w panelu administracyjnym
admin.site.register(Klasa)
admin.site.register(Przedmiot)
admin.site.register(Uczen)
admin.site.register(Nauczyciel)
admin.site.register(Ocena)