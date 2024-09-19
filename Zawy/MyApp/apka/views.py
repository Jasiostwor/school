from django.shortcuts import render
from django.template import loader, Context
from django.http import HttpResponse
from .models import FrontPage
# Create your views here.
def index(request):
    return HttpResponse("Hell no kitty")

def widok1(request):
    dane = FrontPage.objects.all()
    print(dane)
    tpl = loader.get_template('templejt.html')
    context = {
        'zmienna': dane,
    }
    return HttpResponse(tpl.render(context,request))