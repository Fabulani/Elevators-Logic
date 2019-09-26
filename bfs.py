import networkx as nx
import queue

# G = nx.DiGraph()  # Directed graph
G = nx.Graph()

initial_node = (17, 26, 20, 19, 31)
goal = list(range(21, 26))  # 21 - 25
directions = ['up', 'down']

G.add_node(initial_node)

def transition(current_node, e1, e2, direction):
    n1 = 0
    n2 = 0
    nn = list()
    if direction == 'up':
        if current_node[e1] <= 41 and current_node[e2] <= 41:
            n1 = current_node[e1] + 8
            n2 = current_node[e2] + 8
        else:
            return current_node
    elif direction == 'down':
        if current_node[e1] >= 13 and current_node[e2] >= 13:
            n1 = current_node[e1] - 13
            n2 = current_node[e2] - 13
        else:
            return current_node

    for i in current_node:
        nn.append(i)
    nn[e1] = n1
    nn[e2] = n2
    return tuple(nn)  

def findNeighbors(v):
    neighbors = list()
    
    for direction in directions:
        for i in range(len(v)):
            for j in range(i+1, len(v)):
                new_v = transition(v, i, j, direction)
                if new_v != v:
                    neighbors.append(new_v)
    return neighbors

def updateG(G, current_node):
    neighbors = findNeighbors(current_node)
    for n in neighbors:
        G.add_node(n)
        G.add_edge(current_node, n)
    return

def isGoal(current_node, goal):
    for i in current_node:
        if i not in goal:
            return False
    return True

# IMPLEMENTATION ATTEMPT
def bfs(G, initial_v, goal):
    print("Starting BFS")
    seen = set()
    seen.add(initial_v)
    q = queue.Queue(maxsize=0)
    q.put(initial_v)
    while q is not q.empty():
        #print("- Queue size: {}".format(q.qsize()))
        v = q.get()
        # print("-- Checking node {}".format(v))
        if isGoal(v, goal):
            #print("+++ Goal found: {}".format(v))
            return v
        updateG(G, v)
        for w in G.neighbors(v):
            if w not in seen:
                # print("--- New node found: {}".format(w))
                seen.add(w)
                #print("-- Nodes seen: {}".format(len(seen)) )
                # w.parent = v
                q.put(w)
    return False


'''
1  procedure BFS(G,start_v):
2      let Q be a queue
3      label start_v as discovered
4      Q.enqueue(start_v)
5      while Q is not empty
6          v = Q.dequeue()
7          if v is the goal:
8              return v
9          for all edges from v to w in G.adjacentEdges(v) do
10             if w is not labeled as discovered:
11                 label w as discovered
12                 w.parent = v
13                 Q.enqueue(w) 
'''

bfs(G, initial_node, goal)

nx.draw(G)