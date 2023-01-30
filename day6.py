def puzzle(file):
    with open(file, 'r') as f:
        content = f.read()
        found = False
        for i in range(len(content)):
            if found:
                break
            elif not found:
                count = 0
                mini = content[i:i+4]
                for j in mini:
                    if mini.count(j) > 1:
                        break
                    elif mini.count(j) == 1 and count != 3:
                        count+=1
                    elif mini.count(j) == 1 and count == 3:
                        print("Found at ", i , mini)
                        found = True

puzzle("day6.txt")

"""
Example :
Find the first substring of 4 Unique Letters
mjqjpqmgbljsphdztnvjfqwrcgsmlb
After the first three characters (mjq) have been received, there haven't been enough characters received yet to find the marker. The first time a marker could occur is after the fourth character is received, making the most recent four characters mjqj. Because j is repeated, this isn't a marker.

The first time a marker appears is after the seventh character arrives. Once it does, the last four characters received are jpqm, which are all different. In this case, your subroutine should report the value 7, because the first start-of-packet marker is complete after 7 characters have been processed.

Here are a few more examples:

bvwbjplbgvbhsrlpgdmjqwftvncz: first marker after character 5
nppdvjthqldpwncqszvftbrmjlhg: first marker after character 6
nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg: first marker after character 10
zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw: first marker after character 11
How many characters need to be processed before the first start-of-packet marker is detected?"""