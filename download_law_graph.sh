#!/bin/bash

basename=$1
for ext in .properties .graph .md5sums; do
	wget -c $basename$ext
done
