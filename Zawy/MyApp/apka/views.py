from django.shortcuts import render
from django.template import loader, Context
from django.http import HttpResponse

# Create your views here.
def index(request):
    return HttpResponse("Hell no kitty")