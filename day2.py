rps = open("day2.txt","r")
score = 0
for line in rps:
    opp , me = line[0],line[2]
    score += ord(me) - 87
    if me == 'X':
        if opp == 'A':
            score+=3
        elif opp =='B':
            score+=0
        elif opp == 'C':
            score+=6
    elif me == 'Y':
        if opp == 'B':
            score+=3
        elif opp == 'C':
            score+=0
        elif opp == 'A':
            score +=6
    elif me == 'Z':
        if opp =='C':
            score+=3
        elif opp == 'A':
            score+=0
        elif opp == 'B':
            score+=6



print(score)