infile = 'new_health.txt'
outfile  = 'new_health_noline.txt'
fin = open(infile)
fout = open(outfile, "w+")
for line in fin.readlines():
    if not line.strip(): continue
    fout.write(line)
