N  = 0
M = 0
g = 0
possible = 0

def isSafe(v,colour, c):
    for i in range(N):
        if g[v][i] == 1 and colour[i] == c:
            return False
    return True
    
def solve(m, colour, v):
    if v == N:
        return True

    for c in range(1, m + 1):
        if isSafe(v, colour, c) == True:
            colour[v] = c
            if solve(m, colour, v + 1) == True:
                return True
            colour[v] = 0

if __name__ == "__main__":
    N = int(input('Enter Number Node: '))
    M = int(input('Enter number Edges: '))

    g = [[0 for i in range(N)] for j in range(N)]

    print('Enter All Edges')
    for _ in range(M):
        line = input().split(' ')
        a = int(line[0]) - 1
        b = int(line[1]) - 1
        g[a][b] = 1
        g[b][a] = 1

    possible = int(input("Enter the Number of colours: "))

    colour = [0 for i in range(N)]
    temp = ['','Red','Blue','Green','Yellow'] 
    if(solve(possible,colour,0) == None):
        print('Solution Does not Exitst')
    else:
        nd=1
        print ("Solution ")
        for c in colour:
            print (str(nd) +" -> " + temp[c])
            nd=nd+1