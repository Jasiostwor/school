from django.contrib import admin
from .models import Person, Group

@admin.register(Person)
class PersonAdmin(admin.ModelAdmin):
    list_display = ('nazwisko', 'imie')
    search_fields = ('nazwisko', 'imie', 'stanowisko')
    list_filter = ('imie', 'grupa')

admin.site.register(Group)
