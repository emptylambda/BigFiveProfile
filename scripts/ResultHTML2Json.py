#!/usr/bin/python

"""Quick script to JSONfy result.html
A tiny quick and dirty script to grab result scores and produce JSON format.
"""

from bs4 import BeautifulSoup
import json

def isTitle(s):
    return s.startswith("Domain/Facet")

def isCaption(s):
    return s.isupper()

def getScore(s):
    heading, score = s.split('.')[0], s.split('.')[-1]
    return (heading, int(score))

# def facet_extract(jsonFile, codeBlock):

with open('result.html', 'r') as inputFile:
    content = inputFile.read()
    soup = BeautifulSoup(content, 'html.parser')
    jsonContent = dict()
    jsonContent['NAME'] = 'Jeff'
    jsonContent['EMAIL'] = 'jeff@gmail.com'
    for codeBlock in soup.find_all('code'):
        children = codeBlock.findChildren()
        for p in children:
            if isTitle(p.text):
                continue #Skip titles
            if isCaption(p.text):
                caption, overallScore = getScore(p.text)
                jsonContent[caption] = dict()
                jsonContent[caption]['Overall Score'] = overallScore
                jsonContent[caption]['Facets'] = dict()
            else:
                facetCaption, score = getScore(p.text)
                jsonContent[caption]['Facets'][facetCaption]=score
    with open("result.json", "w") as outfile:
        outfile.write(json.dumps(jsonContent, indent = 4))
        outfile.close()
