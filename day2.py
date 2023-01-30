def day2(file):
    total = 0
    with open (file, 'r') as f:
        for line in f:
            e = line[0]
            m = line[2]
            if e == 'A':
                if m == 'X':
                    total+=4
                if m == 'Y':
                    total+=8
                if m == 'Z':
                    total+=3
            if e == 'B':
                if m == 'X':
                    total+=1
                if m == 'Y':
                    total+=5
                if m == 'Z':
                    total+=9
            if e == 'C':
                if m == 'X':
                    total+=7
                if m == 'Y':
                    total+=2
                if m == 'Z':
                    total+=6
    return total

day2("day2.txt")

"""
Example:
A Y
B X
C Z
This strategy guide predicts and recommends the following:

In the first round, your opponent will choose Rock (A), and you should choose Paper (Y). This ends in a win for you with a score of 8 (2 because you chose Paper + 6 because you won).
In the second round, your opponent will choose Paper (B), and you should choose Rock (X). This ends in a loss for you with a score of 1 (1 + 0).
The third round is a draw with both players choosing Scissors, giving you a score of 3 + 3 = 6.
In this example, if you were to follow the strategy guide, you would get a total score of 15 (8 + 1 + 6).

What would your total score be if everything goes exactly according to your strategy guide?

"""