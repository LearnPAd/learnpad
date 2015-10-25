#!/usr/bin/python
import httplib
import urllib
import random
import time
import os
import unicodedata
import sys
import csv
from datetime import datetime
import fileinput
import re
import HTMLParser


data=""



unicodedata.name(u'\uFB01')
unicodedata.name(u'\u0308')
unicodedata.name(u'\u2010')

if len(sys.argv) < 1:
    print "script.py <namefileinput1> <namefileinput2> <namefileoutput>"
    exit(1)
    
def readTXTFileLatin(input):
	spamReader = list(csv.reader(open(input,'U'), delimiter=';'))
	header = spamReader[0]
	del spamReader[0]
	dict={}

	for line in spamReader:
		i=0
		key = ""
		value = ""
		for element in line:
			if(i==0):
				key= element
			else:
				if(len(line)==i+1):
					value += element
				else:
					value += element+", "
			i+=1
		dict[key] = value
	return dict




dict1 = readTXTFileLatin(sys.argv[1])



print "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\r"
print "<AlternativeTermsSet>\n\r"

for element in dict1:
	if (not "#" in element):
		print "<Alternative word=\"%s\" >" %(element)
		print "<Suggestion>%s</Suggestion>" %dict1[element]
		print "</Alternative>"
print "</AlternativeTermsSet>\n\r"

exit(0)
