# Problem J1: Deliv-e-droid
def deliverScore():
    p = int(input())
    c = int(input())
    f = p*50 -c*10
    if p>c:
        f += 500
    return f


# Problem J2: Chili Peppers
def chiliPeppers():
    shu = {
        "Poblano": 1500,
        "Mirasol": 6000,
        "Serrano": 15500,
        "Cayenne": 40000,
        "Thai": 75000,
        "Habanero": 125000
    }
    n = int(input())
    total = 0
    for i in range(n):
        pepper = input()
        total += shu[pepper]
    return total


# Problem J3: Special Event
# Sample Input:
# n = 5
# availability = ['YY..Y','.YY.Y','.Y.Y.','.YY.Y', 'Y...Y']
                
def eventDays(n,availability):
    res = []
    curMax = 0
    for j in range(5):
        curSum = 0
        for i in range(n):
            if availability[i][j] == 'Y':
                curSum +=1
        if curSum >curMax:
            res = [str(j+1)]
            curMax = curSum
        elif curSum ==curMax:
            res.append(str(j+1))
    return ','.join(res)  



# Problem J4/S1: Trianglane
# c = 7
# colors = [[0,0,1,1,0,1,0],[0,0,1,0,1,0,0]]
def wetWarn(c, colors):
    res = 0
    
    def dfs(i,j):
        if i<0 or i>1 or j<0 or j>=c or colors[i][j] == 0:
            return 0
        colors[i][j] = 0
        return 1+ dfs(i+1,j) + dfs(i,j+1)
    
    for i in range(2):
        for j in range(c):
            if colors[i][j] == 1:
                res += 2+dfs(i,j)
                print(res)
                print(colors)
    return res


# Problem J5: CCC Word Hunt
letter = "MENU"
n = 5
m = 7
grid = ["FTRUBLK","PMNAXCU","AERCNEO","MNEUARM","MUNEMNS"]

def wordHunt(grid,letter,n,m):
    res = 0
    def dfs(i,j,curInd,dr,dc):
        if 0>i or i>=n or 0>j or j>=m or grid[i][j] != letter[curInd]:
            return False
        if grid[i][j] == letter[curInd] and curInd == len(letter)-1:
            # print(i,j)
            return True
        return dfs(i+dr,j+dc,curInd+1,dr,dc)
  
    dir = [(0,1),(1,0),(0,-1),(-1,0),(1,1),(1,-1),(-1,1),(-1,-1)]

    for i in range(n):
        for j in range(m):
            if grid[i][j] == letter[0]:
                for dr,dc in dir:
                    if dfs(i,j,0,dr,dc):
                        res +=1
    return res

print(wordHunt(grid,letter,n,m))

