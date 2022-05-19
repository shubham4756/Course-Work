import random

class Graph:
    
    def __init__(self, graph_dict=None, directed=True):
        self.graph_dict = graph_dict or {}
        self.directed = directed
        if not directed:
            self.make_undirected()
    
    def make_undirected(self):
        for a in list(self.graph_dict.keys()):
            for (b, dist) in self.graph_dict[a].items():
                self.graph_dict.setdefault(b, {})[a] = dist
    
    def connect(self, A, B, distance=1):
        self.graph_dict.setdefault(A, {})[B] = distance
        if not self.directed:
            self.graph_dict.setdefault(B, {})[A] = distance
    
    def get(self, a, b=None):
        links = self.graph_dict.setdefault(a, {})
        if b is None:
            return links
        else:
            return links.get(b)

    # def nodes(self):
    #     s1 = set([k for k in self.graph_dict.keys()])
    #     s2 = set([k2 for v in self.graph_dict.values() for k2, v2 in v.items()])
    #     nodes = s1.union(s2)
    #     return list(nodes)

class Node:
    
    def __init__(self, name:str, parent:str):
        self.name = name
        self.parent = parent 
        self.g = 0                   # distance
        self.h = 0                   # heuristics
        self.f = 0                   # sum of distance & heuristics

    def __eq__(self, other):
        return self.name == other.name

    def __lt__(self, other):
         return self.f < other.f

    def __repr__(self):
        return ('({0},{1})'.format(self.name, self.f))

def astar_search(graph, heuristics, start, end):
    
    open = []
    closed = []
    start_node = Node(start, None)
    goal_node = Node(end, None)
    open.append(start_node)
    
    while len(open) > 0:
        open.sort()                 # like min-heap 
        current_node = open.pop(0)  
        closed.append(current_node)
        
        if current_node == goal_node:
            path = []
            while current_node != start_node:
                path.append(current_node.name + ': ' + str(current_node.g))
                current_node = current_node.parent
            path.append(start_node.name + ': ' + str(start_node.g))
            return path[::-1]
            
        neighbors = graph.get(current_node.name)
        
        for key, _ in neighbors.items():
            neighbor = Node(key, current_node)

            if(neighbor in closed):
                continue

            neighbor.g = current_node.g + graph.get(current_node.name, neighbor.name)    # distance
            neighbor.h = heuristics.get(neighbor.name)                                   # heuristics
            neighbor.f = neighbor.g + neighbor.h                                         # sum of distance & heuristics

            if(add_to_open(open, neighbor) == True):
                open.append(neighbor)

    return None

def add_to_open(open, neighbor):
    for node in open:
        if (neighbor == node and neighbor.f > node.f):
            return False
    return True

def main():
    graph = Graph()
    N = int(input("Enter No. of Cities: "))
    
    for i in range(N):
        x = random.randint(5,N/10)
        for _ in range(x):
            y = random.randint(0,N-1)
            if(i == y):
                continue
                        # node 1, node 2, distance
            graph.connect(str(i),str(y),random.randint(10,1000))

    graph.make_undirected()
    
    heuristics = {}
    for i in range(N):
        x = random.randint(10,1000)
        heuristics[str(i)] = x

    City1 = int(input("Enter City 1: "))
    City2 = int(input("Enter City 2: "))
    heuristics[str(City2)] = 0
    path = astar_search(graph, heuristics, str(City1), str(City2))
    print(path)
    
if __name__ == "__main__": 
    main()