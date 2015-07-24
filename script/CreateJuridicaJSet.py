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
	dict={}
	f = open(input,'r')
	lines = f.readlines()
	lineZero = ""
	i=0
	for line in lines:
		if i==0:
			lineZero = line.replace("\n","").replace("\r","")
		if len(line)>3 and i>0:
			words = line.split("\t")
			jj = words[0].lower()
			temp1 = words[1].replace("\n","").replace("\r","")
			temp = words[2].replace("\n","").replace("\r","")
			if(len(temp)>1):
				description = temp
			else:
				description = temp1
			dict[jj] = [description,lineZero]
		i+=1
	return dict

dict3 = readTXTFileLatin(sys.argv[3])    
#print dict3
#exit(0)	
def readTXTFile(input):
	dict={}
	f = open(input,'r')
	lines = f.readlines()
	lineZero = ""
	i=0
	for line in lines:
		if i==0:
			lineZero = line.replace("\n","").replace("\r","")
		if len(line)>3:
			words = line.split(":")
			jj = words[0].replace(":","").lower()
			description = words[1].replace("\n","").replace("\r","")
			dict[jj] = [description,lineZero]
		i+=1
	return dict


dict1 = readTXTFile(sys.argv[1])
dict2 =  readTXTFile(sys.argv[2])
#print len(dict1)
for element in dict2:
	if (not element in dict1.keys()):
		dict1[element] = dict2[element]


print "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\r"
print "<juridaljargonSet>\n\r"

for element in dict1:
	print "<juridicaljargon jj=\"%s\" ref=\"%s\">" %(element,dict1[element][1])
	print "<description>%s</description>\n\r</juridicaljargon>" %dict1[element][0]
	
for element in dict3:
	print "<juridicaljargon jj=\"%s\" ref=\"%s\">" %(element,dict3[element][1])
	print "<description>%s</description>\n\r</juridicaljargon>" %dict3[element][0]

print "</juridaljargonSet>\n\r"

exit(0)
