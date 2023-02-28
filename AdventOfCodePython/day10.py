'''Start by figuring out the signal being sent by the CPU. The CPU has a single register, X, which starts with the value 1. It supports only two instructions:

addx V takes two cycles to complete. After two cycles, the X register is increased by the value V. (V can be negative.)
noop takes one cycle to complete. It has no other effect.
The CPU uses these instructions in a program (your puzzle input) to, somehow, tell the screen what to draw.

Consider the following small program:

noop
addx 3
addx -5
Execution of this program proceeds as follows:

At the start of the first cycle, the noop instruction begins execution. During the first cycle, X is 1. After the first cycle, the noop instruction finishes execution, doing nothing.
At the start of the second cycle, the addx 3 instruction begins execution. During the second cycle, X is still 1.
During the third cycle, X is still 1. After the third cycle, the addx 3 instruction finishes execution, setting X to 4.
At the start of the fourth cycle, the addx -5 instruction begins execution. During the fourth cycle, X is still 4.
During the fifth cycle, X is still 4. After the fifth cycle, the addx -5 instruction finishes execution, setting X to -1.

Find the signal strength during the 20th, 60th, 100th, 140th, 180th, and 220th cycles. What is the sum of these six signal strengths?
'''
def xvalue(lines,i):
    add = []
    cycle = 1
    total = 1
    for line in lines:
        print(line)
        if cycle >= i -1:
            break
        if line == "noop":
            cycle+=1
        else:
            add.append(int(line[5:]))
            print(int(line[5:]))
            total+=int(line[5:])
            cycle+=2
    return total*i


with open("day10.txt",'r') as f:
    total = 0
    lines = [line.strip() for line in f.readlines()]
    total+=xvalue(lines,20)
    total+=xvalue(lines,60)
    total+=xvalue(lines,100)
    total+=xvalue(lines,140)
    total+=xvalue(lines,180)
    total+=xvalue(lines,220)
    print(total)
