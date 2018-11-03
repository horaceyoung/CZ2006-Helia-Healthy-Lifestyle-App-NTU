infile = 'exercisefacilities.txt'
outfile  = 'exercisefacilities_edited.txt'


delete_list = ['<head>','</head>', '<META http-equiv="Content-Type" content="text/html">',
               '<meta http-equiv="content-type" content="text/html; charset=UTF-8">',
               '<body style="margin:0px 0px 0px 0px;overflow:auto;background:#FFFFFF;">',
               '<table style="font-family:Arial,Verdana,Times;font-size:12px;text-align:left;width:100%;border-collapse:collapse;padding:3px 3px 3px 3px">',
               '<tr style="text-align:center;font-weight:bold;background:#9CBCE2">',
               '<td>','</td>','</tr>','<tr>','<tr bgcolor="#D4E4F3">','<td>&lt;Null&gt;</td>','HYPERLINK','&lt;Null&gt;',
               '<altitudeMode>','</altitudeMode>','<html xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:msxsl="urn:schemas-microsoft-com:xslt">',
               '<table style="font-family:Arial,Verdana,Times;font-size:12px;text-align:left;width:100%;border-spacing:0px; padding:3px 3px 3px 3px">',
               'PHOTOURL','</table>', '</body>', '</html>']
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

infile = 'exercisefacilities_edited.txt'
outfile  = 'new_exercisefacilities_edited.txt'
fin = open(infile)
fout = open(outfile, "w+")
for line in fin.readlines():
    if not line.strip(): continue
    fout.write(line)
fin.close()
fout.close()

infile = 'exercisefacilities_edited.txt'
outfile  = 'new_exercisefacilities_edited.txt'
fin = open(infile)
fout = open(outfile, "w+")
for line in fin.readlines():
    line = line.strip('\n')
    fout.write(line)
fin.close()
fout.close()


