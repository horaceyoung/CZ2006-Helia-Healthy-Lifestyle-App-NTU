infile = 'healthier_eateries_kml.txt'
outfile  = 'new_health.txt'


delete_list = ['<td>', '</td>', '</tr><tr bgcolor=/"">','</tr><tr bgcolor="">','<th>',
               '</th>','</tr><tr bgcolor="#E3E3F3">',"<center><table><tr><th colspan='2' align='center'><em>Attributes</em>",
               "</tr></table></center>"]
fin = open(infile)
fout = open(outfile, "w+")
for line in fin:
    for word in delete_list:
        line = line.replace(word, "")
    fout.write(line)
fin.close()
fout.close()
