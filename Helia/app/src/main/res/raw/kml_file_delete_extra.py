infile = 'healthier_eateries_kml.txt'
outfile  = 'new_health.txt'


delete_list = ['<td>', '</td>', '</tr><tr bgcolor=/"">','</tr><tr bgcolor="">','<th>',
               '</th>','</tr><tr bgcolor="#E3E3F3">',"<center><table><tr><th colspan='2' align='center'><em>Attributes</em>",
               "</tr></table></center>",'PHOTOURL','HYPERLINK']
replace_list = ['ADDRESSBLOCKHOUSENUMBER','ADDRESSBUILDINGNAME',
               'ADDRESSSTREETNAME','ADDRESSTYPE','DESCRIPTION','LANDXADDRESSPOINT','LANDYADDRESSPOINT','NAME',
                'INC_CRC','FMEL_UPD_D','ADDRESSUNITNUMBER',
                "Choose healthier options from F&B outlets under HPB's Healthier Dining Programme"]
replace_postcode = ['ADDRESSPOSTALCODE']
replace_floor = ['ADDRESSFLOORNUMBER']

fin = open(infile)
fout = open(outfile, "w+")
for line in fin:
    for word in delete_list:
        line = line.replace(word, "")
    for replaceword in replace_list:
        line = line.replace(replaceword, " ")
    for postcode in replace_postcode:
        line = line.replace(postcode, "Singapore ")
    for floor_no in replace_floor:
        line = line.replace(floor_no, "FloorNo:  ")
    fout.write(line)
fin.close()
fout.close()

infile = 'new_health.txt'
outfile  = 'new_health_noline.txt'
fin = open(infile)
fout = open(outfile, "w+")
for line in fin.readlines():
    if not line.strip(): continue
    fout.write(line)
fin.close()
fout.close()

infile = 'new_health.txt'
outfile  = 'new_health_noline.txt'
fin = open(infile)
fout = open(outfile, "w+")
for line in fin.readlines():
    line = line.strip('\n')
    fout.write(line)
fin.close()
fout.close()


