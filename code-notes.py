import networkx as nx
G = nx.DiGraph()  # Directed graph
G = nx.Graph()

G.add_node('node')
G.add_edge('node1', 'node2')
list(G.nodes())
list(G.edges())


nx.draw_circular(G, 
                 node_color=COLORS[0],
                 node_size=2000, 
                 with_labels=True)


#  Position dict as second parameter
nx.draw(G, positions,
        node_color=COLORS[1],
        node_shape='s',
        node_size=2500,
        with_labels=True)



nx.draw_networkx_edge_labels(G, positions,
                             edge_labels=drive_times)


positions = dict(Albany=(-74, 43),
                Boston=(-71, 42),
                NYC=(-74, 41),
                Philly=(-75, 40))

drive_times = { ('Albany', 'Boston'): 3,
                ('Albany', 'NYC'): 4,
                ('Boston', 'NYC'): 4,
                ('NYC', 'Philly'): 2}

G.add_nodes_from(positions)
G.add_edges_from(drive_times)



def reachable_nodes(G, start):
    seen = set()
    stack = [start]
    while stack:
        node = stack.pop()
        if node not in seen:
            seen.add(node)
            stack.extend(G.neighbors(node))
    return seen


