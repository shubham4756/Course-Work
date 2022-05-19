from queue import Queue
N=7
class NQueens:
    def __init__(self, size):
        self.size = size
    def solve_bfs(self):
        if self.size < 1:
            return []
        solutions = []
        queue = Queue()
        queue.put([])
        while not queue.empty():
            solution = queue.get()
            if self.conflict(solution):
                continue
            row = len(solution)
            if row == self.size:
                solutions.append(solution)
                continue
            for col in range(self.size):
                queen = (row, col)
                queens = solution.copy()
                queens.append(queen)
                queue.put(queens)
        return solutions
    def conflict(self, queens):
        for i in range(1, len(queens)):
            for j in range(0, i):
                a, b = queens[i]
                c, d = queens[j]
                if a == c or b == d or abs(a - c) == abs(b - d):
                    return True
        return False
    def print(self, queens):
        for i in range(self.size):
            for j in range(self.size):
                p = 'Q' if (i, j) in queens else '-'
                print('%s ' % p, end='')
            print()

def main():
    n_queens = NQueens(N)
    bfs_solutions = n_queens.solve_bfs()
    if len(bfs_solutions)==0:
        print("Solution doesn't exists")
    for i, solution in enumerate(bfs_solutions):
        print('BFS Solution %d:' % (i + 1))
        n_queens.print(solution)

if __name__ == '__main__':
    main()
