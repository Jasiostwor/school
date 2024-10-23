# Generated by Django 5.1.1 on 2024-10-22 20:42

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Group',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=100)),
            ],
        ),
        migrations.CreateModel(
            name='Person',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nazwisko', models.CharField(max_length=100)),
                ('imie', models.CharField(max_length=100)),
                ('stanowisko', models.CharField(max_length=100)),
                ('grupa', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='apppka.group')),
            ],
        ),
    ]