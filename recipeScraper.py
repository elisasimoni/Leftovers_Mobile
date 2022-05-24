# -*- coding: utf-8 -*-
"""
Created on Thu May 19 17:57:33 2022

@author: Elisa
"""

from recipe_scrapers import scrape_me



id ='   "id": '
name ='   "name":'
total_time ='   "total_time": '
yields ='   "yields": '
ingredients = '   "ingredients": '
instructions='   "instructions": '
image ='   "image": '
host ='   "host": '
links = '   "links": '
nutrients='   "nutrients": '

comma = ","



contatore = 100008
print("[")
while contatore <= 200000:
     
    scraper = scrape_me('https://www.allrecipes.com/recipe/'+str(contatore)+'/')
    
    if(scraper.title()!=("Johnsonville® Three Cheese Italian Style Chicken Sausage Skillet Pizza")):
        print("  {")
        print(id+'"'+str(contatore)+'"'+comma)
        print(name+'"'+scraper.title()+'"'+comma)
        print(total_time+'"'+str(scraper.total_time())+' min"'+comma)
        print(yields+'"'+scraper.yields()+'"'+comma)
        print(ingredients+"[")
        for l in scraper.ingredients():
            print('       "'+l+'"'+comma)
        print("    ],")
        print(instructions+'"'+scraper.instructions()+'"'+comma)
        print(image+'"'+scraper.image()+'"'+comma)
        #print(host+scraper.host()+comma)
        #print(links+scraper.links()+comma)
        
        #print(nutrients+scraper.nutrients()+comma)
        print("  },")
    
    contatore = contatore + 1


print("]")
