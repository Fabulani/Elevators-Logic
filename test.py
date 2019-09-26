def test():

    v = [1, 2, 3, 4, 5]
    goal_range = range(21, 26)
    print(goal_range)

    for i in v:
        if i not in goal_range:
            return False
    return True

print(test())
