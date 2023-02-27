with open("day4.txt",'r') as f:
    total = 0
    lines = [line.strip() for line in f.readlines()]
    for line in lines:
        first = line.split(",")[0]
        second = line.split(",")[1]
        ffirst = (int)(first.split("-")[0])
        sfirst = (int)(first.split("-")[1])
        fsecond = (int)(second.split("-")[0])
        ssecond = (int)(second.split("-")[1])

        if (ffirst<=fsecond and sfirst>=ssecond) or (fsecond<=ffirst and ssecond>=sfirst):
            #covers whole range
            total+=1
    print(total)