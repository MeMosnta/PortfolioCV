inps = open("day3.txt","r")
score = 0
for line in inps:
    l = line.strip()
    f = l[0:int(len(l)/2)]
    s = l[int(len(l)/2):len(l)]
    c = ''
    for i in f:
        for j in s:
            if i == j:
                c = i
    if(c.islower()):
        score+=ord(c)-96
    else:
        score+=ord(c)-38
print(score)