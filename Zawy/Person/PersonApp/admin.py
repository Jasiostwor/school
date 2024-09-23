from django.contrib import admin
from .models import Grupa, Person

# Register your models here.

class PersonAdmin(admin.ModelAdmin):
    # 1. Pola, po których będzie można wyszukiwać
    search_fields = ['nazwisko', 'imie', 'stanowisko']
    
    # 2. Pola do filtrowania
    list_filter = ['imie', 'grupa']
    
    # 3. Pola do wyświetlania na liście (i sortowania)
    list_display = ['imie', 'nazwisko', 'stanowisko', 'grupa']
    
    # Możliwość sortowania po każdym polu modelu
    ordering = ['imie', 'nazwisko', 'stanowisko', 'grupa']


admin.site.register(Grupa)
admin.site.register(Person, PersonAdmin)