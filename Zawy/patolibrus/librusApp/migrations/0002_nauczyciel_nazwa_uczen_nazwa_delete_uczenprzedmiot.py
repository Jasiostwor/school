# Generated by Django 5.1.1 on 2024-09-16 08:53

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('librusApp', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='nauczyciel',
            name='nazwa',
            field=models.CharField(default='tak', max_length=100),
        ),
        migrations.AddField(
            model_name='uczen',
            name='nazwa',
            field=models.CharField(default='tak', max_length=100),
        ),
        migrations.DeleteModel(
            name='UczenPrzedmiot',
        ),
    ]
