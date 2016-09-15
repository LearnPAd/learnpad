#!/bin/bash

export rname=$1
if [ "$rname" == "" ]; then
  echo no argument, specify the name or the repository to create
  exit 2
fi


../bin/sesameCLI --do create --serverUrl http://localhost:8080/openrdf-sesame --id $rname --configFile ../config/owlim-max-nopartial.ttl
