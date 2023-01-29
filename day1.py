totals = []
f = open("adventofcodepython/day1.txt","r")
lines = f.readlines()
total = 0
for line in lines:
    if(line != "\n"):
        total+=int(line.strip())
    else:
        totals.append(total)
        total = 0


print(max(totals))


    